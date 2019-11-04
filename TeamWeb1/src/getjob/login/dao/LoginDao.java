package getjob.login.dao;

import common.dto.Member;

public interface LoginDao {
	public Member selectMemberWithId(Member member);
	public Member selectMemberWithPassword(Member member);
}
