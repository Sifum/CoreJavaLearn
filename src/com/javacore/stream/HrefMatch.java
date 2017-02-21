package com.javacore.stream;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class HrefMatch {
	public static void main(String args[]){
		try
		{
			String urlString;
			if(args.length > 0) urlString = args[0];
			else urlString = "http://www.ttnzh.com";
			System.out.println("urlString is \n" + urlString);
			
			InputStreamReader in = new InputStreamReader(new URL(urlString).openStream());	
			StringBuilder input = new StringBuilder();
			System.out.println("input is:\n" + input);
			
			int ch;
			while((ch = in.read()) != -1)
				input.append((char)ch);
			
//			String patternString = "<a\\s+href\\s*(\"[^\"]*\"|[^\\s>]*)\\s*>";
			String patternString = "<img\\s+src\\s*(\"[^\"]*\"|[^\\s>]*)\\s*>";
			Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(input);
			
			System.out.println("Begining:");
			int n = 1;
			while(matcher.find())
			{
				int start = matcher.start();
				int end = matcher.end();
				String match = input.substring(start, end);
				int st = match.indexOf("\"");
				int en = match.lastIndexOf("\"");
				String matchSrc = match.substring(st + 1, en);
				
				URL f = new URL(urlString + "/" + matchSrc);
				BufferedImage image = ImageIO.read(f);
				File ff = new File("static/images/" + n++ + ".png");
				ImageIO.write(image, "png", ff);
				System.out.println(matchSrc);
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch(PatternSyntaxException e)
		{
			e.printStackTrace();
		}
	}
}
