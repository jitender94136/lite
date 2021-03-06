package in.flexsol.dao.ums;

import in.flexsol.modal.menu.Menu;
import in.flexsol.modal.user.Role;
import in.flexsol.modal.user.User;

import java.util.List;

public interface UMSDao {

	List<Menu> findMenusByRole(int roleId);

	List<Menu> findAllMenus();

	public Role getRoleById(int roleId);
	
	List<Role> findAllRoles();

	int insertUpdateRole(Role role);

	void batchUpdateMenuAccess(int userId,int roleId, List<String> menusList);

	void insertUpdateModuleMapping(User user, List<Object[]> batchArgs);

}
