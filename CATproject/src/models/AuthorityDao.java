package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.AllDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class AuthorityDao {
	@Autowired
	AllDao alldao;
	@Autowired
	PictureDao pdao;
	private static final String DELETE_AUTH="delete from AUTHORITY where ID='%s'";
	private static final String SELECT_ALL_ID="select ID from MEMBER";
	private static final String SELECT_ID_AUTH="select ID, AUTHORITY from AUTHORITY where ID='%s'";
	private static final String SET_AUTH_TO_ID="insert into AUTHORITY(ID,AUTHORITY)"
											+" values ('%s','%s')";
	
	public void authSetting(String userid){
		alldao.update(SET_AUTH_TO_ID, userid, "user");
	}
	public String authChange(String userid,String authority){
		alldao.update(DELETE_AUTH, userid);
		switch(authority){
		case "gm":
			alldao.update(SET_AUTH_TO_ID, userid, "gm");
			alldao.update(SET_AUTH_TO_ID, userid, "vip");
			alldao.update(SET_AUTH_TO_ID, userid, "user");
			break;
		case "vip":
			alldao.update(SET_AUTH_TO_ID, userid, "vip");
			alldao.update(SET_AUTH_TO_ID, userid, "user");
			break;
		
		case "user":
			alldao.update(SET_AUTH_TO_ID, userid, "user");
			break;
		default :
			return "nnnn";
		}
	
		return "yyyy";
	}
	
	public List authorityList(String userid){
		if(userAuthority(userid).contains("master")){
			return userAuthorityToMaster();
		} else if(userAuthority(userid).contains("gm")){
			return userAuthorityToGm();
		} else{
			return null;
		}
	}
	public List userAuthorityToGm(){
		List<Map> usrList=alldao.selectList(SELECT_ALL_ID);
		List<Map<String,String>> resultList=new ArrayList<>();
		for(Map<String,String> idmap : usrList){
			Map<String,String> tmp=new HashMap<>();
			String id=idmap.get("ID");
			List alist=userAuthority(id);
			if(alist.contains("master") || alist.contains("gm")){
				continue;
			} else {
				tmp.put("img", pdao.getLastImageURL(id));
				tmp.put("userid", id);
				tmp.put("userauth", maxAuthority(alist));
				if(alist.contains("vip")){
					tmp.put("auth", "vip");
				}else{
					tmp.put("auth", "user");
				}
				resultList.add(tmp);
			}
		}
		
		return resultList;
	}
	
	public List userAuthorityToMaster(){
		List<Map> usrList=alldao.selectList(SELECT_ALL_ID);
		List<Map<String,String>> resultList=new ArrayList<>();
		for(Map<String,String> idmap : usrList){
			Map<String,String> tmp=new HashMap<>();
			String id=idmap.get("ID");
			List alist=userAuthority(id);
			if(alist.contains("master")){
				continue;
			} else {
				tmp.put("img", pdao.getLastImageURL(id));
				tmp.put("userid", id);
				tmp.put("userauth", maxAuthority(alist));
				if(alist.contains("gm")){
					tmp.put("auth", "gm");
				}else if(alist.contains("vip")){
					tmp.put("auth", "vip");
				}else{
					tmp.put("auth", "user");
				}
				System.out.println(tmp);
				resultList.add(tmp);
			}
		}
		
		return resultList;
	}
	public List userAuthority(String id){
		System.out.println(id);
		List<Map> list=alldao.selectList(SELECT_ID_AUTH,id);
		List resultList=new ArrayList<>();
		for(Map map : list){
			resultList.add(map.get("AUTHORITY"));
		}
		
		return resultList;
	}
	public String maxAuthority(List auth){
		System.out.println(auth);
		if(auth.contains("master")){
			return "master";
		} else if(auth.contains("gm")){
			return "gm";
		} else if(auth.contains("vip")){
			return "vip";
		} else {
			return "user";
		}
	}
//	public List userAuthorityList(String userid){
//		
//	}
}
