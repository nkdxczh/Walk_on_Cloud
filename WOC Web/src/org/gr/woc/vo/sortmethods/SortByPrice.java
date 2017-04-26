package org.gr.woc.vo.sortmethods;

import java.util.Comparator;

import org.gr.woc.vo.Commodities;

public class SortByPrice implements Comparator<Commodities> {

	@Override
	public int compare(Commodities o1, Commodities o2) {
		// TODO Auto-generated method stub
		if (o1.getPrice() <= o2.getPrice()) {
			return -1;
		}
		if (o1.getPrice() == o2.getPrice()) {
			return 0;
		}
		if (o1.getPrice() >= o2.getPrice()) {
			return 1;
		}
		else {
			return 0;
		}
	}

}
