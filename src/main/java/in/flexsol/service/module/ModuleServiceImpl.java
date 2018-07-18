package in.flexsol.service.module;

import in.flexsol.dao.module.ModuleDao;
import in.flexsol.modal.menu.Menu;
import in.flexsol.modal.module.Module;
import in.flexsol.modal.user.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ModuleServiceImpl implements ModuleService {

	@Autowired
	ModuleDao moduleDao;
	
	
	@Override
	@Transactional
	public List<Module> getModulesList(User user) {
		return moduleDao.getModulesList(user);
	}


	@Override
	@Transactional
	public Module getModuleById(User user,int moduleId) {
		return moduleDao.getModuleById(user,moduleId);
	}


	@Override
	@Transactional
	public List<Menu> getModuleMenus(User user, int moduleId) {
		return moduleDao.getModuleMenus(user, moduleId);
	}


	@Override
	@Transactional
	public List<Module> finAllModules() {
		return moduleDao.finAllModules();
	}

}
