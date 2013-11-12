package com.se.kinderlearn.core;

public class Score implements Comparable<Score> {

	private String date;
	public int value;

	public Score(String date, int value) {
		this.date = date;
		this.value = value;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public int compareTo(Score other) {
		// equal: 0, greater: -1 ; less: 1
		if (this.value == other.value)
			return 0;
		
		else if (this.value > other.value)
			return -1;
		else
			return 1;
	}

	public String toString() {
		return date + " --- " + value;
	}

}
