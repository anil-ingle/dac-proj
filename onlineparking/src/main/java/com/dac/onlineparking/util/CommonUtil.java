package com.dac.onlineparking.util;

import java.util.ArrayList;
import java.util.List;

public class CommonUtil {
	public static List<Integer> csvIds(String ids) {
		List<Integer> integers = new ArrayList<Integer>();
		String[] csvIds = ids.split(",");
		if (csvIds != null) {

			for (String val : csvIds) {
				integers.add(Integer.parseInt(val));
			}
		}

		return integers;

	}

}