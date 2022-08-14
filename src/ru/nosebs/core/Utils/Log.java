package ru.nosebs.core.Utils;

public class Log
{
	public enum Tag {
		INFO,
		ERROR;
	}
	public static void log(Tag tag, String message) {
		System.out.println(tag.toString() + " -> " + message);
	}
}
