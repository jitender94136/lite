package in.flexsol.web.controller.login;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import in.flexsol.modal.module.Module;
import in.flexsol.modal.user.User;
import in.flexsol.modal.user.UserVerification;
import in.flexsol.service.login.LoginService;
import in.flexsol.service.mail.EmailService;
import in.flexsol.utility.Constants;
import in.flexsol.utility.Utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	
	@Autowired
	EmailService emailService;
	
	@Value("${CONTEXT_URL}")
	String contextUrl;
	
	@Value("${CONTEXT_PATH}")
	String contextPath;
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String homePage() {
				return "/landingpage";	
	}
	
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public ModelAndView register(@ModelAttribute("user") User user,BindingResult result) {
		ModelAndView mv = new ModelAndView();
		int status = -1;
		if(result.hasErrors()) {
			logger.info(result.toString());
		}
		try {
				if(user.getEmailId() == null || user.getEmailId().trim().length() == 0) {
					status = -1;
				} else if(user.getPassword() == null || user.getPassword().trim().length() == 0) {
					status = -1;
				} else {
					String hashPass = Utility.hashPassword(user.getPassword());
					user.setPassword(hashPass);
					status = loginService.addNewUser(user);
					if(status > 0) {
						user.setId(status);
						sendVerificationMail(user);
					}
				}
		} catch(Exception e) {
				e.printStackTrace();
				status = -1;
		}
		logger.debug("the value of status in register method is "+status);
		mv.addObject("status", status);
		mv.setViewName("home");
		return mv;
	}
	
	
	private void sendVerificationMail(User user) {
			UserVerification userVerificationData = loginService.getUserVerficationData(user);
			String mailMessagae = draftEmail(userVerificationData,user);
			emailService.sendHTML("jitendersingh@flexsol.in", user.getEmailId(), "Regarding User Verification", mailMessagae);
	}


	private String draftEmail(UserVerification userVerificationData,User user) {
		StringBuilder mailBody = new StringBuilder();
		mailBody.append("Hi "+user.getFirstName()+" <br/>");
		mailBody.append("Please click on this link to validate your account...<br/>");
		String link = contextUrl+"verifyUser?emailId="+userVerificationData.getEmailId().trim()+"&hash="+ userVerificationData.getHash();
		mailBody.append("<a href='"+link+"' > "+link+" </a> <br/>");
		mailBody.append("Regards, <br/>");
		mailBody.append("Team Filite <br/>");
		return mailBody.toString();
	}


	@RequestMapping(value="/verifyUser",method=RequestMethod.GET)
	public ModelAndView verifyUser(@ModelAttribute UserVerification userVerificationData,BindingResult result) {
		int status;
		if(result.hasErrors()) {
			logger.debug(result.toString());
		}
		ModelAndView mv = new ModelAndView();
		try {
			status = loginService.verifyUser(userVerificationData);			
		} catch(Exception e) {
			e.printStackTrace();
			status = -1;
		}
		logger.debug("The value of return status in verifyuser is "+status);
		mv.addObject("status", status);
		mv.setViewName("userVerificationStatus");
		return mv;
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView login(@ModelAttribute User user,BindingResult result,HttpServletRequest request) {
		int status;
		if(result.hasErrors()) {
			logger.error(result.toString());
		}
		ModelAndView mv = new ModelAndView();
		try {
			String plainPassword = user.getPassword();
			user = loginService.verifyUserLogin(user);
			if(user == null) {
				status = 0;
			} else {
				boolean passStatus = Utility.checkPass(plainPassword, user.getPassword());
				if(passStatus) {
					   request.getSession().setAttribute(Constants.USER, user);
					   status = 1;
				} else {
					   status = 2;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			status = -1;
		}
		logger.error("The value of return status in verifyUser is "+status);
		mv.addObject("status", status);
		if(status == -1 || status == 0 || status == 2) {
			mv.setViewName("landingpage");
		} else {
			mv.setViewName("loginSuccess");
		}
			
		return mv;
	}
	
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpSession session) {
					session.invalidate();
					return "redirect:"+contextPath;
	}
	
	
	
}
