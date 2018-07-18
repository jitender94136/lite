package in.flexsol.modal.module;

public class Module {

			int id;
			String moduleName;
			String modulePic;
			int active;
			public int getId() {
				return id;
			}
			public String getModulePic() {
				return modulePic;
			}
			public void setModulePic(String modulePic) {
				this.modulePic = modulePic;
			}
			public void setId(int id) {
				this.id = id;
			}
			public String getModuleName() {
				return moduleName;
			}
			public void setModuleName(String module) {
				this.moduleName = module;
			}
			public int getActive() {
				return active;
			}
			public void setActive(int active) {
				this.active = active;
			}
}
