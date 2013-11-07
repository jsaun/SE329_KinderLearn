package com.se.kinderlearn.core;

public class Problem {
	private String expression;
	private int answer;
	
	public Problem(String ex, int ans){
		this.expression = ex;
		this.answer = ans;
	}

	public String getExpression() {
		return expression;
	}

	public int getAnswer() {
		return answer;
	}
	
	public boolean checkAnswer(int ans){
		return ans == this.answer;
	}
	
}
