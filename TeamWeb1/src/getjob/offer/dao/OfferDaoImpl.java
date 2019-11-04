package getjob.offer.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import common.dto.Offer;
import common.model.JobWithSkills;
import common.model.OfferDetail;

@Repository
public class OfferDaoImpl implements OfferDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Offer> selectOfferList() {
		String sql = "select * from " + 
				"(select o.no, o.begin_date, o.end_date, o.title, o.content, o.ci_img, m.name " + 
				"from offer o join member m on o.corporate_id = m.id " + 
				"order by o.no) " + 
				"where rownum <= 200";
		List<Offer> offers = jdbcTemplate.query(sql, new RowMapper<Offer>() {

			@Override
			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Offer offer = new Offer();
				offer.setNo(rs.getInt(1));
				offer.setBegin_date(rs.getDate(2));
				offer.setEnd_date(rs.getDate(3));
				offer.setTitle(rs.getString(4));
				offer.setContent(rs.getString(5));
				offer.setCi_img(rs.getString(6));
				offer.setCorporate_id(rs.getString(7));
				return offer;
			}
		});
		return offers;
	}

	@Override
	public int insertOfferDetail(OfferDetail detail) { 
		String insert_offer = "insert into offer values(?, ?, ?, ?, ?, ?, ?)";
		Offer offer = detail.getOffer();
		int result = jdbcTemplate.update(insert_offer, new Object[] {
				offer.getNo(), 
				offer.getBegin_date(), 
				offer.getEnd_date(),
				offer.getTitle(),
				offer.getContent(),
				offer.getCi_img(),
				offer.getCorporate_id()
				});
		result += insertOfferSpecs(offer.getNo(), detail.getJobs());
		return result;
	}

	@Override
	public OfferDetail selectOneOfferDetail(int number) {
		OfferDetail offerWithSpecs = new OfferDetail();

		String offer_sql = "select o.no, o.begin_date, o.end_date, o.title, o.content, o.ci_img, m.name " + 
				"from offer o join member m on o.corporate_id = m.id " + 
				"where o.no = ?";
		Offer offer = 
			jdbcTemplate.queryForObject(offer_sql, new RowMapper<Offer>() {
				@Override
				public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
					Offer offer = new Offer();
					offer.setNo(rs.getInt(1));
					offer.setBegin_date(rs.getDate(2));
					offer.setEnd_date(rs.getDate(3));
					offer.setTitle(rs.getString(4));
					offer.setContent(rs.getString(5));
					offer.setCi_img(rs.getString(6));
					offer.setCorporate_id(rs.getString(7));
					return offer;
				}
			}, new Object[] {number});
		offerWithSpecs.setOffer(offer);

		String specs_sql = "select offer_no, job_name, skill_name, seq from offer_specs";

		ArrayList<JobWithSkills> jobs = jdbcTemplate.query(specs_sql, new ResultSetExtractor<ArrayList<JobWithSkills>>() {
			ArrayList<JobWithSkills> specs = new ArrayList<>();
			@Override
			public ArrayList<JobWithSkills> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					JobWithSkills jobWithSkills = offerWithSpecs.getJobWithSkills(rs.getString(2));
					jobWithSkills.addSkill(rs.getString(3));
					specs.add(jobWithSkills);
				}
				return specs;
			}
		});
		offerWithSpecs.setJobs(jobs);
		return offerWithSpecs;
	}

	@Override
	public int updateOfferDetail(OfferDetail detail) {
		String update_offer = "update offer set begin_date = ?, end_date = ?, title = ?, content = ?, ci_img = ?, corporate_id = ? "
				+ "where no = ?";
		Offer o = detail.getOffer();
		int res = jdbcTemplate.update(update_offer, new Object[] {o.getBegin_date(), o.getEnd_date(), o.getTitle(),
															o.getContent(), o.getCi_img(), o.getCorporate_id(), o.getNo()});
		int [] result = new int[] {
				deleteOfferSpecs(o.getNo()), 
				insertOfferSpecs(o.getNo(), detail.getJobs())
				};
		
		return Arrays.stream(result).sum();
	}

	@Override
	public int deleteOfferDetail(int number) {
		String delete_offer = "delete from offer where no = ?";
		String delete_offer_specs = "delete from offer_specs where offer_no = ?";
		String delete_member_offer = "delete from member_offer where offer_no = ?";
		String delete_member_recom_offer = "delete from member_recom_offer where offer_no = ?";
		
		boolean deleteAll = false;
		Connection conn;
		String[] sqls = new String[] {delete_offer_specs, delete_member_offer, delete_member_recom_offer, delete_offer};
		int res = Arrays.stream(sqls)
			.mapToInt((sql->jdbcTemplate.update(sql, new Object[] {number})))
			.max().orElse(0);
		if (res > 0) {
			System.out.println("삭제되었습니다.");
		}
		if (deleteAll) {
			System.out.println("공고가 삭제되었습니다.");
		}
		return res;
	}


	@Override
	public int insertOfferSpecs(int number, List<JobWithSkills> jobs) {
		String sql = "insert into offer_specs values(?, ?, ?, ?)";
//		int seq = 0;
//		for (JobWithSkills job : jobs) {
//			for (String skill : job.getSkills()) {
//				++seq;
//				jdbcTemplate.update(sql, new Object[] {number, job.getJobName(), skill, seq});
//			}
//			seq = 0;
//		}
//		for (JobWithSkills job:jobs) {
		int result = 
			jobs.stream().mapToInt(job->{
				int seq = 0;
				return job.getSkills()
					.stream()
					.mapToInt((skill)-> 
						jdbcTemplate.update(sql, 
							new Object[] { number, job.getJobName(), skill, seq })
						).sum();
				}).sum();
//		}
		return result;
	}

	@Override
	public int deleteOfferSpecs(int number) {
		String delete_offer_specs = "delete from offer_specs where offer_no = ?";
		int result = jdbcTemplate.update(delete_offer_specs, new Object[] {number});
		if (result > 0) {
			System.out.println("요구직무가 삭제되었습니다");
		}
		return result;
	}
}
