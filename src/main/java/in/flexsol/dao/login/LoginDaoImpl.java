package in.flexsol.dao.login;

import in.flexsol.modal.user.User;
import in.flexsol.modal.user.UserVerification;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDaoImpl implements LoginDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Override
	public int addNewUser(User user) {
		int returnStatus;
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
					new SqlParameter("in_created_by", Types.INTEGER),
					new SqlOutParameter("out_status",Types.TINYINT));
				Map<String, Object> result = new HashMap<>();
				result.put("in_id", user.getId());
				result.put("in_first_name", user.getFirstName());
				result.put("in_last_name", user.getLastName());
				result.put("in_email_id", user.getEmailId());
				result.put("in_password", user.getPassword());
				result.put("in_dob", user.getDob());
				result.put("in_active", user.getActive());
				result.put("in_created_by", user.getCreatedBy());
			    result = jdbcCall.execute(result);
			    returnStatus = (int)result.get("out_status");
		} catch(Exception e) {
			e.printStackTrace();
			returnStatus = -1;
		}
		return returnStatus;  // Will change this to return the Errorcode as an int...
	}	


	@Override
	public User getUserData(int userId) {
		String sql = "select * from `filite`.user_master where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[] {userId},new BeanPropertyRowMapper<User>(User.class));		
	}
	

	
	@Override
	public List<User> getUsersList() {
		String sql = "select * from `filite`.user_master";
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<User>(User.class));		
	}

	@Override
	public UserVerification getUserVerficationData(User user) {
		 String sql = "select * from `filite`.user_verification_master where id =  ?";
		 return jdbcTemplate.queryForObject(sql, new Object[] {user.getId()},new BeanPropertyRowMapper<UserVerification>(UserVerification.class));
	}

	@Override
	public int verifyUser(UserVerification userVerificationData) {
		 		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withoutProcedureColumnMetaDataAccess()
				.withSchemaName("filite")
				.withProcedureName("sp_verify_user_registeration")
				.declareParameters(	
					new SqlParameter("in_email_id",Types.VARCHAR),
					new SqlParameter( "in_hash", Types.VARCHAR ),
					new SqlOutParameter("out_status",Types.TINYINT));
				Map<String, Object> result = new HashMap<>();
				result.put("in_email_id", userVerificationData.getEmailId());
				result.put("in_hash", userVerificationData.getHash());
			    result = jdbcCall.execute(result);
			    int returnStatus = (int)result.get("out_status");
			    return returnStatus;
	}

	@Override
	public User verifyUserLogin(User user) {
		String sql = "call `filite`.`sp_verify_user_login`(?)";
		try {
			user = jdbcTemplate.queryForObject(sql, new Object[] {user.getEmailId()}, new BeanPropertyRowMapper<User>(User.class));
		} catch(Exception e) {
			user = null;
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public int updateUserData(User user) {
		String sql = "update user_master set first_name = ?, last_name = ?, dob = ?, user_type = ?, active = ? where id = ?";
		return jdbcTemplate.update(sql, new Object[] {user.getFirstName(),user.getLastName(),user.getDob(),user.getUserType(),user.getActive(),user.getId()});
	}


	
}
