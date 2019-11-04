package getjob.it_introduction.dao;

import java.util.List;

import common.dto.Job;
import common.dto.Milestone;
import common.dto.Skill;
import common.model.JobWithSkills;

public interface ItDao {
	public List<Job> selectAllJob();
	public int insertJob(Job job);
	public Job selectJob(String name);
	public int updateJob(Job job);
	public int deleteJob(String name);
	
	public List<Skill> selectAllSkill(String name);
	public JobWithSkills selectJobSkills(String name);
	public int insertSkill(Skill skill);
	public Skill selectSkill(String name);
	public int updateSkill(Skill skill);
	public int deleteSkill(String name);
	
	
	public List<Milestone> selectAllMilestone();
	public int insertMilestone(String jobname, Milestone milestone);
	public Milestone selectMilestone(String name);
	public int updateMilestone(String jobname, Milestone milestone);
	public int deleteMilestone(String name);

}
