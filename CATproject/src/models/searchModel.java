package models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class searchModel {
	/*사용할 데이터
	제목 : items=[{title}] 
	이미지 :  items=[{image}] 
	별점	:  items=[{userRating}] 
	평점	:(상세페이지에서)
	참여인원 :(게시판)
	장르	: X
	나라	: X
	서브장르 :  items=[{subtitle}] 
	상영시간 X
	제작년도 : items=[{pubDate}]
	감독 : items=[{director}] 
	*/
	
	public Map<String,Object> data1(String text) throws IOException {//String text
		String clientId = "gFMFKc6XwI7_ujbmeRaD";
		String clientSecret = "DOC8JR_Zzu";
		//String text = URLEncoder.encode("블랙", "UTF-8");

		String apiURL = "https://openapi.naver.com/v1/search/movie.json?query=" + text;// json

		// 요청보내는 방법은 꽤 많다.
		apiURL += "&display=100";
		URL url = new URL(apiURL);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("X-Naver-Client-Id", clientId);
		con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
		int responseCode = con.getResponseCode();
		
		BufferedReader br;
		if (responseCode == 200) { // 정상 처리?연결시
			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		} else { // 에러시
			br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		}
		
		
		 /*String inputLine; 
         StringBuffer response = new StringBuffer();
         while ((inputLine = br.readLine()) != null) {
             response.append(inputLine);
         }
         br.close();
         System.out.println(response.toString());*/
		

		String outstr = "";

		while (true) {
			String str = br.readLine();
			if (str == null)
				break;
			// out.println(str);
			outstr += str;
		}
		// out.println(outstr);
		// JSon type String <==> Java Object
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String,Object> json = mapper.readValue(outstr, LinkedHashMap.class);
	
		int total = (int) json.get("total");
			if(total >= 100){
				total =100;
			}
			json.put("total",total);
	
		for(Map<String,Object> map1 : (List<Map>)json.get("items")){
			String actor = (String) map1.get("actor");
			String[] actor_list =actor.split("[|]");	
			String director = (String) map1.get("director");
			String[] director_list =director.split("[|]");
			String link = (String)map1.get("link");
			String[] link_list =link.split("=");
			System.out.println("link_list="+link_list[1]);
			
			map1.put("link", link_list[1]);
			map1.put("actor", actor_list);
			map1.put("director", director_list);
			
		}
	
	
		return json;
	}
	
	
	
	
	
	
	
	public ArrayList<Map> data2(String text) throws IOException {//String text
		String clientId = "gFMFKc6XwI7_ujbmeRaD";
		String clientSecret = "DOC8JR_Zzu";
		//String text = URLEncoder.encode("블랙", "UTF-8");

		String apiURL = "https://openapi.naver.com/v1/search/movie.json?query=" + text;// json

		// 요청보내는 방법은 꽤 많다.
		apiURL += "&display=100";
		URL url = new URL(apiURL);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("X-Naver-Client-Id", clientId);
		con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
		int responseCode = con.getResponseCode();
		
		BufferedReader br;
		if (responseCode == 200) { // 정상 처리?연결시
			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		} else { // 에러시
			br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		}
		

		String outstr = "";

		while (true) {
			String str = br.readLine();
			if (str == null)
				break;
			// out.println(str);
			outstr += str;
		}
		
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String,Object> json = mapper.readValue(outstr, LinkedHashMap.class);
	
		ArrayList<Map> maplist = new ArrayList<Map>();
		
		Map<String,Object> map2 = new LinkedHashMap<>();
		for(Map<String,Object> map1 : (List<Map>)json.get("items")){
			
			String actor = (String) map1.get("actor");
			String[] actor_list =actor.split("[|]");	
			String director = (String) map1.get("director");
			String[] director_list =director.split("[|]");
			String link = (String)map1.get("link");
			String[] link_list =link.split("=");
			//System.out.println("link_list="+link_list[1]);
			
			map1.put("link", link_list[1]);
			
			map1.put("actor", actor_list);
			map1.put("director", director_list);
			
			/*System.out.println(map1.toString());*/
			maplist.add(map1);
			
		}
		
		return maplist;
	}
	
}
