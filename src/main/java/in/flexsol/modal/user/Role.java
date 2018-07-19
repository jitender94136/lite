package in.flexsol.modal.user;

public class Role {
			int id;
			String role;
			int active;
			int createdBy;
			
			public int getCreatedBy() {
				return createdBy;
			}
			public void setCreatedBy(int createdBy) {
				this.createdBy = createdBy;
			}
			public int getId() {
				return id;
			}
			public void setId(int id) {
				this.id = id;
			}
			public String getRole() {
				return role;
			}
			public void setRole(String role) {
				this.role = role;
			}
			public int getActive() {
				return active;
			}
			public void setActive(int active) {
				this.active = active;
			}
}
