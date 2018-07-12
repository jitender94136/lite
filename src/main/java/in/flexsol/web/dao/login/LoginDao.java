package in.flexsol.web.dao.login;

import java.util.List;

import in.flexsol.modal.user.User;

public interface LoginDao {

	public int addEditUser(User user); 
	public User getUser(User user);
	public List<User> getUsersList();
	
	
}
