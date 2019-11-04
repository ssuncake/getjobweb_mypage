package getjob.offer.dao;

import java.util.List;

import common.dto.Offer;
import common.model.JobWithSkills;
import common.model.OfferDetail;

public interface OfferDao {
	public List<Offer> selectOfferList(); //전체목록조회
	public int insertOfferDetail(OfferDetail detail); //새공고 작성하기 -> 작성완료확인
	public OfferDetail selectOneOfferDetail(int number); //공고상세보기
	public int updateOfferDetail(OfferDetail detail); //공고내용수정 -> 변경완료확인
	public int deleteOfferDetail(int number); //공고삭제하기

	public int deleteOfferSpecs(int number); //요구직무/기술 태그 삭제
	public int insertOfferSpecs(int number, List<JobWithSkills> jobs); //요구직무/기술 태그 추가

}