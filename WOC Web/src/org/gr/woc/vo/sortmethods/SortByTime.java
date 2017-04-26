package org.gr.woc.vo.sortmethods;

import java.util.Comparator;

import org.gr.woc.vo.Commodities;

public class SortByTime implements Comparator<Commodities> {

	public int compare(Commodities o1, Commodities o2) {
		// TODO Auto-generated method stub
		return o1.getReleaseTime().compareTo(o2.getReleaseTime());
	}

}
