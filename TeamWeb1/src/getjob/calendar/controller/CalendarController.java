package getjob.calendar.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import common.dto.Code;
import common.dto.Offer;
import common.dto.Test;
import getjob.calendar.biz.CalendarBiz;

@Controller
public class CalendarController {

	@Autowired
	private CalendarBiz biz;
	
	Map<String,String> map = new HashMap<String, String>();
	
	@RequestMapping(value="getoffer.job")
	@ResponseBody
	public List<Offer> getOffer(){
		List<Offer> of = biz.select_All_Offer();
		return of;
	}
	
	@RequestMapping(value="gettestschedule.job", method=RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Test> gettestSchedule(@RequestParam("codeno") int codeno) {
		System.out.println(map);
		List<Test> test = biz.get_Test_Schedule(codeno);
		return test;
	}
	
	@RequestMapping(value="getcer.job")
	@ResponseBody
	public List<Code> getAllCertificate(){
		List<Code> cer = biz.get_All_Certificate();
		return cer;
	}
}
