/**
 * 
 */
package com.phobes.cloudDisk.utils;

/** @author  pangchao E-mail: pangchao620@163.com
 * @date : 2016�?1�?22�? 下午8:17:04 
 * @Description : 字符串复杂操作的工具�?
 * @version 1.0 
 */
public class StringUtil {



	
	//测试主函�?
	public static void main(String[] args) {
		
		/*String s = ": :ss :tt::::::::::::";
		String[] ss = s.split(":", -1);
		for (int i = 0; i < ss.length; i++) {
			System.out.println(ss[i]);
		}*/
		String s = "a::b";
		String[] ss = s.split(":", -1); 
		for (int i = 0; i < ss.length; i++) {
			System.out.println(ss[i]);
		}
	}

}
