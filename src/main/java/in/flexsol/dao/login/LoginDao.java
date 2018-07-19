package in.flexsol.dao.login;

import java.util.List;

import in.flexsol.modal.module.Module;
import in.flexsol.modal.user.Role;
import in.flexsol.modal.user.User;
import in.flexsol.modal.user.UserVerification;

public interface LoginDao {

	public int addNewUser(User user); 
	public List<User> getUsersList();
	public UserVerification getUserVerficationData(User user);
	public int verifyUser(UserVerification userVerificationData);
	public User verifyUserLogin(User user);
	public User getUserData(int userId);
	public int updateUserData(User user);
	
	
	
}
