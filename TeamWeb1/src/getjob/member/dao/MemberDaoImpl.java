package getjob.member.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import common.dto.Member;
import common.model.MemberInfo;
import common.model.MemberRecomOffers;


@Repository
public class MemberDaoImpl implements MemberDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Member> selectAllMembers() {
		String sql = "select * from member";
		List<Member> members = jdbcTemplate.query(sql, new RowMapper<Member>() {
			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				Member member = new Member();
				member.setId(rs.getString(1));
				member.setName(rs.getString(3));
				member.setType(rs.getString(4));
				member.setAuth(rs.getString(5));
				member.setCertificate(rs.getString(6));
				return member;
			}
		});
		return members;
	}

	// 회원 가입
	@Override
	public int insertMember(Member member) {
		String sql = "insert into member values (?, ?, ?, ?, ?, ?)";
		int result = jdbcTemplate.update(sql, new Object[] {
				member.getId(), 
				member.getPassword(), 
				member.getName(),
				member.getType(), 
				member.getAuth(), 
				member.getCertificate()
		});
		return result;
	}

	// 회원 정보 보기
	@Override
	public Member selectMember(String id) {
		String sql = "select * from member where id = ?";
		Member member = jdbcTemplate.queryForObject(sql, new RowMapper<Member>() {

			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				Member member = new Member();
				member.setId(id);
				member.setPassword(rs.getString(2));
				member.setName(rs.getString(3));
				member.setType(rs.getString(4));
				member.setAuth(rs.getString(5));
				member.setCertificate(rs.getString(6));
				return member;
			}
			
		}, new Object[] {id});
		return member;
	}

	// 회원 정보 수정
	@Override
	public int updateMemberInfo(MemberInfo memberInfo) {
		Member member = memberInfo.getMember();
		updateMember(member);
		
		List<String> jobs = memberInfo.getJobs();
		deleteMemberJobs(member.getId());
		int limit1 = jobs.size();
		for (int i = 0; i < limit1; i++) {
			memberInfo.addJob(jobs.get(i));
		}
		
		List<String> skills = memberInfo.getSkills();
		int limit2 = skills.size();
		deleteMemberSkills(member.getId());
		for (int i = 0; i < limit2; i++) {
			memberInfo.addSkill(skills.get(i));
		}
		
//		ArrayList<Integer> intArr = new ArrayList<Integer>() {
//			{
//				add(updateMember(member));
//				add(deleteMemberJobs(member.getId()));
//				add(deleteMemberSkills(member.getId()));
//			}
//		};
//		return intArr.stream().mapToInt(i->i).sum();
		return 0;
	}

	@Override
	public int deleteMember(String id) {
		String sql = "delete from member where id = ?";
		int result = jdbcTemplate.update(sql, new Object[] {id});
		return result;
	}


	@Override
	public List<String> selectMemberJobs(String id) {
		String sql = "select * from member_job where id = ?";
		List<String> jobs = jdbcTemplate.query(sql, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}}, new Object[] {id});

		return jobs;
	}

	@Override
	public int insertMemberJobs(String id, List<String> jobs) {
		String sql = "insert into member_job values(?, ?)";
		int result = jobs.stream().mapToInt((job)-> jdbcTemplate.update(sql, new Object[] {job, id}))
				.min().orElse(0);
		return result;
	}

	@Override
	public int deleteMemberJobs(String id) {
		String sql = "delete from member_job where id = ?";
		int result = jdbcTemplate.update(sql, new Object[] {id});
		return result;
	}

	@Override
	public List<String> selectMemberSkills(String id) {
		String sql = "select * from member_skill where id = ?";
		ArrayList<String> skills = jdbcTemplate.query(sql, new ResultSetExtractor<ArrayList<String>>() {
			ArrayList<String> skills = new ArrayList<>();
			@Override
			public ArrayList<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					skills.add(rs.getString(2));
				}
				return skills;
			}
		}, new Object[] {id});
		return skills;
	}

	@Override
	public int insertMemberSkills(String id, List<String> skills) {
		String sql = "insert into member_skill where id = ?";
		int result = skills.stream().mapToInt((skill)->jdbcTemplate.update(sql, new Object[] {skill}))
				.min().orElse(0);
		return result;
	}

	@Override
	public int deleteMemberSkills(String id) {
		String sql = "delete from member_skill where id = ?";
		int result = jdbcTemplate.update(sql, new Object[] {id});
		return result;
	}

	@Override
	public MemberRecomOffers selectMemberRecomOffers(String id) {
		String sql = "select * from member_recom_offer where id = ?";
		MemberRecomOffers recomOffers = jdbcTemplate.query(sql, new ResultSetExtractor<MemberRecomOffers>() {
			MemberRecomOffers map = new MemberRecomOffers();
			@Override
			public MemberRecomOffers extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					map.addOffer(rs.getInt(1), rs.getString(3));
				}
				return map;
			}
		});
		return recomOffers;
	}

	@Override
	public int insertMemberRecomOffers(List<Integer> offers, String id, String type) {
		String sql = "insert into member_recom_offer values(?, ?, ?)";
		int result = offers.stream().mapToInt((offer)-> jdbcTemplate.update(sql, new Object[] {offer, id, type}))
						.min().orElse(0);
		return result;
	}

	@Override
	public int deleteMemberRecomOffers(String id) {
		String sql = "delete from member_recom_offer where id = ?";
		int result = jdbcTemplate.update(sql, new Object[] {id});
		return result;
	}

	@Override
	public Member selectMember(String id, String password) {
		String sql = "select * from member where id = ? and password = ?";
		return jdbcTemplate.queryForObject(sql, new RowMapper<Member>() {

			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				Member member = new Member();
				member.setId(id);
				member.setPassword(rs.getString(2));
				return member;
			}
			
		} 
		, new Object[] {id, password});
	}

	@Override
	public MemberInfo selectMemberInfo(String id) {
		MemberInfo memberInfo = new MemberInfo();
		Member member = selectMember(id);
		memberInfo.setMember(member);

		List<String> jobs = selectMemberJobs(id);
		jobs.forEach((job)->memberInfo.addJob(job));
		memberInfo.setJobs(jobs);
		
		List<String> skills = selectMemberSkills(id);
		skills.forEach((skill)->memberInfo.addSkill(skill));
		memberInfo.setSkills(skills);

		return memberInfo;
	}

	@Override
	public int updateMember(Member member) {
		String sql = "update member set password = ?, name = ?, type = ?, auth = ?, certificate = ? where id = ?";
		int result = jdbcTemplate.update(sql, new Object[] {
			member.getPassword(), member.getName(), member.getType(),
			member.getAuth(), member.getCertificate(), member.getId()
		});
		return result;
	}



}
