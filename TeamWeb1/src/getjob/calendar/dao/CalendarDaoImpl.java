package getjob.calendar.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import common.dto.Code;
import common.dto.Offer;
import common.dto.Test;
import common.model.TestSchedule;

@Repository
public class CalendarDaoImpl implements CalendarDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	// 자격증 목록 보기

	@Override
	public List<Code> selectAllCertificates() {
		String sql = "select codeno, codename from code where attribute='certificate'";
		return jdbcTemplate.query(sql, new RowMapper<Code>() {
			public Code mapRow(ResultSet rs, int rowNum) throws SQLException {
				Code code = new Code();
				code.setCodeno(rs.getInt("codeno"));
				code.setCodename(rs.getString("codename"));
				return code;
			}
		});
	}

	// 자격시험일정 불러오기
	@Override
	public List<Test> selectAllTestSchedules(String cert_name) {
		String sql = "select * from TEST where cert_name = ?";
		return jdbcTemplate.query(sql, new RowMapper<Test>() {
			public Test mapRow(ResultSet rs, int rowNum) throws SQLException {
				Test test = new Test(rs.getString("cert_name"), rs.getDate("begin_date"), rs.getDate("end_date"),
						rs.getString("section"));
				return (Test) test;
			}
		}, cert_name);
	}

	// 선택한 자격시험일정 불러오기
	@Override
	public List<Test> selectTestSchedule(int codeno) {
		String sql = "select cert_name, begin_date, end_date, section from test where codeno = ?";
		return jdbcTemplate.query(sql, new RowMapper<Test>() {
			@Override
			public common.dto.Test mapRow(ResultSet rs, int rowNum) throws SQLException {
				Test test = new common.dto.Test(rs.getString("cert_name"),rs.getDate("begin_date"),rs.getDate("end_date"),rs.getString("section"));
				return test;
			}
		}, codeno);
	}

	// 회원별-자격증 추가
	@Override
	public int insertMemberTestSchedule(List<TestSchedule> schedules) {
		String sql = "insert into MEMBER_TEST values(?,?,?,?)";
		int res = schedules.stream().mapToInt((schedule)->jdbcTemplate.update(sql, new Object[] { 
				schedule.getId(), 
				schedule.getCert_name(), 
				schedule.getBegin_date(), 
				schedule.getEnd_date()
				})).sum();
		return res;
	}

	// 회원별-자격시험 일정 보기
	@Override
	public List<Test> selectAllMemberTestSchedules(String id) {
		String sql = "SELECT cert_name, begin_date, end_date  FROM MEMBER_TEST   where id=?";
		return jdbcTemplate.query(sql, new RowMapper<Test>() {
			public Test mapRow(ResultSet rs, int rowNum) throws SQLException {
				Test test = new Test();
				test.setCert_name(rs.getString("cert_name"));
				test.setBegin_date(rs.getDate("begin_date"));
				test.setEnd_date(rs.getDate("end_date"));
				return (Test) test;
			}
		}, id);
	}

	// 회원별-자격시험 일정 삭제
	@Override
	public int deleteMemberTestSchedule(List<TestSchedule> schedules) {
		String sql = "delete from MEMBER_TEST where cert_name=? and id = ? and begin_date = ?";
		int res = schedules.stream().mapToInt((schedule)->jdbcTemplate.update(sql, new Object[] { 
				schedule.getCert_name(), 
				schedule.getId(), 
				schedule.getBegin_date()
				})).sum();
		return res;
	}

	// 전체 공고일정 불러오기
	@Override
	public List<Offer> selectAllOfferSchedules() {
		String sql = "select no,begin_date, end_date, title from OFFER";
		return jdbcTemplate.query(sql, new RowMapper<Offer>() {
			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Offer ov = new Offer();
				ov.setBegin_date(rs.getDate("begin_date"));
				ov.setEnd_date(rs.getDate("end_date"));
				ov.setTitle(rs.getString("title"));
				ov.setNo(rs.getInt("no"));
				return ov;
			}
		});
	}

	// 회원별-공고 추가
	@Override
	public int insertOfferSchedule(List<Integer> offers, String id) {
		String sql = "insert into MEMBER_OFFER values(?,?)";
		int result 
			= offers.stream()
				.mapToInt((offer)-> jdbcTemplate.update(sql, new Object[] { offer, id }))
				.sum();
		return result;
	}

	// 회원별-공고 일정 보기
	@Override
	public List<Offer> selectAllMemberOfferSchedules(String id) {
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
		simpleJdbcCall.withProcedureName("MY_OFFER");
		SqlParameterSource in = new MapSqlParameterSource().addValue("m_id", id);
		simpleJdbcCall.returningResultSet("RES_CURSOR", new RowMapper<Offer>() {
			@Override
			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Offer offer = new Offer();
				offer.setNo(rs.getInt(1));
				offer.setBegin_date(rs.getDate(2)); 
				offer.setEnd_date(rs.getDate(3));
				offer.setTitle(rs.getString(4));
				return offer;
			}
		});
		List<Offer> list = (List<Offer>) simpleJdbcCall.execute(in).get("RES_CURSOR");
		return list;
	}

	// 회원별-공고삭제
	@Override
	public int deleteMemberOfferSchedule(List<Integer> offers, String id) {
		String sql = "delete from MEMBER_OFFER where offer_no=? and id = ?";
		int result = offers.stream()
				.mapToInt((offer)-> jdbcTemplate.update(sql, new Object[] { offer, id }))
				.sum();
		return result;
	}


}