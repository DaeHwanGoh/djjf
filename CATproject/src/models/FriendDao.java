package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import models.AllDao;

@Service
public class FriendDao {
	@Autowired
	MongoTemplate template;
	@Autowired
	AllDao alldao;

	// public void insertReqF(HttpSession session){
	// template.upsert(query, update, collectionName);
	// }
	public Map fmap(String userid) {
		Query query = new Query();
		Criteria criteria = Criteria.where("userid").is(userid);

		query.addCriteria(criteria);
		Map map = template.findOne(query, Map.class, "friend");
		return map;
	}

	public List infoflist(String userid) {
		// System.out.println("=========================");
		// System.out.println(userid+" |||||| "+fid);
		// System.out.println("=========================");
		// Query query=new Query();
		// Criteria criteria=Criteria.where("userid").is(userid);
		// Update update=new Update();
		// query.addCriteria(criteria);
		// update.push("sendreqflist", fid);
		// template.upsert(query, update, "friend");
		Map map = fmap(userid);
		List flist = (List) map.get("flist");
		System.out.println("dddddddddddddddddddddddddddddddddd");
		System.out.println(flist);
		System.out.println("dddddddddddddddddddddddddddddddddd");
		List resultlist = new ArrayList<>();
		if (flist != null) {
			String sql= "select a.ID,a.GENDER,a.AGE,b.URL from MEMBER a,PICTURE b where a.ID=b.ID and a.ID='%s'"
					+ " order by b.NUM desc";
			for (String fid : (List<String>) flist) {
				resultlist.add(alldao.selectOne(sql, fid));
			}
		}
		return resultlist;
	}

	public void reqf(String userid, String fid) {
		System.out.println("=========================");
		System.out.println(userid + " |||||| " + fid);
		System.out.println("=========================");
		Query query = new Query();
		Criteria criteria = Criteria.where("userid").is(userid);
		Update update = new Update();
		query.addCriteria(criteria);
		update.addToSet("sendreqflist", fid);
		template.upsert(query, update, "friend");

		Query query1 = new Query();
		Criteria criteria1 = Criteria.where("userid").is(fid);
		Update update1 = new Update();
		query1.addCriteria(criteria1);
		update1.addToSet("recieveflist", userid);
		template.upsert(query1, update1, "friend");
	}

	public void accept(String userid, String fid) {
		Query query = new Query(); // 사용자입장 친구리스트 등록, 친구아이디 리시브목록에서 삭제
		Criteria criteria = Criteria.where("userid").is(userid);
		Update update = new Update();
		query.addCriteria(criteria);
		update.pull("recieveflist", fid);
		template.upsert(query, update, "friend");
		update = new Update();
		update.addToSet("flist", fid);
		template.upsert(query, update, "friend");
		// ==============================================
		query = new Query(); // 친구입장 친구리스트 등록, 친구아이디 요청목록에서 삭제
		update = new Update();
		criteria = Criteria.where("userid").is(fid);
		query.addCriteria(criteria);
		update.addToSet("flist", userid);
		template.upsert(query, update, "friend");
		update = new Update();
		update.pull("sendreqflist", userid);
		template.upsert(query, update, "friend");

	}

	public void refuse(String userid, String fid) {
		Query query = new Query(); // 사용자입장 친구아이디 리시브목록에서 삭제
		Criteria criteria = Criteria.where("userid").is(userid);
		Update update = new Update();
		query.addCriteria(criteria);
		update.pull("recieveflist", fid);
		template.upsert(query, update, "friend");
		// ==============================================
		query = new Query(); // 친구입장 친구아이디 요청목록에서 삭제
		update = new Update();
		criteria = Criteria.where("userid").is(fid);
		query.addCriteria(criteria);
		update.pull("sendreqflist", userid);
		template.upsert(query, update, "friend");
	}

	public void insertinit(String userid) {
		Map map = new HashMap<>();
		map.put("userid", userid);
		map.put("flist", new ArrayList<>());
		map.put("sendreqflist", new ArrayList<>());
		map.put("recieveflist", new ArrayList<>());
		template.insert(map, "friend");
	}

}
