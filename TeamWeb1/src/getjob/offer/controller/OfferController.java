package getjob.offer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import common.dto.Offer;
import common.model.JobWithSkills;
import common.model.OfferDetail;
import getjob.offer.biz.OfferBiz;

@Controller
public class OfferController {
	
	@Autowired
	private OfferBiz offerBiz;

//	@RequestMapping(value="offerDetailRequested.job", method = RequestMethod.GET)
//	public ModelAndView offerDetail(@RequestParam int offer) {
//		OfferDetail detail = offerBiz.get_offer(offer);
//		Offer off= detail.getOffer();
//		List<JobWithSkills> jobs = detail.getJobs();
//		
//		return new ModelAndView() {
//			{
//				setViewName("/_VIEW/offer/detailed_offer.jsp");
//				addObject("offer", off);
//				addObject("jobs", jobs);
//			}
//		};
//	}
	@CrossOrigin(origins = "http://127.0.0.1")
	@RequestMapping(value="offerDetailRequested.job", method = RequestMethod.GET)
	@ResponseBody
	public List<JobWithSkills> offerDetail(@RequestParam int offer) {
		OfferDetail detail = offerBiz.get_offer(offer);
		Offer off= detail.getOffer();
		List<JobWithSkills> jobs = detail.getJobs();
		return jobs;
	}
}
