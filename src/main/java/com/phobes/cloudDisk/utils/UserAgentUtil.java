/**
 * 
 */
package com.phobes.cloudDisk.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;


/** @author  pangchao E-mail: pangchao620@163.com
 * @date : 2016�?2�?24�? 上午11:27:26 
 * @Description : 文件下载时浏览器端防乱码编码工具�?
 * @version 1.0 
 */
public class UserAgentUtil {

	public static String encodeFileName(HttpServletRequest request, String fileName) {
	    String userAgent = request.getHeader("User-Agent");
	    String rtn = "";
	    try {
	        String new_filename = URLEncoder.encode(fileName, "UTF8");
	        // 如果没有UA，则默认使用IE的方式进行编码，因为毕竟IE还是占多数的
	        rtn = "filename=\"" + new_filename + "\"";
	        if (userAgent != null) {
	            userAgent = userAgent.toLowerCase();
	            // IE浏览器，只能采用URLEncoder编码
	            if (userAgent.indexOf("msie") != -1) {
	                rtn = "filename=\"" + new_filename + "\"";
	            }
	            // Opera浏览器只能采用filename*
	            else if (userAgent.indexOf("opera") != -1) {
	                rtn = "filename*=UTF-8''" + new_filename;
	            }
	            // Safari浏览器，只能采用ISO编码的中文输�?
	            else if (userAgent.indexOf("safari") != -1) {
	                rtn = "filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO8859-1") + "\"";
	            }
	            // Chrome浏览器，只能采用MimeUtility编码或ISO编码的中文输�?
	            else if (userAgent.indexOf("applewebkit") != -1) {
	                new_filename = MimeUtility.encodeText(fileName, "UTF8", "B");
	                rtn = "filename=\"" + new_filename + "\"";
	            }
	            // FireFox浏览器，可以使用MimeUtility或filename*或ISO编码的中文输�?
	            else if (userAgent.indexOf("mozilla") != -1) {
	                rtn = "filename*=UTF-8''" + new_filename;
	            }
	        }
	    } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	    }
	    return rtn;
	}
}
