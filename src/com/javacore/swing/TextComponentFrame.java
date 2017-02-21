package com.javacore.swing;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class TextComponentFrame {
	public static void main(String args[])
	{
		EventQueue.invokeLater(new Runnable()
				{
					@Override
					public void run() {
						// TODO Auto-generated method stub
						TFrame tf = new TFrame();
						tf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						tf.setVisible(true);
					}
				});
	}
}

class TFrame extends JFrame 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 400;

	private static final int TEXTAREA_ROWS = 8;
	private static final int TEXTAREA_COLUMNS = 20;
	
	public TFrame()
	{
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		final JTextField textField = new JTextField();
		final JPasswordField passwordField =  new JPasswordField();
		
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(2, 2));
		northPanel.add(new JLabel("User name:", SwingConstants.RIGHT));
		northPanel.add(textField);
		northPanel.add(new JLabel("Password:", SwingConstants.RIGHT));
		northPanel.add(passwordField);
		
		add(northPanel, BorderLayout.NORTH);
		
		final JTextArea textArea = new JTextArea(TEXTAREA_ROWS, TEXTAREA_COLUMNS);
		JScrollPane scrollPane = new JScrollPane(textArea);
		
		add(scrollPane, BorderLayout.CENTER);
		
		JPanel southPanel = new JPanel();
		
		JButton insertButton = new JButton("insert");
		southPanel.add(insertButton);
		insertButton.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						textArea.append("User name:" + textField.getText() + " ,Password:" + new String(passwordField.getPassword()) + "\n");
					}
					
				});
		add(southPanel, BorderLayout.SOUTH);
		pack();
	}
}





