package models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureDao {
	@Autowired
	SqlSessionFactory factory;


	//#사진 가져오기. 가장 최신 URL 만 긁어오기
	public String getLastImageURL(String id) {
		SqlSession session = factory.openSession();
		String picURL =  null;
		try {
			List<Map> list = session.selectList("myinfo.findPicture", id);
			//0번째 껏만 넘겨주던가, List를 통째로 넘겨주고, 뽑아서 0번째 껏만 쓰던가

			if(list.size() > 0) { 
				picURL = (String)list.get(0).get("URL");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return picURL;
	}
	
	//# 정보 가져오기. 
	public Map getLastMemberInfo(String id){
		SqlSession session = factory.openSession();
		Map map = new HashMap<>();
		try {
			
			map = session.selectOne("myinfo.findInfo", id);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
				
		return map;  //정보 통째로 map으로 날려주기 
	} 
	
	
	//# 정보 업데이트 하기 . 정보들을 controller에서 map으로 받기 
	//map으로 pass, name, age, gender, email, id 
	public int updateMemberInfo(Map map){ //업데이트 성공하면 int 날려주기 
		int r = 0;
		SqlSession session = factory.openSession();
		try {
			r = session.update("myinfo.updateInfo", map);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return r;  //update 됐으면 0이 아닌 값임
	} 
	
	
	//# 사진 업데이트(얘는 insert) 하기,  정보들을 controller에서 map(id, filename)으로 받기 
	public int updatePictureImg(Map map){ //업데이트 성공하면 int 날려주기 
		int r = 0;
		SqlSession session = factory.openSession();
		try {
			r = session.insert("myinfo.updatePicture", map);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return r;  //insert 됐으면 0이 아닌 값임
	} 	
}

