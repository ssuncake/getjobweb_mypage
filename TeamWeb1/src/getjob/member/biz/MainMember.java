package getjob.member.biz;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import common.dto.Member;
import common.model.MemberInfo;
import getjob.member.dao.MemberDao;


public class MainMember {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        MemberDao dao = applicationContext.getBean("Member_DaoImpl", MemberDao.class);

        get_all_member_list(dao); // 회원 목록 조회

//        signup_getjob(dao); // 회원 가입
//        leave_getjob(dao); // 회원 탈퇴
//        validate_member_id(dao); //아이디 검색
//        sign_in(dao); //로그인
//
        get_member_info(dao); // 회원 정보 보기
        update_member_info(dao); //회원 정보 변경
        
    }


	private static void get_all_member_list(MemberDao dao) {
		List<Member> members = dao.selectAllMembers();
        for (Member member : members) {
        	System.out.println(member);
        }
	}

	private static void signup_getjob(MemberDao dao) {
		Member member = new Member();
        member.setId("hong@naver.com");
        member.setName("홍길동");
        member.setPassword("hong1234");
        member.setType("member");
        member.setAuth("프로필미작성");
        dao.insertMember(member);
	}

	private static void leave_getjob(MemberDao dao) {
		dao.deleteMember("hong@naver.com"); // 회원 탈퇴 
		dao.deleteMemberJobs("hong@naver.com");  
		dao.deleteMemberRecomOffers("hong@naver.com");
		dao.deleteMemberSkills("hong@naver.com");
	}

	private static void validate_member_id(MemberDao dao) {
		String FIND_ID = "choi@naver.com";
		if (dao.selectMember(FIND_ID).getId() == FIND_ID) {
			System.out.println("아이디가 검색되었습니다");
		}; // 아이디 검색
	}

	private static void sign_in(MemberDao dao) {
		System.out.println("로그인 시도");
		if (dao.selectMember("hwang@naver.com", "ccccc").getId() == "hwang@naver.com"){
			System.out.println("로그인 되었습니다.");
		}; //로그인
	}

	private static void get_member_info(MemberDao dao) {
		MemberInfo memberInfo = dao.selectMemberInfo("hwang@naver.com");// 회원 정보 보기
		Member member = memberInfo.getMember();
		System.out.println(member);
		memberInfo.getJobs().forEach((job)->System.out.println(job));
		memberInfo.getSkills().forEach((skill)->System.out.println(skill));
	}

	private static void update_member_info(MemberDao dao) {
		MemberInfo memberInfo = new MemberInfo();
		Member member = new Member();
        member.setId("hwang@naver.com");
        member.setName("정길동");
        member.setPassword("hong1234");
        member.setType("member");
        member.setAuth("프로필미작성");
        memberInfo.setMember(member);

		ArrayList<String> jobs = new ArrayList<String>();
		jobs.add("frontend");
		jobs.add("backend");
		memberInfo.setJobs(jobs);
		

		ArrayList<String> skills = new ArrayList<String>();
		skills.add("java");
		skills.add("c");
		skills.add("c++");
		memberInfo.setSkills(skills);

		dao.updateMemberInfo(memberInfo); // 회원정보 변경(비밀번호 변경)
	}
}
