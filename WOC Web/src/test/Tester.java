package test;

import java.util.ArrayList;
import java.util.List;

import org.gr.woc.biz.ICommodityBiz;
import org.gr.woc.biz.impl.CommodityBizImpl;
import org.gr.woc.dao.ICommoditiesDao;
import org.gr.woc.dao.impl.CommoditiesDaoImpl;
import org.gr.woc.vo.Commodities;

public class Tester {
	public static void main(String[] args) {
		List<Commodities> lstCommodities = new ArrayList<Commodities>();
		ICommodityBiz biz = new CommodityBizImpl();
		// lstCommodities = biz.searchByComType(11, 1, 6,
	    //	"releaseTime");
		ICommoditiesDao commoditiesDao=new CommoditiesDaoImpl();
		lstCommodities=commoditiesDao.selectCommoditiesByType(10,1,6,"releaseTime");
		for (Commodities commodities : lstCommodities) {
			System.out.println(commodities);
		}
	}
	
}
