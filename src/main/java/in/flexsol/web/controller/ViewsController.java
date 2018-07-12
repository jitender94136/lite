package in.flexsol.web.controller;



import in.flexsol.modal.Record;
import in.flexsol.service.RecordService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application file upload requests
 */
@Controller
public class ViewsController {

	private static final Logger logger = LoggerFactory.getLogger(ViewsController.class);
	
	@Autowired
	RecordService recordService;

	
	@RequestMapping(value = "/aqi", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Record record = recordService.getLatestRecord();
		mv.addObject("record", record);
		mv.setViewName("index");
		return mv;
	}

	@RequestMapping(value = "/compareaqi", method = RequestMethod.GET)
	public ModelAndView tableView(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Record record = recordService.getLatestRecord();
		Record secondSensorRecord = recordService.getSecondSensorRecords();
		List<Record> firstSensorRecords = recordService.getCurrentDayRecords();
		List<Record> secondSensorRecords = recordService.getCurrentDaySecondSensorRecords();
		mv.addObject("record", record);
		mv.addObject("secondSensorRecord",secondSensorRecord);
		mv.addObject("firstSensorRecords", firstSensorRecords);
		mv.addObject("secondSensorRecords", secondSensorRecords);
		mv.setViewName("tableview");
		return mv;
	}

}
