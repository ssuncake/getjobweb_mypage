package getjob.login.controller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.session.web.http.HttpSessionManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import common.dto.Member;
import getjob.login.biz.LoginBiz;

@Controller
public class LoginController {

	@Autowired
	private LoginBiz loginBiz;

	@RequestMapping(value = "loginRequested.job")
	public ModelAndView login(Member memberParam) {
		
		Member member = loginBiz.sign_in(memberParam);
		boolean loginOk = (member != null) ? true : false;
		if (loginOk) {
			
			return new ModelAndView() {
				{
					setViewName("/getjobfront/member/index.jsp");
					addObject("loginOk", loginOk);
				}
			};
		} else {
			return new ModelAndView() {
				{
					setViewName("/getjobfront/login/index.jsp");
				}
			};
		}
	}
}
