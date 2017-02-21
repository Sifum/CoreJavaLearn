package com.javacore.swing;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class DialogFrame {
	public static void main(String args[])
	{
		EventQueue.invokeLater(new Runnable()
				{

					@Override
					public void run() {
						// TODO Auto-generated method stub
						DFrame df =  new DFrame();
						df.setTitle("DialogFrame");
						df.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						df.setVisible(true);
					}
					
				});
	}
}

class DFrame extends JFrame
{
	private static final int DEFAULT_WIDTH =300;
	private static final int DEFAULT_HEIGHT = 200;
	private AboutDialog dialog;
	
	public DFrame()
	{
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		JMenuItem aboutItem = new JMenuItem("About");
		aboutItem.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if(dialog == null)
							dialog = new AboutDialog(DFrame.this);
						dialog.setVisible(true); 
					}
					
				});
		fileMenu.add(aboutItem);
		
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						System.exit(0);
					}
					
				}
				);
		fileMenu.add(exitItem);
	}
}


class AboutDialog extends JDialog
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AboutDialog(JFrame owner)
	{
		super(owner, "About DialogTest", true);
		
		add(new JLabel("<html><h1><i>Jave Core</i></h1><hr>By Minson</html>"), BorderLayout.CENTER);
		
		JButton ok = new JButton("Ok");
		ok.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						setVisible(false);
					}
				});
		JPanel panel = new JPanel();
		panel.add(ok);
		add(panel, BorderLayout.SOUTH);
		pack();
		
	}
	
	
}