package in.flexsol.service.module;

import in.flexsol.modal.menu.Menu;
import in.flexsol.modal.module.Module;
import in.flexsol.modal.user.User;

import java.util.List;

public interface ModuleService {

	List<Module> getModulesList(User user);

	Module getModuleById(User user, int moduleId);

	List<Menu> getModuleMenus(User user, int moduleId);

	List<Module> finAllModules();

			
	
}
