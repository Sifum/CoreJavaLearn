package com.javacore.stream;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {
	public static void main(String args[])
	{
		Pattern pattern = Pattern.compile("<img[a-zA-Z0-9_\"\"=\\s]+>");
		
		Scanner in = new Scanner(System.in);
		System.out.println("Input the words:");
		String input = in.nextLine();
		
		while(input != "quit")
		{
			Matcher matcher = pattern.matcher(input);
			if(matcher.matches())
			{
				System.out.println("matches!");
			}
			else
			{
				System.out.println("no matches!");
			}
			System.out.println("Input the words:");
			input = in.nextLine();
		}

	}
}
