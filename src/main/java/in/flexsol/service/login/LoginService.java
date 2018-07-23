package in.flexsol.service.login;

import java.util.List;
import java.util.Map;

import in.flexsol.modal.user.User;
import in.flexsol.modal.user.UserVerification;

public interface LoginService {

	

	int addNewUser(User user);
	UserVerification getUserVerficationData(User user);
    int verifyUser(UserVerification userVerificationData);
	User verifyUserLogin(User user);
	List<User> getUsersList();
	User getUserData(int userId);
	int updateUserData(User user);
	Map<Integer, String> getUserMappedModules();
	
}
