package models;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import com.fasterxml.jackson.databind.ObjectMapper;

public class APIExam3 {

    public Map json(String arg0) {
    	ObjectMapper om=new ObjectMapper();
        String clientId = "gFMFKc6XwI7_ujbmeRaD";
        String clientSecret = "DOC8JR_Zzu";
        Map map=new LinkedHashMap<>();
        Map mapresult=new LinkedHashMap<>();
        boolean find=false;
        String[] tmp= arg0.split("[|]");
        try {
            String text = URLEncoder.encode(tmp[0], "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/search/movie.json?query="+ text; // json ���
            //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml ���
            apiURL+="&display=100";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine; 
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            map=om.readValue(response.toString(), Map.class);
            
            for(Map<String,String> m : (List<Map>)map.get("items")){
            	if(m.get("link").contains(tmp[1])){
            		find=true;
            		mapresult=m;
            		String[] tt=m.get("link").split("=");
            		mapresult.put("link", tt[1]);
            		/*System.out.println("================================");
            		System.out.println(mapresult);
            		System.out.println("================================");*/
            		break;
            	}
            }            
        } catch (Exception e) {
            System.out.println(e);
        }
        if(find){
        	return mapresult;
        } else {
        return null;
        }
        
    }
}