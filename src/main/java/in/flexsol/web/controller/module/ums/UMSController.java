package in.flexsol.web.controller.module.ums;

import in.flexsol.modal.menu.Menu;
import in.flexsol.modal.module.Module;
import in.flexsol.modal.user.Role;
import in.flexsol.modal.user.User;
import in.flexsol.service.login.LoginService;
import in.flexsol.service.module.ModuleService;
import in.flexsol.service.ums.UMSService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class UMSController {

	@Autowired	
	LoginService loginService;
	
	@Autowired
	UMSService umsService;
					
	@Autowired
	ModuleService moduleService;
	
	@RequestMapping(value="/modules/ums/manageUsers",method=RequestMethod.POST)
	public String manageUsers(HttpSession httpSession, Model model) {
		try {
				List<User> usersList = loginService.getUsersList(); 
				model.addAttribute("usersList", usersList);
				return "ums/manageUsers";
		} catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", -1);
			return "message";
		}
		
		
	}
	
	
	
	@RequestMapping(value="/modules/ums/getEditUserScreen/{id}",method=RequestMethod.POST)
	public String getEditUserScreen(@PathVariable("id") int userId, HttpSession httpSession, Model model) {
		try {
			User user = loginService.getUserData(userId);
			model.addAttribute("user", user);
			return "ums/editUser";
		} catch(Exception e) {
				e.printStackTrace();
				model.addAttribute("msg", -1);
				return "message";	
		}
	}

	
	@RequestMapping(value="/modules/ums/editUser",method=RequestMethod.POST)
	@ResponseBody
	public int editUser(@ModelAttribute User user, BindingResult result) {
		int status;
		try {	
			if(result.hasErrors()) {
				result.toString();
			}
			status = loginService.updateUserData(user);
		} catch(Exception e) {
			e.printStackTrace();
			status = -1;
		}
		return status;
	}
	
	
	@RequestMapping(value="/modules/ums/addUser",method=RequestMethod.POST)
	@ResponseBody
	public int addUser(@ModelAttribute User user, BindingResult result) {
			return loginService.addNewUser(user);
			//if user added then email id of user
			//if there is an exception then -1
			//if user with that email id already exists then 0
	}
	
	
	@RequestMapping(value="/modules/ums/moduleMapping",method=RequestMethod.POST)
	public String moduleMapping(HttpServletRequest request,Model model) {
			try {
						List<User> usersList = loginService.getUsersList(); 
						model.addAttribute("usersList", usersList);
						return "ums/userModuleMapping";
			} catch(Exception e) {
						e.printStackTrace();
						model.addAttribute("msg", -1);
						return "message";
			}
	}
	
	
	
	@RequestMapping(value="/modules/ums/editUserModuleMapping",method=RequestMethod.POST)
	public String editUserModuleMapping(HttpServletRequest request,Model model) {
			try {		//user module mapping 
						//user's role in each module...
						List<User> usersList = loginService.getUsersList(); 
						model.addAttribute("usersList", usersList);
						return "ums/editUserModuleMapping";
			} catch(Exception e) {
						e.printStackTrace();
						model.addAttribute("msg", -1);
						return "message";
			}
	}
	
	
	@RequestMapping(value="/modules/ums/addEditRoleScreen/{roleId}",method=RequestMethod.POST)
	public String getEditRoleScreen(@PathVariable("roleId") int roleId, HttpSession httpSession, Model model) {
		try {
			model.addAttribute("roleId",roleId);
			List<Module> modulesList = moduleService.finAllModules();
			List<Menu> menusList = umsService.findAllMenus();
			model.addAttribute("modulesList", modulesList);
			model.addAttribute("menusList", menusList);
			if(roleId > 0) {
				List<Menu> roleBasedMenusList = umsService.findMenusByRole(roleId);
				Role role = loginService.getRoleById(roleId);
				model.addAttribute("role", role);
				model.addAttribute("roleBasedMenusList", roleBasedMenusList);
			}
			return "ums/addEditRoleAccess";
		} catch(Exception e) {
				e.printStackTrace();
				model.addAttribute("msg", -1);
				return "message";	
		}
	}
	
	
}
