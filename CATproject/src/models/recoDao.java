package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


@Service
public class recoDao {

    @Autowired
    MongoTemplate template;
    
//규동오빠 페이지에서 받아서 몽고디비에 넣기.
	
	//조회수 처리 
	public void ViewCount(Map map) {
//map으로 넘어온 조회한 정보(age(세션에 있는거), 코드, 영화이름, 개요=[범죄,액션,한국,2017,.03.23,], 이미지url, 조회한 사람 성별)
		
		//#map으로 넘겨받은 정보들 
	      int vage = (Integer)map.get("age"); //1. 조회한 회원의 나이 연령대
	      	int vage_group = vage - vage%10; //10대, 20대, 30대, 40대, 50대, 60대 이상(60, 70, 80 왜냐하면 80세까지 존재함)
	   
	      String vmcode = (String)map.get("code");  //2. 조회된 영화 코드
	      String vmname = (String)map.get("tn"); //3. 조회된 영화 이름	    
	      String vmurl = (String)map.get("img"); //4. 이미지 url      
	      String vgender = (String)map.get("gender"); //5. 성별	    
	      String s1 = (String)map.get("genre"); //6. 개요(장르 포함) 
	      //s1 의 데이터 형식 : 개요=[범죄,액션,한국,2017,.03.23,]
	      


	      //정보들 확인
	      System.out.println("==========================상세페이지에서 조회시에 넘겨받은 정보들 ==========================");
	      System.out.println("조회한 사람 나이 연령대 : " + vage_group);
	      System.out.println("조회한 사람 성별 : " + vgender);	      
	      System.out.println("조회된 영화 코드 : " + vmcode);
	      System.out.println("조회된 영화 제목 : " + vmname);
	      System.out.println("조회된 영화포스터 url : " + vmurl);
	      System.out.println("조회된 영화 장르 original_sentence : " + s1);
	      System.out.println("============================================================================================");
	      
	      //s1에서 장르 추출
			String vmgenre="";
			
			if(s1.contains("공포") || s1.contains("모험") || s1.contains("스릴러") || s1.contains("느와르") ||s1.contains("범죄") || s1.contains("미스터리") || s1.contains("전쟁") || s1.contains("액션") ){
				vmgenre = "공포/스릴러/액션";
				
			}else if(s1.contains("SF") || s1.contains("판타지")){
				vmgenre = "SF/판타지";
			}else if(s1.contains("드라마") || s1.contains("멜로") || s1.contains("애정") || s1.contains("로맨스") || s1.contains("코미디")){
				vmgenre = "드라마/로맨스/코미디";
				
			}else{ //없거나 다른 경우
				vmgenre = "가족/아동/애니메이션";
			}
			System.out.println(vmgenre);
			
			String age_gender ="";
			//나이와 성별 구분
			if(vage_group>=60){
				age_gender = "60v_"+vgender; //60v_male, 60v_female 식
			}else{
				age_gender = vage_group+"v_"+vgender; //10v_male, 10v_female 식
			}
	
//몽고 DB에 데이터 셋팅
			//컬렉션 명: recoviewdata  
			//필드명: vmcode(영화 코드),  vmname(영화 제목), vmurl(조회된 영화 포스터), vmgenre(영화 장르), 나이성별 조회("view_group" : "20v_female")
	      //
			
			  Map map1 = new HashMap<>();
	              map1.put("vmcode", vmcode); //영화 코드
	              map1.put("vmname", vmname); //영화 제목
	              map1.put("vmurl", vmurl); //조회된 영화 포스터 이미지 
	              map1.put("vmgenre", vmgenre); //영화 장르
	              map1.put("view_group", age_gender); //나이성별 조회 그룹 ("view_group" : "20v_female")
	          
	          template.insert(map1, "recoviewdata");
          
			/*
			db.recoviewdata.insert(
			{"vmcode":"136872", 
			"vmname":"미녀와 야수", 
			"vmurl":"http://movie.naver.com/movie/bi/mi/photoViewPopup.nhn?movieCode=136872",
			 "vmgenre":"드라마/로맨스/코미디", "view_group":"20v_female"});
			*/
	   }	
	
	
	//좋아요 처리
	public void LikeCount(Map map) {
		
		//map으로 넘어온 좋아요한 정보(age(세션에 있는거), 코드, 영화이름, 개요=[범죄,액션,한국,2017,.03.23,], 이미지url, 좋아요한 사람 성별, 좋아요한 사람 아이디)
		
				//#map으로 넘겨받은 정보들 
			      int lage = (Integer)map.get("age"); //1. 좋아요한 회원의 나이 연령대
			      	int lage_group = lage - lage%10; //10대, 20대, 30대, 40대, 50대, 60대 이상(60, 70, 80 왜냐하면 80세까지 존재함)
			   
			      String lmcode = (String)map.get("code");  //2. 조회된 영화 코드
			      String lmname = (String)map.get("tn"); //3. 조회된 영화 이름	    
			      String lmurl = (String)map.get("img"); //4. 이미지 url      
			      String lgender = (String)map.get("gender"); //5. 성별	    
			      String s2 = (String)map.get("genre"); //6. 개요(장르 포함) 
			      //s1 의 데이터 형식 : 개요=[범죄,액션,한국,2017,.03.23,]
			      String lid = (String)map.get("id"); //7. 좋아요한 사람 id
			      


			      //정보들 확인
			      System.out.println("==========================상세페이지에서 좋아요시에 넘겨받은 정보들 ==========================");
			      System.out.println("좋아요한 사람 나이 연령대 : " + lage_group);
			      System.out.println("좋아요한 사람 성별 : " + lgender);
			      System.out.println("좋아요한 사람 id : " + lid);			      
			      System.out.println("좋아요한 영화 코드 : " + lmcode);
			      System.out.println("좋아요한 영화 제목 : " + lmname);
			      System.out.println("좋아요한 영화포스터 url : " + lmurl);
			      System.out.println("좋아요한 영화 장르 original_sentence : " + s2);
			      System.out.println("==============================================================================================");
			      
			      //s1에서 장르 추출
					String lmgenre="";
					
					if(s2.contains("공포") || s2.contains("모험") || s2.contains("스릴러") || s2.contains("느와르") ||s2.contains("범죄") || s2.contains("미스터리") || s2.contains("전쟁") || s2.contains("액션") ){
						lmgenre = "공포/스릴러/액션";
						
					}else if(s2.contains("SF") || s2.contains("판타지")){
						lmgenre = "SF/판타지";
					}else if(s2.contains("드라마") || s2.contains("멜로") || s2.contains("애정") || s2.contains("로맨스") || s2.contains("코미디")){
						lmgenre = "드라마/로맨스/코미디";
						
					}else{ //없거나 다른 경우
						lmgenre = "가족/아동/애니메이션";
					}
					System.out.println(lmgenre);
					
					String lage_gender ="";
					//나이와 성별 구분
					if(lage_group>=60){
						lage_gender = "60l_"+lgender; //60l_male, 60l_female 식
					}else{
						lage_gender = lage_group+"l_"+lgender; //10l_male, 10l_female 식
					}
		          
		//몽고 DB에 데이터 셋팅
		//컬렉션 명: recolikedata    
		//필드명: vmcode(영화 코드),  vmname(영화 제목), vmurl(조회된 영화 포스터), vmgenre(영화 장르), 나이성별 좋아요( "like_group": "20l_female", ), lid(좋아요한 ID)
      //
				 Map map2 = new HashMap<>();
		              map2.put("lmcode", lmcode); //영화 코드
		              map2.put("lmname", lmname); //영화 제목
		              map2.put("lmurl", lmurl); //조회된 영화 포스터 이미지 
		              map2.put("lmgenre", lmgenre); //영화 장르
		              map2.put("like_group", lage_gender); //나이성별 조회 그룹 ( "like_group": "20l_female", )
		              map2.put("lid", lid);  //좋아요한 사람 아이디
		                 
		          
		          template.insert(map2, "recolikedata");		
		          
					/*

db.recolikedata.insert({"lmcode":"136872", "lmname":"미녀와 야수", "lmurl":"http://movie.naver.com/movie/bi/mi/photoViewPopup.nhn?movieCode=136872",
 "lmgenre":"드라마/로맨스/코미디", "like_group" : "20l_female", "lid" : "jyp"});
					*/         
	      
	   }	
	
//======================================================	
	//몽고 DB 에서 가져오기
//아래 세개 메서드 모두 컨트롤러에서 호출함	
	
	//회원 나이와 성별의 가장 많이 조회한 장르별 랭킹
	public Map<String, List> getgenreViewCount(Map map) {  //컨트롤러로부터 회원의 age, gender 정보를 받음 
		//장르는 네가지.
		//[공포/스릴러/액션]
		//[SF/판타지]
		//[드라마/로맨스/코미디]
		//[가족/아동/애니메이션]
		
		int age = (Integer)map.get("age"); //회원 나이
		String gender = (String)map.get("gender");
      	int age_group = age - age%10; //회원 연령대 10대, 20대, 30대, 40대, 50대, 60대 이상(60, 70, 80 왜냐하면 80세까지 존재함)
      	
		String age_gender ="";  //몽고디비에서 검색할 벨류
		
		//나이와 성별 구분
		if(age_group>=60){
			age_gender = "60v_"+gender; //60v_male, 60v_female 식
		}else{
			age_gender = age_group+"v_"+gender; //10v_male, 10v_female 식
		}
 
		 String[] sgenre = new String[]{"공포/스릴러/액션", "SF/판타지", "드라마/로맨스/코미디", "가족/아동/애니메이션" }; //검색할 장르
		 
		 
		//list에 담아서 페이지로 던져줘야할 거..
				//장르별("vmgenre")로 20v_female 이 높은 상위 5개 (view_group)의 
			     //img url(vmurl), 영화 code(vmcode),
		
		

	        Aggregation aggregation1 = Aggregation.newAggregation(
	        		Aggregation.match(Criteria.where("view_group").is(age_gender)),
	        		Aggregation.match(Criteria.where("vmgenre").is(sgenre[0])),	        		
	                Aggregation.group("vmcode", "vmurl").count().as("cnt"), 
	                Aggregation.sort(Sort.Direction.DESC, "cnt")
	         );

	        Aggregation aggregation2 = Aggregation.newAggregation(
	        		Aggregation.match(Criteria.where("view_group").is(age_gender)),
	        		Aggregation.match(Criteria.where("vmgenre").is(sgenre[1])),	        		
	                Aggregation.group("vmcode", "vmurl").count().as("cnt"), 
	                Aggregation.sort(Sort.Direction.DESC, "cnt")
	         );
	        
	        Aggregation aggregation3 = Aggregation.newAggregation(
	        		Aggregation.match(Criteria.where("view_group").is(age_gender)),
	        		Aggregation.match(Criteria.where("vmgenre").is(sgenre[2])),	        		
	                Aggregation.group("vmcode", "vmurl").count().as("cnt"), 
	                Aggregation.sort(Sort.Direction.DESC, "cnt")
	         );
	        
	        Aggregation aggregation4 = Aggregation.newAggregation(
	        		Aggregation.match(Criteria.where("view_group").is(age_gender)),
	        		Aggregation.match(Criteria.where("vmgenre").is(sgenre[3])),	        		
	                Aggregation.group("vmcode", "vmurl").count().as("cnt"), 
	                Aggregation.sort(Sort.Direction.DESC, "cnt")
	         );
	        Map<String, List> li = new HashMap<>();
	        li.put(sgenre[0], template.aggregate(aggregation1, "recoviewdata", Map.class).getMappedResults());
	        li.put(sgenre[1], template.aggregate(aggregation2, "recoviewdata", Map.class).getMappedResults());
	        li.put(sgenre[2], template.aggregate(aggregation3, "recoviewdata", Map.class).getMappedResults());
	        li.put(sgenre[3], template.aggregate(aggregation4, "recoviewdata", Map.class).getMappedResults());
	        	
	     return li; 
	}		
	
	//검색하고자 하는 나이, 성별의 조회 랭킹
	//조회수  (list 로 영화 URL, CODE  담아서 반환해야함) -> 그려면 reco_main.jsp 에서 영화 눌렀을 때, 규동오빠 페이지로 영화 코드 파라미터로 넘겨줘야함
	public List<Map> getsearchViewCount(Map map) { //컨트롤러로부터 조회할 회원의 age, gender 정보를 받음 

		int age = (Integer)map.get("sage"); //검색할 연령대
		String gender = (String)map.get("sgender");
      	int age_group = age - age%10; //회원 연령대 10대, 20대, 30대, 40대, 50대, 60대 이상(60, 70, 80 왜냐하면 80세까지 존재함)
      	
		String age_gender ="";  //몽고디비에서 검색할 벨류
		
		//나이와 성별 구분
		if(age_group>=60){
			age_gender = "60v_"+gender; //60v_male, 60v_female 식
		}else{
			age_gender = age_group+"v_"+gender; //10v_male, 10v_female 식
		}
 
		
		System.out.println("getsearchViewCount:"+age_gender);
		
//담아서 페이지로 던져줘야할 거..
		//해당 나이, 성별에 맞는 조회수 가장 많은 상위 30개
		//img url(vmurl), 영화 code(vmcode), 
		
        Aggregation aggregation = Aggregation.newAggregation(
        		Aggregation.match(Criteria.where("view_group").is(age_gender)),
                Aggregation.group("vmcode", "vmurl").count().as("cnt"), 
                Aggregation.sort(Sort.Direction.DESC, "cnt")
         );
        
        
        return template.aggregate(aggregation, "recoviewdata", Map.class).getMappedResults(); //통째로 던져주면 여기서 30개만 뽑아서 쓰기
   
    		

	      
	}	
	
	//좋아요 수 (list 로 영화 URL, CODE  담아서 반환해야함) -> 그려면 reco_main.jsp 에서 영화 눌렀을 때, 규동오빠 페이지로 영화 코드 파라미터로 넘겨줘야함
	//검색하고자 하는 나이, 성별의 좋아요 랭킹
	public List<Map> getsearchLikeCount(Map map) {//컨트롤러로부터 조회할 회원의 age, gender 정보를 받음 

		int age = (Integer)map.get("sage"); //회원 나이
		String gender = (String)map.get("sgender");
      	int age_group = age - age%10; //회원 연령대 10대, 20대, 30대, 40대, 50대, 60대 이상(60, 70, 80 왜냐하면 80세까지 존재함)
      	
		String age_gender ="";  //몽고디비에서 검색할 벨류
		
		//나이와 성별 구분
		if(age_group>=60){
			age_gender = "60l_"+gender; //60l_male, 60l_female 식
		}else{
			age_gender = age_group+"l_"+gender; //10l_male, 10l_female 식
		}
 
		
//담아서 페이지로 던져줘야할 거..
		//해당 나이, 성별에 맞는 좋아요수 가장 많은 상위 30개
		//img url, 영화 code, 	      

        Aggregation aggregation = Aggregation.newAggregation(
        		Aggregation.match(Criteria.where("like_group").is(age_gender)),
                Aggregation.group("lmcode", "lmurl").count().as("cnt"), 
                Aggregation.sort(Sort.Direction.DESC, "cnt")
         );
        
        
        return template.aggregate(aggregation, "recolikedata", Map.class).getMappedResults();
   
	 }
	
	
	
	//규동오빠가 한 부분
	public boolean function(String id, String code){
	      List<Map> list = new ArrayList<>();
	      
	      Criteria c1 = Criteria.where("lid").is(id);
	      Criteria c2 = Criteria.where("lmcode").is(code);
	      Query query = new Query();
	      
	      query.addCriteria(c1);
	      query.addCriteria(c2);
	      
	      list = template.find(query, Map.class, "recolikedata");
	      
	      if(list.size()==0){
	         return false;
	      }else
	         
	      return true;
	   }	
	
}
