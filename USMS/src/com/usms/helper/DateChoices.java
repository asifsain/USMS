package com.usms.helper;

import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

@ManagedBean
@ApplicationScoped
public class DateChoices implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int[] days;
	private int[] years;
	private Map<String, Integer> months;

	private static int[] intArray(int from, int to) {
		int[] result = new int[to - from + 1];
		for (int i = from; i <= to; i++)
			result[i - from] = i;
		return result;
	}

	public DateChoices() {
		System.out.println("Date Choices constructed");
		
		days = intArray(1, 31);
		years = intArray(2012, 2100);
		months = new LinkedHashMap<String, Integer>();
		String[] names = new DateFormatSymbols().getMonths();
	//	months.put("Select", 0);
		for (int i = 0; i < 12; i++)
			months.put(names[i], i + 1);
		
	}

	public int[] getDays() {
		return days;
	}

	public int[] getYears() {
		return years;
	}

	public Map<String, Integer> getMonths() {
		return months;
	}
}
