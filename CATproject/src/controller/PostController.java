package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import models.AllDao;
import models.PostDao;

@SuppressWarnings("unchecked")
@Controller
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	ModelAndView mav;

	@Autowired
	AllDao alldao;

	@Autowired
	PostDao pdao;

	@RequestMapping("/write")
	public ModelAndView writePost() {
		mav.setViewName("t_pstore");
		return mav;
	}

	// 寃뚯떆湲� �옉�꽦���옣 post /媛쒖씤而щ젆�뀡 write�벑濡�
	@RequestMapping("/store")
	@SuppressWarnings("unchecked")
	public ModelAndView storePost(HttpSession session, HttpServletRequest req, @RequestParam Map resultmap)
			throws Exception {

		String id = (String) session.getAttribute("auth_id");
		Map map = alldao.selectOne("select URL from PICTURE where ID='%s' order by NUM desc", id); // �궗吏꾩젙蹂�
		System.out.println((String) map.get("URL"));
		String pictureUrl = (String) map.get("URL");
		String date = "" + System.currentTimeMillis();
		List commentuser = new ArrayList<>();
		List likeuser = new ArrayList<>();
		List commentlist = new ArrayList<>();
			
		resultmap.put("userid", id);
		resultmap.put("pictureUrl", pictureUrl);
		resultmap.put("date", date);
		resultmap.put("commentuser", commentuser);
		resultmap.put("likeuser", likeuser);
		resultmap.put("commentlist", commentlist);

		pdao.insertMongo(resultmap); // 寃뚯떆湲�post而щ젆�뀡 �벑濡�
		pdao.insertperson(date, id); // 媛쒖씤�젙蹂댁뿉 post_id�벑濡�

		return mav;
	}
	
	/*@RequestMapping("/likeBtnLoading")
	public String likeLoading(HttpSession session, HttpServletRequest req) throws Exception {
		String userid = (String) session.getAttribute("auth_id");
		ObjectId post_id = new ObjectId(req.getParameter("post_id")); //ObjectId
		
		
		if(pdao.userLikeBoolean(userid, post_id)){
			return "<i class='glyphicon glyphicon-thumbs-up' style='font-size: 20px; color: blue;'></i>";
		} else {
			return "<i class='glyphicon glyphicon-thumbs-up' style='font-size: 20px; color: white;'></i>";
		}
	}*/
	
	
	// 醫뗭븘�슂 �벑濡�--------------------------------------------------------
		@RequestMapping("/likeBtnClick")
		@ResponseBody
		public String likeData(HttpSession session, HttpServletRequest req) throws Exception {
			String userid = (String) session.getAttribute("auth_id");
			ObjectId post_id = new ObjectId(req.getParameter("post_id")); //ObjectId
			System.out.println("醫뗭븘�슂 post_id=" + post_id);
			String date = "" + System.currentTimeMillis();
			
			if(pdao.userLikeBoolean(userid, post_id)){
				pdao.likeremove(post_id, userid);
				return "remove";
			} else {
				Map datamap = new HashMap<>();
				datamap.put("post_id", post_id);
				datamap.put("userid", userid);
				datamap.put("date", date);
				datamap.put("mod", "like");
				pdao.likeperson(datamap, userid); // 媛쒖씤�젙蹂댁뿉 post_id�벑濡�
				return "false";
			}
			
		}
		
	
	
	// 肄붾찘�듃 異붽��벑濡� /遺덈윭�삤湲� �뿉�씠�옉�뒪-------------------------------------
	@RequestMapping("/comentAjax")
	public ModelAndView postlist(HttpServletRequest req, HttpSession session) { 

		// 肄붾찘�듃 �벑濡앺븳 �궗�엺 id/�봽濡쒗븘�궗吏�/肄붾찘�듃 �궡�슜/寃뚯떆湲�id(李얠쓣�궎)

		String id = (String) session.getAttribute("auth_id");
		Map map = alldao.selectOne("select URL from PICTURE where ID='%s' order by NUM desc", id); // �궗吏꾩젙蹂�
		String pictureUrl = (String) map.get("URL");
		String comment = req.getParameter("coment");
		ObjectId post_id = new ObjectId(req.getParameter("post_id")); //ObjectId
		String date = "" + System.currentTimeMillis();

		Map commentmap = new HashMap<>();
		commentmap.put("id", id);
		commentmap.put("comment", comment);
		commentmap.put("date", date);
		
		/*{date=1490852093068, comment=肄붾찘�듃1, id=namnami}
		{date=1490852740204, comment=湲�湲�湲�, id=namnami}
		{date=1490857921728, comment=�뙎湲�4, id=namnami}*/
		List<Map> comentlist = pdao.comentData(commentmap, post_id);
		for(Map<String,Object> m: comentlist){
			Map picmap = alldao.selectOne("select URL from PICTURE where ID='%s' order by NUM desc", (String)m.get("id")); // �궗吏꾩젙蹂�
			String pictureUrl2 = (String) map.get("URL");
			m.put("pictureUrl", pictureUrl2);
		}
		mav.addObject(comentlist);
		mav.setViewName("/poat/postAjax");
		return mav;
	}

	//�돱�뒪�뵾�뱶 �뜲�씠�꽣---------------------------------------------
	@RequestMapping("/newsFeed")
	public ModelAndView NewsFeed(HttpSession session){
		
		String userId=(String)session.getAttribute("auth_id");
		List feedlist = pdao.getNewsFeed(userId);
		
		for(Map<String,Object> feedmap:(List<Map>)feedlist){
			System.out.println(feedmap.toString());
			String id = (String)feedmap.get("userid");
			Map picmap = alldao.selectOne("select URL from PICTURE where ID='%s' order by NUM desc",id); 
			String pictureUrl = (String) picmap.get("URL");
			feedmap.put("pictureUrl", pictureUrl);
		}
		
		mav.addObject("feedlist",feedlist);
		mav.setViewName("nFeed_t");
		return mav;
	}
	
	@RequestMapping("/nav")
	@ResponseBody
	public List postnav(HttpSession session) {
		List resultlist = new ArrayList<>();
		if (session.getAttribute("auth_id") != null) {
			String userid = (String) session.getAttribute("auth_id");
			resultlist.addAll(pdao.alarmList(userid));
		}
		return resultlist;
	}

}
