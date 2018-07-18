package in.flexsol.interceptors;

import in.flexsol.modal.user.User;
import in.flexsol.utility.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class RequestInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		long startTime = System.currentTimeMillis();
		logger.error("Request URL::" + request.getRequestURL().toString()
				+ ":: Start Time=" + System.currentTimeMillis());
		request.setAttribute("startTime", startTime);
		HttpSession session = request.getSession();
		User user = (User)request.getSession().getAttribute(Constants.USER);
		if(user == null && isXMLHttpRequest(request)) {
			response.sendError(403);
			return false;
		} else if(user == null) {
			session.invalidate();
			response.sendRedirect("/filite/");
			return false;
		}
//		if(isXMLHttpRequest(request)) {
//			  response.sendError(403);
//			  return false;
//		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.error("Request URL::" + request.getRequestURL().toString()
				+ " Sent to Handler :: Current Time=" + System.currentTimeMillis());
		//we can add attributes in the modelAndView and use that in the view page
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		long startTime = (Long) request.getAttribute("startTime");
		logger.error("Request URL::" + request.getRequestURL().toString()
				+ ":: End Time=" + System.currentTimeMillis());
		logger.error("Request URL::" + request.getRequestURL().toString()
				+ ":: Time Taken=" + (System.currentTimeMillis() - startTime));
	}
	
	private boolean isXMLHttpRequest(HttpServletRequest request) {
	  return request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equals("XMLHttpRequest");
	}
	
	
}
