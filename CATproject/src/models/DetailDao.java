package models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailDao {
	@Autowired
	SqlSessionFactory factory;
	
	public String getThumnailURL(String code) throws IOException{
		Document doc = Jsoup.connect("http://movie.naver.com/movie/bi/mi/basic.nhn?code="+code).get();
		Elements metas = doc.getElementsByClass("mv_info_area");
		String mpos;
		try{
			Element e = metas.get(0);
			mpos = e.getElementsByClass("poster").get(0).getElementsByTag("a").get(0).getElementsByTag("img")
					.get(0).attr("src");
			System.out.println(mpos);
			return mpos;
			}catch(Exception e1){
				mpos="http://ph.sisain.co.kr/news/photo/201211/14757_29835_397.jpg";
			}finally{
			}
		return mpos;
	}

	public Map detailOut(String code) {
//		String[] tmp=code.split("=");
		Map m = new HashMap();
		try {
			Document doc = Jsoup.connect("http://movie.naver.com/movie/bi/mi/basic.nhn?code="+code).get();
			
			// Document doc =
			// Jsoup.connect("http://movie.naver.com//movie/bi/mi/detail.nhn?code=136872").get();
			// 윗부분 정보
			Elements metas = doc.getElementsByClass("mv_info_area");
			Element e = metas.get(0);

			// 아랫부분 정보
			Elements metas2 = doc.getElementsByClass("section_group section_group_frst");
			Element e2 = metas2.get(0);

			// 포토?
			Document doc2 = Jsoup.connect("http://movie.naver.com/movie/bi/mi/photo.nhn?code="+code+"#tab").get();
			Elements metas3 = doc2.getElementsByClass("photo");
			Element e3 = metas3.get(0);

			// 동영상?

			// System.out.println(e2);

			// System.out.println(e);

			// ==================================================================================================================================
			// 윗부분!!!!!!!!!!
			// 제목(한글)
			String tn = e.getElementsByClass("h_movie").get(0).getElementsByTag("a").get(0).html();
			//System.out.println("영화제목(한글) : " + tn);
			m.put("tn", tn);
			// 제목(영문)
			String tn2 = e.getElementsByClass("h_movie2").get(0).getElementsByTag("strong").get(0).html();
			//System.out.println("영화제목(영문) : " + tn2);
			m.put("tn2", tn2);
			// 메인포스터 주소
			try{
			String mpos = e.getElementsByClass("poster").get(0).getElementsByTag("a").get(0).getElementsByTag("img")
					.get(0).attr("src");
			//System.out.println("메인포스터 : " + mpos);
			m.put("mpos", mpos);
			
			}catch(Exception e1){
			System.out.println("메인포스터 : 결과없음" );
			}
			
			
			Map tmpp=new LinkedHashMap();
			// 영화개요 - 통합
			for (int i = 0; i < e.getElementsByClass("info_spec").get(0).getElementsByTag("em").size(); i++) {
				String em = e.getElementsByClass("info_spec").get(0).getElementsByTag("em").get(i).html();
				List lii=new ArrayList<>();
				if (!em.startsWith("도움말") & !em.startsWith("닫기")) {
					//System.out.println("테이블 : " + em);
					if(em.equals("흥행")) {
						String a = e.getElementsByClass("info_spec").get(0).getElementsByClass("count").get(0).html();
						//System.out.print("객체 : " + a + "\n");
						tmpp.put(em, a);
						continue;
					}
				}
				for (int j = 0; j < e.getElementsByClass("info_spec").get(0).getElementsByTag("p").size(); j++) {
					if (i == j) {
						for (int k = 0; k < e.getElementsByClass("info_spec").get(0).getElementsByTag("p").get(j)
								.getElementsByTag("a").size(); k++) {

							String a = e.getElementsByClass("info_spec").get(0).getElementsByTag("p").get(j)
									.getElementsByTag("a").get(k).html();
							if (!em.startsWith("도움말") & !em.startsWith("닫기") & !a.startsWith("<em>")) {
//								if (em.equals("흥행")) {
//									System.out.println("===========");
//									a = e.getElementsByClass("info_spec").get(0).getElementsByClass("count").get(0)
//											.html();
//								}
								//System.out.print("객체 : " + a + "\n");
								lii.add(a);
								tmpp.put(em, lii);
							}
						}
					}
				}
				
			}
			m.put("all", tmpp);
			
			
			
			
			
/*
			// 개요 - 장르
			try{
			List li = new ArrayList<>();
			for (int i = 0; i < e.getElementsByClass("info_spec").get(0).getElementsByTag("dd").get(0)
					.getElementsByTag("p").get(0).getElementsByTag("span").get(0).getElementsByTag("a").size(); i++) {
				String gen = e.getElementsByClass("info_spec").get(0).getElementsByTag("dd").get(0)
						.getElementsByTag("p").get(0).getElementsByTag("span").get(0).getElementsByTag("a").get(i)
						.html();
				System.out.println("장르 : " + gen);
				li.add(gen);
			}
			m.put("gens", li);
			}catch(Exception e4){
				System.out.println("장르 : 결과없음");
			}
			
			
			
			
			// 영화 개요 - 국가
			try{
			String na = e.getElementsByClass("info_spec").get(0).getElementsByTag("dd").get(0).getElementsByTag("p")
					.get(0).getElementsByTag("span").get(1).getElementsByTag("a").get(0).html();
			System.out.println("영화개요 - 국가 : " + na);
			m.put("na", na);
			}catch(Exception e5){
				System.out.println("영화 개요 - 국가 : 결과없음");
			}
			
			// 영화 개요 - 상영시간
			try{
			String time = e.getElementsByClass("info_spec").get(0).getElementsByTag("dd").get(0).getElementsByTag("p")
					.get(0).getElementsByTag("span").get(2).html();
			System.out.println("영화 개요 - 상영시간 : " + time);
			m.put("time", time);
			}catch(Exception e5){
				System.out.println("영화 개요 - 상영시간 : 결과없음");
			}
			
			
			
			// 영화 개요 - 개봉 일자
			String year = e.getElementsByClass("info_spec").get(0).getElementsByTag("dd").get(0).getElementsByTag("p")
					.get(0).getElementsByTag("span").get(3).getElementsByTag("a").get(0).html();
			String md = e.getElementsByClass("info_spec").get(0).getElementsByTag("dd").get(0).getElementsByTag("p")
					.get(0).getElementsByTag("span").get(3).getElementsByTag("a").get(1).html();
			String date = year + md;
			System.out.println("영화 개요 - 개봉 일자 : " + date);
			m.put("date", date);

			// 영화 감독
			String dir = e.getElementsByClass("info_spec").get(0).getElementsByTag("dd").get(1).getElementsByTag("a")
					.get(0).html();
			System.out.println("영화감독 : " + dir);
			m.put("dir", dir);

			// 배우
			List li2 = new ArrayList<>();
			for (int i = 0; i < e.getElementsByClass("info_spec").get(0).getElementsByTag("dd").get(2)
					.getElementsByTag("a").size() - 1; i++) {
				String act = e.getElementsByClass("info_spec").get(0).getElementsByTag("dd").get(2)
						.getElementsByTag("a").get(i).html();
				System.out.println("배우 : " + act);
				li2.add(act);
			}
			m.put("acts", li2);

			// 등급 (국내)
			String grade = e.getElementsByClass("info_spec").get(0).getElementsByTag("dd").get(3).getElementsByTag("a")
					.get(0).html();
			System.out.println("등급 (국내) : " + grade);
			m.put("grade", grade);

			// 누적 관객
			String acc = e.getElementsByClass("info_spec").get(0).getElementsByClass("step9_cont").get(0)
					.getElementsByTag("p").get(1).html();
			System.out.println("누적관객 : " + acc);
			m.put("acc", acc);

			// System.out.println("==============");
			// System.out.println(e.getElementsByClass("info_spec").get(0));
			// String f =
			// e.getElementsByClass("info_spec").get(0).getElementsByClass("count").get(0).html();
			// System.out.println("=============");
*/
			
			
			
			
			
			
			
			// ==================================================================================================================================

			// 아랫부분!!!!!!!!!!

			// 메인 스토리
			try{
			String msto = e2.getElementsByClass("h_tx_story").get(0).html();
			//System.out.println("메인스토리 : " + msto);
			m.put("mainstory", msto);
			}catch(Exception e1){
				System.out.println("메인스토리 : 정보없음");
			}
			// 세부 스토리
			
			try{
			String sto = e2.getElementsByClass("con_tx").get(0).html();
			//System.out.println("세부스토리 : " + sto);
			m.put("detailstory", sto);
			}catch(Exception e1){
				System.out.println("세부스토리 : 정보없음");
			}

			// 배우/제작진 - 사진/이름/캐스팅/역할
			try{
			List li4 = new ArrayList<>();
			for (int i = 0; i < e2.getElementsByClass("people").get(0).getElementsByTag("img").size(); i++) {
				Map obj = new HashMap<>();
				String peo1 = e2.getElementsByClass("people").get(0).getElementsByTag("img").get(i).attr("src");
				String peo2 = e2.getElementsByClass("people").get(0).getElementsByClass("tx_people").get(i).html();
				String peo3 = e2.getElementsByClass("people").get(0).getElementsByTag("dt").get(i).html();
				//System.out.println("사진 : " + peo1);
				//System.out.println("이름 : " + peo2);
				//System.out.println("출연정보 : " + peo3);
				obj.put("photo", peo1);
				obj.put("name", peo2);
				obj.put("part", peo3);
				if (i >= 1) {
					String peo4 = e2.getElementsByClass("people").get(0).getElementsByTag("dd").get(i - 1).html();
					//System.out.println("역할 : " + peo4);
					obj.put("role", peo4);
				}
				li4.add(obj);
			}
			m.put("peos", li4);
			}catch(Exception e1){
				System.out.println("정보없음");
				
			}
			
			
			
			
			
			
			// 포토
			try{
			List li3 = new ArrayList<>();
			for (int i = 0; i < e3.getElementsByClass("gallery_group").get(0).getElementsByTag("img").size(); i++) {
				String pho = e3.getElementsByClass("gallery_group").get(0).getElementsByTag("img").get(i).attr("src");
				//System.out.println("세부이미지 :  " + pho);
				li3.add(pho);
			}
			int size =li3.size(); //total
			
			m.put("photos", li3);
			}catch(Exception e1){
				System.out.println("세부이미지 :  정보없음" );
			}
			
			
			
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		return m;
	}
}
