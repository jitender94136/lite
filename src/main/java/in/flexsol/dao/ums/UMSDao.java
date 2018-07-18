package in.flexsol.dao.ums;

import in.flexsol.modal.menu.Menu;

import java.util.List;

public interface UMSDao {

	List<Menu> findMenusByRole(int roleId);

	List<Menu> findAllMenus();

}
