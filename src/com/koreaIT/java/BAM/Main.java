package com.koreaIT.java.BAM;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		System.out.println("== 프로그램 시작 ==");
		
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			System.out.printf("명령어 : ");
			String cmd = sc.nextLine();
			
			// 종료시
			if(cmd.equals("exit")) {
				break;
			}
		}
		
		System.out.println("== 프로그램 종료 ==");
		sc.close();
	}
	
}