package in.flexsol.dao.module;

import in.flexsol.modal.menu.Menu;
import in.flexsol.modal.module.Module;
import in.flexsol.modal.user.User;
import in.flexsol.modal.usermodulerolemapping.UserModuleRoleMapping;

import java.util.List;

public interface ModuleDao {

	List<Module> getModulesList(User user);

	Module getModuleById(User user, int moduleId);

	List<Menu> getModuleMenus(User user, int moduleId);

	List<Module> finAllModules();

	List<UserModuleRoleMapping> userModuleRoleMappingList(User user);

}
