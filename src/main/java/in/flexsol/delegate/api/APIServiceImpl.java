package com.journaldev.spring.delegate.api;

import org.springframework.stereotype.Service;

import com.journaldev.spring.dao.api.APIDao;
import com.journaldev.spring.modal.gurgaon.GurgaonAirFeed;


@Service
public class APIServiceImpl implements APIService {

	APIDao apiDao;
	
	@Override
	public int saveAPIDataGurgaon(GurgaonAirFeed response) {
		
		return apiDao.saveAPIDataGurgaon(response);
	}



	

}
