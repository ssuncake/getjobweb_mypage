package getjob.offer.biz;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import common.dto.Offer;
import common.model.JobWithSkills;
import common.model.OfferDetail;
import getjob.offer.dao.OfferDao;

public class Main_Offer {
	// github webhook test
	public static void main(String[] args) {
		ApplicationContext factory 
			= new ClassPathXmlApplicationContext("beans.xml");
		OfferDao dao = (OfferDao) factory.getBean("Offer_management_DaoImpl");
		
		get_offer_list(dao); //채용공고목록조회

		add_contents(dao); //작성하기
		get_offer(dao); //채용공고조회
		update_contents(dao); //변경저장
		delete_contents(dao); //삭제
		
		add_job_and_skill_to_offer(dao); //요구직무/기술 태그 추가
		delete_job_and_skill_from_offer(dao); //요구직무/기술 태그 삭제
	}


	private static void get_offer_list(OfferDao dao) {
		List<Offer> offers = dao.selectOfferList();
		System.out.printf("%-8s%-32s%-14s%-44s%-10s\n", "게시물번호", "제목", "내용", "기업로고 ", "마감일");
		for (Offer offer : offers) {
			System.out.print(offer.getNo() + "\t");
			System.out.print(offer.getTitle() + "\t");
			System.out.print(offer.getContent() + "\t");
			System.out.print(offer.getCi_img() + "\t");
			System.out.print(offer.getEnd_date() + "\t");
			System.out.println();
		}
	}

	public static void add_contents(OfferDao dao) {
		OfferDetail offerDetail = new OfferDetail();
		Offer offer = new Offer();
		offer.setNo(110);
		offer.setBegin_date(new Date());
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 5);
		offer.setEnd_date(cal.getTime());
		offer.setTitle("LG 신입사원 채원공고...");
		offer.setContent("LG와 함께할 개발자 모여라...");
		offer.setCi_img("/aaa/bbb/ccc.jpg");
		offer.setCorporate_id("146-86-00579");
		offerDetail.setOffer(offer);

		List<JobWithSkills> jobs = new ArrayList<>();
		JobWithSkills job1 = new JobWithSkills("dbdeveloper");
		job1.addSkill("sql");
		jobs.add(job1);
		offerDetail.setJobs(jobs);
		dao.insertOfferDetail(offerDetail);
	}

	static void get_offer(OfferDao dao) {
		OfferDetail offerWithSpecs = dao.selectOneOfferDetail(101);
		System.out.println("-------채용공고---------");
		System.out.println(offerWithSpecs.getOffer().getNo());
		System.out.println(offerWithSpecs.getOffer().getTitle());
		System.out.println(offerWithSpecs.getOffer().getContent());
		System.out.println(offerWithSpecs.getOffer().getCi_img());
		System.out.println(offerWithSpecs.getOffer().getEnd_date());
		System.out.println("----------------------");
		for (JobWithSkills j : offerWithSpecs.getJobs()) {
			for (String s : j.getSkills()) {
				System.out.println(j.getJobName() + ":" + s);
			}
		}
		System.out.println("----------------------");
	}

	private static void update_contents(OfferDao dao) {
		OfferDetail offerDetail = new OfferDetail();
		Offer offer = new Offer();
		offer.setNo(110);
		offer.setBegin_date(new Date());
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 5);
		offer.setEnd_date(cal.getTime());
		offer.setTitle("LG 경력사원 채원공고...");
		offer.setContent("LG와 함께할 개발자 모여라...");
		offer.setCi_img("/aaa/bbb/ccc.jpg");
		offer.setCorporate_id("146-86-00579");
		offerDetail.setOffer(offer);

		List<JobWithSkills> jobs = new ArrayList<>();
		JobWithSkills job1 = new JobWithSkills("dataanalyst");
		job1.addSkill("python");
		jobs.add(job1);
		offerDetail.setJobs(jobs);
		dao.updateOfferDetail(offerDetail); //변경 저장
	}

	private static void delete_contents(OfferDao dao) {
		dao.deleteOfferDetail(101);
		dao.deleteOfferDetail(102);
		dao.deleteOfferDetail(103);
		dao.deleteOfferDetail(104);
	}

	private static void add_job_and_skill_to_offer(OfferDao dao) {
		List<JobWithSkills> jobs = new ArrayList<>();
		JobWithSkills job1 = new JobWithSkills("frontend");
		job1.addSkill("html");
		job1.addSkill("css");
		job1.addSkill("javascript");
		jobs.add(job1);
		JobWithSkills job2 = new JobWithSkills("backend");
		job2.addSkill("java");
		job2.addSkill("c++");
		job2.addSkill("javascript");
		jobs.add(job2);
		
		dao.insertOfferSpecs(101, jobs); //요구직무/기술 태그 추가
	}

	private static void delete_job_and_skill_from_offer(OfferDao dao) {
		dao.deleteOfferSpecs(101); //요구직무/기술 태그 삭제
	}
}
