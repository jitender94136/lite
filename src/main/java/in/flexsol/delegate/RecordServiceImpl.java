package com.journaldev.spring.delegate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.journaldev.spring.dao.RecordDao;
import com.journaldev.spring.modal.Record;


@Service
public class RecordServiceImpl implements RecordService {

	@Autowired
	RecordDao recordDao;
	
	@Override
	public List<Record> getRecords() {
		
		return recordDao.getRecords();
	}

	@Override
	public Record getLatestRecord() {
		return recordDao.getLatestRecord();
	}

	@Override
	public List<Record> getCurrentDayRecords() {
		
		return recordDao.getCurrentDayRecords();
	}

	@Override
	public Record getSecondSensorRecords() {
		
		return recordDao.getSecondSensorRecords();
	}

	@Override
	public List<Record> getCurrentDaySecondSensorRecords() {
		// TODO Auto-generated method stub
		return recordDao.getCurrentDaySecondSensorRecords();
	}

	@Override
	public List<Record> getCentralParkFeed() {
		// TODO Auto-generated method stub
		return recordDao.getCentralParkFeed() ;
	}

}
