/**
 * 
 */
package com.phobes.cloudDisk.utils;

import java.util.UUID;

/**
 * @author  pangchao E-mail: pangchao620@163.com
 * @date : 2015�?12�?7�? 下午4:47:47 
 * @Description : 自定义的ID生成�?
 * @version 1.0 
 */
public class IdCreator {

	/**
	 * 
	 * @Description:生成客户方用户ID
	 * @return
	 * String
	 * @exception:
	 */
	public static String createClientId() {
		return "CL" + UUID.randomUUID();
	}
	
}
