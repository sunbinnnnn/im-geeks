package com.imgeeks.common.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imgeeks.utils.DateUtil;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.baidu.ueditor.PathFormat;
import com.baidu.ueditor.define.FileType;
import com.google.gson.JsonObject;
import com.imgeeks.base.BaseController;
import com.imgeeks.bean.MessageBean;
import com.imgeeks.common.bean.ChatRoom;
import com.imgeeks.common.bean.Label;
import com.imgeeks.common.bean.SessionUser;
import com.imgeeks.common.bean.User;
import com.imgeeks.common.bean.UserEducation;
import com.imgeeks.common.bean.UserProfile;
import com.imgeeks.common.bean.UserProject;
import com.imgeeks.common.bean.UserSkill;
import com.imgeeks.common.bean.UserWorkHistory;
import com.imgeeks.common.constant.Constant;
import com.imgeeks.common.service.ChatRoomService;
import com.imgeeks.common.service.CityService;
import com.imgeeks.common.service.HobbyService;
import com.imgeeks.common.service.HoyoService;
import com.imgeeks.common.service.LabelService;
import com.imgeeks.common.service.UserAttentionService;
import com.imgeeks.common.service.UserEducationService;
import com.imgeeks.common.service.UserProfileService;
import com.imgeeks.common.service.UserProjectService;
import com.imgeeks.common.service.UserService;
import com.imgeeks.common.service.UserSkillService;
import com.imgeeks.common.service.UserSupportService;
import com.imgeeks.common.service.UserWorkHistoryService;
import com.imgeeks.utils.BasicAjaxUtil;
import com.imgeeks.utils.QueryResult;
import com.imgeeks.utils.UtilTools;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    private static final Logger logger = Logger.getLogger(UserController.class);
    @Autowired
    private CityService cityService;
    @Autowired
    private HobbyService hobbyService;
    @Autowired
    private UserService userService;
    @Autowired
    private LabelService labelService;
    @Autowired
    private UserProfileService userProfileService;
    @Autowired
    private UserSkillService userSkillSerivice;
    @Autowired
    private UserWorkHistoryService userWorkHistoryService;
    @Autowired
    private UserEducationService userEducationService;
    @Autowired
    private UserProjectService userProjectService;
    @Autowired
    private UserAttentionService userAttentionService;
    @Autowired
    private UserSupportService userSupportService;
    @Autowired
    private ChatRoomService chatRoomService;
    @Autowired
    private HoyoService hoyoService;

    /**
     * 用户完善信息
     *
     * @param request
     * @return
     */
    @RequestMapping("/wsxx")
    public String wsxx(HttpServletRequest request) {
        return "home/grzx-wsxx.html";
    }

    /**
     * 跳转网站首页
     *
     * @param request
     * @return
     */
    @RequestMapping("/homepage")
    public String indexsy(HttpServletRequest request) {
        SessionUser sessionUser = getSessionUser(request);
        int userid = sessionUser.getUserid();
        List<UserSkill> userKillByUsers = userSkillSerivice
                .getUserKillByUserid(userid);
        List<Integer> uids = userService.getUserIdByKilll(userKillByUsers);
        List ulist = userService.getUserAndSkillByUids(uids, userid);
        request.setAttribute("ulist", ulist);
        return "index.html";
    }

    /**
     * 跳转登陆页
     *
     * @param request
     * @return
     */
    @RequestMapping("/login")
    public String login(HttpServletRequest request) {

        return "home/login.html";
    }

    /**
     * 关注用户
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/attenuser")
    public String attenUser(HttpServletRequest request,
                            HttpServletResponse response, int otherid) {
        SessionUser sessionUser = getSessionUser(request);
        boolean b = userAttentionService.isAttentionUser(
                sessionUser.getUserid(), otherid);
        if (!b) {
            boolean bo = userAttentionService.insertAttentionUser(
                    sessionUser.getUserid(), otherid);
            BasicAjaxUtil.writeJsonObj(response, new MessageBean(bo,
                    bo ? "关注成功！！" : "服务器出现错误"));
        } else {
            BasicAjaxUtil.writeJsonObj(response, new MessageBean(false,
                    "你已经关注啦！！"));
        }
        return null;
    }

    /**
     * 谁关注了我
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/whoattenme")
    public String whoAttenMe(HttpServletRequest request,
                             HttpServletResponse response, int pageno) {
        SessionUser sessionUser = getSessionUser(request);
        List<User> userList = userAttentionService.whoAttentionMe(
                sessionUser.getUserid(), pageno);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userList", userList);
        map.put("pageno", pageno);
        BasicAjaxUtil.writeJsonObj(response, new MessageBean(true, "", map));
        return null;
    }

    /**
     * 点赞用户
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/supportuser")
    @ResponseBody
    public String supportUser(HttpServletRequest request,
                              HttpServletResponse response, int otherid) {
        SessionUser sessionUser = getSessionUser(request);
        boolean b = userSupportService.isSupportUser(sessionUser.getUserid(),
                otherid);
        if (!b) {
            boolean bo = userSupportService.insertSupportUser(
                    sessionUser.getUserid(), otherid);
            BasicAjaxUtil.writeJsonObj(response, new MessageBean(bo,
                    bo ? "点赞成功！！" : "服务器出现错误"));
        } else {
            BasicAjaxUtil.writeJsonObj(response, new MessageBean(false,
                    "你已经点赞啦！！"));
        }
        return null;
    }

    /**
     * 谁关注了我
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/whosuportme")
    public String whoSupportMe(HttpServletRequest request,
                               HttpServletResponse response, int pageno) {
        SessionUser sessionUser = getSessionUser(request);
        List<Map> userList = userSupportService.whoSupportMe(
                sessionUser.getUserid(), pageno);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userList", userList);
        map.put("pageno", pageno);
        BasicAjaxUtil.writeJsonObj(response, new MessageBean(true, "", map));
        return null;
    }

    /**
     * 获取用户基本信息
     */
    @RequestMapping("/requireuser")
    public String requireUser(HttpServletRequest request,
                              HttpServletResponse response) {
        SessionUser sessionUser = getSessionUser(request);
        UserProfile userProlfile = userProfileService
                .getUserJbxxProfile(sessionUser.getUserid());
        if (userProlfile == null) {
            BasicAjaxUtil.writeJsonObj(response, new MessageBean(false,
                    Constant.MESSAGE.CONTACT_ADMIN));
        } else {
            BasicAjaxUtil.writeJsonObj(response, new MessageBean(true, "",
                    userProlfile));
        }
        return null;
    }

    /**
     * 编辑用户信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/updateuser")
    public String updateUser(HttpServletRequest request,
                             HttpServletResponse response, User user, String grjj) {
        JsonObject jsonObject = new JsonObject();
        SessionUser sessionUser = getSessionUser(request);
        user.setId(sessionUser.getUserid());
        boolean b = userService.updateUserInformation(user, grjj);
        jsonObject.addProperty("flag", b);
        jsonObject.addProperty("message", b ? Constant.MESSAGE.UPDATE_SUCCESS
                : Constant.MESSAGE.UPDATE_FAILURE);
        BasicAjaxUtil.writeJson(response, jsonObject.toString());
        return null;
    }

    @RequestMapping("/insertworkhistory")
    public String insertworkhistory(HttpServletRequest request,
                                    HttpServletResponse response, UserWorkHistory userWorkHistory) {
        JsonObject jsonObject = new JsonObject();
        SessionUser sessionUser = getSessionUser(request);
        User u = new User();
        u.setId(sessionUser.getUserid());
        userWorkHistory.setUser(u);
        boolean b = userWorkHistoryService.insertWorkHhistory(userWorkHistory);
        jsonObject.addProperty("flag", b);
        jsonObject.addProperty("message", b ? Constant.MESSAGE.ADD_SUCCESS
                : Constant.MESSAGE.ADD_FAILURE);
        BasicAjaxUtil.writeJson(response, jsonObject.toString());
        return null;

    }

    /**
     * 修改工作经历
     *
     * @param request
     * @param response
     * @param userWorkHistory
     * @return
     */
    @RequestMapping("/updatetworkhistory")
    public String updatetWorkHistory(HttpServletRequest request,
                                     HttpServletResponse response, UserWorkHistory userWorkHistory) {
        JsonObject jsonObject = new JsonObject();
        SessionUser sessionUser = getSessionUser(request);
        User u = new User();
        u.setId(sessionUser.getUserid());
        userWorkHistory.setUser(u);
        boolean b = userWorkHistoryService.updatetWorkHistory(userWorkHistory);
        jsonObject.addProperty("flag", b);
        jsonObject.addProperty("message", b ? Constant.MESSAGE.EDIT_SUCCESS
                : Constant.MESSAGE.EDIT_FAILURE);
        BasicAjaxUtil.writeJson(response, jsonObject.toString());
        return null;

    }

    /**
     * 删除用户工作经历
     *
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping("/deluserworkhistory")
    public String delUserWork(HttpServletRequest request,
                              HttpServletResponse response, int id) {
        JsonObject jsonObject = new JsonObject();
        SessionUser sessionUser = getSessionUser(request);
        boolean b = userWorkHistoryService.delUserWork(id,
                sessionUser.getUserid());
        jsonObject.addProperty("flag", b);
        jsonObject.addProperty("message", b ? Constant.MESSAGE.DELE_SUCCESS
                : Constant.MESSAGE.DELE_FAILURE);
        BasicAjaxUtil.writeJson(response, jsonObject.toString());

        return null;
    }

    /**
     * 插入用户教育信息
     *
     * @param request
     * @param response
     * @param userEducation
     * @return
     */
    @RequestMapping("/insertuseredu")
    public String insertUseredu(HttpServletRequest request,
                                HttpServletResponse response, UserEducation userEducation) {
        JsonObject jsonObject = new JsonObject();
        SessionUser sessionUser = getSessionUser(request);
        User u = new User();
        u.setId(sessionUser.getUserid());
        userEducation.setUser(u);
        boolean b = userEducationService.insertUserEducation(userEducation);
        jsonObject.addProperty("flag", b);
        jsonObject.addProperty("message", b ? Constant.MESSAGE.ADD_SUCCESS
                : Constant.MESSAGE.ADD_FAILURE);
        BasicAjaxUtil.writeJson(response, jsonObject.toString());
        return null;
    }

    /**
     * 修改教育经历
     *
     * @param request
     * @param response
     * @return
     */

    @RequestMapping("/updatetUserEdu")
    public String updatetUserEdu(HttpServletRequest request,
                                 HttpServletResponse response, UserEducation userEducation) {
        JsonObject jsonObject = new JsonObject();
        SessionUser sessionUser = getSessionUser(request);
        User u = new User();
        u.setId(sessionUser.getUserid());
        userEducation.setUser(u);
        boolean b = userEducationService.updatetUserEdu(userEducation);
        jsonObject.addProperty("flag", b);
        jsonObject.addProperty("message", b ? Constant.MESSAGE.EDIT_SUCCESS
                : Constant.MESSAGE.EDIT_FAILURE);
        BasicAjaxUtil.writeJson(response, jsonObject.toString());
        return null;

    }

    /**
     * 删除用户教育信息
     *
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping("/deluseredu")
    public String delUserEdu(HttpServletRequest request,
                             HttpServletResponse response, int id) {
        JsonObject jsonObject = new JsonObject();
        SessionUser sessionUser = getSessionUser(request);
        boolean b = userEducationService
                .deluserEdu(id, sessionUser.getUserid());
        jsonObject.addProperty("flag", b);
        jsonObject.addProperty("message", b ? Constant.MESSAGE.DELE_SUCCESS
                : Constant.MESSAGE.DELE_FAILURE);
        BasicAjaxUtil.writeJson(response, jsonObject.toString());

        return null;
    }

    /**
     * 跳转展示大厅
     *
     * @param request
     * @return
     */
    @RequestMapping("/showuser")
    public String showuser(HttpServletRequest request) {
        return "home/showuser.html";
    }

    /**
     * 展示大厅
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/showhall")
    public String showhall(HttpServletRequest request,
                           HttpServletResponse response, String cityname, String direct,
                           int status, int sort) {
        JsonObject jsonObject = new JsonObject();
        SessionUser sessionUser = getSessionUser(request);
        List<Map<String, Object>> userlist = userService.getUserListByCase(
                cityname, direct, status, sort);
        BasicAjaxUtil.writeJsonObj(response,
                new MessageBean(true, "", userlist));
        return null;
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping("/queryUser")
    public String queryUser(HttpServletRequest request, String area,
                            String direct, String status, String sort, int pageno) {
        List cityList = cityService.getAllCity();
        List hobbyList = hobbyService.getAllHobby();
        return "home/showuser.html";
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping("/getCity")
    public String getCity(HttpServletRequest request,
                          HttpServletResponse response) {
        List cityList = cityService.getAllCity();
        BasicAjaxUtil.writeJsonObj(response,
                new MessageBean(true, "", cityList));
        return null;
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping("/getHobby")
    public String gethobby(HttpServletRequest request,
                           HttpServletResponse response) {
        List hobbyList = hobbyService.getAllHobby();
        BasicAjaxUtil.writeJsonObj(response, new MessageBean(true, "",
                hobbyList));
        return null;
    }

    /**
     * 跳转首页
     *
     * @param request
     * @return
     */
    @RequestMapping("/userhome")
    public String userhome(HttpServletRequest request) {
        SessionUser sessionUser = getSessionUser(request);
        Map<String, Object> asNum = userAttentionService
                .getAttentNum(sessionUser.getUserid());
        request.setAttribute("asNum", asNum);

        return "home/grzx-overflow.html";
    }

    /**
     * 添加技能
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/insertuserskill")
    public String insertUserSkill(HttpServletRequest request,
                                  HttpServletResponse response, UserSkill userSkill) {
        JsonObject jsonObject = new JsonObject();
        SessionUser sessionUser = getSessionUser(request);
        User u = new User();
        u.setId(sessionUser.getUserid());
        userSkill.setUser(u);
        boolean b = userSkillSerivice.insertSkill(userSkill);
        jsonObject.addProperty("flag", b);
        jsonObject.addProperty("message", b ? Constant.MESSAGE.ADD_SUCCESS
                : Constant.MESSAGE.ADD_FAILURE);
        BasicAjaxUtil.writeJson(response, jsonObject.toString());
        return null;

    }

    /**
     * 编辑技能
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/edituserskill")
    public String editUserSkill(HttpServletRequest request,
                                HttpServletResponse response, UserSkill userSkill) {
        JsonObject jsonObject = new JsonObject();
        SessionUser sessionUser = getSessionUser(request);
        User u = new User();
        u.setId(sessionUser.getUserid());
        userSkill.setUser(u);
        boolean b = userSkillSerivice.editSkill(userSkill);
        jsonObject.addProperty("flag", b);
        jsonObject.addProperty("message", b ? Constant.MESSAGE.EDIT_SUCCESS
                : Constant.MESSAGE.EDIT_FAILURE);
        BasicAjaxUtil.writeJson(response, jsonObject.toString());

        return null;
    }

    /**
     * 删除用户技能
     *
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping("/deluserskill")
    public String delUserSkill(HttpServletRequest request,
                               HttpServletResponse response, int id) {
        JsonObject jsonObject = new JsonObject();
        SessionUser sessionUser = getSessionUser(request);
        boolean b = userSkillSerivice.delSkill(id, sessionUser.getUserid());
        jsonObject.addProperty("flag", b);
        jsonObject.addProperty("message", b ? Constant.MESSAGE.DELE_SUCCESS
                : Constant.MESSAGE.DELE_FAILURE);
        BasicAjaxUtil.writeJson(response, jsonObject.toString());

        return null;
    }

    /**
     * 跳转用户信息
     *
     * @param request
     * @return
     */
    @RequestMapping("/userinfo")
    public String userinfo(HttpServletRequest request) {

        SessionUser sessionUser = (SessionUser) request.getSession()
                .getAttribute(Constant.Session.SESSION_NAME);
        int userid = sessionUser.getUserid();
        List<Label> labellist = labelService.getLabelByUserId(userid);
        List<Label> tjlabel = labelService.getOtherLabelByLabelList(labellist);
        List<UserSkill> userkilllist = userSkillSerivice
                .getUserKillByUserid(userid);
        UserProfile userProfile = userProfileService
                .getUserProfileByUserId(userid);
        User baseUserInformation = userService.getBaseUserInformation(userid);
        List<UserWorkHistory> userWorkHistoryList = userWorkHistoryService
                .getUserWorkHistoryByUserid(userid);
        List<UserEducation> userEducationList = userEducationService
                .getUserEducationByUserid(userid);
        Map<String, Object> asNum = userAttentionService
                .getAttentNum(sessionUser.getUserid());
        request.setAttribute("asNum", asNum);
        request.setAttribute("labellist", labellist);
        request.setAttribute("userkilllist", userkilllist);
        request.setAttribute("userWorkHistoryList", userWorkHistoryList);
        request.setAttribute("userProfile", userProfile);
        request.setAttribute("tjlabel", tjlabel);
        request.setAttribute("userEducationList", userEducationList);
        request.setAttribute("baseUserInformation", baseUserInformation);
        return "home/grzx-information.html";
    }

    @RequestMapping("/adduserlabel")
    public String addUserlabel(HttpServletRequest request,
                               HttpServletResponse response, int id) {
        JsonObject jsonObject = new JsonObject();
        SessionUser sessionUser = getSessionUser(request);
        boolean b = labelService.addUserlabel(sessionUser.getUserid(), id);
        jsonObject.addProperty("flag", b);
        jsonObject.addProperty("message", b ? Constant.MESSAGE.ADD_SUCCESS
                : Constant.MESSAGE.DELE_FAILURE);
        BasicAjaxUtil.writeJson(response, jsonObject.toString());

        return null;
    }

    @RequestMapping("/deluserlabel")
    public String delUserlabel(HttpServletRequest request,
                               HttpServletResponse response, int id) {
        JsonObject jsonObject = new JsonObject();
        SessionUser sessionUser = getSessionUser(request);
        boolean b = labelService.deleUserLabel(sessionUser.getUserid(), id);
        jsonObject.addProperty("flag", b);
        jsonObject.addProperty("message", b ? Constant.MESSAGE.DELE_SUCCESS
                : Constant.MESSAGE.DELE_FAILURE);
        BasicAjaxUtil.writeJson(response, jsonObject.toString());

        return null;
    }

    /**
     * 关注用户
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/attentionuser")
    public String attentionUser(HttpServletRequest request,
                                HttpServletResponse response) {
        SessionUser sessionUser = getSessionUser(request);
        Map<String, Object> asNum = userAttentionService
                .getAttentNum(sessionUser.getUserid());
        request.setAttribute("asNum", asNum);
        List<Map<String, Object>> userAttention = userAttentionService
                .getUserAttention(sessionUser.getUserid(), 0);
        List<Map<String, Object>> attentionUser = userAttentionService
                .getAttentionUser(sessionUser.getUserid(), 0);
        request.setAttribute("userAttention", userAttention);
        request.setAttribute("attentionUser", attentionUser);
        return "home/grzx-users.html";
    }

    /**
     * 用户项目
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/userproject")
    public String userProject(HttpServletRequest request,
                              HttpServletResponse response) {
        SessionUser sessionUser = getSessionUser(request);
        Map<String, Object> asNum = userAttentionService
                .getAttentNum(sessionUser.getUserid());
        request.setAttribute("asNum", asNum);
        return "home/grzx-projects.html";
    }

    /**
     * 跳转个人主页
     *
     * @param request
     * @return
     */
    @RequestMapping("/userinfozy")
    public String userinfozy(HttpServletRequest request) {

        SessionUser sessionUser = (SessionUser) request.getSession()
                .getAttribute(Constant.Session.SESSION_NAME);
        int userid = sessionUser.getUserid();
        List<Label> labellist = labelService.getLabelByUserId(userid);
        UserProfile userProfile = userProfileService
                .getUserProfileByUserId(userid);
        User baseUserInformation = userService.getBaseUserInformation(userid);
        return "home/grzy.html";
    }

    /**
     * 关注用户
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/attentionuserzy")
    public String attentionUserzy(HttpServletRequest request,
                                  HttpServletResponse response) {
        return "home/grzx-users.html";
    }

    /**
     * 用户项目
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/userprojectzy")
    public String userProjectzy(HttpServletRequest request,
                                HttpServletResponse response) {
        return "home/grzx-projects.html";
    }

    @RequestMapping("/querylabelforpage")
    public String queryLabelForPage(HttpServletRequest request,
                                    HttpServletResponse response, int pageno, int pageSize) {
        QueryResult<Label> labelByPageNo = labelService.getLabelByPageNo(
                pageno, pageSize);
        BasicAjaxUtil.writeJsonObj(response, new MessageBean(true, "",
                labelByPageNo));
        return null;
    }

    /**
     * 编辑工作经历
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/editworkhistory")
    public String editworkhistory(HttpServletRequest request,
                                  HttpServletResponse response, int id) {
        UserWorkHistory userWorkHistory = userWorkHistoryService
                .getUserWorkHistoryById(id);
        BasicAjaxUtil.writeJsonObj(response, new MessageBean(true, "",
                userWorkHistory));
        return null;
    }

    /**
     * 编辑用户学历
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/edituseredu")
    public String edituseredu(HttpServletRequest request,
                              HttpServletResponse response, int id) {
        UserEducation userEducation = userEducationService.getUserEduById(id);
        BasicAjaxUtil.writeJsonObj(response, new MessageBean(true, "",
                userEducation));
        return null;
    }

    /**
     * 添加用户作品
     *
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/{id}/project")
    @ResponseBody
    public Object uploadProject(HttpServletRequest request,
                                HttpServletResponse response, @PathVariable int id, UserProject userProject) {
        User user = userService.getBaseUserInformation(id);
        userProject.setUser(user);
        userProject.setAuthor(user.getUsername());
        boolean b = userProjectService.insertUserProject(userProject);
        return new MessageBean(b, b?"信息已收录！":"系统错误!");
    }

    /**
     * 查看用户作品
     *
     * @param request
     * @param response
     * @return
     */
    @GetMapping("{id}/project")
    public String lookUserWork(HttpServletRequest request,
                               HttpServletResponse response,@PathVariable int id, int pageno) {
        QueryResult<UserProject> projectlist = userProjectService.getUserProjectList(
                pageno, Constant.Common.WORK_NUM,id);
        request.setAttribute("projectlist",projectlist);
        return "common/projectlist.html";
    }

    /**
     * 删除用户作品
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/deluserproject")
    public String delUserProject(HttpServletRequest request,
                                 HttpServletResponse response, int id) {
        boolean b = userProjectService.delUserProject(id);
        BasicAjaxUtil.writeJsonObj(response, new MessageBean(true,
                b ? Constant.MESSAGE.DELE_SUCCESS
                        : Constant.MESSAGE.EDIT_FAILURE));
        return null;
    }

    @RequestMapping("/chat")
    public String chat(HttpServletRequest request, HttpServletResponse response) {
        SessionUser sessionUser = getSessionUser(request);
        List list = userService.getUserFriendsByUserId(sessionUser.getUserid());
        request.setAttribute("useFriendList", list);
        return "home/user-chat.html";
    }

    /**
     * 校验roomid是否存在
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/validationRoomId")
    public String validationRoomId(HttpServletRequest request,
                                   HttpServletResponse response) {
        SessionUser sessionUser = getSessionUser(request);
        ChatRoom chatRoom = chatRoomService.getChatRoomByuid(sessionUser
                .getUserid());
        BasicAjaxUtil.writeJsonObj(response, new MessageBean(
                chatRoom != null ? true : false, "", chatRoom));
        return null;
    }

    /**
     * 校验好友是否存在
     *
     * @param request
     * @param response
     * @param username
     * @return
     */
    @RequestMapping("/checkfriend")
    public String checkFriend(HttpServletRequest request,
                              HttpServletResponse response, String username) {
        SessionUser sessionUser = getSessionUser(request);
        int currentuserid = sessionUser.getUserid();
        boolean b = hoyoService.checkFriend(currentuserid, username);
        BasicAjaxUtil.writeJsonObj(response, new MessageBean(b,
                b ? "该用户已经是好友了！" : ""));
        return null;
    }

    @RequestMapping("/addfriend")
    public String addFriend(HttpServletRequest request,
                            HttpServletResponse response, String username, String roomid) {
        SessionUser sessionUser = getSessionUser(request);
        int currentuserid = sessionUser.getUserid();
        boolean b = hoyoService.addFriend(currentuserid, username, roomid);
        BasicAjaxUtil.writeJsonObj(response, new MessageBean(b, "添加成功"));
        return null;
    }

    @RequestMapping("/recommend")
    public String recommend(HttpServletRequest request,
                            HttpServletResponse response) {
        SessionUser sessionUser = getSessionUser(request);
        int userid = sessionUser.getUserid();
        List<UserSkill> userKillByUsers = userSkillSerivice
                .getUserKillByUserid(userid);
        List<Integer> uids = userService.getUserIdByKilll(userKillByUsers);
        List ulist = userService.getRecommendByUids(uids);
        BasicAjaxUtil.writeJsonObj(response, new MessageBean(true, "", ulist));
        return null;
    }

    /**
     * 上传
     *
     * @param request
     * @param response
     * @param file
     */
    @RequestMapping("/upload")
    public void upload(HttpServletRequest request,
                       HttpServletResponse response,
                       @RequestParam(value = "qqfile", required = true) MultipartFile file) {
        SessionUser sessionUser = getSessionUser(request);
        response.setContentType("text/html");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            if (!file.isEmpty()) {
                byte[] data = file.getBytes();
                String upath = request.getSession().getServletContext()
                        .getRealPath("/");
                String path = UtilTools.getConfig().getProperty("uploadImg")+sessionUser.getUserid()+"/";
                String filename = file.getOriginalFilename();
                UUID uuid = UUID.randomUUID();
                String uidStr = uuid.toString() + "_" + sessionUser.getUserid();
                String suffix = FileType.getSuffixByFilename(filename);
                path += uidStr;
                path = path + suffix;
                // path = PathFormat.parse(path, filename);
                File file2 = new File(upath + path);
                FileUtils.writeByteArrayToFile(file2, data);
                logger.info("系统提示："+path);
                int w = 0;
                int h = 0;
                try {
                    BufferedImage bufferedImage = ImageIO.read(file2);
                    w = bufferedImage.getWidth();
                    h = bufferedImage.getHeight();
                } catch (Exception ee) {
                }
                out.print("{\"success\": \"true\",\"imgpath\":\"" + path
                        + "\",\"imgw\":\"" + w + "\",\"imgh\":\"" + h + "\"}");
                // out.write("<script>parent.callback('sucess')</script>");
            } else {
                // out.write("<script>parent.callback('fail')</script>");
                out.print("{\"success\": \"false\"}");
            }

        } catch (Exception e) {
            out.print("{\"success\": \"false\"}");
        }
    }

    @RequestMapping("/uploadfile")
    public String uploadfile(HttpServletRequest request,
                             HttpServletResponse response, String filepath) {
        SessionUser sessionUser = getSessionUser(request);
        sessionUser.setHeadimg(filepath);
        request.getSession().setAttribute(Constant.Session.SESSION_NAME,
                sessionUser);
        userService.updateUserHeadById(sessionUser.getUserid(), filepath);
        BasicAjaxUtil.writeJsonObj(response, new MessageBean(true, "上传成功！",
                null));
        return null;
    }

}
