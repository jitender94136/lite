package in.flexsol.modal.menu;

public class Menu {
			int id;
			String menuName;
			String menuHref;
			String menuIcon;
			int active;
			int moduleId;
			public String getMenuIcon() {
				return menuIcon;
			}
			public void setMenuIcon(String menuIcon) {
				this.menuIcon = menuIcon;
			}
	
			public int getModuleId() {
				return moduleId;
			}
			public void setModuleId(int moduleId) {
				this.moduleId = moduleId;
			}
			public int getId() {
				return id;
			}
			public void setId(int id) {
				this.id = id;
			}
			public String getMenuName() {
				return menuName;
			}
			public void setMenuName(String menuName) {
				this.menuName = menuName;
			}
			public String getMenuHref() {
				return menuHref;
			}
			public void setMenuHref(String menuHref) {
				this.menuHref = menuHref;
			}
			public int getActive() {
				return active;
			}
			public void setActive(int active) {
				this.active = active;
			}
}
