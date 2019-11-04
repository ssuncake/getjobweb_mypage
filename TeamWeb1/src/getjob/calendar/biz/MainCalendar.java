package getjob.calendar.biz;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import common.dto.Code;
import common.dto.Offer;
import common.dto.Test;
import common.model.TestSchedule;
import getjob.calendar.dao.CalendarDao;

public class MainCalendar {
	public static void main(String[] args) {
		ApplicationContext act = new ClassPathXmlApplicationContext("beans.xml");
		CalendarDao dao = act.getBean("Calendar_DaoImpl",CalendarDao.class);
		
		search_certificate(dao); //자격증 목록 보기
//		get_test_schedule(dao); //선택한 자격증 일정 출력
//		get_member_certificate_list(dao); //회원별-자격증 보기
//		delete_member_certificate(dao); //회원별-자격증 삭제
//		add_member_certificate(dao); //회원별-자격증 추가 

//		get_offer_schedule(dao); //전체 공고 일정 출력
//		get_member_offer_list(dao); //회원별-공고 보기
//		delete_member_offer(dao); //회원별-공고 삭제
//		add_offer_schedule(dao); //회원별-공고 추가	

	}

	private static void search_certificate(CalendarDao dao) {
		List<Code> cert_list = dao.selectAllCertificates();
		for(Code code: cert_list) {
			System.out.println(code);
		}
	}

	private static void get_member_certificate_list(CalendarDao dao) {
		List<Test> test2 = dao.selectAllMemberTestSchedules("choi@naver.com");
		System.out.println("choi@naver.com의 자격증 시험 일정은");
		for(Test t : test2) {
			System.out.println(t.getCert_name()+"  "+t.getBegin_date()+"  "+t.getEnd_date());
		}
	}

	private static void get_test_schedule(CalendarDao dao) {
		String cert = "정보처리기사";
		List<Test> test = dao.selectAllTestSchedules(cert);
		System.out.println(cert+" 자격증 시험 일정은");
		for(Test t : test) {
			System.out.println(t.getCert_name()+"  "+t.getBegin_date()+"  "+t.getEnd_date()+"  "+t.getSection());
		}
	}

	private static void delete_member_certificate(CalendarDao dao) {
//		int r = dao.deleteMemberTestSchedule("정보처리기사", "choi@naver.com", new Date("2019/11/15"));
		List<TestSchedule> schedules = new ArrayList<>();
		TestSchedule schedule1 = new TestSchedule();
		schedule1.setCert_name("정보처리기사");
		schedule1.setId("choi@naver.com");
		schedule1.setBegin_date(new Date("2019/11/15"));
		schedules.add(schedule1);

		TestSchedule schedule2 = new TestSchedule();
		schedule2.setCert_name("정보처리기사");
		schedule2.setId("choi@naver.com");
		schedule2.setBegin_date(new Date("2019/06/29"));
		schedules.add(schedule2);

		int result = dao.deleteMemberTestSchedule(schedules);
		System.out.println(result+"개 자격증일정 삭제 완료");
	}

	private static void add_member_certificate(CalendarDao dao) {
//		int r = dao.insertTestSchedule("choi@naver.com", "정보처리기사", new Date("2019/11/15"), new Date("2019/11/15"));
		List<TestSchedule> schedules = new ArrayList<>();
		TestSchedule schedule1 = new TestSchedule();
		schedule1.setCert_name("정보처리기사");
		schedule1.setId("choi@naver.com");
		schedule1.setBegin_date(new Date("2019/11/15"));
		schedule1.setEnd_date(new Date("2019/11/15"));
		schedules.add(schedule1);

		TestSchedule schedule2 = new TestSchedule();
		schedule2.setCert_name("정보처리기사");
		schedule2.setId("choi@naver.com");
		schedule2.setBegin_date(new Date("2019/06/29"));
		schedule2.setEnd_date(new Date("2019/06/29"));
		schedules.add(schedule2);

		int result = dao.insertMemberTestSchedule(schedules);
		System.out.println(result+"개 자격증일정 추가 완료");
	}


	private static void get_offer_schedule(CalendarDao dao) {
		List<Offer> offer_list = dao.selectAllOfferSchedules();
		for(Offer offer : offer_list) {
			System.out.println(offer.getNo()+"  "+offer.getTitle()+"  "+offer.getBegin_date()+"  "+offer.getEnd_date());		
		}
	}

	private static void get_member_offer_list(CalendarDao dao) {
		List<Offer> memberOffers = dao.selectAllMemberOfferSchedules("choi@naver.com");
		System.out.println("choi@naver.com의 공고 일정은 ");
		System.out.printf("%-12s%-24s%-24s%-10s\n","번호", "시작일", "종료일", "제목");
		for (Offer offer: memberOffers) {
			System.out.printf("%-8s%-16s%-16s%-10s",offer.getNo(),offer.getBegin_date(), offer.getEnd_date(), offer.getTitle());
			System.out.println();
		}
	}

	private static void delete_member_offer(CalendarDao dao) {
		ArrayList<Integer> offers = new ArrayList<>();
		offers.add(103);
		offers.add(104);
		int result = dao.deleteMemberOfferSchedule(offers, "choi@naver.com");
		System.out.println(result+"개 회원별-공고삭제 완료");
	}
	
	private static void add_offer_schedule(CalendarDao dao) {
		ArrayList<Integer> offers = new ArrayList<>();
		offers.add(103);
		offers.add(104);
		int result = dao.insertOfferSchedule(offers, "choi@naver.com");
		System.out.println(result+"개 공고일정 추가 완료");
	}
	
}
