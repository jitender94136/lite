package in.flexsol.service.login;

import in.flexsol.dao.login.LoginDao;
import in.flexsol.modal.user.User;
import in.flexsol.modal.user.UserVerification;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class LoginServiceImpl implements LoginService {
			
	@Autowired
	LoginDao loginDao;

	@Override
	@Transactional
	public int addNewUser(User user) {
		return loginDao.addNewUser(user);
	}

	@Override
	@Transactional
	public UserVerification getUserVerficationData(User user) {
		return loginDao.getUserVerficationData(user);
	}

	@Override
	@Transactional
	public int verifyUser(UserVerification userVerificationData) {
		return loginDao.verifyUser(userVerificationData);
	}

	@Override
	@Transactional
	public User verifyUserLogin(User user) {
		return loginDao.verifyUserLogin(user);
	}

	@Override
	@Transactional
	public List<User> getUsersList() {
		return loginDao.getUsersList();
	}

	@Override
	@Transactional
	public User getUserData(int userId) {
	
		return loginDao.getUserData(userId);
	}

	@Override
	@Transactional
	public int updateUserData(User user) {
		return loginDao.updateUserData(user);
	}

	@Override
	@Transactional
	public  Map<Integer, String> getUserMappedModules() {
		return loginDao.getUserMappedModules();
	}

	


	
	
	
}
