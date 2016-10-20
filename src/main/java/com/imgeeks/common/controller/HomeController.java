/*
 * Copyright (C), 2013-2014, 南京华内斯信息技术有限公司
 * FileName: LoginController.java
 * Author:   dong
 * Date:     Nov 4, 2014 7:43:00 PM
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.imgeeks.common.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imgeeks.utils.MD5;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.imgeeks.base.BaseController;
import com.imgeeks.bean.MessageBean;
import com.imgeeks.common.bean.Label;
import com.imgeeks.common.bean.SessionUser;
import com.imgeeks.common.bean.User;
import com.imgeeks.common.bean.UserEducation;
import com.imgeeks.common.bean.UserProfile;
import com.imgeeks.common.bean.UserSkill;
import com.imgeeks.common.bean.UserWorkHistory;
import com.imgeeks.common.constant.Constant;
import com.imgeeks.common.service.LabelService;
import com.imgeeks.common.service.UserEducationService;
import com.imgeeks.common.service.UserProfileService;
import com.imgeeks.common.service.UserService;
import com.imgeeks.common.service.UserSkillService;
import com.imgeeks.common.service.UserWorkHistoryService;
import com.imgeeks.filter.AuthFilter;
import com.imgeeks.utils.BasicAjaxUtil;
import com.imgeeks.utils.StringUtil;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * 
 * @author dong
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Controller
public class HomeController extends BaseController {
	
    /**
     * LOG对象
     */
    private static final Logger logger = Logger.getLogger(HomeController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private LabelService labelService;
    @Autowired
    private UserProfileService userProfileService;
    @Autowired
    private UserSkillService userSkillServce;
    @Autowired
    private UserWorkHistoryService userWorkHistoryService;
    @Autowired
    private UserEducationService userEducationService;

    @RequestMapping("/index")
    public String index(HttpServletRequest request) {

        SessionUser sessionUser = this.getSessionUser(request);
        if (null != sessionUser) {
            if (Constant.Role.ROLECODE_ADMIN.equals(sessionUser.getRoleCode())) {
                return "redirect:admin/main.htm";
            } else {
                return "redirect:user/homepage.htm";
            }
        }

        return "home/ui-login.html";
    }


    /**
     * 功能描述: <br>
     * 〈登录校验〉
     *
     * @param request <code>HttpServletRequest</code>对象
     * @param response <code>HttpServletResponse</code>对象
     * @return 跳转路径
     */
    @RequestMapping("/login")
    @ResponseBody
    public Object login(HttpServletRequest request, HttpServletResponse response) {


        // 校验邮箱
        String email = request.getParameter("email");
        // 校验密码
        String userPwd = request.getParameter("password");

        if (StringUtil.isEmpty(email) || StringUtil.isEmpty(userPwd)) {
            return new MessageBean(false,"邮箱 或 密码不能为空！");
        } else {
            // 查找登录信息
            MD5 md5 = new MD5();
            Subject users = SecurityUtils.getSubject();
            String md5pwd = md5.getMD5ofStr(userPwd);
            UsernamePasswordToken token = new UsernamePasswordToken(email,md5pwd);
            token.setRememberMe(true);
            try {
            	users.login(token);
                User userRole = userService.getUserRoleByEmail(email);
        		SessionUser currentUser = new SessionUser();
        		currentUser.setUserid(userRole.getId());
        		currentUser.setUserName(userRole.getUsername());
        		currentUser.setEmail(userRole.getEmail());
        		currentUser.setRealName(userRole.getRealname());
        		currentUser.setRoleName(userRole.getRoles().get(0).getRolename());
        		currentUser.setRoleCode(userRole.getRoles().get(0).getRolecode());
        		currentUser.setHeadimg(userRole.getHeadimg());
        		logger.info("用户：" + currentUser.getUserName() + "-登入系统!");

        		request.getSession().setAttribute(Constant.Session.SESSION_NAME, currentUser);
                SavedRequest savedRequest = WebUtils.getSavedRequest(request);
                String redirecturl = null;

                // 获取保存的URL
                if (savedRequest != null && savedRequest.getRequestUrl() != null) {
                	redirecturl = savedRequest.getRequestUrl();
                }
                return new MessageBean(true,redirecturl);

            } catch (UnknownAccountException e) {
				token.clear();
                return new MessageBean(false,"邮箱 或 密码不能为空！");
            }catch (IncorrectCredentialsException e) {
				token.clear();
                return new MessageBean(false,"邮箱 或 密码不能为空！");
			}

        }
    }

    
    /**
     * 用户注册界面
     * @param request
     * @return
     */
    @RequestMapping("/regeister")
    public String regeister(HttpServletRequest request) {
        return "home/ui-register.html";
    }
    
    
    /**
     * 用户注册界面
     * @param request
     * @return
     */
    @RequestMapping("/regeisterinfo")
    public String regeisterinfo(HttpServletRequest request, HttpServletResponse response) {
    	JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("flag", false);
        
        //校验用户名
        String userName = request.getParameter("username");
        // 校验邮箱
        String email = request.getParameter("email");
        // 校验密码
        String userPwd = request.getParameter("password");
        MD5 md5 = new MD5();
        Boolean checkUserByEmail = userService.checkUserByEmail(email);
        if(checkUserByEmail){
        	jsonObject.addProperty("flag", true);
        	boolean regeisterUser = userService.regeisterUser(userName,email,md5.getMD5ofStr(userPwd));
        	if(regeisterUser){
            	jsonObject.addProperty("flag", true);
            	request.setAttribute("email", email);
            	request.setAttribute("password", userPwd);
            	login(request,response);
        	}else{
        		jsonObject.addProperty("message", "注册失败！");
        	}
        }else{
        	jsonObject.addProperty("message", "邮箱已被注册！");
        }
       
        BasicAjaxUtil.writeJson(response, jsonObject.toString());
        return null;
    }
    
    
    /**
     * 
     * 功能描述: 注销
     * 
     * @param request
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping("/indexLogout")
    public String indexLogout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("sessionUser");
        return "include/loginInfo.ftl";
    }

    /**
     * 功能描述: 注销
     * 
     * @param request
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
    	Subject subject = SecurityUtils.getSubject();
    	if (subject.isAuthenticated()) {
    		subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
    	}
        return "redirect:index.htm";
    }


}
