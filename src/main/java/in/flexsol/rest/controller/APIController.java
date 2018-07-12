package in.flexsol.rest.controller;

import in.flexsol.modal.gurgaon.GurgaonAirFeed;
import in.flexsol.service.api.APIService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



@RestController
public class APIController {

	@Autowired
	APIService apiService;
	
	//@RequestMapping(value = "/gurgaonAirFeed", method = RequestMethod.GET)
	@Scheduled(cron="")
	public void  getGurgaonAirPollutionData(HttpServletRequest request) {
				RestTemplate restTemplate = new RestTemplate();
				String apiURL = "http://api.airpollutionapi.com/1.0/aqi?lat=28.4291&lon=77.0367&APPID=tpqn3d3ep5g5eq86fvqt1rr73n";
				GurgaonAirFeed response = restTemplate.getForObject(apiURL,GurgaonAirFeed.class);
				int status = apiService.saveAPIDataGurgaon(response);
				System.out.println("The number of records entered:- "+status);
					
	}
	
	
	@Scheduled(cron="")
	public void  getDelhiAirPollutionData(HttpServletRequest request) {
				RestTemplate restTemplate = new RestTemplate();
				String apiURL = "http://api.airpollutionapi.com/1.0/aqi?lat=28.4291&lon=77.0367&APPID=tpqn3d3ep5g5eq86fvqt1rr73n";
				GurgaonAirFeed response = restTemplate.getForObject(apiURL, GurgaonAirFeed.class);
				
					
	}
	
}
