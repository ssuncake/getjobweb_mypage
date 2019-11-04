package getjob.it_introduction.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.dto.Job;
import common.dto.Milestone;
import common.dto.Skill;
import getjob.it_introduction.dao.ItIntroductionDaoImpl;

@Service
public class ItBiz {

	@Autowired
	private ItIntroductionDaoImpl itDao;
	
	public List<Job> selectAllJob(){
		List<Job> jobs = itDao.selectAllJob();
		return jobs;
	}
	
	public List<Job> get_job_list() {
		List<Job> jobs = itDao.selectAllJob();
		return jobs;
	}//직무 목록 보기

	public Job get_job_content(String name) {
		Job job = itDao.selectJob(name);
		return job;
	}//직무 내용 보기

	public int add_job(Job job) {
		int result = itDao.insertJob(job);
		return result;
	} //직무 추가하기

	public int update_job(Job job) {
		int result = itDao.updateJob(job);
		return result;
	} //직무 설명 변경하기

	public int delete_job(String name) {
		int result = itDao.deleteJob(name);
		return result;
	} //직무 삭제하기

	public List<Skill> get_skill_list(String name) {
		List<Skill> skills = itDao.selectAllSkill(name);
		return skills;
	} //기술 목록 보기

	public Skill get_skill_content(String name) {
		Skill skill = itDao.selectSkill(name);
		return skill;
	} //기술 내용 보기

	public int add_skill(Skill skill) {
		int result = itDao.insertSkill(skill);
		return result;
	} //기술 추가하기

	public int update_skill(Skill skill) {
		int result = itDao.updateSkill(skill);
		return result;
	} //기술 변경하기

	public int delete_skill(String name) {
		int result = itDao.deleteSkill(name);
		return result;
	} //기술 삭제하기
		
	public List<Milestone> get_milestone_list() {
		List<Milestone> milestones = itDao.selectAllMilestone();
		return milestones;
	} //마일스톤 목록 보기

	public Milestone get_miletone_content(String name) {
		Milestone milestone = itDao.selectMilestone(name);
		return milestone;
	} //마일스톤 내용 보기
		
	public int add_milestone(String jobname, Milestone milestone) {
		int result = itDao.insertMilestone(jobname, milestone);
		return result;
	}

	public int update_milestone(String name, Milestone milestone) {
		int result = itDao.updateMilestone(name, milestone);
		return result;
	}

	public int delete_milestone(String name) {
		int result = itDao.deleteJob(name);
		return result;
	}
}
