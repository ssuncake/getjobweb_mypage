package getjob.it_introduction.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import common.dto.Job;
import common.dto.Milestone;
import common.dto.Skill;
import common.model.JobWithSkills;

@Repository
public class ItIntroductionDaoImpl implements ItDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Job> selectAllJob() {
		String sql = "select * from job";
		List<Job> jobs = jdbcTemplate.query(sql, new RowMapper<Job>() {
			@Override
			public Job mapRow(ResultSet rs, int rowNum) throws SQLException {
				Job job = new Job();
				job.setName(rs.getString(1));
				job.setDescript(rs.getString(2));
				return job;
			}
		});
		return jobs;
	}
	
	@Override
	public int insertJob(Job job) {
		String sql = "insert into job(name, descript) values(?, ?)";
		return jdbcTemplate.update(sql, new Object[] {job.getName(), job.getDescript()});
	}

	
	@Override
	public Job selectJob(String name) {
		String sql = "select * from job where name = ?";
		Job job = jdbcTemplate.queryForObject(sql, new RowMapper<Job>() {
			@Override
			public Job mapRow(ResultSet rs, int rowNum) throws SQLException {
				Job job = new Job();
				job.setName(rs.getString(1));
				job.setDescript(rs.getString(2));
				return job;
			}
		}, new Object[] {name});
		return job;
	}

	@Override
	public int updateJob(Job job) {
		String sql = "update job set descript = ? where name = ?";
		return jdbcTemplate.update(sql, new Object[] {job.getDescript(), job.getName()});
	}

	@Override
	public int deleteJob(String name) {
		String sql = "delete job where name = ?";
		return jdbcTemplate.update(sql, new Object[] {name});
	}

	@Override
	public List<Skill> selectAllSkill(String name) {
		String sql = "select * from skill";
		List<Skill> skills = jdbcTemplate.query(sql, new RowMapper<Skill>() {
			@Override
			public Skill mapRow(ResultSet rs, int rowNum) throws SQLException {
				Skill skill = new Skill();
				skill.setName(rs.getString(1));
				skill.setDescript(rs.getString(2));
				return skill;
			}
		});
		return skills;
	}


	@Override
	public JobWithSkills selectJobSkills(String name) {
		String sql = "select * from job_skill where job_name = ?";
		JobWithSkills result = 
			jdbcTemplate.query(sql, new ResultSetExtractor<JobWithSkills>() {
				JobWithSkills jobWithSkills = new JobWithSkills(name);

				@Override
				public JobWithSkills extractData(ResultSet rs) throws SQLException, DataAccessException {
					while(rs.next()) {
						jobWithSkills.addSkill(rs.getString(2));
					}
					return jobWithSkills;
				}
				
			}, new Object[] {name});
		return result;
	}


	@Override
	public int insertSkill(Skill skill) {
		String sql = "insert into skill(name, descript) values(?, ?)";
		return jdbcTemplate.update(sql, new Object[] {skill.getName(), skill.getDescript()});
	}

	@Override
	public Skill selectSkill(String name) {
		String sql = "select * from skill where name = ?";
		Skill skill = jdbcTemplate.queryForObject(sql, new RowMapper<Skill>() {
			@Override
			public Skill mapRow(ResultSet rs, int rowNum) throws SQLException {
				Skill skill = new Skill();
				skill.setName(rs.getString(1));
				skill.setDescript(rs.getString(2));
				return skill;
			}
		}, new Object[] {name});
		return skill;
	}

	@Override
	public int updateSkill(Skill skill) {
		String sql = "update skill set descript = ? where name = ?";
		return jdbcTemplate.update(sql, new Object[] {skill.getDescript(), skill.getName()});
	}

	@Override
	public int deleteSkill(String name) {
		String sql = "delete skill where name = ?";
		return jdbcTemplate.update(sql, new Object[] {name});
	}

	@Override
	public List<Milestone> selectAllMilestone() {
		String sql = "select * from job_milestone";
//		getJdbcTemplate().query(sql, new RowMapper<Milestone>() {
//
//			@Override
//			public Milestone mapRow(ResultSet rs, int rowNum) throws SQLException {
//				return new Milestone() {
//					{setName(rs.getString(1));
//					setSeq(rs.getInt(2));
//					setDescript(rs.getString(3));}
//				};
//			}
//		});

		return jdbcTemplate.query(sql, (rs, rowNum) ->
				new Milestone() {
					{setName(rs.getString(1));
					setSeq(rs.getInt(3));
					setDescript(rs.getString(4));};
			});
	}

	@Override
	public int insertMilestone(String jobname, Milestone milestone) {
		String sql = "insert into job_milestone values(?, ?, ?, ?)";
		return jdbcTemplate.update(sql, new Object[] {
				milestone.getName(), 
				jobname,
				milestone.getSeq(),
				milestone.getDescript()
				});
	}

	@Override
	public Milestone selectMilestone(String name) {
		String sql = "select * from job_milestone where name = ?";
		return jdbcTemplate.queryForObject(sql, (rs, rowNum)-> new Milestone() {
			{setName(rs.getString(1)); setSeq(rs.getInt(3)); setDescript(rs.getString(4));}
		}, new Object[] {name});
	}

	@Override
	public int updateMilestone(String name, Milestone milestone) {
		String sql = "update job_milestone set descript = ?, seq = ? where name = ?";
		return jdbcTemplate.update(sql, new Object[] {
				milestone.getDescript(), 
				milestone.getSeq(), 
				milestone.getName()
				}
		);
	}

	@Override
	public int deleteMilestone(String name) {
		String sql = "delete job_milestone where name = ?";
		return jdbcTemplate.update(sql, new Object[] {name});
	}
}
