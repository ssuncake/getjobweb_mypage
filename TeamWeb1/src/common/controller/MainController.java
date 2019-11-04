package common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import common.dto.Job;
import common.dto.Member;
import common.dto.Offer;
import getjob.it_introduction.biz.ItBiz;
import getjob.login.biz.LoginBiz;
import getjob.member.biz.MemberBiz;
import getjob.offer.biz.OfferBiz;

@Controller
public class MainController {
	@Autowired
	private OfferBiz offerBiz;
	
	@Autowired
	private ItBiz itBiz;

	@Autowired
	private MemberBiz memberBiz;
	
	@Autowired
	private LoginBiz loginBiz;

	@RequestMapping(value = "it_introduction.job")
	public ModelAndView it_introduction() {
		List<Job> jobs = itBiz.selectAllJob();
		return new ModelAndView() {
			{
				setViewName("/getjobfront/it_introduction/index.jsp");
				addObject("jobs", jobs);
			}
		};
	}

	@RequestMapping(value = "calendar.job")
	public String calendar() {
		return "/calendar/index.jsp";
	}

	@RequestMapping(value = "login.job")
	public ModelAndView loginPrompt() {
		return new ModelAndView() {
			{
				setViewName("/member/login/index.jsp");
			}
		};
	}

	@RequestMapping(value = "offer.job")
	public ModelAndView offer() {
//		List<Offer> offers = offerBiz.get_offer_list();

		return new ModelAndView() {
			{
				setViewName("/offer/index.html");
//				addObject("offers", offers);
			}
		};
	}

	@RequestMapping(value = "getOffers.job")
	@ResponseBody
	public List<Offer> getOffers() {
		List<Offer> offers = offerBiz.get_offer_list();
		return offers;
	}

	@RequestMapping(value = "member.job")
	public ModelAndView member() {
//		List<Member> members = memberBiz.get_all_member_list();
		return new ModelAndView() {
			{
				setViewName("/member/mypage/index.html");
//				addObject("members", members);
			}
		};
	}

}
