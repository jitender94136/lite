package in.flexsol.service.ums;

import in.flexsol.dao.ums.UMSDao;
import in.flexsol.modal.menu.Menu;
import in.flexsol.modal.user.Role;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UMSServiceImpl implements UMSService {

	@Autowired
	UMSDao umsDao;
	
	
	@Override
	@Transactional
	public List<Menu> findMenusByRole(int roleId) {
		return umsDao.findMenusByRole(roleId);
	}


	@Override
	@Transactional
	public List<Menu> findAllMenus() {
		return umsDao.findAllMenus();
	}


	@Override
	@Transactional
	public List<Role> findAllRoles() {
		return umsDao.findAllRoles();
	}

	
	@Override
	@Transactional
	public Role getRoleById(int roleId) {
		return umsDao.getRoleById(roleId);
	}


	@Override
	@Transactional
	public void insertUpdateRoleAcess(Role role, String moduleMenuMapping) {
		String moduleMenuMappingArr[] = moduleMenuMapping.split("-");
		List<String> menusList = new ArrayList<String>();
		for(int i = 0; i < moduleMenuMappingArr.length; i++) {
			      String modulesAndMenus = moduleMenuMappingArr[i];
				  String arr[] = modulesAndMenus.split("=");
				  if(arr.length == 2 && arr[1] != null) {
					  	String menuIds = arr[1];
					  	for(String menuId : menuIds.split(",")) {
					  				menusList.add(menuId);
					  	}
				  }
		}
		int roleId = umsDao.insertUpdateRole(role);
		umsDao.batchUpdateMenuAccess(role.getCreatedBy(),roleId,menusList);
	}


	@Override
	public int insertUpdateModuleMapping(int userId, String moduleMapping) {
		return 0;//umsDao.insertUpdateModuleMapping(userId, moduleMapping);
	}
	
}
