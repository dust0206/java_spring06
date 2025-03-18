package com.example.spring06.controller.member;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring06.model.member.AdminDAO;
import com.example.spring06.model.member.MemberDTO;

@Controller
@RequestMapping("/admin/*")		// 공통적인 url pattern
public class AdminController {
	
	@Inject
	AdminDAO adminDao;
	
	@RequestMapping("login.do")
	public String login() {
		System.out.println("login.do");
		return "admin/login";	// views/admin/login.jsp
	}
	
	@RequestMapping("login_check.do")	// 세부적인 url
//	public ModelAndView login_check(@ModelAttribute MemberDTO dto, HttpSession session, ModelAndView mav) {
	public ModelAndView login_check(MemberDTO dto, HttpSession session, ModelAndView mav) {
		System.out.println("login_check.do");
		String name = adminDao.login(dto);
		if(name != null) {	//로그인 성공
			// 세션에 변수에 관리자랑 일반유저 저장
			session.setAttribute("admin_userid", dto.getUserid());
			session.setAttribute("admin_name", name);
			session.setAttribute("userid", dto.getUserid());
			session.setAttribute("name", name);
			mav.setViewName("admin/admin");		// 페이지 이름(view/admin/admin.jsp
			mav.addObject("message", "success");	// 자료 저장
		} else {	// 로그인 실패
			mav.setViewName("admin/login");
			mav.addObject("message", "error");
		}
		
		return mav;
	}
	// ModelAndView를 Model로 바꿔봄
/*	
	@RequestMapping("login_check.do")
	public String log_check_1(MemberDTO dto, HttpSession session, Model model) {
		String name = adminDao.login(dto);
		if(name != null) {	//로그인 성공
			// 세션에 변수 저장
			session.setAttribute("admin_userid", dto.getUserid());
			session.setAttribute("admin_name", name);
			session.setAttribute("userid", dto.getUserid());
			session.setAttribute("name", name);
			model.addAttribute("message", "success");	// 자료 저장
		} else {	// 로그인 실패
			model.addAttribute("message","error");
		}
		
		return "admin/login";
	}
*/	
	
	@RequestMapping("logout.do")
	public String logout(HttpSession session) {
		session.invalidate();	// 세션 클리어
		return "redirect:/admin/login.do?message=logout";
	}
	
	
}
