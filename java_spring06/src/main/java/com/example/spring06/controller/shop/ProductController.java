package com.example.spring06.controller.shop;

import java.io.File;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring06.model.shop.ProductDAO;
import com.example.spring06.model.shop.ProductDTO;

@Controller
@RequestMapping("/shop/product/*")	// 공통적인 url pattern
public class ProductController {
	
	@Inject
	ProductDAO productDao;
	
	/**
	 * <PRE>
	 * 1. MethodName : write
	 * 2. ClassName  : ProductController
	 * 3. Comment   : 상풍 등록 페이지
	 * 4. 작성자    : 박원영
	 * 5. 작성일    : 2025. 3. 23. 오후 4:54:19
	 * </PRE>
	 *   @return String
	 *   @return
	 */
	@RequestMapping("write.do")
	public String write() {
		return "shop/product_write"; 	// 상품 등록 페이지
	}
	
	@RequestMapping("insert.do")
	public String insert(ProductDTO dto, HttpServletRequest request ) {
		String filename = "-";	// 첨부 파일이 없을 때
		if( !dto.getFile1().isEmpty() ) {	// 첨부파일이 있을때
			filename = dto.getFile1().getOriginalFilename();	// 파일 이름
			try {
				// application 객체 생성(서버 전체에서 공유)
				ServletContext  application = request.getSession().getServletContext();
				// 실제 서비스 경로
				String path = application.getRealPath("/WEB-INF/views/images");
				new File(path).mkdir();		// 디렉토리가 없으면은 디렉토리 생성
				// 첨부파일이 지정된 디렉토리에 복사
				dto.getFile1().transferTo(new File(path + filename));
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		dto.setFilename(filename);	// 첨부파일 이름 저장
		productDao.insert(dto);		// 레코드 저장
		
		return "redirect:/shop/product/list.do";
	}
	
	@RequestMapping("list.do")
	public ModelAndView list(ModelAndView mav) {
		mav.setViewName("/shop/product_list");
		mav.addObject("list", productDao.list());
		return mav;
	}
	// @PathVariable : 주소로 넘어오는 변수
	@RequestMapping("edit/{product_code}")
	public ModelAndView edit(@PathVariable("product_code") int product_code , ModelAndView mav) {
		mav.setViewName("/shop/product_edit");	// 페이지 이동
		mav.addObject("dto",productDao.detail(product_code));
		return mav;
	}
	
	@RequestMapping("update.do")
	public String update(ProductDTO dto, HttpServletRequest request) {
		String filename = "-";
		if ( !dto.getFile1().isEmpty() ) {		// 첨부파일이 있으면
			filename = dto.getFile1().getOriginalFilename();
			try {
				// application 객체 생성(서버 전체에서 공유)
				ServletContext  application = request.getSession().getServletContext();
				// 실제 서비스 경로
				String path = application.getRealPath("/WEB-INF/views/images");
				new File(path).mkdir();		// 디렉토리가 없으면은 디렉토리 생성
				// 첨부파일이 지정된 디렉토리에 복사
				dto.getFile1().transferTo(new File(path + filename));
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else {	// 첨부파일이 없으면
				// 기존 첨후파일을 목록 가져오기
			ProductDTO dto2 = productDao.detail(dto.getProduct_code());
			dto.setFilename(dto2.getFilename());
		}
		
		productDao.update(dto);
		return "redirect:/shop/product/list.do";
	}
	
	@RequestMapping("delete.do")
	public String delete(int product_code, HttpServletRequest request) {
		String filename = productDao.file_info(product_code);
		if( filename != null && !filename.equals("-")) {
			ServletContext application = request.getSession().getServletContext();
			String path = application.getRealPath("/WEB-INF/views/images/");
			File file = new File(path + filename);
			if(file.exists())	// 첨부파일이 있으면
				file.delete();	// 파일 삭제
		}
		
		productDao.delete(product_code);	// 레코드 삭제
		return "redirect:/shop/product/list.do";
	}
	
	@RequestMapping("detail/{product_code")
	public ModelAndView detail(@PathVariable int product_code, ModelAndView mav) {
		mav.setViewName("/shop/product/product_detail");
		mav.addObject("dto", productDao.detail(product_code));
		return mav;
	}
	
	
	
	
	
	
}
