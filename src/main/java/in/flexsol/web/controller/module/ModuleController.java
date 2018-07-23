package in.flexsol.web.controller.module;

import in.flexsol.modal.menu.Menu;
import in.flexsol.modal.module.Module;
import in.flexsol.modal.user.User;
import in.flexsol.service.module.ModuleService;
import in.flexsol.utility.Constants;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ModuleController {

	
	@Autowired
	ModuleService moduleService;
	
	
	@RequestMapping(value="/modules",method=RequestMethod.GET)
	public ModelAndView modules(HttpServletRequest request,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		try {
			   User user = (User)session.getAttribute(Constants.USER);
			   List<Module> moduleList = moduleService.getModulesList(user);
			   Integer status = (Integer)request.getAttribute("status");
			   mv.addObject("status",status == null ? null : status.intValue());
			   mv.addObject("moduleList", moduleList);
		} catch(EmptyResultDataAccessException e) {
			e.printStackTrace();
			mv.addObject("status", -2);				
		} catch(Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("module/modulelist");
		return mv;
	}
	
	
	@RequestMapping(value="/modules/{id}",method=RequestMethod.GET)
	public String moduleById(@PathVariable("id") int moduleId, HttpServletRequest request,Model model) {
		try {
			   User user = (User) request.getSession().getAttribute(Constants.USER);
			   Module module = moduleService.getModuleById(user,moduleId);
			   List<Menu> menusList = moduleService.getModuleMenus(user,moduleId);//menus based on user role...
			   model.addAttribute("menusList", menusList);
			   model.addAttribute("module", module);
			   return "module/modulehome";
		} catch(EmptyResultDataAccessException e) {
					e.printStackTrace();
					request.setAttribute("status", -1);				
		} catch(Exception e) {
					e.printStackTrace();
					request.setAttribute("status", 0);				
		}
		return	"forward:/modules";  
		
				
	}
	
}
