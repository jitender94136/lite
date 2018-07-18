package in.flexsol.service.ums;

import in.flexsol.dao.ums.UMSDao;
import in.flexsol.modal.menu.Menu;

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

}
