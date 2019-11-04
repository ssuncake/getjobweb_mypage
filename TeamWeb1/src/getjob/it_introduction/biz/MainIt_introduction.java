package getjob.it_introduction.biz;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import common.dto.Job;
import common.dto.Milestone;
import common.dto.Skill;
import common.model.JobWithSkills;
import getjob.it_introduction.dao.ItDao;

public class MainIt_introduction {
	public static void main(String[] args) {
		ApplicationContext factory = new ClassPathXmlApplicationContext("beans.xml");
		ItDao dao = (ItDao) factory.getBean("IT_Introduction_DaoImpl");

		get_job_list(dao); // 직무 목록 보기
		get_job_content(dao); // 직무 내용 보기
//
//	add_job(dao); //직무 추가하기
//	update_job(dao); //직무 설명 변경하기
//	delete_job(dao); //직무 삭제하기
//
//	get_skill_list(dao); //기술 목록 보기
//	get_skill_content(dao); //기술 내용 보기
//
//	add_skill(dao); //기술 추가하기
//	update_skill(dao); //기술 변경하기
//	delete_skill(dao); //기술 삭제하기

//	get_milestone_list(dao); //마일스톤 목록 보기
//	get_miletone_content(dao); //마일스톤 내용 보기

//	add_milestone(dao);
//	update_milestone(dao);
//	delete_milestone(dao);

		((ClassPathXmlApplicationContext) factory).close();
	}

	private static void delete_milestone(ItDao dao) {
		dao.deleteMilestone("js 중급");
	}

	private static void update_milestone(ItDao dao) {
		dao.updateMilestone("frontend", new Milestone() {
			{
				setName("js 중급");
				setSeq(1);
				setDescript("프론트엔드 개발자 뿐만 아니라 node.js 백엔드 개발자에게도 필요한...");
			}
		});
	}

	private static void add_milestone(ItDao dao) {
		dao.insertMilestone("frontend", new Milestone() {
			{
				setName("js 중급");
				setSeq(1);
				setDescript("프론트엔드 개발자에게 필요한... ");
			}
		});
	}

	private static void get_job_list(ItDao dao) {
		List<Job> jobs = dao.selectAllJob();
		for (Job job : jobs) {
			System.out.println(job.getName());
		}
	}

	private static void get_job_content(ItDao dao) {
		Job job = dao.selectJob("backend");
		System.out.printf(job.getName() + " : " + job.getDescript());
	}

	private static void add_job(ItDao dao) {
		dao.insertJob(new Job() {
			{
				setName("블록체인 개발자");
				setDescript("블록체인...... 을 개발하는...");
			}
		});
	}

	private static void update_job(ItDao dao) {
		dao.updateJob(new Job() {
			{
				setName("블록체인 개발자");
				setDescript("블록체인 개발자는요...");
			}
		});
	}

	private static void delete_job(ItDao dao) {
		dao.deleteJob("블록체인 개발자");
	}

	private static void get_skill_list(ItDao dao) {
		JobWithSkills jobWithSkills = dao.selectJobSkills("frontend");
		String jobName = jobWithSkills.getJobName();
		for (String skillName : jobWithSkills.getSkills()) {
			System.out.println(jobName + " : " + skillName);
		}

		String JOBNAME = "frontend";
		List<Skill> skills = dao.selectAllSkill(JOBNAME);
		for (Skill skill : skills) {
			System.out.println(JOBNAME + " : " + skill.getName());
		}
	}

	private static void get_skill_content(ItDao dao) {
		String SKILLNAME = "java";
		Skill skill = dao.selectSkill(SKILLNAME);
		System.out.printf(skill.getName() + " : " + skill.getDescript());
	}

	private static void add_skill(ItDao dao) {
		dao.insertSkill(new Skill() {
			{
				setName("html");
				setDescript("html은 구조화된 문서 형식을 작성하는 마크업 언어로..");
			}
		});
	}

	private static void update_skill(ItDao dao) {
		dao.updateSkill(new Skill() {
			{
				setName("html");
				setDescript("html은 문서의 구조를...");
			}

		});
	}

	private static void delete_skill(ItDao dao) {
		dao.deleteJob("html");
	}

	private static void get_milestone_list(ItDao dao) {
		List<Milestone> milestones = dao.selectAllMilestone();
		for (Milestone milestone : milestones) {
			System.out.println(milestone.getName());
		}
	}

	private static void get_miletone_content(ItDao dao) {
		Milestone milestone = dao.selectMilestone("SQL 기초");
		System.out.println(milestone);
	}

}
