package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class ReviewDao {
	@Autowired
	MongoTemplate template;
	
	public void createOne(Map map) {
		//System.out.println("모델에 넘어온 데이터"+map.toString());
		template.insert(map,"review");
	}

	public List readAll(String code) {
		List<Map> list = new ArrayList<>();
		Query query = new Query();
		//Criteria c1 = new Criteria("code");
		Criteria c1 = Criteria.where("code").is(code);
		query.addCriteria(c1);
		Sort c2 = new Sort(Sort.Direction.DESC,"date");
		query.with(c2);
			
			//System.out.println("query======"+query);
			//System.out.println("========================");
			list = template.find(query, Map.class, "review");
			//System.out.println("1:데이터추출성공=="+list.size());
			for (Map<String,Object> e : list) {
				//System.out.println(e.toString());
			}
			
			return list;
	}
	
	
	
	
	/*
	public void postData() {
		
		String  id= "namnami";
		
		Query query = new Query();
			Criteria c1 = new Criteria("id");
				c1.is(id);
			query.addCriteria(c1);
			System.out.println(query); 
		System.out.println("여기========================");
		List<Map> li = template.find(query,Map.class,"post");
		System.out.println("1:데이터추출성공=="+li.size());
		for (Map<String,Object> e : li) {
			System.out.println(e.toString());
			
		}
	}
	
	
	
	
	public int getArticlesCount() {
		int cnt = 0;
		try {
			Connection conn = DBUtil.open();
			String sql = "select count(*) as cnt from freeboard";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next(); // 무조건 나오니까 boolean 값 체크 불필요. 커서만 내려두고.
			cnt = rs.getInt("cnt");
			conn.close();
			for(Map w : list){
				cnt +=1;
			}
			map.put()
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	*/
	
	
 

}
