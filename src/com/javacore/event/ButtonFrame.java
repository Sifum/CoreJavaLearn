package com.javacore.event;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ButtonFrame {
	public static void main(String args[])
	{
		ButtonFm bf = new ButtonFm();
		bf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bf.setVisible(true);
	}
}

class ButtonFm extends JFrame
{
	private JPanel buttonPanel;
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	
	public ButtonFm()
	{
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		JButton yellowButton = new JButton("Yellow");
		JButton redButton = new JButton("red");
		JButton blueButton = new JButton("blue");
		
		buttonPanel = new JPanel();
		
		buttonPanel.add(redButton);
		buttonPanel.add(blueButton);
		buttonPanel.add(yellowButton);
		
		add(buttonPanel);
		
		ColorAction yellowAction = new ColorAction(Color.yellow);
		ColorAction redAction = new ColorAction(Color.red);
		ColorAction blueAction = new ColorAction(Color.blue);
		
		yellowButton.addActionListener(yellowAction);
		redButton.addActionListener(redAction);
		blueButton.addActionListener(blueAction);
		
	}
	
	
	private class ColorAction implements ActionListener
	{
		private Color backgroundColor;
		
		public ColorAction(Color c)
		{
			backgroundColor = c;
		}
		
		
		public void actionPerformed(ActionEvent event)
		{
			buttonPanel.setBackground(backgroundColor);
		}
		
	}
	
	
}