package getjob.offer.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.dto.Offer;
import common.model.JobWithSkills;
import common.model.OfferDetail;
import getjob.offer.dao.OfferDaoImpl;

@Service
public class OfferBiz {

	@Autowired
	private OfferDaoImpl offerDao;

	public List<Offer> get_offer_list() {
		//채용공고목록조회
		List<Offer> offers = offerDao.selectOfferList();
		return offers;
	}

	public int add_contents(OfferDetail offerDetail) {
		int result = offerDao.insertOfferDetail(offerDetail);
		return result;
	} //작성하기

	public OfferDetail get_offer(int num) {
		OfferDetail offerDetail = offerDao.selectOneOfferDetail(num);
		return offerDetail;
	} //채용공고조회

	public int update_contents(OfferDetail detail) {
		int result = offerDao.updateOfferDetail(detail);
		return result;
	} //변경저장

	public int delete_contents(int number) {
		int result = offerDao.deleteOfferDetail(number);
		return result;
	} //삭제
	
	public int add_job_and_skill_to_offer(int number, List<JobWithSkills> jobs) {
		int result = offerDao.insertOfferSpecs(number, jobs);
		return result;
	} //요구직무/기술 태그 추가

	public int delete_job_and_skill_from_offer(int number) {
		int result = offerDao.deleteOfferSpecs(number);
		return result; 
	} //요구직무/기술 태그 삭제

}