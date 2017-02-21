package com.javacore.awt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;

import com.sun.javafx.geom.Ellipse2D;

public class SimpleFrameTest
{
	public static void main(String args[])
	{
		EventQueue.invokeLater(new Runnable()
				{
					public void run()
					{
						System.out.println(System.currentTimeMillis());
						SimpleFrame frame = new SimpleFrame();
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						frame.setVisible(true);
					}
				});
	}
}

class SimpleFrame extends JFrame
{
	private static final int DEFAULT_WIDTH = 500;
	private static final int DEFAULT_HEIGHT = 300;
	
	public SimpleFrame()
	{
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		setTitle("SimpleFrame");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setLocation((screenWidth-DEFAULT_WIDTH)/2, (screenHeight-DEFAULT_HEIGHT)/2);
		add(new NoHelloWorldComponent());
		add(new DrawComponent());
	}
}

class NoHelloWorldComponent extends JComponent
{
	public static final int MESSAGE_X = 75;
	public static final int MESSAGE_Y = 100;
	
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	
	public void paintComponent(Graphics g)
	{
		g.drawString("Not a Hello World program", MESSAGE_X, MESSAGE_Y);
	}
	
	public Dimension getPreferredSize(){return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);}
}

class DrawComponent extends JComponent
{
	private static final int DEFAULT_WIDTH = 400;
	private static final int DEFAULT_HEIGHT = 400;
	
	private static StringBuilder sb;
	private static final String url = "/static/images/12.jpg";
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		
		Image image = new ImageIcon(url).getImage();
		g2.drawImage(image, 0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT, 0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT, null);
		
	}
}