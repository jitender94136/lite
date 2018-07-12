package in.flexsol.web.controller.login;

import in.flexsol.modal.user.User;
import in.flexsol.utility.Utility;
import in.flexsol.web.delegate.login.LoginService;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	LoginService loginService;
	
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String homePage() {
				return "/landingpage";	
	}
	
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public ModelAndView register(@ModelAttribute("user") User user,BindingResult result) {
		ModelAndView mv = new ModelAndView();
		int status = 1;
		if(result.hasErrors()) {
			logger.info(result.toString());
		}
		try {
				if(user.getEmailId() == null || user.getEmailId().trim().length() == 0) {
					status = -1;
				}
				if(user.getPassword() == null || user.getPassword().trim().length() == 0) {
					status = -1;
				}
				String hashPass = Utility.hashPassword(user.getPassword());
				user.setPassword(hashPass);
				status = loginService.addEditUser(user);
		} catch(Exception e) {
				e.printStackTrace();
				status = -2;
		}
		mv.setViewName("/home");
		return mv;
	}
	
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView login() {
			return null;
	}
	
	
	
	
	
}
