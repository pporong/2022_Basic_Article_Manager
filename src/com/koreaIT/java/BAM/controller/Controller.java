package com.koreaIT.java.BAM.controller;

public abstract class Controller {

//	public static Member loginedMember;
	
	public abstract void doAction(String cmd, String actionMethodName);

	public abstract void makeTestData();
}
