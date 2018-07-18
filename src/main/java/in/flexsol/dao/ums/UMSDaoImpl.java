package in.flexsol.dao.ums;

import in.flexsol.modal.menu.Menu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UMSDaoImpl implements UMSDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
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

}
