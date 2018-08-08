package in.flexsol.dao.module;

import in.flexsol.modal.menu.Menu;
import in.flexsol.modal.module.Module;
import in.flexsol.modal.user.User;
import in.flexsol.modal.usermodulerolemapping.UserModuleRoleMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class ModuleDaoImpl implements ModuleDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Module> getModulesList(User user) {
		String sql = "call sp_get_user_module_list(?,?)";
		return jdbcTemplate.query(sql,new Object[]{user.getId(),user.getUserType()},new BeanPropertyRowMapper<Module>(Module.class));
	}
	
	@Override
	public Module getModuleById(User user,int moduleId) {
		String sql = "call sp_get_user_module_data(?,?,?)";
		return jdbcTemplate.queryForObject(sql, new Object[] {user.getUserType(),user.getId(),moduleId},new BeanPropertyRowMapper<Module>(Module.class));
	}

	@Override   //menus mapped to a particular user's role
	public List<Menu> getModuleMenus(User user, int moduleId) {
		String sql = "call sp_get_user_menus_list(?,?,?)";
		return jdbcTemplate.query(sql,new Object[]{user.getUserType(),user.getId(),moduleId},new BeanPropertyRowMapper<Menu>(Menu.class));
	}

	@Override
	public List<Module> finAllModules() {
		String sql = "select * from module_master";
		return jdbcTemplate.query(sql,new Object[]{},new BeanPropertyRowMapper<Module>(Module.class));
	}

	@Override
	public List<UserModuleRoleMapping> userModuleRoleMappingList(User user) {
		String sql = "select * from user_module_mapping_master where user_id = ?";
		return jdbcTemplate.query(sql,new Object[]{user.getId()},new BeanPropertyRowMapper<UserModuleRoleMapping>(UserModuleRoleMapping.class));
	}

}
