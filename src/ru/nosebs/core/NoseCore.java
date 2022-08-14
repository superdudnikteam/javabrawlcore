package ru.nosebs.core;

public class NoseCore extends Thread
{
	private static String art = " ,--.  ,--.                      ,-----.                      \n" +
	"|  ,'.|  | ,---.  ,---.  ,---. '  .--./ ,---. ,--.--. ,---.  \n" +
	"|  |' '  || .-. |(  .-' | .-. :|  |    | .-. ||  .--'| .-. : \n" +
	"|  | `   |' '-' '.-'  `)|   --.'  '--'\' '-' '|  |   |   --. \n" +
	"`--'  `--' `---' `----'  `----' `-----' `---' `--'    `----' \n";
	
	public static void main(String... args) {
		System.out.println(art);
		new Server().start();
	}
}
