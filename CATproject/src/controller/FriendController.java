package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import models.AllDao;
import models.FriendDao;
import models.LoginRecordDao;

@Controller
@RequestMapping("/friend")
public class FriendController {

	@Autowired
	ModelAndView mav;
	@Autowired
	AllDao alldao;
	@Autowired
	FriendDao fdao;
	@Autowired
	LoginRecordDao logdao;

	@RequestMapping("/search")
	@ResponseBody
	public List writePost(HttpSession session, @RequestParam Map map) {
		String userid = (String) session.getAttribute("auth_id");
		System.out.println("유저아이디 : " + userid);
		Map fmap = fdao.fmap(userid);
		System.out.println("$$$$$$$$$$$$$$$$$$$$");
		System.out.println(fmap);
		System.out.println("$$$$$$$$$$$$$$$$$$$$");
		List[] flist = new List[2];
		flist[0] = (List) fmap.get("flist");
		flist[1] = (List) fmap.get("sendreqflist");
		String sql = "select a.ID,b.URL,a.AGE,a.GENDER,b.NUM from MEMBER a,PICTURE b "
				+ "where a.ID=b.ID and a.ID LIKE '%#{fid }%' order by a.ID,b.NUM";
		List<Map> usrlist = alldao.selectList(sql, map);
		for (int i = usrlist.size() - 1; i >= 0; i--) {
			if (flist[0].contains(usrlist.get(i).get("ID")) || flist[1].contains(usrlist.get(i).get("ID"))
					|| usrlist.get(i).get("ID").equals(userid)) {
				usrlist.remove(i);
				continue;
			}
			if (i > 0) {
				if (usrlist.get(i).get("ID").equals(usrlist.get(i - 1).get("ID"))) {
					usrlist.remove(i - 1);
				}
			}
		}
		// for(Map<String,String> tmp: (List<Map>)usrlist ){
		// String replace=tmp.get("ID").replaceFirst((String)map.get("fid"),
		// "<b>"+map.get("fid")+"</b>");
		// tmp.put("ID", replace);
		// }
		// System.out.println(usrlist);

		return usrlist;
	}

	@RequestMapping("/reqf")
	@ResponseBody
	public String aaa(HttpSession session, @RequestParam String fid) {
		String userid = (String) session.getAttribute("auth_id");
		System.out.println("삽입시 유저아이디" + userid);
		fdao.reqf(userid, fid);
		return "";
	}

	@RequestMapping("/nav")
	@ResponseBody
	public List nav(HttpSession session) {
		List resultlist = new ArrayList<>();
		if (session.getAttribute("auth_id") != null) {
			String userid = (String) session.getAttribute("auth_id");
			Map fmap = fdao.fmap(userid);
			List recieveflist = (List) fmap.get("recieveflist");
			for (String fid : (List<String>) recieveflist) {
				String sql = "select a.ID,a.GENDER,a.AGE,b.URL from MEMBER a,PICTURE b where a.ID=b.ID and a.ID='%s'"
						+ " order by b.NUM desc";
				Map tmp = alldao.selectOne(sql, fid);
				resultlist.add(tmp);
			}
		}
		return resultlist;
	}

	@RequestMapping("/accept")
	@ResponseBody
	public String accept(HttpSession session, @RequestParam String fid) {
		String userid = (String) session.getAttribute("auth_id");
		fdao.accept(userid, fid);
		return "";
	}

	@RequestMapping("/refuse")
	@ResponseBody
	public String refuse(HttpSession session, @RequestParam String fid) {
		String userid = (String) session.getAttribute("auth_id");
		fdao.refuse(userid, fid);
		return "";
	}

//	@RequestMapping("/init")
//	@ResponseBody
//	public String aaa() {
//		String sql = "select ID from MEMBER GROUP BY ID";
//		List list = alldao.selectList(sql);
//		System.out.println(list);
//		for (Map<String, String> map : (List<Map>) list) {
//			logdao.logInit(map.get("ID"));
////			fdao.insertinit(map.get("ID"));
//		}
//		return "";
//	}

}
