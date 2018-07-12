package in.flexsol.dao.api;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import in.flexsol.modal.gurgaon.GurgaonAirFeed;


public class APIDaoImpl implements APIDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
		
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	
	
	@Override
	public int saveAPIDataGurgaon(GurgaonAirFeed response) {
		
		String sql = "insert into gurgaon_records (lat,long,pm25,pm10,) values ()";
		
		return 0;
	}

	
//	@Override
//	public int saveAPIDataDelhi(GurgaonAirFeed response) {
//		
//		String sql = "insert into delhi_records () values ()";
//		
//		return 0;
//	}
//	

			
	
	
}
