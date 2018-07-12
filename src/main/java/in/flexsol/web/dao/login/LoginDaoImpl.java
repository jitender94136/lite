package in.flexsol.web.dao.login;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.flexsol.modal.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDaoImpl implements LoginDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//https://docs.spring.io/spring/docs/3.0.0.M4/reference/html/ch12s05.html
	//to get the resultset look at the end of the page...
	
	@Override
	public int addEditUser(User user) {
		int returnStatus = -1;
		try {
				SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withoutProcedureColumnMetaDataAccess()
				.withSchemaName("filite")
				.withProcedureName("sp_save_user_details")
				.declareParameters(	
					new SqlParameter("in_id",Types.INTEGER),
					new SqlParameter( "in_first_name", Types.VARCHAR ),
					new SqlParameter("in_last_name", Types.VARCHAR ),
					new SqlParameter( "in_email_id", Types.VARCHAR ),
					new SqlParameter("in_password", Types.VARCHAR ),
					new SqlParameter( "in_dob", Types.VARCHAR),
					new SqlParameter("in_active", Types.TINYINT),
					new SqlOutParameter("out_status",Types.TINYINT));
				Map<String, Object> result = new HashMap<>();
				result.put("in_id", user.getId());
				result.put("in_first_name", user.getFirstName());
				result.put("in_last_name", user.getLastName());
				result.put("in_email_id", user.getEmailId());
				result.put("in_password", user.getPassword());
				result.put("in_dob", user.getDob());
				result.put("in_active", user.getActive());
			    result = jdbcCall.execute(result);
			    returnStatus = (int)result.get("out_status");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return returnStatus;  // Will change this to return the Errorcode as an int...
	}	
		
//		 Map<String,Object> returnData = jdbcTemplate.call(new CallableStatementCreator() {
//			@Override
//			public CallableStatement createCallableStatement(Connection con)
//					throws SQLException {
//				String sql = "call sp_save_user_details(?,?,?,?,?,?,?)";
//				CallableStatement cstmt = con.prepareCall(sql);
//				return cstmt;
//			}
//		}, new ArrayList<SqlParameter>());

//		String sql = "call sp_save_user_details(?,?,?,?,?,?,?)";
//		return jdbcTemplate.update(sql, new Object[] {user.getId(),user.getFirstName(),user.getLastName(),user.getEmailId(),user.getPassword(),user.getDob(),user.getActive()});		
	


	@Override
	public User getUser(User user) {
		String sql = "select * from user_master where user_id=?";
		return jdbcTemplate.queryForObject(sql, new Object[] {user.getId()},new BeanPropertyRowMapper<User>(User.class));		
	}
	

	
	@Override
	public List<User> getUsersList() {
		String sql = "select * from user_master";
		return jdbcTemplate.queryForList(sql,User.class);		
	}
	
}
