package com.javacore.swing;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

public class RadioButtonFrame {
	public static void main(String args[])
	{
		EventQueue.invokeLater(new Runnable()
		{

			@Override
			public void run() {
				// TODO Auto-generated method stub
				RBFrame bf = new RBFrame();
				bf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				bf.setVisible(true);
			}
			
		});
	}
}

class RBFrame extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel buttonPanel;
	private ButtonGroup group;
	private JLabel label;
	private static final int DEFAULT_SIZE = 36;
	
	public RBFrame()
	{
		label = new JLabel("The quick brown fox jumps over the lazy dog.");
		label.setFont(new Font("Serif", Font.PLAIN, DEFAULT_SIZE));
		add(label, BorderLayout.CENTER);
		
		buttonPanel = new JPanel();
		group = new ButtonGroup();
		
		addRadioButton("Small", 8);
		addRadioButton("Medium", 12);
		addRadioButton("Large", 18);
		addRadioButton("Extra Large", 36);
		
		Border etched = BorderFactory.createEtchedBorder();
		Border titled= BorderFactory.createTitledBorder(etched, "Border Types");
		buttonPanel.setBorder(titled);
		add(buttonPanel, BorderLayout.SOUTH);
		pack();
		
	}
	
	public void addRadioButton(String name, final int size)
	{
		boolean selected = size == DEFAULT_SIZE;
		JRadioButton button = new JRadioButton(name, selected);
		group.add(button);
		buttonPanel.add(button);
		
		ActionListener listener = new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						label.setFont(new Font("Serif", Font.PLAIN, size));
					}
					
				};
		button.addActionListener(listener);
		
	}
	
}






