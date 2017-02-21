package com.javacore.internet;

import java.io.*;
import java.net.*;
import java.util.*;

public class EchoServer {
	public static void main(String args[]) throws IOException
	{
		try(ServerSocket s = new ServerSocket(8181))
		{
			try(Socket incoming = s.accept())
			{
				InputStream inputStream = incoming.getInputStream();
				OutputStream outputStream = incoming.getOutputStream();
				
				try(Scanner in = new Scanner(inputStream))
				{
					PrintWriter out = new PrintWriter(outputStream, true);
					out.println("Hello enter bye to exit:");
					
					boolean done = false;
					while(!done && in.hasNextLine())
					{
						String line = in.nextLine();
						out.println("Echo:" + line);
						if(line.trim().equals("bye")) done = true;
					}
				}
			}
		}
	}
}
