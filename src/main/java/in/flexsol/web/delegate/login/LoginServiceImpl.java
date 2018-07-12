package in.flexsol.web.delegate.login;

import in.flexsol.modal.user.User;
import in.flexsol.web.dao.login.LoginDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class LoginServiceImpl implements LoginService {
			
	@Autowired
	LoginDao loginDao;

	@Override
	@Transactional
	public int addEditUser(User user) {
		
		return loginDao.addEditUser(user);
	}
	
	
	
	
}
