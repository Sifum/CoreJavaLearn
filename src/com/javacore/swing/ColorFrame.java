package com.javacore.swing;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ColorFrame {
	public static void main(String args[])
	{
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JFrame frame = new CFrame();
				frame.setTitle("ColorFrame");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

class CFrame extends JFrame
{
	private JPanel panel;
	private JTextField redField;
	private JTextField greenField;
	private JTextField blueField;
	
	public CFrame()
	{
		DocumentListener listener = new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				setColor();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				setColor();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		
		panel = new JPanel();
		
		panel.add(new JLabel("Red:"));
		redField = new JTextField("255", 3);
		panel.add(redField);
		redField.getDocument().addDocumentListener(listener);
		
		panel.add(new JLabel("Gree:"));
		greenField = new JTextField("255", 3);
		panel.add(greenField);
		greenField.getDocument().addDocumentListener(listener);
		panel.add(new JLabel("Blue:"));
		blueField = new JTextField("255", 3);
		panel.add(blueField);
		blueField.getDocument().addDocumentListener(listener);	
		
		add(panel);
		pack();
	}
	
	
	public void setColor()
	{
		try
		{
			int red = Integer.parseInt(redField.getText().trim());
			int green = Integer.parseInt(greenField.getText().trim());
			int blue = Integer.parseInt(blueField.getText().trim());
			panel.setBackground(new Color(red, green, blue));
		}
		catch (NumberFormatException e) {
			// TODO: handle exception
			
		}
	}
}




