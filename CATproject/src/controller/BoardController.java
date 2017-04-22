package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	ServletContext application;

	@RequestMapping(value = "/imageUpload", method = RequestMethod.POST)
    public void communityImageUpload(HttpServletRequest request, 
    		HttpServletResponse response, @RequestParam MultipartFile upload) throws IOException{
 
        PrintWriter printWriter = null;
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
 
        try{
 
//            String fileName = upload.getOriginalFilename();
//            byte[] bytes = upload.getBytes();
//            String uploadPath = "저장경로/" + fileName;//저장경로
// 
//            out = new FileOutputStream(new File(uploadPath));
//            out.write(bytes);
            String callback = request.getParameter("CKEditorFuncNum");
// 
            printWriter = response.getWriter();
        	long ti=System.currentTimeMillis();
        	String orgName=ti+"_"+upload.getOriginalFilename();
            String fileUrl = "/images/" + orgName;//url경로
        	String path = application.getRealPath("/images"); // 업로드하고싶은 실 경로를 구하고,
    		File dir = new java.io.File(path);
    		if (!dir.exists()){ // 경로는 없다면 Exception 발생하니까
    			dir.mkdirs(); // 디렉토리 생성하고.
    		}

    		// 옮겨둘 파일에 아웃을 만들고, read() ,, write() 작업
    		File dest = new File(path, orgName); // 있다면 겹침.. 중복을 피하는건 요령껏.
    		// 만약에 원본이름이 다시 사용되어야 된다고 생각하면, 따로 저장을 해둘것.

    		upload.transferTo(dest);
 
            printWriter.println("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction("
                    + callback
                    + ",'"
                    + fileUrl
                    + "','이미지를 업로드 하였습니다.'"
                    + ")</script>");
            printWriter.flush();
 
        }catch(IOException e){
            e.printStackTrace();
        } finally {
            try {
                if (printWriter != null) {
                    printWriter.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
 
        return;
    }
}
