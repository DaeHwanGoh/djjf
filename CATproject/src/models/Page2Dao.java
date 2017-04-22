package models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class Page2Dao {
	@Autowired 
	MongoTemplate template;
	@Autowired
	APIExam3 api;
	public Map mongoMap(String tg) throws IOException{
		Query query=new Query();
		Criteria criteria=Criteria.where("tg").is(tg);
		query.addCriteria(criteria);
		Map map=template.findOne(query, Map.class, "view2");
		return map;
	}
	public void mongomixedranking(Map map,String tg) throws IOException {
		Query query=new Query();
		Criteria criteria=Criteria.where("tg").is(tg);
		query.addCriteria(criteria);
		Update update=new Update();
		update.set("data", map);
		template.upsert(query, update, "view2");
	}
	public void mongomainranking(String sel,List rank) throws IOException{
		Query query=new Query();
		Criteria criteria=Criteria.where("sel").is(sel);
		query.addCriteria(criteria);
		Update update=new Update();
		update.set("data", rank);
		template.upsert(query, update, "mainViewRanking");
	}
	public List mongoMainList(String sel) throws IOException{
		Query query=new Query();
		Criteria criteria=Criteria.where("sel").is(sel);
		query.addCriteria(criteria);
		Map map=template.findOne(query, Map.class, "mainViewRanking");
		List resultList=(List) map.get("data");
		return resultList;
	}

	public List mixedranking(String sel, String... tg) throws IOException {
		int cnt = 0;
		List resultlist = new ArrayList<>();
		List[] list = new List[tg.length];
		Set set=new LinkedHashSet<>();
		
		if (sel.equals("cnt")) { 
			for (String s : tg) {
				list[cnt++] = ranking(sel, s);
			}
			cnt=0;
			while (set.size() < 20) {
				for (int i = 0; i < tg.length; i++) {
					set.add(list[i].get(cnt));
				}
				cnt++;
			}
			resultlist.addAll(set);

		} else {
			for (String s : tg) { 
//				resultlist.addAll(ranking(sel, s));
				set.addAll(ranking(sel, s));
			}
			resultlist.addAll(set);
			resultlist.sort(new RankComparator());
		}
		// int[] chk=new int[tg.length];
		return resultlist;
	}

	public List ranking(String sel, String tg) throws IOException {
		List list = new ArrayList<>();
		int max = 0;
		String str = "http://movie.naver.com/movie/sdb/rank/rmovie.nhn?sel=";
		str += sel;
		str += "&date=" + getDate(0);
		str += "&tg=" + tg;

		// URL url = new URL(str);
		// InputStream is = url.openStream();
		// InputStreamReader isr = new InputStreamReader(is,
		// Charset.forName("utf-8"));
		// BufferedReader isb = new BufferedReader(isr);
		List<String> searchlist = new ArrayList<>();
		URL url = new URL(str);
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), Charset.forName("euc-kr")));
		String s = "";
		String tmp = "";
		int ccnt = 0;
		aa: while (true) {
			s = br.readLine();
			// System.out.println(s);
			if (s.contains("<!-- 랭킹 리스트 -->")) {
				while (true) {
					s = br.readLine();
					if (s.contains("<td class=\"title")) {
						ccnt++;
						if (ccnt == 1) {
							continue;
						}
						if (ccnt == 40) {
							break aa;
						}
						s = br.readLine();
						s = br.readLine();
						int tn = s.indexOf("title");
						int b = s.indexOf("\"", tn);
						int e = s.indexOf("\"", b + 1);
						// list.add(s.substring(b + 1, e));
						tmp = s.substring(b + 1, e);
						tn = s.indexOf("code");
						b = s.indexOf("=", tn);
						e = s.indexOf("\"", b + 1);
						// list.add(s.substring(b + 1, e));
						tmp += "|" + s.substring(b + 1, e);
						searchlist.add(tmp);

					}
					if (s.contains("<!-- //랭킹 리스트 -->")) {
						break aa;
					}

				}

			}
		}
		for (String t : searchlist) {
			Map tmp1 = api.json(t);
			if (tmp1 == null) {
				continue;
			} else {
				list.add(tmp1);
			}
			// System.out.println(t);
			if (list.size() == 25) {
				break;
			}
		}
		return list;

	}

	public String getDate(int cnt) {
		Calendar day = Calendar.getInstance();
		Calendar tday = (Calendar) day.clone();
		tday.add(Calendar.DATE, -1 + cnt);
		int y = tday.get(Calendar.YEAR);
		int m = tday.get(Calendar.MONTH) + 1;
		int t = tday.get(Calendar.DATE);
		String m1 = "";
		String t1 = "";
		if (t < 10) {
			t1 += "0" + t;
		} else {
			t1 += t;
		}
		if (m < 10) {
			m1 += "0" + m;
		} else {
			m1 += m;
		}
		return "" + y + m1 + t1;
	}
}