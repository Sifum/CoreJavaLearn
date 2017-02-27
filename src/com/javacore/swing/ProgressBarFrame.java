package com.javacore.swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;

public class ProgressBarFrame {
	public static void main(String args[])
	{
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JFrame frame = new PBFrame();
				frame.setTitle("ProgressBar");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

class PBFrame extends JFrame
{
	public static final int TEXT_ROWS = 10;
	public static final int TEXT_COLUMNS = 40;
	
	private JButton startButton;
	private JProgressBar progressBar;
	private JCheckBox checkBox;
	private JTextArea textArea;
	private SimulatedActivity activity;
	public PBFrame()
	{
		textArea = new JTextArea(TEXT_ROWS, TEXT_COLUMNS);
		
		final int MAX = 1000;
		JPanel panel = new JPanel();
		startButton = new JButton("Start");
		progressBar = new JProgressBar(0, MAX);
		progressBar.setStringPainted(true);
		panel.add(startButton);
		panel.add(progressBar);
		
		checkBox = new JCheckBox("indeterminate");
		checkBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				progressBar.setIndeterminate(checkBox.isSelected());
				progressBar.setStringPainted(!progressBar.isIndeterminate());
			}
		});
		
		panel.add(checkBox);
		add(new JScrollPane(textArea), BorderLayout.CENTER);
		add(panel, BorderLayout.SOUTH);
		
		startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				startButton.setEnabled(false);
				activity = new SimulatedActivity(MAX);
				activity.execute();
			}
		});
		pack();
	}
	
	class SimulatedActivity extends SwingWorker<Void, Integer>
	{
		private int current;
		private int target;
		
		public SimulatedActivity(int t)
		{
			current = 0;
			target = t;
		}
		@Override
		protected Void doInBackground() throws Exception {
			// TODO Auto-generated method stub
			try
			{
				while(current < target)
				{
					Thread.sleep(100);
					current++;
					publish(current);
				}
			}
			catch(InterruptedException e)
			{
				
			}
			return null;
		}
		
		
		protected void process(List<Integer> chunks)
		{
			for(Integer chunk : chunks)
			{
				textArea.append(chunk + "\n");
				progressBar.setValue(chunk);
			}
		}
		protected void done()
		{
			startButton.setEnabled(true);
		}
	}
	
}