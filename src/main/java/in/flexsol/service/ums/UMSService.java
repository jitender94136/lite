package in.flexsol.service.ums;

import in.flexsol.modal.menu.Menu;
import in.flexsol.modal.user.Role;

import java.util.List;

public interface UMSService {

	List<Menu> findMenusByRole(int roleId);

	List<Menu> findAllMenus();

	Role getRoleById(int roleId);
	
	List<Role> findAllRoles();

	void insertUpdateRoleAcess(Role role, String roleMenuMapping);

	int insertUpdateModuleMapping(int userId, String moduleMapping);

}
