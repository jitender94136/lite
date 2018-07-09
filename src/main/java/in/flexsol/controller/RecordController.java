package com.journaldev.spring.controller;


import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.journaldev.spring.delegate.RecordService;
import com.journaldev.spring.modal.Record;

/**
 * Handles requests for the application file upload requests
 */
@RestController
public class RecordController {
	
	private static final Logger logger = LoggerFactory.getLogger(RecordController.class);

	
	@Autowired
	RecordService recordService;
	
	
	
	
	@RequestMapping(value = "/records", method = RequestMethod.GET)
	public List<Record> getRecords(HttpServletRequest request) {
		  return recordService.getRecords();
	}

	
	@RequestMapping(value = "/record", method = RequestMethod.GET)
	public Record getLatestRecord(HttpServletRequest request) {
		  return recordService.getLatestRecord();
	}
	
	@RequestMapping(value = "/getCurrentDayRecords", method = RequestMethod.GET)
	public List<Record> getCurrentDayData(HttpServletRequest request) {
		  System.out.println("System properties :- "+System.getProperties().toString());
		  return recordService.getCurrentDayRecords();
	}
	
	@RequestMapping(value = "/getCurrentDaySecondSensorRecords", method = RequestMethod.GET)
	public List<Record> getCurrentDaySecondSensorData(HttpServletRequest request) {
		  return recordService.getCurrentDaySecondSensorRecords();
	}
	
	@RequestMapping(value = "/getPM25TrendData", method = RequestMethod.GET)
	public List<Record> getCentralParkFeed(HttpServletRequest request) {
		  return recordService.getCentralParkFeed();
	}
	
	
	
	
}
