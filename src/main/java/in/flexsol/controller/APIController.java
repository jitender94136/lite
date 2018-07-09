package com.journaldev.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.journaldev.spring.delegate.api.APIService;
import com.journaldev.spring.modal.gurgaon.GurgaonAirFeed;



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
