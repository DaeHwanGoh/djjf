package controller;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import models.AllDao;
import models.FriendDao;
import models.PostDao;

@Controller
@RequestMapping("/mypage")
public class MypageController {
	
	@Autowired
	ModelAndView mav;
	
	@Autowired
	AllDao alldao;
	
	@Autowired
	FriendDao fdao;
	
	@Autowired
	PostDao pdao;
	
	@RequestMapping("/main")
	public ModelAndView writePost(HttpSession session){
		String userid=(String) session.getAttribute("auth_id");
		List flist=fdao.infoflist(userid);
		System.out.println(flist);
		mav.addObject("flist", flist);
		Map map = alldao.selectOne("select URL from PICTURE where ID='%s' order by NUM desc", userid); // 사진정보
		String pictureUrl = (String) map.get("URL");
		  
		List<Map> my_postlist =pdao.mypost(userid);
	    int size =0;
	    for(Map<String,Object> e:my_postlist){
			  size++;
			  
		 }
	    
		 mav.setViewName("post_t_01"); 
		 mav.addObject("pictureUrl",pictureUrl);
		 mav.addObject("my_postlist",my_postlist);
		 mav.addObject("id",userid);
		 mav.addObject("size",size);
		return mav;
	}
	

}
