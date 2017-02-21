package com.javacore.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculatorPanel {
	public static void main(String args[])
	{
		EventQueue.invokeLater(new Runnable()
				{
					@Override
					public void run() {
						// TODO Auto-generated method stub
						Jframe jf = new Jframe();
						jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						jf.setVisible(true);
					}
				});
	}
}

class Jframe extends JFrame
{
	private final CPanel cp;
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 400;
	
	public Jframe()
	{
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		cp = new CPanel();
		add(cp);
	}
}

class CPanel extends JPanel
{
	private JTextField display;
	private JPanel panel;
	private double result;
	private String lastCommand;
	private boolean start;
	
	public CPanel()
	{
		setLayout(new BorderLayout());
		result = 0;
		lastCommand = "=";
		start = true;
		
		display = new JTextField("0");
		display.setEnabled(false);
		display.setFont(new Font("SansSerif", Font.BOLD, 20));
		display.setDisabledTextColor(new Color(0, 128, 128));
		add(display, BorderLayout.NORTH);
		
		ActionListener insert = new InsertAction();
		ActionListener command = new CommandAction();
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(4, 4));
		
		addButton("7", insert);
		addButton("8", insert);
		addButton("9", insert);
		addButton("/", command);
		
		addButton("4", insert);
		addButton("5", insert);
		addButton("6", insert);
		addButton("*", command);
		
		addButton("1", insert);
		addButton("2", insert);
		addButton("3", insert);
		addButton("-", command);
		
		addButton("0", insert);
		addButton(".", insert);
		addButton("=", command);
		addButton("+", command);
		
		add(panel, BorderLayout.CENTER);
	}
	
	
	private void addButton(String label, ActionListener listener)
	{
		JButton button = new JButton(label);
		button.addActionListener(listener);
		panel.add(button);
	}
	
	
	private class InsertAction implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			String input = event.getActionCommand();
			if(start)
			{
				display.setText("");
				start = false;
			}
			display.setText(display.getText() + input);
		}
	}
	
	
	private class CommandAction implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			String command = event.getActionCommand();
			
			if(start)
			{
				if(command.equals("-"))
				{
					display.setText(command);
					start = false;
				}
				else
					lastCommand = command;
			}
			else
			{
				calculate(Double.parseDouble(display.getText()));
				lastCommand = command;
				start = true;
			}
		}
	}
	
	
	public void calculate(double x)
	{
		if(lastCommand.equals("+")) result += x;
		else if(lastCommand.equals("-")) result -= x;
		else if(lastCommand.equals("*")) result *= x;
		else if(lastCommand.equals("/")) result /= x;
		else if(lastCommand.equals("=")) result = x;
		display.setText("" + result);
	}
	
}




















