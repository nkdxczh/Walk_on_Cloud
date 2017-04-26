package org.gr.woc.utils;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.gr.woc.biz.ICommodityBiz;
import org.gr.woc.vo.Commodities;

public class SendByUserCookie {

	private ICommodityBiz commoditybiz;

	public SendByUserCookie(final ICommodityBiz commodityBiz) {
		super();
		// TODO Auto-generated constructor stub
		this.commoditybiz=commodityBiz;
		
	}
	public List<Commodities>findUserLike(String username, HttpServletRequest request){
		Cookie[] c=request.getCookies();
		String params="";
		for (Cookie cookie : c) {
			if(cookie.getName().equals(username)){
				params=cookie.getValue();
			}
		}
		if(params=="")
			return null;
		int[] counts=new int[3];
		TypeCount(counts, params);
		int[] typeQue=typeQue(counts);
		List<Commodities>toReturn=new ArrayList<Commodities>();
		List<Commodities>lst1=commoditybiz.searchByComType(10, 1, 10, "releaseTime");
		List<Commodities>lst2=commoditybiz.searchByComType(11, 1, 10, "releaseTime");
		List<Commodities>lst3=commoditybiz.searchByComType(12, 1, 10, "releaseTime");
		int k1=0;int k2=0;int k3=0;
		for(int i=0;i<typeQue.length;i++){
			switch(typeQue[i]){
			case 1:
				
				if(k1<lst1.size()&&lst1.size()>0){
				toReturn.add(lst1.get(k1));
				k1++;
				break;
				}
			case 2:
				
				if(k2<lst2.size()&&lst2.size()>0){
				toReturn.add(lst2.get(k2));
				k2++;
				break;
				}
			case 3:
				
				if(k3<lst3.size()&&lst3.size()>0){
				toReturn.add(lst3.get(k3));
				k3++;
				break;
				}
			}
		}
		return toReturn;
	}
	private void TypeCount(int[] counts, String params){
		int k=0;
		String temp="";
		int state=0;
		for(int i=1;i<params.length();i++){
			if(params.charAt(i)=='n'){
				state=1;
				continue;
			}
			if(params.charAt(i)=='b'){
				state=0;
				continue;
			}
			switch(state){
			case 0:
				counts[k]=Integer.parseInt(temp);
				k++;
				temp="";
				break;
			case 1:
				temp+=params.charAt(i);
				break;
			}
		}
	}
	private int[] typeQue(int[] counts){
		int all=0;
		int[] weight=new int[3];
		for(int i=0;i<counts.length;i++){
			all+=counts[i];
		}
		for(int i=0;i<counts.length;i++){
			if(all!=0)
				weight[i]=counts[i]*10/all;
		}
		int typeQue[]=new int[8];
		for(int i=0;i<8;i++){
			typeQue[i]=maxType(weight);
		}
		return typeQue;
	}
	private int maxType(int[] weight){
		if(weight[0]>=weight[1]){
			if(weight[0]>=weight[2]){
				weight[0]--;
				return 1;
			}
			weight[2]--;
			return 3;
		}
		if(weight[1]>=weight[2]){
			weight[1]--;
			return 2;
		}
		weight[2]--;
		return 3;
	}
}
