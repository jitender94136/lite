package in.flexsol.service.module;

import in.flexsol.dao.module.ModuleDao;
import in.flexsol.modal.menu.Menu;
import in.flexsol.modal.module.Module;
import in.flexsol.modal.user.User;
import in.flexsol.modal.usermodulerolemapping.UserModuleRoleMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


	@Override
	@Transactional
	public List<UserModuleRoleMapping> userModuleRoleMappingList(User user) {
		return moduleDao.userModuleRoleMappingList(user);
	}


	@Override
	public List<Integer> getUserMappedModulesIdsList(List<UserModuleRoleMapping> userModuleRoleMappingList) {
		List<Integer> userMappedModuleIdsList = new ArrayList<Integer>();
		for(UserModuleRoleMapping  userModuleRoleMapping :  userModuleRoleMappingList) {
						userMappedModuleIdsList.add(userModuleRoleMapping.getModuleId());
		}	
		return userMappedModuleIdsList;
	}


	@Override
	public Map<Integer, List<Integer>> getModuleMappedRoleIdsMap(List<UserModuleRoleMapping> userModuleRoleMappingList) {
		Map<Integer, List<Integer>> moduleMappedRoleIdsMap = new HashMap<>();
		for(UserModuleRoleMapping  userModuleRoleMapping :  userModuleRoleMappingList) {
			List<Integer> roleIdsList = moduleMappedRoleIdsMap.get(userModuleRoleMapping.getModuleId());
			if(roleIdsList == null) {
				roleIdsList = new ArrayList<Integer>();
			}
			roleIdsList.add(userModuleRoleMapping.getRoleId());
			moduleMappedRoleIdsMap.put(userModuleRoleMapping.getModuleId(), roleIdsList);
		}
		return moduleMappedRoleIdsMap;
	}

}
