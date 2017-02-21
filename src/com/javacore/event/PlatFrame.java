package com.javacore.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PlatFrame {
	public static void main(String args[])
	{
		Pframe pf = new Pframe();
		pf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pf.setVisible(true);
	}
}

class Pframe extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel buttonPanel;
	
	public Pframe()
	{
		buttonPanel = new JPanel();
		
		UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
		
		for(UIManager.LookAndFeelInfo info : infos)
		{
			makeButton(info.getName(), info.getClassName());
		}
		
		add(buttonPanel);
		pack();
		
		
	}
	
	void makeButton(String name, final String plafName)
	{
		JButton button = new JButton(name);
		buttonPanel.add(button);
		
		button.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						try
						{
							UIManager.setLookAndFeel(plafName);
							SwingUtilities.updateComponentTreeUI(Pframe.this);
							pack();
						}
						catch(Exception err)
						{
							err.printStackTrace();
						}
						
					}
				});
		
	}
	
}