package org.gr.woc.utils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * 功能：实现输入字符串返回一个经过加密的MD5的字符串值
 * 开发时间：2014-7-23
 * 作者：许精策
 * 
 *
 */

public class MD5String {
	//字节数据转换为字符串数据
	private String byteToString(byte[] Byte){
		String[] strString={"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", 
				"c", "d", "e", "f"};
		StringBuffer sBuffer=new StringBuffer();
		for(int i=0;i<Byte.length;i++){
			int ret=Byte[i];
			if(ret<0)
				ret+=256;
			int iD1=ret/16;
			int iD2=ret%16;
			sBuffer.append(strString[iD1]+strString[iD2]);
		}
		return sBuffer.toString();
	}
	//输入字符串生成MD5值
	public String getMD5(String str){
		String result = null;
		try {
			MessageDigest md=MessageDigest.getInstance("MD5");
			result=byteToString(md.digest(str.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
