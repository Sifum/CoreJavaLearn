package com.javacore.concurrency;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Bounce {
	public static void main(String args[])
	{
		EventQueue.invokeLater(new Runnable()
				{
					public void run()
					{
						JFrame frame = new BounceFrame();
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						frame.setVisible(true);						
					}
				});
	}
}

class BounceFrame extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_WIDTH = 450;
	private static final int DEFAULT_HEIGHT = 350;
	private BallComponent comp;
	public static final int STEPS = 1000;
	public static final int DELAY = 3;
	
	public BounceFrame()
	{
		setTitle("Bounce");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		comp = new BallComponent();
		add(comp, BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		addButton(buttonPanel, "Start", new ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						addBall();
					}
				});
		
		addButton(buttonPanel, "Close", new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				System.exit(0);
			}
		});		
		add(buttonPanel, BorderLayout.SOUTH);
	}
	
	public void addButton(Container c, String title, ActionListener listener)
	{
		JButton button = new JButton(title);
		c.add(button);
		button.addActionListener(listener);
	}
	
	public void addBall()
	{
		try
		{
			Ball ball = new Ball();
			comp.add(ball);
			
			for(int i = 1; i <= STEPS; i++)
			{
				ball.move(comp.getBounds());
				comp.paint(comp.getGraphics());
				Thread.sleep(DELAY);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}



















