package getjob.member.dao;

import java.util.List;

import common.dto.Member;
import common.model.MemberInfo;
import common.model.MemberRecomOffers;

public interface MemberDao {

	public List<Member> selectAllMembers();
	public int insertMember(Member member);
	public Member selectMember(String id);
	public Member selectMember(String id, String password);
	public MemberInfo selectMemberInfo(String string);
	public List<String> selectMemberJobs(String id);

	public int updateMemberInfo(MemberInfo memberInfo);
	public int updateMember(Member member);
	public int deleteMember(String id);
	
	public int insertMemberJobs(String id, List<String> jobs);
	public int deleteMemberJobs(String id);

	public List<String> selectMemberSkills(String id);
	public int insertMemberSkills(String id, List<String> skills);
	public int deleteMemberSkills(String id);
	
	public MemberRecomOffers selectMemberRecomOffers(String id);
	public int insertMemberRecomOffers(List<Integer> offers, String id, String type);
	public int deleteMemberRecomOffers(String id);

}
