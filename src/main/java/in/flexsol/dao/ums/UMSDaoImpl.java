package in.flexsol.dao.ums;

import in.flexsol.modal.menu.Menu;
import in.flexsol.modal.user.Role;
import in.flexsol.modal.user.User;
import in.flexsol.web.controller.login.LoginController;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

@Repository
public class UMSDaoImpl implements UMSDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Override  //menus mapped to a particular role.
	public List<Menu> findMenusByRole(int roleId) {
		String sql = "call sp_get_role_based_menus_list(?)";
		return jdbcTemplate.query(sql,new Object[] {roleId},new BeanPropertyRowMapper<Menu>(Menu.class));
	}

	@Override
	public List<Menu> findAllMenus() {
		String sql = "select * from menu_master";
		return jdbcTemplate.query(sql,new Object[] {},new BeanPropertyRowMapper<Menu>(Menu.class));
		
	}

	@Override
	public List<Role> findAllRoles() {
		String sql = "select * from role_master";
		return jdbcTemplate.query(sql,new Object[] {},new BeanPropertyRowMapper<Role>(Role.class));
	}

	@Override
	public Role getRoleById(int roleId) {
		String sql = "select * from role_master where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[] {roleId},new BeanPropertyRowMapper<Role>(Role.class));
	}

	@Override
	public int insertUpdateRole(Role role) {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
		.withoutProcedureColumnMetaDataAccess()
		.withSchemaName("filite")
		.withProcedureName("sp_insert_update_role")
		.declareParameters(	
			new SqlParameter("in_role_id",Types.VARCHAR),
			new SqlParameter( "in_role", Types.VARCHAR ),
			new SqlParameter( "in_user_id", Types.VARCHAR ),
			new SqlOutParameter("out_role_id",Types.TINYINT));
		Map<String, Object> result = new HashMap<>();
		result.put("in_role_id", role.getId());
		result.put("in_role", role.getRole());
		result.put("in_user_id", role.getCreatedBy());
	    result = jdbcCall.execute(result);
	    int returnRoleId = (int)result.get("out_role_id");
	    return returnRoleId;
	}

	@Override
	public void batchUpdateMenuAccess(int userId,int roleId, List<String> menusList) {
			String deleteQuery = "delete from role_menu_mapping_master where role_id = ?";
			int count = jdbcTemplate.update(deleteQuery, new Object[] {roleId});
			logger.debug("number of role menu mappings deleted : "+count);
		    String insertQuery = "insert into role_menu_mapping_master(role_id, menu_id,created_by) values (?,?,?)";
	        List<Object[]> inputList = new ArrayList<Object[]>();
	        for(String menuId : menusList){
	            Object[] tmp = {roleId, menuId, userId};
	            inputList.add(tmp);
	        }
	        jdbcTemplate.batchUpdate(insertQuery, inputList);
	}

	@Override
	public void insertUpdateModuleMapping(User user, List<Object[]> batchArgs) {
		String deleteQuery = "delete from user_module_mapping_master where user_id = ?";
		int count = jdbcTemplate.update(deleteQuery, new Object[] {user.getId()});
		logger.debug("number of user module mappings deleted : "+count);
		String insertQuery = "insert into user_module_mapping_master(user_id,module_id,role_id,created_by) values (?,?,?,?)";
		jdbcTemplate.batchUpdate(insertQuery, batchArgs);
	}
	
}
