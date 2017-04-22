package controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import models.MemberDao;
import models.PictureDao;

@Controller
@RequestMapping("/my")
public class myInfoController {
	@Autowired
	PictureDao pdao;
	
	@Autowired
	MemberDao mdao;	
	
	@Autowired
	ServletContext sc;
	
	
//===========================================	
//프로필 정보 (info.jsp) 
	@RequestMapping("/info")
	public ModelAndView infomainHandler(HttpSession session, HttpServletRequest request) {
		System.out.println("info 잘 들어옴");
		
		String id = (String)session.getAttribute("auth_id");
		String picURL = pdao.getLastImageURL(id);
		System.out.println("============================================================");
		System.out.println(picURL);
		System.out.println("============================================================");
		
/*		if(picURL== null){
			picURL = "/picture/profile.png";
		}	*/	
		
		// 리턴되는 값에는 이름, 나이, 성별, 이메일 주소들이 다 담겨져 있어야 함.
		Map val = new HashMap<>();
			val = pdao.getLastMemberInfo(id);
				
			
			 
		ModelAndView mav = new ModelAndView("t_infomain"); //(update_form.jsp)
			mav.addObject("url", picURL);
			mav.addObject("map", val);
			
		return mav;
	}	
	
	//사진 정보 변경 (update_picture.jsp)
	@RequestMapping("/update_picture")
	public ModelAndView updatepictureHandler(@RequestParam Map map, HttpSession session, HttpServletRequest request,   @RequestParam(name="pic") MultipartFile file) {
		ModelAndView mav = new ModelAndView();
		Map val = new HashMap<>();

		
        boolean empty = file.isEmpty(); //파일이 있냐 없냐.. 
        	System.out.println(" 파일 선택안했냐? isEmpty == "+empty);
        
        String name = file.getName(); //파라미터명 확인
        	System.out.println("파라미터명이 뭐로 넘어왔냐? getName == "+name);    
        
        long size = file.getSize(); //파일 크기(byte)
        	System.out.println("파일 크기(byte)가 어떻게 되냐? getSize == "+size + "(byte)");    
        
        String orgName = file.getOriginalFilename(); //실제 파일 이름..
        	System.out.println("사용자가 올린 파일이름 뭐냐? getOriginalFilename == "+orgName);    
        
        String content = file.getContentType(); //파일 포멧
        	System.out.println("파일 형식? getContentType == "+content);
        
            String path = sc.getRealPath("/share");  //업로드 하고 싶은 경로를 구하고
            File dir = new File(path); //만약 이 경로가 없어도 오류 안뜨게 하기 위해서 
            if(!dir.exists()){ //경로가 없다면 exception 발생하니까
                dir.mkdirs();    //디렉토리 생성하고..
            }
            
            long ti = System.currentTimeMillis();// 현재시간 뽑음
            String rename = ti+"_"+orgName;
            File dest = new File(path, rename);            
            
        
            try {
				file.transferTo(dest);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //원리는 같다.. 복제 원리임..input도 필요가 없어지게 됨..  속도가 훨씬 빠름
            
            
		String id = (String)session.getAttribute("auth_id");
		
			val.put("id", id);
			val.put("filename", "/share/"+rename); 
			
		int r = pdao.updatePictureImg(val);
		
		if(r!=0){ //업데이트 성공
			mav.setViewName("redirect:/my/info");
		}else{ // 업데이트 실패
			System.out.println("update_picture문제. 사진 정보 업데이트 실패");
			mav.setViewName("redirect:/my/info");
		}
		
		
		return mav;
	}		

	//(사진제외)정보 변경 결과 (update_info.jsp)
	@RequestMapping("/update_info")
	public ModelAndView updateinfoHandler(@RequestParam Map map, HttpSession session, HttpServletRequest request) {
		System.out.println("update_info 잘 들어옴");
		ModelAndView mav = new ModelAndView();
		//update_form.jsp 에서 수정한 정보들을 던져주는 파라미터 확보 계열
		try {
			request.setCharacterEncoding("UTF-8");
			String id = (String)session.getAttribute("auth_id");
			

/*			//원래 나이랑, 성별이랑 패스워드
			int sessionage = (Integer)session.getAttribute("auth_age");
			String sessiongender = (String)session.getAttribute("auth_gender");
			String sessionpass = (String)session.getAttribute("auth_pass");
	
			System.out.println("================================================");
			System.out.println("원래 세션에 저장된 age:  " + sessionage);
			System.out.println("원래 세션에 저장된 gender:  " + sessiongender);
			System.out.println("원래 세션에 저장된 pass:  " + sessionpass);
			
*/
			//바뀐 나이, 성별, 패스워드, 이메일, 이름
			String gender = (String)map.get("gender");
			String pass = (String)map.get("pass");
			int age = Integer.parseInt((String)(map.get("age")));
			String email = ((String)map.get("email")).toUpperCase();			
			String name = (String)map.get("name");
				
			
			//세션값 변경해주고
			session.setAttribute("auth_age", age);
			session.setAttribute("auth_pass", pass);	
			session.setAttribute("auth_gender", gender); 

/*			//바꼈는지 확인
			int csessionage = (Integer)session.getAttribute("auth_age");
			String csessiongender = (String)session.getAttribute("auth_gender");
			String csessionpass = (String)session.getAttribute("auth_pass");
			*/
				
			
			//map2에 put 해서 sql 업데이트 시키기 
			
			Map map2 = new HashMap<>();
			
			map2.put("id", id);
			map2.put("pass", pass);
			map2.put("name", name);
			map2.put("age", age);
			map2.put("gender", gender);
			map2.put("email", email);
			
			System.out.println(map2);
			//update 시키기
			int r=pdao.updateMemberInfo(map2);
			
			if(r!=0){ //업데이트 성공
				mav.setViewName("redirect:/my/info");
			}else{ // 업데이트 실패
				System.out.println("update_info 문제. 정보 업데이트 실패");
				mav.setViewName("redirect:/my/info");
			}
			

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return mav;
	}			
	
	
//===========================================	
//회원탈퇴 (leave.jsp)
	

	//  비밀번호 확인 후 탈퇴 요청 (input_pass.jsp)
	@RequestMapping("/leave") //(leave.jsp)
	public ModelAndView leaveHandler() {
		ModelAndView mav = new ModelAndView("t_leave");   
		
		return mav;
	}	
	
	
	//  회원 탈퇴 처리하고 결과 (leave_result.jsp)
	@RequestMapping("/leave_result") 
	public ModelAndView leaveresultHandler(@RequestParam Map map, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		Map map1 = new HashMap<>();
		String pass = (String)map.get("pass");
		String id = (String) session.getAttribute("auth_id");
		
		map1.put("id", id);
		map1.put("pass", pass);
		
			
		boolean f = mdao.deleteMember(map1);
		
		if(f==true){
			mav.setViewName("redirect:/log/logout");
		}else{ //오류
			mav.setViewName("redirect:/my/leave");
		}
			
		return mav;
	}	
	
	
//===========================================
	
}







