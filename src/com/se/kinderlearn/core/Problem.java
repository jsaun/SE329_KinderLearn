package com.se.kinderlearn.core;

import java.util.Random;

public class Problem {
	private String expression;
	private int answer;
	private int[] possibleAnswers;
	
	public Problem(String ex, int ans){
		this.expression = ex;
		this.answer = ans;
		this.possibleAnswers = new int[4];
		Random r = new Random();
		int pos = r.nextInt(4);
		int[] fake = new int[3];
		if(r.nextBoolean()){
			fake[0] = answer + (r.nextInt(1) + 1);
		} else {
			fake[0] = answer - (r.nextInt(1) + 1);
		}
		if(r.nextBoolean()){
			fake[1] = answer + (r.nextInt(4) + 1);
		} else {
			fake[1] = answer - (r.nextInt(4) + 1);
		}		if(r.nextBoolean()){
			fake[2] = answer + (r.nextInt(9) + 1);
		} else {
			fake[2] = answer - (r.nextInt(9) + 1);
		}
		if(pos == 0){
			possibleAnswers[0] = this.answer;
			possibleAnswers[1] = fake[0];
			possibleAnswers[2] = fake[1];
			possibleAnswers[3] = fake[2];
		} else if(pos == 1){
			possibleAnswers[1] = this.answer;
			possibleAnswers[0] = fake[0];
			possibleAnswers[2] = fake[1];
			possibleAnswers[3] = fake[2];
		} else if(pos == 2){
			possibleAnswers[2] = this.answer;
			possibleAnswers[1] = fake[0];
			possibleAnswers[0] = fake[1];
			possibleAnswers[3] = fake[2];
		} else {
			possibleAnswers[3] = this.answer;
			possibleAnswers[1] = fake[0];
			possibleAnswers[2] = fake[1];
			possibleAnswers[0] = fake[2];
		}
	}

	public String getExpression() {
		return expression;
	}

	public int getAnswer() {
		return answer;
	}
	
	public int[] getPossibleAnswers(){
		return possibleAnswers;
	}
	
	public boolean checkAnswer(int ans){
		return ans == this.answer;
	}
	
}
