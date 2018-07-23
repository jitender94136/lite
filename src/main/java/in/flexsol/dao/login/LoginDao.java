package in.flexsol.dao.login;

import java.util.List;
import java.util.Map;
import in.flexsol.modal.user.User;
import in.flexsol.modal.user.UserVerification;

public interface LoginDao {

	int addNewUser(User user); 
	List<User> getUsersList();
	UserVerification getUserVerficationData(User user);
	int verifyUser(UserVerification userVerificationData);
	User verifyUserLogin(User user);
	User getUserData(int userId);
	int updateUserData(User user);
	Map<Integer, String> getUserMappedModules();
	
	
	
}
