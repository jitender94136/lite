package com.journaldev.spring.delegate;

import java.util.List;

import com.journaldev.spring.modal.Record;

public interface RecordService {

	
				public List<Record> getRecords();

				public Record getLatestRecord();

				public List<Record> getCurrentDayRecords();

				public Record getSecondSensorRecords();

				public List<Record> getCurrentDaySecondSensorRecords();

				public List<Record> getCentralParkFeed();
	
}
