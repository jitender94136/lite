package in.flexsol.dao;

import java.util.List;
import in.flexsol.modal.Record;

public interface RecordDao {
			
	
	public List<Record> getRecords();

	public Record getLatestRecord();

	public List<Record> getCurrentDayRecords();
	
	public List<Record> getWeeklyRecords();	
	
	public List<Record> getMonthlyRecords();
	
	public List<Record> getYearlyRecords();

	public Record getSecondSensorRecords();

	public List<Record> getCurrentDaySecondSensorRecords();

	public List<Record> getCentralParkFeed();
	
	
	
}