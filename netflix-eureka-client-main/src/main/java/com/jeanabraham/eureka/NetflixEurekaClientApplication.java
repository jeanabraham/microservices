package com.jeanabraham.eureka;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class NetflixEurekaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflixEurekaClientApplication.class, args);
	}
}


@RestController
class ServiceInstanceRestController {

	@Autowired
	private DiscoveryClient discoveryClient;

	@RequestMapping("/service-instances/{applicationName}")
	public List<ServiceInstance> serviceInstancesByApplicationName(
			@PathVariable String applicationName) {
		return this.discoveryClient.getInstances(applicationName);
	}

	@RequestMapping("/sentence")
	public @ResponseBody String getSentence() {
	   return 
	      getWord("subject") + " "
	      + getWord("verb") + " "
	      + getWord("object") + "."
	      ;
	}

	public String getWord(String service) {
		List<ServiceInstance> list = discoveryClient.getInstances(service);
		if (list != null && list.size() > 0 ) {
			URI uri = list.get(0).getUri();
			uri = uri.resolve("/word");
			if (uri !=null ) {
				return (new RestTemplate()).getForObject(uri,String.class);
			}
		}
		return null;
	}
}