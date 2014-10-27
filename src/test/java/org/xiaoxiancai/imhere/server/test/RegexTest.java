/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.server.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author xiannenglin
 */
public class RegexTest {

	private static Pattern commandPattern = Pattern
			.compile("(\\w+) (-(\\w+) (\\w+))");

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RegexTest tester = new RegexTest();
		String command = "update -maxThread 999 -minThread 100";
		Matcher matcher = commandPattern.matcher(command);
		System.out.println(matcher.matches());
		System.out.println(matcher.group(1));
		System.out.println(matcher.group(2));
		System.out.println(matcher.group(3));
		System.out.println(matcher.group(4));
	}

}
