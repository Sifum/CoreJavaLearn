package com.javacore.event;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class ActionFrame {
	public static void main(String args[])
	{
		Aframe af = new Aframe();
		af.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		af.setVisible(true);
	}
}

class Aframe extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel buttonPanel;
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	
	public Aframe()
	{
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		buttonPanel = new JPanel();
		
		Action yellowAction = new ColorAction("Yellow", new ImageIcon("static/images/logo.jpg"), Color.YELLOW);
		Action blueAction = new ColorAction("Blue", new ImageIcon("static/images/logo.jpg"), Color.BLUE);
		Action redAction = new ColorAction("Red", new ImageIcon("static/images/logo.jpg"), Color.RED);
		
		buttonPanel.add(new JButton(yellowAction));
		buttonPanel.add(new JButton(blueAction));
		buttonPanel.add(new JButton(redAction));
		
		add(buttonPanel);
		
		/*associate the Y, B, and R keys with names*/
		InputMap imap = buttonPanel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		imap.put(KeyStroke.getKeyStroke("ctrl Y"), "panel.yellow");
		imap.put(KeyStroke.getKeyStroke("ctrl B"), "panel.blue");
		imap.put(KeyStroke.getKeyStroke("ctrl R"), "panel.red");
		
		ActionMap amap = buttonPanel.getActionMap();
		amap.put("panel.yellow", yellowAction);
		amap.put("panel.blue", blueAction);
		amap.put("panel.red", redAction);
		
	}
	
	class ColorAction extends AbstractAction
	{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ColorAction(String name, Icon icon, Color c)
		{
			putValue(Action.NAME, name);
			putValue(Action.SMALL_ICON, icon);
			putValue(Action.SHORT_DESCRIPTION, "Set Panel color to " + name.toLowerCase());
			putValue("color", c);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Color c = (Color) getValue("color");
			buttonPanel.setBackground(c);
		}
		
	}	
	
}

