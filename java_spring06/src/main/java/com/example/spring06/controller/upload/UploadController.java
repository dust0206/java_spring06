package com.example.spring06.controller.upload;

import java.io.File;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class UploadController {

	// servlet-context.xml에 지정된 첨부파일의 저장 디렉토리를 불러온다
	@Resource(name = "upload_path")
	String upload_path;
//	String upload_path = "c:/eclipse_fileupload" 의 형식으로 지정해도 된다;
	
	@RequestMapping("/upload/input.do")
	public String input() {
		return "upload/input";		// WEB-INF/view/upload/input.jsp
	}
	
	
	// form tag의 name="file" 과 MultipartFile file의 file이 같기 때문에 MultipartFile file에 자동으로 전달된다
	@RequestMapping("/upload/upload.do")
	public ModelAndView uploadForm(MultipartFile file, ModelAndView mav) throws Exception {
		
		System.out.println("file.getName : " + file.getName());
		System.out.println(" file.getOriginalFilename : " + file.getOriginalFilename());
		
		// 첨부파일의 이름
		String savedName = file.getOriginalFilename();	// 첨부파일 이름
		
		// uuid를 추가한 파일이름 (여러사람이 업로드 하기 때문에 같은 이름을 피하고자 함)
		savedName = uploadFile(savedName, file.getBytes());
		
		// jsp page의 이름
		mav.setViewName("upload/upload_result");	// 출력페이지
		
		// jsp page에 전달할 변수 저장
		mav.addObject("saved_name", savedName);
		
		// page로 포워드
		return mav;
	}
	
	// 파일이름 중복하지 않게 하기
	public String uploadFile(String originalName, byte[] fileData) throws Exception {
		
		// uuid 생성 - Universal Unique Identifier, 범용 고유 식별자
		UUID uid = UUID.randomUUID();		// uuid : 파일이름이 중복되지 않도록
		
//		System.out.println("uid : " + uid);
		String savedName = uid.toString() + "_" + originalName;  // 파일이름 앞에 uuid를 붙인다
		
		File target = new File(upload_path, savedName );
		
		// 파일 복사 - 서버의 임시 디렉토리에서 지정된 곳으로 복사 된다
		FileCopyUtils.copy(fileData, target);
		return savedName;
	}
}
