package com.javacore.swing;

import java.awt.*;
import java.awt.event.*;
import java.beans.EventHandler;
import javax.swing.*;


public class TalbeTest {
	public static void main(String args[])
	{
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JFrame frame = new PlanetTableFrame();
				frame.setTitle("TableTest");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

class PlanetTableFrame extends JFrame
{
	private String[] columnNames = {"Planet", "Radius", "Moons", "Gaseous", "Color"};
	private Object[][] cells = {
			{"Mercury", 244.0, 0, false, Color.YELLOW},
			{"Mercury", 244.0, 0, false, Color.YELLOW},
			{"Mercury", 244.0, 0, false, Color.YELLOW},
			{"Mercury", 244.0, 0, false, Color.YELLOW},
			{"Mercury", 244.0, 0, false, Color.YELLOW},
			{"Mercury", 244.0, 0, false, Color.YELLOW},
			{"Mercury", 244.0, 0, false, Color.YELLOW},
	};
	
	public PlanetTableFrame()
	{
		final JTable table = new JTable(cells, columnNames);
		table.setAutoCreateRowSorter(true);
		add(table, BorderLayout.CENTER);
		JButton printButton = new JButton("print");
		printButton.addActionListener(EventHandler.create(ActionListener.class, table, "print"));
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(printButton);
		add(buttonPanel, BorderLayout.SOUTH);
		pack();
		
	}
	
}








