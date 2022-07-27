package com.koreaIT.java.BAM.dao;

public abstract class Dao {

	protected int lastId;
	// 생성자
	Dao() {
		lastId = 0;
	}

	public int getLastId() {
		return lastId;
	}

	public int getNewId() {
		return lastId +1;
	}
	;
	
}
