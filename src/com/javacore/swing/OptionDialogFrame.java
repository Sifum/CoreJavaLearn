package com.javacore.swing;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.Date;

import javax.swing.*;

public class OptionDialogFrame {
	public static void main(String args[])
	{
		EventQueue.invokeLater(new Runnable()
				{

					@Override
					public void run() {
						// TODO Auto-generated method stub
						ODFrame df = new ODFrame();
						df.setTitle("OptionDialogFrame");
						df.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						df.setVisible(true);
					}
					
				});
	}
}


class ODFrame extends JFrame
{
	private ButtonPanel typePanel;
	private ButtonPanel messagePanel;
	private ButtonPanel messageTypePanel;
	private ButtonPanel optionTypePanel;
	private ButtonPanel optionsPanel;
	private ButtonPanel inputPanel;
	private String messageString = "Message";
	private Icon messageIcon = new ImageIcon("static/images/logo.jpg");
	private Object messageObject = new Date();
	private Component messageComponent = new SampleComponent();
	private static final int DEFAULT_WIDTH = 500;
	private static final int DEFAULT_HEIGHT = 400;
	
	public ODFrame()
	{
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		JPanel gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(2, 3));
		
		typePanel = new ButtonPanel("Type", "Message", "Confirm", "Option", "Input");
		messageTypePanel = new ButtonPanel("Message Type", "ERROR_MESSAGE", "INFORMATION_MESSGE", "WARNING_MESSAGE", "QUESTION_MESSAGE", "PLAIN_MESSAGE");
		messagePanel = new ButtonPanel("Message", "String", "Icon", "Component", "Other", "Object[]");
		optionTypePanel = new ButtonPanel("Confirm", "DEFAULT_OPTION", "YES_NO_OPTION", "YES_NO_CANCEL_OPTION", "OK_CANCEL_OPTION");
		optionsPanel = new ButtonPanel("Option", "String[]", "Icon[]", "Object[]");
		inputPanel = new ButtonPanel("Input", "Text field", "Combo box");
		
		gridPanel.add(typePanel);
		gridPanel.add(messageTypePanel);
		gridPanel.add(messagePanel);
		gridPanel.add(optionTypePanel);
		gridPanel.add(optionsPanel);
		gridPanel.add(inputPanel);
		
		JPanel showPanel = new JPanel();
		JButton showButton = new JButton("Show");
		showButton.addActionListener(new ShowAction());
		showPanel.add(showButton);
		
		add(gridPanel, BorderLayout.CENTER);
		add(showPanel, BorderLayout.SOUTH);
		pack();
	}
	
	public Object getMessage()
	{
		String s = messagePanel.getSelection();
		if(s.equals("String")) return messageString;
		else if(s.equals("Icon")) return messageIcon;
		else if(s.equals("Component")) return messageComponent;
		else if(s.equals("Object[]")) return new Object[]{messageString, messageIcon, messageComponent, messageObject};
		else if(s.equals("Other")) return messageObject;
		else return null;
	}
	
	public Object[] getOptions()
	{
		String s = optionsPanel.getSelection();
		if(s.equals("String[]")) return new String[]{"Yellow", "Blue", "Red"};
		else if(s.equals("Icon[]")) return new Icon[]{new ImageIcon("static/images/log.jpg"),new ImageIcon("static/images/log.jpg"),new ImageIcon("static/images/log.jpg")};
		else if(s.equals("Object[]")) return new Object[]{messageString, messageIcon, messageComponent, messageObject};
		else return null;
	}
	
	public int getType(ButtonPanel panel)
	{
		String s = panel.getSelection();
		try
		{
			return JOptionPane.class.getField(s).getInt(null);
		}
		catch(Exception e)
		{
			return -1;
		}
	}
	
	private class ShowAction implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			if(typePanel.getSelection().equals("Confirm")) JOptionPane.showConfirmDialog(ODFrame.this, getMessage(), "Title", getType(optionTypePanel), getType(messageTypePanel));
			else if(typePanel.getSelection().equals("Input"))
			{
				if(inputPanel.getSelection().equals("Text field")) JOptionPane.showInputDialog(ODFrame.this, getMessage(), "Title", getType(messageTypePanel));
				else JOptionPane.showInputDialog(ODFrame.this, getMessage(), "Title", getType(messageTypePanel), null, new String[]{"Yellow", "Blue", "Red"}, "Blue");
			}
			else if(typePanel.getSelection().equals("Message"))  JOptionPane.showMessageDialog(ODFrame.this, getMessage(), "Title", getType(messageTypePanel));
			else if(typePanel.getSelection().equals("Option"))  JOptionPane.showOptionDialog(ODFrame.this, getMessage(), "Title", getType(optionTypePanel), getType(messageTypePanel), null, getOptions(), getOptions()[0]);
		}
		
	}
	
	
}
/***********************************/

class SampleComponent extends JComponent
{
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		Rectangle2D rect = new Rectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1);
		g2.setPaint(Color.YELLOW);
		g2.fill(rect);
		g2.setPaint(Color.BLUE);
		g2.draw(rect);
	}
	
	public Dimension getPreferredSize()
	{
		return new Dimension(10, 10);
	}
}

/****************************************/
class ButtonPanel extends JPanel
{
	private ButtonGroup group;
	
	public ButtonPanel(String title, String...options)
	{
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), title));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		group = new ButtonGroup();
		for(String option : options)
		{
			JRadioButton b = new JRadioButton(option);
			b.setActionCommand(option);
			add(b);
			group.add(b);
			b.setSelected(option == options[0]);
		}
	}
	
	public String getSelection()
	{
		return group.getSelection().getActionCommand();
	}
}