package in.flexsol.service;

import java.util.List;
import in.flexsol.modal.Record;

public interface RecordService {

	
				public List<Record> getRecords();

				public Record getLatestRecord();

				public List<Record> getCurrentDayRecords();

				public Record getSecondSensorRecords();

				public List<Record> getCurrentDaySecondSensorRecords();

				public List<Record> getCentralParkFeed();
	
}
