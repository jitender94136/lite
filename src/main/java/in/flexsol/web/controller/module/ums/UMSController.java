package in.flexsol.web.controller.module.ums;

import in.flexsol.modal.menu.Menu;
import in.flexsol.modal.module.Module;
import in.flexsol.modal.user.Role;
import in.flexsol.modal.user.User;
import in.flexsol.modal.usermodulerolemapping.UserModuleRoleMapping;
import in.flexsol.service.login.LoginService;
import in.flexsol.service.module.ModuleService;
import in.flexsol.service.ums.UMSService;
import in.flexsol.utility.Constants;
import in.flexsol.utility.Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
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
				return "module/ums/manageUsers";
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
			return "module/ums/editUser";
		} catch(Exception e) {
				e.printStackTrace();
				model.addAttribute("msg", -1);
				return "message";	
		}
	}

	
	@RequestMapping(value="/modules/ums/editUser",method=RequestMethod.POST)
	@ResponseBody
	public int editUser(@ModelAttribute User user, BindingResult result,HttpSession session) {
		int status;
		try {	
			if(result.hasErrors()) {
				result.toString();
			}
			User sessionUser = (User) session.getAttribute(Constants.USER);
			user.setUpdatedBy(sessionUser.getId());
			status = loginService.updateUserData(user);
		} catch(Exception e) {
			e.printStackTrace();
			status = -1;
		}
		return status;
	}
	
	
	@RequestMapping(value="/modules/ums/addUser",method=RequestMethod.POST)
	@ResponseBody
	public int addUser(@ModelAttribute User user, BindingResult result, HttpSession session) {
		    User sessionUser = (User) session.getAttribute(Constants.USER);
		    user.setCreatedBy(sessionUser.getId());
		    user.setPassword(Utility.hashPassword("user"));
		    return loginService.addNewUser(user);
			//if user added then email id of user
			//if there is an exception then -1
			//if user with that email id already exists then 0
	}
	
	
	@RequestMapping(value="/modules/ums/moduleMapping",method=RequestMethod.POST)
	public String moduleMapping(HttpServletRequest request,Model model) {
			try {
						List<User> usersList = loginService.getUsersList();
						Map<Integer,String> userMappedModules = loginService.getUserMappedModules();
						model.addAttribute("usersList", usersList);
						model.addAttribute("userMappendModules", userMappedModules);
						return "module/ums/userModuleMappingGrid";
			} catch(Exception e) {
						e.printStackTrace();
						model.addAttribute("msg", -1);
						return "message";
			}
	}
	
	
	
	@RequestMapping(value="/modules/ums/editUserModuleMapping",method=RequestMethod.POST)
	public String editUserModuleMapping(@RequestParam("userId") int userId,HttpServletRequest request,Model model) {
			try {		
						User user = loginService.getUserData(userId);
						List<Module> modulesList = moduleService.finAllModules();
						List<Role> rolesList = umsService.findAllRoles();
						//List<Module> userMappedModules = moduleService.getModulesList(user);
						List<UserModuleRoleMapping> userModuleRoleMappingList = moduleService.userModuleRoleMappingList(user);
						List<Integer> userMappedModuleIdsList = moduleService.getUserMappedModulesIdsList(userModuleRoleMappingList);
						Map<Integer,List<Integer>> moduleMappedRoleIdsMap = moduleService.getModuleMappedRoleIdsMap(userModuleRoleMappingList);
						model.addAttribute("user", user);
						model.addAttribute("modulesList", modulesList);
						model.addAttribute("userMappedModuleIdsList", userMappedModuleIdsList);
						model.addAttribute("moduleMappedRoleIdsMap", moduleMappedRoleIdsMap);
						model.addAttribute("rolesList", rolesList);
						return "module/ums/editUserModuleMapping";
			} catch(Exception e) {
						e.printStackTrace();
						model.addAttribute("msg", -1);
						return "message";
			}
	}
	
	@RequestMapping(value="/modules/ums/insertUpdateModuleRoleMapping",method=RequestMethod.POST)
	@ResponseBody
	public int insertUpdateModuleMapping(@ModelAttribute User userId, BindingResult result,@RequestParam String moduleToRoleMapping,HttpSession session) {
			try {
						User sessionUser = (User)session.getAttribute(Constants.USER);
						userId.setCreatedBy(sessionUser.getId());
						umsService.insertUpdateModuleMapping(userId,moduleToRoleMapping);
						return 1;
			} catch(Exception e) {
						e.printStackTrace();
						return -1;
			}
	}
	
	@RequestMapping(value="/modules/ums/manageRoles",method=RequestMethod.POST)
	public String manageRoles(HttpSession httpSession, Model model) {
		try {
				List<Role> rolesList = umsService.findAllRoles(); 
				model.addAttribute("rolesList", rolesList);
				return "module/ums/manageRoles";
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
				List<Integer> roleBasedMenuIdsList = new ArrayList<Integer>(); 
				for(Menu m : roleBasedMenusList) {
						roleBasedMenuIdsList.add(m.getId());
				}
				Role role = umsService.getRoleById(roleId);
				model.addAttribute("role", role);
				model.addAttribute("roleBasedMenuIdsList", roleBasedMenuIdsList);
			}
			return "module/ums/addEditRoleAccess";
		} catch(Exception e) {
				e.printStackTrace();
				model.addAttribute("msg", -1);
				return "message";	
		}
	}
	
	
	
	@RequestMapping(value="modules/ums/insertUpdateRoleAccess",method=RequestMethod.POST)
	@ResponseBody
	public int insertUpdateRoleAccess(@ModelAttribute("role") Role role, @RequestParam String moduleMenuMapping, HttpSession httpSession) {
		try {
				User user = (User) httpSession.getAttribute(Constants.USER);
				role.setCreatedBy(user.getId());
				int status = umsService.insertUpdateRoleAcess(role,moduleMenuMapping);
				return status;
		} catch(Exception e) {
				e.printStackTrace();
				return 0;
		}
	}
	
	
}
