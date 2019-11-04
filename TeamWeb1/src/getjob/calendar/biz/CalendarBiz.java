package getjob.calendar.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.dto.Code;
import common.dto.Offer;
import common.dto.Test;
import common.model.TestSchedule;
import getjob.calendar.dao.CalendarDaoImpl;

@Service
public class CalendarBiz {
	
	@Autowired
	private CalendarDaoImpl calendarDao;
	
	public List<Code> get_All_Certificate(){
		return calendarDao.selectAllCertificates();
	}
	
	public List<Test> get_Test_Schedule(int codename){
		return calendarDao.selectTestSchedule(codename);
	}
	
	public List<Offer> select_All_Offer() {
		return calendarDao.selectAllOfferSchedules();
	}

	public List<Code> search_certificate() {
		return calendarDao.selectAllCertificates();
	} //자격증 목록 보기

	public List<Test> get_test_schedule(int codeno) {
		return calendarDao.selectTestSchedule(codeno);
	} //선택한 자격증 일정 출력

	public List<Test> get_member_certificate_list(String id) {
		return calendarDao.selectAllMemberTestSchedules(id);
	} //회원별-자격증 보기

	public int delete_member_certificate(List<TestSchedule> schedules) {
		return calendarDao.deleteMemberTestSchedule(schedules);
	} //회원별-자격증 삭제

	public int add_member_certificate(List<TestSchedule> schedules) {
		return calendarDao.insertMemberTestSchedule(schedules);
	} //회원별-자격증 추가 

	public List<Offer> get_offer_schedule(String id) {
		return calendarDao.selectAllMemberOfferSchedules(id);
	} //전체 공고 일정 출력

	public List<Offer> get_member_offer_list(String id) {
		return calendarDao.selectAllMemberOfferSchedules(id);
	} //회원별-공고 보기

	public int delete_member_o3ffer(List<Integer> offers, String id) {
		return calendarDao.deleteMemberOfferSchedule(offers, id);
	} //회원별-공고 삭제

	public int add_offer_schedule(List<Integer> offers, String id) {
		return calendarDao.insertOfferSchedule(offers, id);
	} //회원별-공고 추가
}
