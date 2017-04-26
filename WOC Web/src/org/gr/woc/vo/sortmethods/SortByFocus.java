package org.gr.woc.vo.sortmethods;

import java.util.Comparator;

import org.gr.woc.vo.Commodities;

public class SortByFocus implements Comparator<Commodities> {

	public int compare(Commodities o1, Commodities o2) {
		// TODO Auto-generated method stub
		if (o1.getFocusNumber() <= o2.getFocusNumber()) {
			return -1;
		}
		if (o1.getFocusNumber() == o2.getFocusNumber()) {
			return 0;
		}
		if (o1.getFocusNumber() >= o2.getFocusNumber()) {
			return 1;
		}
		else {
			return 0;
		}
	}

}
