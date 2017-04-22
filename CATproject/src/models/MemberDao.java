package models;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MemberDao {
	@Autowired
	SqlSessionFactory factory;

	
	public int addMember(Map map) {
	      SqlSession session = factory.openSession();
	      Map p=new HashMap<>();
	      	p.put("id",map.get("id"));
	      	p.put("filename", "/picture/profile.png");
	      int r = 0;
	      try {
	         r = session.insert("member.createOne", map);
	         session.insert("myinfo.updatePicture", p);
	         session.commit();
	      } catch (Exception e) {
	         e.printStackTrace();
	      } finally {
	         session.close();
	      }
	      return r;
	   }	
	
	public boolean checkID(String id) {
		boolean flag = false;
		
		SqlSession session = factory.openSession();
		try {
			// select mapper의 resultType은 데이터 한줄을 바꿀 객체 
			// select 를 사용할때 한줄이 나오면 selectOne ==> resultType
			//                    여러줄이 나오면 selectList ==> List<resultType> 
			int r = session.selectOne("member.checkCountID", id);
			if(r==1)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return flag;
	}
	
	public boolean checkMember(Map map) {
		boolean flag = false;
		SqlSession session = factory.openSession();
		try {
			// select mapper의 resultType은 데이터 한줄을 바꿀 객체 
			// select 를 사용할때 한줄이 나오면 selectOne ==> resultType
			//                    여러줄이 나오면 selectList ==> List<resultType> 
			int r = session.selectOne("member.checkCountOne", map);
			if(r==1)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return flag;
	}
	
	//받는이 존재 하는지 
	public boolean existOne(String target) {
		boolean rst = false;
		try {
			SqlSession sql = factory.openSession();
			HashMap map = sql.selectOne("member.findTarget", target);
			if(map != null)
				rst = !rst;
			sql.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rst;
	}	
	
	
	//# 정보 가져오기. 
	public Map getMemberInfo(String target){
		Map map = new HashMap<>();
		SqlSession session = factory.openSession();
		try {
			map = session.selectOne("member.findTarget", target);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return map;  //정보 통째로 map으로 날려주기 
	} 
	
	//#회원 삭제하기 
	public boolean deleteMember(Map map) {
		boolean flag = false;
		SqlSession session = factory.openSession();
		try {
			// select mapper의 resultType은 데이터 한줄을 바꿀 객체 
			// select 를 사용할때 한줄이 나오면 selectOne ==> resultType
			//                    여러줄이 나오면 selectList ==> List<resultType> 
			int r = session.delete("member.deleteMember", map);
			
			if(r!=0)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return flag;
	}	
	
}
