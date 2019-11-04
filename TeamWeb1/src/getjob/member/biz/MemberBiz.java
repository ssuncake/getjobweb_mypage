package getjob.member.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.dto.Member;
import common.model.MemberInfo;
import getjob.member.dao.MemberDaoImpl;

@Service
public class MemberBiz {

	@Autowired
	private MemberDaoImpl memberDao;

	public List<Member> get_all_member_list() {
		List<Member> members = memberDao.selectAllMembers();
		return members;
		// 회원 목록 조회
	}

	public int signup_getjob(Member member) {
		int result = memberDao.insertMember(member);
		return result;
	} // 회원 가입

	public int leave_getjob(String id) {
		int result = memberDao.deleteMember(id);
		return result;
	} // 회원 탈퇴

	public boolean validate_member_id(String id) {
		Member member = memberDao.selectMember(id);
		return (member != null) ? true : false;
	} // 아이디 검색

	public boolean sign_in(String id, String password) {
		Member member = memberDao.selectMember(id, password);
		return (member != null) ? true : false;
	} // 로그인

	public MemberInfo get_member_info(String id) {
		MemberInfo info = memberDao.selectMemberInfo(id);
		return info;
	} // 회원 정보 보기

	public int update_member_info(MemberInfo memberInfo) {
		int result = memberDao.updateMemberInfo(memberInfo);
		return result;
	} // 회원 정보 변경

}
