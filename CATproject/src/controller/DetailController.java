package controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import models.DetailDao;
import models.MemberDao;
import models.PictureDao;
import models.ReviewDao;
import models.recoDao;


@Controller
@RequestMapping("/detail")
public class DetailController {
	@Autowired
	DetailDao ddao;
	@Autowired
	ReviewDao rdao;
	@Autowired
	PictureDao pdao;
	@Autowired
	MemberDao mdao;

	@Autowired
	recoDao recodao;
	
	@RequestMapping("/print")
	public ModelAndView detailHandler(@RequestParam(name="code", defaultValue="5000")String code, HttpSession session, HttpServletRequest req){
		
		String userid=(String) session.getAttribute("auth_id");
		boolean like = recodao.function(userid, code);
		
		System.out.println("======================================");
        System.out.println(like); 
        System.out.println("======================================");
		List<Map> li = rdao.readAll(code);
		// 프로필 사진 처리		
		for(Map o : li) {
			String id = (String)(o.get("id"));
			String picurl = pdao.getLastImageURL(id);
			o.put("picurl", picurl);
		}
		
		// 리뷰 페이징 처리
		int total =li.size();	
				// select count(*) from review where code=??
		
		//System.out.println("li 개수=============="+total);
		int size = total%5 == 0 ? total/5 : total/5+1;
		String pStr = req.getParameter("page")==null ? "1":req.getParameter("page");
		req.setAttribute("total", total);
		req.setAttribute("size", size);
		req.setAttribute("page", pStr);
		
		int start = (Integer.parseInt(pStr)-1)*5;	// 0
		int end = start+5 <= total ? start+5 : total;	// 4	
		
		li = li.subList(start, end);
		//
		
		Map map = ddao.detailOut(code);
		
		
		// 좋아요, 조회수 목록에 들어갈 정보 
		String code1 =  (String)req.getParameter("code");
		String tn =  (String)map.get("tn");
		String img = (String)map.get("mpos");
		String genre="개요=[";
		for(String tmp: ((Map<String,List<String>>)map.get("all")).get("개요")){
			genre+=tmp+",";
		}
		genre+="]";
		//System.out.println(genre);
		
		System.out.println("받아진 제목"+tn);
		System.out.println("받아진 url"+img);
		
		
	
		//조회시 
		
	      if((String)session.getAttribute("auth_id") != null){
	          
	          String id = (String)session.getAttribute("auth_id");
	          int age = (Integer)session.getAttribute("auth_age");
	          String gender = (String)session.getAttribute("auth_gender");         
	          
	          Map map2= new HashMap<>();
	                map2.put("age", age);
	                map2.put("code", code1);
	                map2.put("tn", tn);
	                map2.put("img", img);
	               map2.put("genre", genre);
	               map2.put("gender", gender);
	           recodao.ViewCount(map2);
	        
	       }
		


		
		
		ModelAndView mav = new ModelAndView("t_main3");
		mav.addObject("map", map);
		mav.addObject("code", code);
		mav.addObject("li",li);
		mav.addObject("like", like);
		return mav;
	}
	@RequestMapping("/print2")
	public ModelAndView detailHandler2(@RequestParam(name="code", defaultValue="5000")String code){
		ModelAndView mav = new ModelAndView("detail_view/detail2");
		Map map= ddao.detailOut(code);
		System.out.println("=============");
		System.out.println(map);
		mav.addObject("map", map);
		return mav;
	}
	
	
	
	@RequestMapping("/like")
	public void likeHandle(@RequestParam(name="code", defaultValue="5000")String code, HttpSession session, HttpServletRequest req){
		// 좋아요시 좋아요 눌렀을 때
				Map map = ddao.detailOut(code);
				String code1 =  (String)req.getParameter("code");
				String tn =  (String)req.getParameter("tn");
				String img = (String)req.getParameter("img");
				String genre="개요=[";
				for(String tmp: ((Map<String,List<String>>)map.get("all")).get("개요")){
					genre+=tmp+",";
				}
				genre+="]";
				
				
				// 좋아요시 좋아요 눌렀을 때
				
				
				
		      if((String)session.getAttribute("auth_id") != null){
			         String id = (String)session.getAttribute("auth_id");
			         int age = (Integer)session.getAttribute("auth_age");
			         String gender = (String)session.getAttribute("auth_gender");
			       // 좋아요시
			          Map map3= new HashMap<>();
			            map3.put("age", age);    // 나이
			            map3.put("code", code1); // 영화코드
			            map3.put("tn", tn);      // 타이틀이름(한글)
			            map3.put("img", img);    // 메인포스터
			            map3.put("genre", genre);  // 장르
			            map3.put("gender", gender); // 성별
			            map3.put("id", id);    // 아이디
			         recodao.LikeCount(map3);
			         
			      }
	}
	
	
	@RequestMapping("/print/review")
	@ResponseBody
	public String reviewNewHandle(HttpSession session, HttpServletRequest req){

		ModelAndView mav = new ModelAndView("");
		// ModelAndView mva = new ModelAndView();
		// 브라우저에서 넘어오는 값
		long curr = System.currentTimeMillis();  // 또는 System.nanoTime();
		// (2) 출력 형태를 지정하기 위해 Formatter를 얻는다.
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy년:MM월:dd일-hh시:mm분:ss초");
		// (3) 출력 형태에 맞는 문자열을 얻는다.
		String date = sdf2.format(new Date(curr));
		
		
		 String comment = (String)req.getParameter("comment");
		 String id = (String)session.getAttribute("auth_id");
		 String code =  (String)req.getParameter("code");
		 String tn =  (String)req.getParameter("tn");
		 String img = (String)req.getParameter("img");
		 //SQL에서 뽑아오는 값
		 String picurl = pdao.getLastImageURL(id);
		 
		
		// String id = "testid";
		// String code =  "testcode";
		 //String tn =  "testtn";
		// String img = "testimg";
		// String date = "20170303";
		 //System.out.println(id);
		 //SQL에서 뽑아오는 값
		// String picurl = pdao.getLastImageURL(id);
		 
		 //한곳에 묶기
		 Map map= new HashMap<>();
		 	 map.put("comment", comment);
			 map.put("id",id);
			 map.put("code",code);
			 map.put("tn",tn);
			 map.put("img", img);
			 map.put("date", date);
			 map.put("picurl",picurl);
		 
		 //묶어서 mongo로 던지기
		 rdao.createOne(map);
		 return "YYYYY";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	@RequestMapping("/print/review/list")
	public ModelAndView listHandle(HttpServletRequest req) throws UnsupportedEncodingException{
		String review = (String)req.getParameter("review");
		String text = URLEncoder.encode(review, "UTF-8");
		Map<String, Object> map = 
	}
	*/

	/*
	
	@RequestMapping("/new")
	public ModelAndView review(HttpServletRequest req, HttpSession sessoin) {
		String addr = req.getRemoteAddr();

		
		ModelAndView mav = new ModelAndView("t_main3");	
		mav.addObject("title","review");
		sessoin.setAttribute("addr", addr);
		return mav;
	}

	@RequestMapping("/addNew")
	public ModelAndView reviewAddHandle(HttpServletRequest req, @RequestParam Map<String, String> map) 
															throws UnsupportedEncodingException {
		req.setCharacterEncoding("utf-8");
		System.out.println(req.getParameter("writer"));
		System.out.println(map.toString());

		int r = rdao.createOne(map.get("writer"), map.get("content"), map.get("ip"));
		if (r == 1) {
			return new ModelAndView("redirect:t_main3");
		} else {
			ModelAndView mav = new ModelAndView("reviewerror");
			mav.addObject("msg", "글 등록에 문제가 발생하였습니다.");
			return mav;
		}
	}

	@RequestMapping("/all")
	public ModelAndView reviewAllHandle(HttpSession session) {

		List all = rdao.readAll();
		ModelAndView mav = new ModelAndView("t_main3");
		session.setAttribute("data", all);
		session.setAttribute("dataSize", all.size());
		return mav;
	}

	 */
}
	
	
	


