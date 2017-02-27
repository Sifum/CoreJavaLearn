package com.javacore.swing;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import javax.swing.*;

import com.sun.swing.internal.plaf.metal.resources.metal_zh_TW;

public class DeskFrame {
	public static void main(String args[])
	{
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JFrame frame = new DestopFrame();
				frame.setTitle("DeskFrame");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

class DestopFrame extends JFrame
{
	private static final int DEFAULT_WIDTH = 600;
	private static final int DEFAULT_HEIGHT = 400;
	private static final String[] planets = {"Mercury", "Venus", "Earth"};
	
	private JDesktopPane desktop;
	private int nextFrameX;
	private int nextFrameY;
	private int frameDistance;
	private int counter;
	
	public DestopFrame() 
	{
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		desktop = new JDesktopPane();
		desktop.setBackground(new Color(144, 254, 54));;
		add(desktop, BorderLayout.CENTER);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		JMenuItem openItem = new JMenuItem("New");
		openItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(planets[counter]);
				createInternalFrame(new JLabel(new ImageIcon("static/images/12.jpg")), planets[counter]);
				counter = (counter + 1) % planets.length;
			}
		});
		fileMenu.add(openItem);
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		fileMenu.add(exitItem);
		JMenu windowMenu = new JMenu("window");
		menuBar.add(windowMenu);
		JMenuItem nextItem = new JMenuItem("Next");
		nextItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				selectNextWindow();
			}
		});
		windowMenu.add(nextItem);
		JMenuItem cascadeItem = new JMenuItem("Cascade");
		cascadeItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cascadeWindows();
			}
		});
		windowMenu.add(cascadeItem);
		JMenuItem tileItem = new JMenuItem("Tile");
		tileItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tileWindows();
			}
		});
		windowMenu.add(tileItem);
		final JCheckBoxMenuItem dragOutlineItem = new JCheckBoxMenuItem("Drag Outline");
		dragOutlineItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				desktop.setDragMode(dragOutlineItem.isSelected() ? JDesktopPane.OUTLINE_DRAG_MODE : JDesktopPane.LIVE_DRAG_MODE);
			}
		});
		windowMenu.add(dragOutlineItem);
	}
	
	public void createInternalFrame(Component c, String t)
	{
		final JInternalFrame iFrame = new JInternalFrame(t, true, true, true, true);
		iFrame.add(c, BorderLayout.CENTER);
		desktop.add(iFrame);
		
		iFrame.setFrameIcon(new ImageIcon("static/images/logo.jpg"));
		
		iFrame.addVetoableChangeListener(new VetoableChangeListener() {
			
			@Override
			public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
				// TODO Auto-generated method stub
				String  name = evt.getPropertyName();
				Object value = evt.getNewValue();
				
				if(name.equals("closed") && value.equals(true))
				{
					int result = JOptionPane.showInternalConfirmDialog(iFrame, "OK to close?", "Select an Option", JOptionPane.YES_NO_OPTION);
					
					PropertyChangeEvent event = null;
					if(result != JOptionPane.YES_OPTION) 
						throw new PropertyVetoException("User canceled close", event);
				}
			}
		});
		
		int width = desktop.getWidth();
		int height = desktop.getHeight();
		iFrame.reshape(nextFrameX, nextFrameY, width, height);
		
		iFrame.show();
		
		try
		{
			iFrame.setSelected(true);
		}
		catch(PropertyVetoException ex)
		{
			
		}
		
		frameDistance = iFrame.getHeight() - iFrame.getContentPane().getHeight();
		
		nextFrameX += frameDistance;
		nextFrameY += frameDistance;
		if(nextFrameX + width > desktop.getWidth()) nextFrameX = 0;
		if(nextFrameY + height > desktop.getHeight()) nextFrameY = 0;
	}
	
	public void cascadeWindows()
	{
		int x = 0;
		int y = 0;
		int width = desktop.getWidth() / 2;
		int height = desktop.getHeight() / 2;
		
		for(JInternalFrame frame : desktop.getAllFrames())
		{
			if(!frame.isIcon())
			{
				try
				{
					frame.setMaximum(false);
					frame.reshape(x, y, width, height);
					
					x += frameDistance;
					y += frameDistance;
					if(x + width > desktop.getWidth()) x = 0;
					if(y + height > desktop.getHeight()) y = 0;
				}
				catch (PropertyVetoException e) {
					// TODO: handle exception
				}
			}
		}
		
	}
	
	
	public void tileWindows()
	{
		int frameCount = 0;
		for(JInternalFrame frame : desktop.getAllFrames())
			if(!frame.isIcon()) frameCount++;
		if(frameCount == 0) return;
		
		int rows = (int) Math.sqrt(frameCount);
		int cols = frameCount / rows;
		int extra = frameCount % rows;
		
		int width = desktop.getWidth() / cols;
		int height = desktop.getHeight() / rows;
		int r = 0;
		int c = 0;
		for(JInternalFrame frame : desktop.getAllFrames())
		{
			if(!frame.isIcon())
			{
				try
				{
					frame.setMaximum(false);
					frame.reshape(c * width, r * height, width, height);
					r++;
					if(r == rows)
					{
						r = 0;
						c++;
						if(c == cols - extra)
						{
							rows++;
							height = desktop.getHeight() / rows;
						}
					}
				}catch (PropertyVetoException e) {
					// TODO: handle exception
				}
			}
		}
		
	}
	
	
	public void selectNextWindow()
	{
		JInternalFrame[] frames = desktop.getAllFrames();
		for(int i = 0; i < frames.length; i++)
		{
			if(frames[i].isSelected())
			{
				int next = (i + 1) % frames.length;
				while(next != i)
				{
					if(!frames[next].isIcon())
					{
						try
						{
							frames[next].setSelected(true);
							frames[next].toFront();
							frames[i].toBack();
							return;
						}
						catch (PropertyVetoException e) {
							// TODO: handle exception
						}
					}
					next = (next + 1) % frames.length;
				}
			}
		}
	}	
	
}