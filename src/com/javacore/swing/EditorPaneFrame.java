package com.javacore.swing;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.Stack;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import com.sun.security.jgss.ExtendedGSSContext;
import com.sun.webkit.network.URLs;

import javafx.scene.layout.Border;

public class EditorPaneFrame {
	public static void main(String args[])
	{
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JFrame frame = new EPFrame();
				frame.setTitle("EditorPanelFrame");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

class EPFrame extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_WIDTH = 600;
	private static final int DEFAULT_HEIGHT = 400;
	
	public EPFrame()
	{
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		final Stack<String> urlStack = new Stack<>();
		final JEditorPane editorPane = new JEditorPane();
		final JTextField url = new JTextField(30);
		
		editorPane.setEditable(false);
		editorPane.addHyperlinkListener(new HyperlinkListener() {
			
			@Override
			public void hyperlinkUpdate(HyperlinkEvent e) {
				// TODO Auto-generated method stub
				if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
				{
					try
					{
						urlStack.push(e.getURL().toString());
						url.setText(e.getURL().toString());
						editorPane.setPage(e.getURL());
					}
					catch (IOException e1) {
						// TODO: handle exception
						editorPane.setText("Exception: " + e1);
					}
				}
			}
		});
		
		final JCheckBox editable = new JCheckBox();
		editable.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				editorPane.setEditable(editable.isSelected());
			}
		});
		
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try
				{
					urlStack.push(url.getText());
					editorPane.setPage(url.getText());
				}
				catch (IOException e1) {
					// TODO: handle exception
					editorPane.setText("Exception: " + e1);
				}
			}
		};
		
		JButton loadButton = new JButton("Load");
		loadButton.addActionListener(listener);
		url.addActionListener(listener);
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(urlStack.size() <= 1) return;
				try
				{
					urlStack.pop();
					String urlString = urlStack.peek();
					url.setText(urlString);
					editorPane.setPage(urlString);
				}
				catch (IOException e1) {
					// TODO: handle exception
					editorPane.setText("Exception: " + e1);
				}
			}
		});
		
		
		add(new JScrollPane(editorPane), BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.add(new JLabel("URL"));
		panel.add(url);
		panel.add(loadButton);
		panel.add(backButton);
		panel.add(new JLabel("Editable"));
		panel.add(editable);
		
		add(panel, BorderLayout.SOUTH);
		
	}
}