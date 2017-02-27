package com.javacore.swing;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

public class TabbedPaneFrame {
	public static void main(String args[])
	{
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JFrame frame = new TPFrame();
				frame.setTitle("TabbedPaneFrame");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}


class TPFrame extends JFrame
{
	private static final int DEFAULT_WIDHT = 400;
	private static final int DEFAULT_HEIGHT = 300;
	
	private JTabbedPane tabbedPane;
	public TPFrame()
	{
		setSize(DEFAULT_WIDHT, DEFAULT_HEIGHT);
		
		tabbedPane = new JTabbedPane();
		ImageIcon icon = new ImageIcon(getClass().getResource("static/images/logo.jpg"));
		
		tabbedPane.addTab("Mercury", icon, null);
		tabbedPane.addTab("Venus", icon, null);
		tabbedPane.addTab("Pluto", icon, null);
		
		final int plutoIndex = tabbedPane.indexOfTab("Pluto");
		JPanel plutoPanel = new JPanel();
		plutoPanel.add(new JLabel("Pluto", icon, SwingConstants.LEADING));
		JToggleButton plutoCheckBox = new JCheckBox();
		plutoCheckBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tabbedPane.remove(plutoIndex);
			}
		});
		
		plutoPanel.add(plutoCheckBox);
		tabbedPane.setTabComponentAt(plutoIndex, plutoPanel);
		
		add(tabbedPane, "Center");
		
		tabbedPane.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				if(tabbedPane.getSelectedComponent() == null)
				{
					int n = tabbedPane.getSelectedIndex();
					loadTab(n);
				}
			}
		});
		loadTab(0);
		
		JPanel buttonPanel = new JPanel();
		ButtonGroup buttonGroup = new ButtonGroup();
		JRadioButton wrapButton = new JRadioButton("Wrap tabs");
		wrapButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
			}
		});
		buttonPanel.add(wrapButton);
		buttonGroup.add(wrapButton);
		wrapButton.setSelected(true);
		JRadioButton scrollButton = new JRadioButton("Scroll tabs");
		scrollButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
			}
		});
		buttonPanel.add(scrollButton);
		buttonGroup.add(scrollButton);
		add(buttonPanel, BorderLayout.SOUTH);
	}
	
	
	private void loadTab(int n)
	{
		String title = tabbedPane.getTitleAt(n);
		ImageIcon planetIcon = new ImageIcon(getClass().getResource("static/images/logo.jpg"));
		tabbedPane.setComponentAt(n, new JLabel(planetIcon));
		
		tabbedPane.setIconAt(n, new ImageIcon(getClass().getResource("static/images/logo.jpg")));
	}
}