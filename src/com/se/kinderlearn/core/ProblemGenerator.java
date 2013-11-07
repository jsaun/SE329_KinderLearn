package com.se.kinderlearn.core;

import java.util.ArrayList;
import java.util.Random;

public class ProblemGenerator {
	private String grade;
	
	public ProblemGenerator(String grade){
		this.grade = grade;
	}
	
	public ArrayList<Problem> getProblems(int n){
		ArrayList<Problem> reList = new ArrayList<Problem>();
		Random generator = new Random();
		for(int i = 0; i < n; i++){
			if(grade.equals("k")){
				int first = generator.nextInt(4);
				int second = generator.nextInt(4);
				reList.add(new Problem(new String("" + first + " + " + second), first + second));
			} else if(grade.equals("1")) {
				int first = generator.nextInt(10);
				int second = generator.nextInt(10);
				int r = generator.nextInt(3);
				if(r == 2) {
					if(first > second)
						reList.add(new Problem(new String("" + first + " - " + second), first - second));
					else
						reList.add(new Problem(new String("" + second + " - " + first), second - first));
				} else {
					reList.add(new Problem(new String("" + first + " + " + second), first + second));
				}
				
			} else if(grade.equals("2")) {
				int first = generator.nextInt(100);
				int second = generator.nextInt(100);
				int r = generator.nextInt(3);
				if(r == 2) {
					reList.add(new Problem(new String("" + first + " - " + second), first - second));
				} else {
					reList.add(new Problem(new String("" + first + " + " + second), first + second));
				}
				
			} else if(grade.equals("3")) {
				int r = generator.nextInt(2);
				if(r == 0){
					int first = generator.nextInt(151);
					int second = generator.nextInt(151);
					int r2 = generator.nextInt(3);
					if(r2 == 2) {
						reList.add(new Problem(new String("" + first + " - " + second), first - second));
					} else {
						reList.add(new Problem(new String("" + first + " + " + second), first + second));
					}
				} else {
					int first = generator.nextInt(6);
					int second = generator.nextInt(6);
					reList.add(new Problem(new String("" + first + " * " + second), first * second));
				}
				
			} else if(grade.equals("4")) {
				int r = generator.nextInt(3);
				if(r == 0){
					int first = generator.nextInt(251);
					int second = generator.nextInt(200);
					int r2 = generator.nextInt(3);
					if(r2 == 2) {
						reList.add(new Problem(new String("" + first + " - " + second), first - second));
					} else {
						reList.add(new Problem(new String("" + first + " + " + second), first + second));
					}
				} else {
					int first = generator.nextInt(11);
					int second = generator.nextInt(11);
					int r2 = generator.nextInt(3);
					if(r2 == 2) {
						reList.add(new Problem(new String("" + first*second + " / " + second), first));
					} else {
						reList.add(new Problem(new String("" + first + " * " + second), first * second));
					}
				}

			} else if(grade.equals("5")) {
				int r = generator.nextInt(4);
				if(r == 0){
					int first = generator.nextInt(301);
					int second = generator.nextInt(301);
					int r2 = generator.nextInt(3);
					if(r2 == 2) {
						reList.add(new Problem(new String("" + first + " - " + second), first - second));
					} else if(r2 == 1) {
						reList.add(new Problem(new String("" + first + " + " + second), first + second));
					} else {
						reList.add(new Problem(new String("X + " + second + " = " + (first+second)), first));
					}
				} else {
					int first = generator.nextInt(10) + 3;
					int second = generator.nextInt(11) + 2;
					int r2 = generator.nextInt(3);
					if(r2 == 2) {
						reList.add(new Problem(new String("" + first*second + " / " + second), first));
					} else {
						reList.add(new Problem(new String("" + first + " * " + second), first * second));
					}
				}
			} else if(grade.equals("6")) {
				int r = generator.nextInt(5);
				if(r == 0){
					int first = generator.nextInt(501);
					int second = generator.nextInt(500);
					int r2 = generator.nextInt(3);
					if(r2 == 2) {
						reList.add(new Problem(new String("" + first + " - " + second), first - second));
					} else if(r2 == 1) {
						reList.add(new Problem(new String("" + first + " + " + second), first + second));
					} else {
						reList.add(new Problem(new String("X + " + second + " = " + (first+second)), first));
					}
				} else {
					int first = generator.nextInt(12) + 3;
					int second = generator.nextInt(12) + 3;
					int r2 = generator.nextInt(3);
					if(r2 == 2) {
						reList.add(new Problem(new String("" + first*second + " / " + second), first));
					} else if(r2 == 1) {
						reList.add(new Problem(new String("" + first + " * " + second), first * second));
					} else {
						reList.add(new Problem(new String(" X * " + second + " = " + (first*second)), first));
					}
				}
			} else {
				return null;
			}
		}
		return reList;
	}
	
	
}
