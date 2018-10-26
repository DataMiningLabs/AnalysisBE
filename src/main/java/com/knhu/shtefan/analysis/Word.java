package com.knhu.shtefan.analysis;

import java.io.Serializable;

public class Word implements Serializable {

	private String word;	//the word itself
	private int spamCount;	//number of this words appearances in spam messages
	private int hamCount;	//number of this words appearances in ham messages
	private float spamRate;	//spamCount divided by total spam count
	private float hamRate;	//hamCount divided by total ham count
	private float probOfSpam;	//probability of word being spam

	public Word(String word){
		this.word = word;
		spamCount = 0;
		hamCount = 0;
		spamRate = 0.0f;
		hamRate = 0.0f;
		probOfSpam = 0.0f;	
	}
	
	public void countSpam(){
		spamCount += 1;
	}
	
	public void countHam(){
		hamCount++;
	}
	
	//calculates the probability of spam, 
	//and gives the smallest and biggest probabilities more precedence
	public void calculateProbability(int totalSpam, int totalHam){
		spamRate = spamCount / (float) totalSpam;
		hamRate = hamCount / (float) totalHam;
		
		if(spamRate + hamRate > 0){
			probOfSpam = spamRate / (spamRate + hamRate);
		}
		if(probOfSpam < 0.01f){
			probOfSpam = 0.01f;
		}
		else if(probOfSpam > 0.99f){
			probOfSpam = 0.99f;
		}
	}

	public String getWord() {
		return word;
	}

	public float getSpamRate() {
		return spamRate;
	}

	public float getHamRate() {
		return hamRate;
	}

	public void setHamRate(float hamRate) {
		this.hamRate = hamRate;
	}

	public float getProbOfSpam() {
		return probOfSpam;
	}

	public void setProbOfSpam(float probOfSpam) {
		this.probOfSpam = probOfSpam;
	}

	public int getSpamCount() {
		return spamCount;
	}

	public int getHamCount() {
		return hamCount;
	}

	@Override
	public String toString() {
		return "Word{" +
			"word='" + word + '\'' +
			", spamRate=" + spamRate +
			", hamRate=" + hamRate +
			", probOfSpam=" + probOfSpam +
			'}';
	}

}
