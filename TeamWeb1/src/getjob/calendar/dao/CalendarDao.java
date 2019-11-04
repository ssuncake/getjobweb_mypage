package getjob.calendar.dao;

import java.util.List;

import common.dto.Code;
import common.dto.Offer;
import common.dto.Test;
import common.model.TestSchedule;

public interface CalendarDao {
	public List<Code> selectAllCertificates(); //자격증 검색
	public List<Test> selectAllTestSchedules(String cert_name); //자격증 확인
	List<Test> selectTestSchedule(int codeno); //선택한 자격증 일정 확인

	public int insertMemberTestSchedule(List<TestSchedule> schedules); // 나의 자격증 담기
	public List<Test> selectAllMemberTestSchedules(String id); // 내 자격증 일정 월별 확인
	public int deleteMemberTestSchedule(List<TestSchedule> schedules); // 나의 자격증 삭제
	
	public List<Offer> selectAllOfferSchedules(); //공고 확인

	public int insertOfferSchedule(List<Integer> offers, String id); // 나의 공고로 담기
	public List<Offer> selectAllMemberOfferSchedules(String id); // 내 공고 일정 월별 확인
	public int deleteMemberOfferSchedule(List<Integer> offers, String id); // 나의 공고 삭제
}
