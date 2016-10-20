/*
 * Copyright (c) 2014, 2015, dhl and/or its affiliates. All rights reserved.
 * dhl PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.imgeeks.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;



/**
 * 工具类，提供公共方法
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
public class UtilTools {
	
	public static void main(String[] args) {
		Object object = UtilTools.getConfig().get("uploadpath");
		System.out.println(object);
	}

	public static String COFING_FILE = "conf/main-setting.properties";
	public static Properties p;

	public static Properties getConfig() {
		if (p == null) {
			p = new Properties();
			try {
				p.load(Thread.currentThread().getContextClassLoader()
						.getResourceAsStream(COFING_FILE));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return p;
	}

	/**
	 * 默认是utf-8编码
	 * 
	 * @param str
	 * @return
	 */
	public static String converStr(String str) {
		return converStr(str, "UTF-8");
	}

	/**
	 * 
	 * @param str
	 * @param encode
	 * @return
	 */
	public static String converStr(String str, String encode) {
		if (str == null || str.equals("null")) {
			return "";
		}
		try {
			byte[] tmpbyte = str.getBytes("ISO8859_1");
			if (encode != null) {
				// 如果指定编码方式
				str = new String(tmpbyte, encode);
			} else {
				// 用系统默认的编码
				str = new String(tmpbyte);
			}
			return str;
		} catch (Exception e) {
		}
		return str;
	}
	
	public static String timeTostrHMS(Date date) {
		String strDate = "";
		if (date != null) {
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			strDate = format.format(date);
		}
		return strDate;
	}

	public static String timeTostr(Date date) {
		String strDate = "";
		if (date != null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			strDate = format.format(date);
		}
		return strDate;
	}
	public static int beforeday(String myString)
	{
		String nowdate = timeTostr(new Date());
//		String myString = "2015-01-16";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		Date now = null;
		try {
			d = sdf.parse(myString);
			now = sdf.parse(nowdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d.compareTo(now);
	}
	
	public static String replaceStr(String str) {
		str = str.replaceAll("%2F", "/");
		return str;
	}

	public static String md5Digest(String src){    
        // 定义数字签名方法, 可用：MD5, SHA-1    
		try
		{
        MessageDigest md = MessageDigest.getInstance("MD5");    
        byte[] b = md.digest(src.getBytes("utf-8"));    
              
        return byte2HexStr(b);    
		}catch(Exception e)
		{
			return "";
		}
   }  
     
 
    private static String byte2HexStr(byte[] b) {    
        StringBuilder sb = new StringBuilder();    
        for (int i = 0; i < b.length; i++) {    
            String s = Integer.toHexString(b[i] & 0xFF);    
            if (s.length() == 1) {    
                 sb.append("0");    
            }    
                    
            sb.append(s.toUpperCase());    
         }    
                
         return sb.toString();    
    }   
    
    
    /**
	 * 删除单个文件
	 * 
	 * @return boolean
	 */
public static boolean deletefile(String filename){
	boolean flag = false;
	File file=new File(filename);
	if(file.exists()){
		file.delete();
		flag = true;
	}
	return flag;
}

public static boolean checkcode(String certificateNo)
{
//	if (certificateNo.length() == 15) {
//
////		System.err.println("身份证号码无效，请使用第二代身份证！！！");
//		return false;
//
//	} else 
	if (certificateNo != null && certificateNo.length() == 18) {
		String address = certificateNo.substring(0, 6);// 6位，地区代码
		String birthday = certificateNo.substring(6, 14);// 8位，出生日期
		String sequenceCode = certificateNo.substring(14, 17);// 3位，顺序码：奇为男，偶为女
//		String checkCode = certificateNo.substring(17);// 1位，校验码：检验位
		
//		System.out.println("身份证号码:" + certificateNo + "、地区代码:" + address
//				+ "、出生日期:" + birthday + "、顺序码:" + sequenceCode + "、校验码:"
//				+ checkCode + "\n");
//		System.out.println("age -------------------- "+getAge(new Date(year, month, date)));
		String[] provinceArray = { "11:北京", "12:天津", "13:河北", "14:山西",
				"15:内蒙古", "21:辽宁", "22:吉林", "23:黑龙江", "31:上海", "32:江苏",
				"33:浙江", "34:安徽", "35:福建", "36:江西", "37:山东", "41:河南",
				"42:湖北 ", "43:湖南", "44:广东", "45:广西", "46:海南", "50:重庆",
				"51:四川", "52:贵州", "53:云南", "54:西藏 ", "61:陕西", "62:甘肃",
				"63:青海", "64:宁夏", "65:新疆", "71:台湾", "81:香港", "82:澳门",
				"91:国外" };

		boolean valideAddress = false;
		for (int i = 0; i < provinceArray.length; i++) {
			String provinceKey = provinceArray[i].split(":")[0];
			if (provinceKey.equals(address.substring(0, 2))) {
				valideAddress = true;
			}

		}

		if (valideAddress) {
			String year = birthday.substring(0, 4);
			String month = birthday.substring(4, 6);
			String day = birthday.substring(6);
			Date tempDate = new Date(Integer.parseInt(year),
					Integer.parseInt(month) - 1, Integer.parseInt(day));

			if ((tempDate.getYear() != Integer.parseInt(year)
					|| tempDate.getMonth() != Integer.parseInt(month) - 1 || tempDate
						.getDate() != Integer.parseInt(day))) {// 避免千年虫问题

//				System.err.println("身份证号码无效，请重新输入！！！");
				return false;
			} else {
				int[] weightedFactors = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7,
						9, 10, 5, 8, 4, 2, 1 };// 加权因子
				int[] valideCode = { 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 };// 身份证验证位值，其中10代表X
				int sum = 0;// 声明加权求和变量
				String[] certificateNoArray = new String[certificateNo
						.length()];
				for (int i = 0; i < certificateNo.length(); i++) {
					certificateNoArray[i] = String.valueOf(certificateNo
							.charAt(i));
				}
				if ("x".equals(certificateNoArray[17].toLowerCase())) {
					certificateNoArray[17] = "10";// 将最后位为x的验证码替换为10
				}
				for (int i = 0; i < 17; i++) {

					sum += weightedFactors[i]
							* Integer.parseInt(certificateNoArray[i]);// 加权求和

				}
				int valCodePosition = sum % 11;// 得到验证码所在位置

				if (Integer.parseInt(certificateNoArray[17]) == valideCode[valCodePosition]) {
//					String sex = "男";
//					if (Integer.parseInt(sequenceCode) % 2 == 0) {
//						sex = "女";
//					}
//					System.out.println("身份证号码有效，性别为：" + sex + "！");
					return true;
				} else {

//					System.err.println("身份证号码无效，请重新输入！！！");
					return false;
				}
			}

		} else {
			return false;
//			System.err.println("身份证号码无效，请重新输入！！！");
		}

	} else {
		return false;
//		System.err.println("非身份证号码，请输入身份证号码！！！");

	}
}

public static String getRemoteAddress(HttpServletRequest request) {  
    String ip = request.getHeader("x-forwarded-for");  
    if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
        ip = request.getHeader("Proxy-Client-IP");  
    }  
    if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
        ip = request.getHeader("WL-Proxy-Client-IP");  
    }  
    if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
        ip = request.getRemoteAddr();  
    }  
    return ip;  
}  

public static void wfile(String str) {
FileWriter fw = null;
try {
//如果文件存在，则追加内容；如果文件不存在，则创建文件
File f=new File("D:\\weike_web\\log.txt");
fw = new FileWriter(f, true);
} catch (IOException e) {
e.printStackTrace();
}
PrintWriter pw = new PrintWriter(fw);
pw.println(str+"\n");
pw.flush();
try {
fw.flush();
pw.close();
fw.close();
} catch (IOException e) {
e.printStackTrace();
}
}

public static boolean isMobile(String str) {   
    Pattern p = null;  
    Matcher m = null;  
    boolean b = false;   
    p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机号  
    m = p.matcher(str);  
    b = m.matches();   
    return b;  
}

/** 
 * 电话号码验证 
 *  
 * @param  str 
 * @return 验证通过返回true 
 */  
public static boolean isPhone(String str) {   
    Pattern p1 = null,p2 = null;  
    Matcher m = null;  
    boolean b = false;    
    p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$");  // 验证带区号的  
    p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");         // 验证没有区号的  
    if(str.length() >9)  
    {   m = p1.matcher(str);  
        b = m.matches();    
    }else{  
        m = p2.matcher(str);  
        b = m.matches();   
    }    
    return b;  
}  



}
