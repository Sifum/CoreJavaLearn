package com.javacore.event;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;


public class MouseComponent {
	public static void main(String args[])
	{
		MFrame jf = new MFrame();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
}

class MFrame extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_WIDTH = 400;
	private static final int DEFAULT_HEIGHT = 300;

	public MFrame()
	{
		add(new MComponent());
		pack();
	}
	
	public Dimension getPreferredSize(){return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);}
}

class MComponent extends JComponent
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int SIDELENTH = 10;
	private ArrayList<Rectangle2D> squares;
	private Rectangle2D current;
	
	public MComponent()
	{
		squares = new ArrayList<>();
		current = null;
		
		addMouseListener(new MouseHandler());
		addMouseMotionListener(new MouseMotionHandler());
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		
		for(Rectangle2D r : squares)
		{
			g2.draw(r);
		}
	}
	
	public Rectangle2D find(Point p)
	{
		for(Rectangle2D r : squares)
		{
			if(r.contains(p)) return r;
		}
		return null;
	}
	
	
	public void add(Point p)
	{
		double x = p.getX();
		double y = p.getY();
		
		current = new Rectangle2D.Double(x - SIDELENTH / 2, y - SIDELENTH / 2, SIDELENTH, SIDELENTH);
		squares.add(current);
		repaint();
		
	}
	
	
	public void remove(Rectangle2D s)
	{
		if(s == null) return;
		if(s == current) current = null;
		
		squares.remove(s);
		repaint();
	}
	
	private class MouseHandler extends MouseAdapter
	{
		public void mousePressed(MouseEvent event)
		{
			current = find(event.getPoint());
			if(current == null) add(event.getPoint());
		}
		public void mouseClicked(MouseEvent event)
		{
			current = find(event.getPoint());
			if(current != null && event.getClickCount() >= 2) remove(current);
		}
	}
	
	private class MouseMotionHandler implements MouseMotionListener
	{
		public void mouseMoved(MouseEvent event)
		{
			if(find(event.getPoint()) == null) setCursor(Cursor.getDefaultCursor());
			else setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		}
		
		public void mouseDragged(MouseEvent event)
		{
			if(current != null)
			{
				int x = event.getX();
				int y = event.getY();
				
				current.setFrame(x - SIDELENTH / 2, y - SIDELENTH / 2, SIDELENTH, SIDELENTH);
				repaint();
			}
		}
	}
	
}