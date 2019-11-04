package getjob.login.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.dto.Member;
import getjob.login.dao.LoginDaoImpl;

@Service
public class LoginBiz {

	@Autowired
	private LoginDaoImpl loginDao;
	
	public Member validate_id(Member member) {
		Member result = loginDao.selectMemberWithId(member);
		return result;
	}
	
	public Member sign_in(Member member) {
		Member result = loginDao.selectMemberWithPassword(member);
		return result;
	}
}
