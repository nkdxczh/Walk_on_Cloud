package org.gr.woc.vo.sortmethods;

import java.util.Comparator;

import org.gr.woc.vo.Commodities;

public class SortByRequiredScore implements Comparator<Commodities> {

	public int compare(Commodities o1, Commodities o2) {
		// TODO Auto-generated method stub
		if (o1.getRequiredScore() <= o2.getRequiredScore()) {
			return -1;
		}
		if (o1.getRequiredScore() == o2.getRequiredScore()) {
			return 0;
		}
		if (o1.getRequiredScore() >= o2.getRequiredScore()) {
			return 1;
		}
		else {
			return 0;
		}
	}
}
