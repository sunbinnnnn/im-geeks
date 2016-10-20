/*
 * Copyright (C), 2013-2014, 南京华内斯信息技术有限公司
 * FileName: Constant.java
 * Author:   dong
 * Date:     Nov 6, 2014 11:46:36 PM
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.imgeeks.common.constant;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * 
 * @author dong
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Constant {
	
	public interface Page{
		int NUM = 10;
	}

    public interface Common {
    	int WORK_NUM = 6;
        String YES = "Y";
        String NOT = "N";
        int PAGE_SIZE = 5;
    }

    public interface Session {
        String SESSION_NAME = "sessionUser";
    }

    public interface Role {
        String ROLECODE_ADMIN = "1";
        String ROLECODE_USER = "2";
    }
    
    
    public interface MESSAGE{
    	
    	String CONTACT_ADMIN = "获取信息有误，请联系管理员";
    	
    	String ADD_SUCCESS="添加成功！";
    	String UPDATE_SUCCESS="修改成功！";
    	String EDIT_SUCCESS="编辑成功！";
    	String DELE_SUCCESS="删除成功！";
    	
    	String ADD_FAILURE="添加失败";
    	String UPDATE_FAILURE="修改失败";
    	String EDIT_FAILURE="编辑失败";
    	String DELE_FAILURE="删除失败！";

    }


}