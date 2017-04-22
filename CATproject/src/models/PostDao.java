package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import models.AllDao;

@Service
public class PostDao {

	@Autowired
	FriendDao fdao;

	@Autowired
	PictureDao pdao;

	@Autowired
	MongoTemplate template;

	@Autowired
	AllDao alldao;

	// 게시글 저장-------------------------------------------------------------
	public void insertMongo(Map resultmap) throws Exception {
		template.insert(resultmap, "post");

	}

	// write데이처 저장
	public void insertperson(String date, String id) throws Exception {

		// post_id값 추출
		Query query = new Query();
		Criteria c1 = Criteria.where("date").is(date).and("userid").is(id);
		query.addCriteria(c1);
		System.out.println(query);
		List<Map> li = template.find(query, Map.class, "post");

		System.out.println(id + "가 쓴 포스팅수==" + li.size());

		// 개인 컬렉션에 write정보 추가
		for (Map<String, Object> e : li) {
			System.out.println(e.toString());
			Map<String, Object> personMap = new HashMap<>();
			personMap.put("post_id", e.get("_id"));
			personMap.put("userid", e.get("userid"));
			personMap.put("date", e.get("date"));
			personMap.put("mod", "write");
			template.insert(personMap, "personalPost");
		}
	}

	// 내 게시글 불러오기
	public List<Map> mypost(String userid) {

		// 개인컬렉션에서 포스트 참조_id가져오기
		Query query = new Query();
		Criteria c1 = Criteria.where("userid").is(userid);
		query.addCriteria(c1);
		List<Map> mypostlist = template.find(query, Map.class, "post");
		System.out.println("내가쓴 글 수=" + mypostlist.size());
		mypostlist.sort(new MyNewsfeedComparator());
		Map fmap = fdao.fmap(userid);
		List<String> flist = (List) fmap.get("flist");

		// _id값으로 post찾기.
		// List<Map> my_postlist = new ArrayList<>();
		// ObjectId post_id = (ObjectId) map.get("post_id");
		// Query query2 = new Query();
		// Criteria c2 = new Criteria("_id").is(post_id);
		// query2.addCriteria(c2);
		// my_postlist.add((Map) template.findOne(query2, Map.class, "post"));
		for (Map<String, Object> map : mypostlist) {
			boolean like = false;
			boolean likeme = false;
			if (((List<String>) map.get("likeuser")).contains(userid)) {
				likeme = true;
			}
			for (String tmp : ((List<String>) map.get("likeuser"))) {
				if (flist.contains(tmp)) {
					like = true;
					if ((((List) map.get("likeuser")).size() - 1) > 0) {
						map.put("likeString",
								tmp + "님 외 " + (((List) map.get("likeuser")).size() - 1) + "명이 좋아합니다.");
					} else {
						map.put("likeString", tmp + "님이 좋아합니다.");
					}
					break;
				}
			}
			if (!like) {
				String ss = "";
				if(likeme){
					if ((((List) map.get("likeuser")).size() - 1) > 0) {
						ss=userid + "님 외 " + (((List) map.get("likeuser")).size() - 1) + "명이 좋아합니다.";
					} else {
						ss=userid + "님이 좋아합니다.";
					}
				} else {
					ss = ((List) map.get("likeuser")).size() + "명이 좋아합니다.";
				}
				map.put("likeString", ss);
			}
			if(((List<String>) map.get("likeuser")).size()==0){
				map.put("likeString", "");
			}
			if (likeme) {
				map.put("likebtn",
						"<i class='glyphicon glyphicon-thumbs-up' style='font-size: 25px; color: blue;'></i>");
			} else {
				map.put("likebtn",
						"<i class='glyphicon glyphicon-thumbs-up' style='font-size: 25px; color: black;'></i>");
			}
			
			for (Map<String, String> tmp : (List<Map>) map.get("commentlist")) {
				String imgUrl = pdao.getLastImageURL(tmp.get("id"));
				tmp.put("pictureUrl", imgUrl);
			}
		}
		return mypostlist;
	}

	public String likeflist(ObjectId post_id, String userid) {
		Map fmap = fdao.fmap(userid);
		List<String> flist = (List) fmap.get("flist");
		Query query = new Query();
		Criteria c1 = Criteria.where("_id").is(post_id);
		query.addCriteria(c1);
		Map postmap = template.findOne(query, Map.class, "post");
		List<String> postLikeList = (List) postmap.get("likeuser");
		List<Map> _idlist = template.find(query, Map.class, "personalPost");
		// for(String s )
		return "";
	}

	// 개인계정 like데이터
	// 저장-------------------------------------------------------------
	public void likeperson(Map datamap, String userid) {
		template.insert(datamap, "personalPost");

		Query query = new Query();
		Criteria c1 = Criteria.where("_id").is(datamap.get("post_id"));
		query.addCriteria(c1);
		Update update = new Update();
		update.addToSet("likeuser", (String) datamap.get("userid"));
		template.updateFirst(query, update, "post");
	}

	public void likeremove(ObjectId post_id, String userid) {
		Query query = new Query();
		Criteria c1 = Criteria.where("post_id").is(post_id);
		Criteria c2 = Criteria.where("userid").is(userid);
		query.addCriteria(c1);
		query.addCriteria(c2);
		template.remove(query, "personalPost");

		query = new Query();
		c1 = Criteria.where("_id").is(post_id);
		query.addCriteria(c1);
		Update update = new Update();
		update.pull("likeuser", userid);
		template.upsert(query, update, "post");
	}

	public boolean userLikeBoolean(String userid, ObjectId _id) {
		boolean likeLog = false;
		Query query = new Query();
		Criteria c1 = Criteria.where("_id").is(_id);
		query.addCriteria(c1);
		Map tmp = template.findOne(query, Map.class, "post");
		if (((List<String>) tmp.get("likeuser")).contains(userid)) {
			likeLog = true;
		}
		return likeLog;
	}

	// 코멘트 등록 및
	// 불러오기-------------------------------------------------------------
	public List comentData(Map commentmap, ObjectId post_id) {

		// post컬렉션 등록
		Query query = new Query();
		Criteria c1 = Criteria.where("_id").is(post_id);
		query.addCriteria(c1);
		Update update = new Update();
		update.push("commentuser", commentmap.get("id"));
		update.push("commentlist", commentmap);
		template.updateMulti(query, update, "post");

		// 개인컬렉션 등록
		Query query2 = new Query();
		Criteria c2 = Criteria.where("_id").is(post_id).and("commentuser").is((String) commentmap.get("id"));
		query2.addCriteria(c2);
		List<Map> li = template.find(query2, Map.class, "post");

		for (Map<String, Object> e : li) {
			Map permap = new HashMap<>();
			permap.put("post_id", post_id); // 어디다 댓글을 달았는지
			permap.put("userid", (String) commentmap.get("id")); // 그 게시글작성자 id
			permap.put("date", (String) commentmap.get("date")); // 내가 코멘트 작성한
																	// 시점
			permap.put("mod", "comment");

			template.insert(permap, "personalPost");

		}

		// 등록한 코멘트 바로 불러오기
		Query query3 = new Query();
		Criteria c3 = new Criteria("_id");
		c3.is(post_id);
		query3.addCriteria(c3);
		Map<String, Object> map2 = template.findOne(query3, Map.class, "post");

		List<Map> list = (List) map2.get("commentlist");
		return list;

	}

	// 내 친구가 좋아요또는 댓글단 게시글 가져오기
	@SuppressWarnings("unchecked")
	public List getNewsFeed(String userId) {
		// ObjectId objectid=new ObjectId("58dba4def5a530168c0174a7");
		// System.out.println(objectid);
		// System.out.println(objectid.toString());
		// System.out.println(objectid.toHexString());
		Map fmap = fdao.fmap(userId);
		List flist = (List) fmap.get("flist");
		System.out.println(flist);
		flist.add(userId);
		Map mapper = new HashMap<>();
		for (String fid : (List<String>) flist) {
			Criteria c1 = Criteria.where("userid").is(fid);
			Query q = new Query();
			q.addCriteria(c1);
			List<Map> tmp = template.find(q, Map.class, "personalPost");
			// System.out.println(tmp);
			for (Map post : tmp) {
				ObjectId post_id = (ObjectId) post.get("post_id");
				if (!mapper.containsKey(post_id)) {
					List aa = new ArrayList<>();
					aa.add(post);
					mapper.put(post_id, aa);
				} else {
					((List) mapper.get(post_id)).add(post);
				}
			}
		}
		System.out.println(mapper);
		List resultList = new ArrayList<>();
		List<Entry> entrylist = new ArrayList<>();
		entrylist.addAll(mapper.entrySet());
		for (Entry e : entrylist) { // mapper형태로 저장된 값(List<Map>)을 date순으로 정렬시킨다
			((List) e.getValue()).sort(new NewsfeedComparator());
		}
		entrylist.sort(new EntrylistComparator());
		for (Entry e : entrylist) {
			// ((Map) ((List) e.getValue()).get(0)).get("userid");
			String firstuserid = (String) ((Map) ((List) e.getValue()).get(0)).get("userid");
			String mod = (String) ((Map) ((List) e.getValue()).get(0)).get("mod");
			int cnt = 0;
			for (Map tmp : (List<Map>) e.getValue()) {
				if (cnt == 0) {
					continue;
				} else if (cnt == 2) {
					break;
				} else {
					if (((String) tmp.get("mod")).equals(mod)) {
						firstuserid += ", " + tmp.get("userid");
					}
				}
				cnt++;
			}
			switch (mod) {
			case "comment":
				mod = " 님이 코멘트를 남겼습니다.";
				break;
			case "write":
				mod = " 님이 글을 남겼습니다.";
				break;
			case "like":
				mod = " 님이 이 포스트를 좋아합니다.";
				break;
			default:
				System.out.println("해당 형식에 안맞음");
				break;
			}
			String alarm = firstuserid + mod;
			Query query = new Query();
			Criteria criteria = Criteria.where("_id").is(e.getKey());
			query.addCriteria(criteria);
			Map resultmap = template.findOne(query, Map.class, "post");
			resultmap.put("alarm", alarm);
			String writerid = (String) resultmap.get("userid");
			String pictureUrl = pdao.getLastImageURL(writerid);
			resultmap.put("pictureUrl", pictureUrl);
			
			
			boolean like = false;
			boolean likeme = false;
			if (((List<String>) resultmap.get("likeuser")).contains(userId)) {
				likeme = true;
			}
			for (String tmp : ((List<String>) resultmap.get("likeuser"))) {
				if (flist.contains(tmp)) {
					like = true;
					if ((((List) resultmap.get("likeuser")).size() - 1) > 0) {
						resultmap.put("likeString",
								tmp + "님 외 " + (((List) resultmap.get("likeuser")).size() - 1) + "명이 좋아합니다.");
					} else {
						resultmap.put("likeString", tmp + "님이 좋아합니다.");
					}
					break;
				}
			}
			if (!like) {
				String ss = "";
				if(likeme){
					if ((((List) resultmap.get("likeuser")).size() - 1) > 0) {
						ss=userId + "님 외 " + (((List) resultmap.get("likeuser")).size() - 1) + "명이 좋아합니다.";
					} else {
						ss=userId + "님이 좋아합니다.";
					}
				} else {
					ss = ((List) resultmap.get("likeuser")).size() + "명이 좋아합니다.";
				}
				resultmap.put("likeString", ss);
			}
			if(((List<String>) resultmap.get("likeuser")).size()==0){
				resultmap.put("likeString", "");
			}
			if (likeme) {
				resultmap.put("likebtn",
						"<i class='glyphicon glyphicon-thumbs-up' style='font-size: 25px; color: blue;'></i>");
			} else {
				resultmap.put("likebtn",
						"<i class='glyphicon glyphicon-thumbs-up' style='font-size: 25px; color: black;'></i>");
			}
			
			
			for (Map<String, String> tmp : (List<Map>) resultmap.get("commentlist")) {
				String imgUrl = pdao.getLastImageURL(tmp.get("id"));
				tmp.put("pictureUrl", imgUrl);
			}
			resultList.add(resultmap);
		}
		return resultList;
	}

	public List alarmList(String userId) {
		// ObjectId objectid=new ObjectId("58dba4def5a530168c0174a7");
		// System.out.println(objectid);
		// System.out.println(objectid.toString());
		// System.out.println(objectid.toHexString());
		Map fmap = fdao.fmap(userId);
		List flist = (List) fmap.get("flist");
		Map mapper = new HashMap<>();
		for (String fid : (List<String>) flist) {
			Criteria c1 = Criteria.where("userid").is(fid);
			Query q = new Query();
			q.addCriteria(c1);
			List<Map> tmp = template.find(q, Map.class, "personalPost");
			// System.out.println(tmp);
			for (Map post : tmp) {
				ObjectId post_id = (ObjectId) post.get("post_id");
				if (!mapper.containsKey(post_id)) {
					List aa = new ArrayList<>();
					aa.add(post);
					mapper.put(post_id, aa);
				} else {
					((List) mapper.get(post_id)).add(post);
				}
			}
		}
		System.out.println(mapper);
		List resultList = new ArrayList<>();
		List<Entry> entrylist = new ArrayList<>();
		entrylist.addAll(mapper.entrySet());
		for (Entry e : entrylist) { // mapper형태로 저장된 값(List<Map>)을 date순으로 정렬시킨다
			((List) e.getValue()).sort(new MyNewsfeedComparator());
		}
		entrylist.sort(new EntrylistComparator());
		for (Entry e : entrylist) {
			// ((Map) ((List) e.getValue()).get(0)).get("userid");
			String firstuserid = (String) ((Map) ((List) e.getValue()).get(0)).get("userid");
			String mod = (String) ((Map) ((List) e.getValue()).get(0)).get("mod");
			// int cnt = 0;
			// for (Map tmp : (List<Map>) e.getValue()) {
			// if (cnt == 0) {
			// continue;
			// } else if (cnt == 1) {
			// break;
			// } else {
			// if (((String) tmp.get("mod")).equals(mod)) {
			// firstuserid += ", " + tmp.get("userid");
			// cnt++;
			// }
			// }
			// }
			switch (mod) {
			case "comment":
				mod = "님의 글에 코멘트를 남겼습니다.";
				break;
			case "write":
				mod = "님이 글을 남겼습니다.";
				break;
			case "like":
				mod = "님의 포스트를 좋아합니다.";
				break;
			default:
				System.out.println("해당 형식에 안맞음");
				break;
			}
			Query query = new Query();
			Criteria criteria = Criteria.where("_id").is(e.getKey());
			query.addCriteria(criteria);
			Map tmpmap = template.findOne(query, Map.class, "post");

			// if() 로그아웃 - 로그인 한 시간동안 포함되는지 boolean반환하는 함수 넣어놓기.
			if (!booleanUserLogRecord(userId, (String) tmpmap.get("date"))) {
				return resultList;
			}

			String writerid = (String) tmpmap.get("userid");
			String alarm = "";
			if (mod.contains("님이 글을 남겼습니다.")) {
				alarm = firstuserid + mod;
			} else {
				alarm = firstuserid + "님이 " + writerid + mod;
			}
			String pictureUrl = pdao.getLastImageURL(firstuserid);
			Map resultmap = new HashMap<>();
			resultmap.put("alarm", alarm); // json부분
			resultmap.put("fid", firstuserid);
			resultmap.put("pictureUrl", pictureUrl);
			resultList.add(resultmap);
		}
		return resultList;
	}

	public boolean booleanUserLogRecord(String userId, String date) {
		Query query = new Query();
		Criteria criteria = Criteria.where("userid").is(userId);
		query.addCriteria(criteria);
		Map tmpmap = template.findOne(query, Map.class, "personalLogRecord");
		String loginLog = (String) tmpmap.get("loginLog");
		String logoutLog = (String) tmpmap.get("logoutLog");
		boolean result = (loginLog.compareTo(date) > 0 && logoutLog.compareTo(date) < 0);
		return result;
	}

}
