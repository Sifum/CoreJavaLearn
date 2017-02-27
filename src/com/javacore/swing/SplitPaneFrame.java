package com.javacore.swing;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.*;


public class SplitPaneFrame {
	public static void main(String args[])
	{
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JFrame frame = new SPFrame();
				frame.setTitle("SplitePaneFrame");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

class SPFrame extends JFrame
{
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 300;
	
	private Planet[] planets = {new Planet("Mercury", 2440, 0), new Planet("Earth", 6378, 1)};
	
	public SPFrame()
	{
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		final JList<Planet> planetList = new JList<>(planets);
		final JLabel planetImage = new JLabel();
		final JTextArea planetDescription = new JTextArea();
		
		planetList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				Planet value = (Planet) planetList.getSelectedValue();
				
				planetImage.setIcon(value.getImage());
				planetDescription.setText(value.getDescription());
			}
		});
		JSplitPane innerPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, planetList, planetImage);
		
		innerPane.setContinuousLayout(true);
		innerPane.setOneTouchExpandable(true);
		
		JSplitPane outPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, innerPane, planetDescription);
		
		add(outPane, BorderLayout.CENTER);
	}
}

class Planet
{
	private String planetName;
	private int planetWeight;
	private int planetNum;
	
	public Planet(String name, int p1, int p2)
	{
		planetName = name;
		planetWeight = p1;
		planetNum = p2;
	}
	
	public ImageIcon getImage()
	{
		return new ImageIcon("static/images/12.jpg");
	}
	
	public String getDescription()
	{
		return "Planet name is :" + planetName + ", weight: " + planetWeight;
	}
	
}