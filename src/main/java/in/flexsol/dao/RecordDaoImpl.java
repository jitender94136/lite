package in.flexsol.dao;

import in.flexsol.modal.Record;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository
public class RecordDaoImpl implements RecordDao {

    private JdbcTemplate jdbcTemplate;
	
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	
	@Override
	public List<Record> getRecords() {
		String sql = "select * from record" ;
		List<Record> records = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Record>(Record.class));
		return records;
		//return null;
	}


	@Override
	public Record getLatestRecord() {
		String sql = "select * from record order by id desc limit 1";
		return jdbcTemplate.queryForObject(sql , new BeanPropertyRowMapper<Record>(Record.class));
		//return null;
	}



	
	@Override
	public List<Record> getCurrentDayRecords() {
		String sql = "select * from record order by id desc limit 10";
		List<Record> records = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Record>(Record.class));;
		return records;
	}
	
	
	@Override
	public List<Record> getWeeklyRecords() {
		String sql = "select * from record order by id desc limit 5";
		List<Record> records = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Record>(Record.class));;
		return records;
	}
	
	@Override
	public List<Record> getMonthlyRecords() {
		String sql = "select * from record order by id desc limit 5";
		List<Record> records = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Record>(Record.class));;
		return records;
	}

	@Override
	public List<Record> getYearlyRecords() {
		String sql = "select * from record order by id desc limit 5";
		List<Record> records = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Record>(Record.class));;
		return records;
	}


	@Override
	public Record getSecondSensorRecords() {
		String sql = "select * from record_2 order by id desc limit 1";
		return jdbcTemplate.queryForObject(sql , new BeanPropertyRowMapper<Record>(Record.class));
	}


	@Override
	public List<Record> getCurrentDaySecondSensorRecords() {
		String sql = "select * from record_2  order by id desc limit 10";
		List<Record> records = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Record>(Record.class));;
		return records;
	}


	@Override
	public List<Record> getCentralParkFeed() {
		Connection con = null;
		List<Record> records = new ArrayList<Record>();
		try {
			//con = jdbcTemplate.getDataSource().getConnection();
			//SimpleJdbcCall jdbcCall =  new SimpleJdbcCall(jdbcTemplate.getDataSource()).withProcedureName("fl_get_central_park_feed");
			//SqlParameterSource in = new MapSqlParameterSource().addValue("in_id", 0);
			//jdbcCall.execute(in);
//			 Map<String,Object> returnData = jdbcTemplate.call(new CallableStatementCreator() {
//				@Override
//				public CallableStatement createCallableStatement(Connection con)
//						throws SQLException {
//					String sql = "call fl_get_central_park_feed()";
//					CallableStatement cstmt = con.prepareCall(sql);
//					return cstmt;
//				}
//			}, new ArrayList<SqlParameter>());
			String sql = "call fl_get_central_park_feed()";
			List<Record> record = jdbcTemplate.query(sql, new Object[] {},  new RowMapper<Record>() {
						@Override
						public Record mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							Record record = new Record();
							record.setPm2(rs.getString("avgpm2"));
							record.setTimestamp(rs.getString("timestamp"));
							return record;
						}
			
			});
			
			System.out.println(record.size()); 
			
			
			
			
			
              
			//String sql = "call fl_get_central_park_feed()";
			//CallableStatement cstmt = con.prepareCall(sql);
			//ResultSet rs = cstmt.executeQuery();
//			while(rs.next()) {
//						Record record = new Record();
//						record.setPm2(rs.getString("avgpm2"));
//						record.setTimestamp(rs.getString("timestamp"));
//						records.add(record);
//			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return records;
	}
	
	
	
	
}
