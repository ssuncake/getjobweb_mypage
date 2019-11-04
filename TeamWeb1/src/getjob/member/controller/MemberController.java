package getjob.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import common.model.MemberInfo;
import getjob.member.biz.MemberBiz;

@Controller
public class MemberController {
	@Autowired
	MemberBiz memberBiz;
	
	@RequestMapping(value = "mypage.job")
	public ModelAndView mypage(@RequestParam String id) {
		System.out.println("id : " + id);
		MemberInfo memberInfo = memberBiz.get_member_info(id);
		return new ModelAndView() {
			{
				setViewName("_VIEW/member/mypage/mypage.jsp");
				addObject("memberinfo", memberInfo);
			}
		};
	}
	
	@RequestMapping(value = "memberjoin.job")
	public String memberJoin(Model model,  HttpServletRequest request) {
		System.out.println("id : " + request.getParameter("id"));
		System.out.println("id : " + request.getParameter("pw"));
		System.out.println("id : " + request.getParameter("name"));
		return "#";
	}


}
