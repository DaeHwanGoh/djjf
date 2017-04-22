package models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



public class UrlList3 {
	@Autowired
	APIExam3 api;
	
	public List ranking(String sel,String date, String tg ) throws IOException { 
		List list=new ArrayList<>();
		int max = 0;
		String str = "http://movie.naver.com/movie/sdb/rank/rmovie.nhn?sel=";
		str+=sel;
		str+="&date="+date;
		str+="&tg="+tg;

		// URL url = new URL(str);
		// InputStream is = url.openStream();
		// InputStreamReader isr = new InputStreamReader(is,
		// Charset.forName("utf-8"));
		// BufferedReader isb = new BufferedReader(isr);
		List<String> searchlist=new ArrayList<>();
		URL url = new URL(str);
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), Charset.forName("euc-kr")));
		String s = "";
		String tmp="";
		int ccnt=0;
		aa: while (true) {
			s = br.readLine();
//			System.out.println(s);
			if (s.contains("<!-- 랭킹 리스트 -->")) {
				while (true) {
					s = br.readLine();
					if (s.contains("<td class=\"title")) {
						ccnt++;
						if(ccnt==1){
							continue;
						}
						if(ccnt==30){
							break aa;
						}
						s = br.readLine();
						s = br.readLine();
						int tn = s.indexOf("title");
						int b = s.indexOf("\"", tn);
						int e = s.indexOf("\"", b + 1);
//						list.add(s.substring(b + 1, e));
						tmp=s.substring(b + 1, e);
						tn = s.indexOf("code");
						b = s.indexOf("=", tn);
						e = s.indexOf("\"", b + 1);
//						list.add(s.substring(b + 1, e));
						tmp+="|"+s.substring(b + 1, e);
						searchlist.add(tmp);

					}
					if (s.contains("<!-- //랭킹 리스트 -->")) {
						break aa;
					}

				}

			}
		}
		for(String t : searchlist){
			Map tmp1=api.json(t);
			if(tmp1==null){
				continue;
			} else{
				list.add(tmp1);
			}
//			System.out.println(t);
			if(list.size()==30){
				break;
			}
		}
		return list;

	}
}