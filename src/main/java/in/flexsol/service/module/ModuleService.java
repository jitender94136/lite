package in.flexsol.service.module;

import in.flexsol.modal.menu.Menu;
import in.flexsol.modal.module.Module;
import in.flexsol.modal.user.User;
import in.flexsol.modal.usermodulerolemapping.UserModuleRoleMapping;

import java.util.List;
import java.util.Map;

public interface ModuleService {

	List<Module> getModulesList(User user);

	Module getModuleById(User user, int moduleId);

	List<Menu> getModuleMenus(User user, int moduleId);

	List<Module> finAllModules();

	List<UserModuleRoleMapping> userModuleRoleMappingList(User user);

	List<Integer> getUserMappedModulesIdsList(List<UserModuleRoleMapping> userModuleRoleMappingList);

	Map<Integer, List<Integer>> getModuleMappedRoleIdsMap(List<UserModuleRoleMapping> userModuleRoleMappingList);

			
	
}
