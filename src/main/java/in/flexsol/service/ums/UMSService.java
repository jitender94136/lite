package in.flexsol.service.ums;

import in.flexsol.modal.menu.Menu;

import java.util.List;

public interface UMSService {

	List<Menu> findMenusByRole(int roleId);

	List<Menu> findAllMenus();

}
