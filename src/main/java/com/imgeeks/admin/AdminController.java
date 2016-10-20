package com.imgeeks.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.imgeeks.bean.MessageBean;
import com.imgeeks.common.service.CityService;
import com.imgeeks.common.service.UserService;
import com.imgeeks.utils.BasicAjaxUtil;
import com.imgeeks.utils.QueryResult;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private CityService cityService;
	@Autowired
	private UserService userService;
	@RequestMapping("/main")
	public String main(HttpServletRequest request) {

		return "admin/main.html";
	}

	@RequestMapping("/top")
	public String top(HttpServletRequest request) {

		return "admin/top.html";
	}

	@RequestMapping("/left")
	public String left(HttpServletRequest request) {

		return "admin/left.html";
	}

	@RequestMapping("/index")
	public String index(HttpServletRequest request) {

		return "admin/index.html";
	}
	
	@RequestMapping("/userright")
	public String userright(HttpServletRequest request) {
		return "admin/right.html";
	}
	
	@RequestMapping("/userlist")
	public String userlist(HttpServletRequest request,int pageno,String username,String cityname) {
		List cityList = cityService.getAllCity();
		QueryResult<?> qr = userService.getUserByPageno(pageno,username,cityname);
		request.setAttribute("username", username);
		request.setAttribute("cityname", cityname);
		request.setAttribute("data", qr.getDatas());
		request.setAttribute("islast", qr.getIsLastPage());
		request.setAttribute("pageno", qr.getPageNumber());
		request.setAttribute("total", qr.getTotalDataCount());
		request.setAttribute("cityList",cityList);
		return "common/userlist.html";
	}
	
	
	@RequestMapping("/deleteuser")
	public String deleteuser(HttpServletRequest request,HttpServletResponse response,int userid) {
		boolean  b = userService.deleteUserByUserId(userid);
		BasicAjaxUtil.writeJsonObj(response, new MessageBean(b,""));
		return null;
	}
	
	
}
