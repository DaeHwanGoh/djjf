package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import models.AuthorityDao;
import models.recoDao;

@Controller
@RequestMapping("/recommend")
public class recommendController {
	@Autowired
	recoDao recodao;
	@Autowired
	AuthorityDao authdao;
	
	
//===========================================	
//추천작 페이지 (reco_main.jsp)
	@RequestMapping("/reco_main")
	public ModelAndView recommendMainHandler(HttpSession session, HttpServletRequest request) {
		System.out.println("recommend 잘 들어옴");
		
		
		
		String id = (String)session.getAttribute("auth_id");
		if(authdao.maxAuthority(authdao.userAuthority(id)).equals("user")){
			ModelAndView mav=new ModelAndView("redirect:/authority.jsp");
			return mav;
		}
		int age = (Integer)session.getAttribute("auth_age");
		String gender = (String)session.getAttribute("auth_gender");

		
		System.out.println("age"+age);
		System.out.println("gender"+gender);			

		//조회를 눌렀을 때 성별과 연령대를 파라미터로 전달받은 것
		String pramage = request.getParameter("sage");
		String sgender =request.getParameter("sgender");
		int sage=0;
		
		System.out.println("==============================");
		System.out.println("pramage"+pramage);
		System.out.println("sage"+sage);
		System.out.println("sgender"+sgender);
		System.out.println("==============================");
		
		if(pramage == null || sgender == null) { //전달받은게 없으면..
			sage=age;
			sgender=gender;
			
		}else{ //전달받은게 있으면
			sage= Integer.parseInt(pramage);
			
		}
		
		System.out.println("변경된 sage"+sage);
		System.out.println("변경된 sgender"+sgender);	
		System.out.println("age"+age);
		System.out.println("gender"+gender);		
		

//getgenreViewCount에서 뽑아서 장르별로 상위 5개의 조회순 뽑아서 넘기기
		 Map mapv1 = new HashMap<>();
		 	mapv1.put("age", age);
		 	mapv1.put("gender", gender);
		 	
 	
		 				 			
		 
		 	Map<String, List> mapresult1 = recodao.getgenreViewCount(mapv1);
			System.out.println(mapresult1);
			
			System.out.println("===============몽고디비에서 뽑히는 값===============");
			System.out.println(mapresult1.get("공포/스릴러/액션"));
			System.out.println(mapresult1.get("SF/판타지"));
			System.out.println(mapresult1.get("드라마/로맨스/코미디"));
			System.out.println(mapresult1.get("가족/아동/애니메이션"));
			System.out.println("====================================================");			

			List<Map> li1 = mapresult1.get("공포/스릴러/액션");
				System.out.println(li1.get(0).get("vmcode"));  //공포/스릴러/액션 장르의 랭킹1의 코드번호만 뽑힘
			
			List<Map> li2 = mapresult1.get("SF/판타지");
			List<Map> li3 = mapresult1.get("드라마/로맨스/코미디");
			List<Map> li4 = mapresult1.get("가족/아동/애니메이션");
			
			
//	getsearchViewCount에서 뽑아서 조회수 높은거 장르 상관없이 30개, 영화 코드랑, 이미지 url
				 Map mapv2 = new HashMap<>();
				 	mapv2.put("sage", sage);
				 	mapv2.put("sgender", sgender);
				 
				 List<Map> slist1 = recodao.getsearchViewCount(mapv2);
				 System.out.println("===============몽고디비에서 뽑히는 getsearchViewCount 값===============");
				 	System.out.println("slist1: \n"+slist1);
				 	System.out.println(slist1.get(0)); //가장 조회수 높은 1위
				 	System.out.println(slist1.get(0).get("vmcode")); //가장 조회수 높은 1위의 코드
				 	System.out.println(slist1.get(0).get("vmurl")); //가장 조회수 높은 1위의 코드
				 	System.out.println(slist1.size()); //영화 갯수 21개
				 System.out.println("========================================================================");	
				 
				 
//getsearchLikeCount에서 뽑아서 좋아요수 높은거 장르 상관없이 30개, 영화 코드랑, 이미지 url
				 	
				 List<Map> slist2 = recodao.getsearchLikeCount(mapv2);
				 System.out.println("===============몽고디비에서 뽑히는 getsearchLikeCount 값===============");
				 	System.out.println("slist2: \n"+slist2);
				 	System.out.println(slist2.get(0)); //가장 좋아요 높은 1위
				 	System.out.println(slist2.get(0).get("lmcode")); //가장 좋아요수 높은 1위의 코드
				 	System.out.println(slist2.get(0).get("lmurl")); //가장 좋아요수 높은 1위의 코드
				 	System.out.println(slist2.size()); //영화 갯수 개
				 System.out.println("========================================================================");	
				 
				 				 
				 	
//====================================================================================		
		ModelAndView mav = new ModelAndView("t_reco_main"); //(reco_main.jsp)
			mav.addObject("id", id);
			mav.addObject("age", age); //회원 나이
			mav.addObject("gender", gender); //회원 성별
			mav.addObject("sage", sage); //조회할 나이
			mav.addObject("sgender", sgender); //조회할 성별
			
			mav.addObject("li1", li1); //장르별 조회 데이터 
			mav.addObject("li2", li2); //장르별 조회 데이터 
			mav.addObject("li3", li3); //장르별 조회 데이터 
			mav.addObject("li4", li4); //장르별 조회 데이터 
			
			mav.addObject("svlist1", slist1); //전체 조회 데이터
			mav.addObject("sllist2", slist2); //전체 좋아요 데이터
			
		return mav;
	}	
	
//===========================================	


	

}

