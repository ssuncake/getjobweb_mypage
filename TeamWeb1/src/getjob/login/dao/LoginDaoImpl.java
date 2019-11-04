package getjob.login.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import common.dto.Member;

@Repository
public class LoginDaoImpl implements LoginDao {

	@Autowired
	private SqlSessionTemplate session;

	public void dao() {
//		String RESOURCE = "getjob/login/mybatis-config.xml";
//		String ID = "choi@naver.com";
//		SqlSessionFactory factory = null;
//		SqlSession session = null;
//		try {
//			InputStream config = Resources.getResourceAsStream(RESOURCE);
//			factory = new SqlSessionFactoryBuilder().build(config);
//			session = factory.openSession();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		String FIND_ID = session.selectOne("LoginMapper.selectMemberForIdCheck", ID);
//		System.out.println(FIND_ID);
	}

	@Override
	public Member selectMemberWithId(Member member) {
		Member result = session.selectOne("LoginMapper.selectMemberForIdCheck", member);
		return result;
	}

	@Override
	public Member selectMemberWithPassword(Member member) {
		Member result = session.selectOne("LoginMapper.selectMemberForPasswordCheck", member);
		return result;
	}

}
