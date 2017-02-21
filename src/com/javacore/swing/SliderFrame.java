package com.javacore.swing;

import java.awt.*;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderFrame {
	public static void main(String args[])
	{
		EventQueue.invokeLater(new Runnable()
				{
					public void run()
					{
						SSFrame sf = new SSFrame();
						sf.setTitle("SliderFrame");
						sf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						sf.setVisible(true);
					}
				});
	}
}

class SSFrame extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel sliderPanel;
	private JTextField textField;
	private ChangeListener listener;
	
	public SSFrame()
	{
		sliderPanel = new JPanel();
		sliderPanel.setLayout(new GridBagLayout());
		
		listener = new ChangeListener()
				{

					@Override
					public void stateChanged(ChangeEvent e) {
						// TODO Auto-generated method stub
						JSlider source = (JSlider) e.getSource();
						textField.setText("" + source.getValue());
					}
				};
		
		JSlider	slider = new JSlider();
		addSlider(slider, "Plain");
		
		slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(5);
		addSlider(slider, "Ticks");
		
		slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setSnapToTicks(true);
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(5);
		addSlider(slider, "Snap to Ticks");		
		
		slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(5);
		slider.setPaintTrack(true);
		addSlider(slider, "No track");		

		slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(5);
		slider.setInverted(true);
		addSlider(slider, "Inverted");

		slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(5);
		addSlider(slider, "Labels");
		
		slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(5);
		
		Dictionary<Integer, Component> labelTable = new Hashtable<>();
		labelTable.put(0, new JLabel("A"));
		labelTable.put(20, new JLabel("B"));
		labelTable.put(40, new JLabel("C"));
		labelTable.put(60, new JLabel("D"));
		labelTable.put(80, new JLabel("E"));
		labelTable.put(100, new JLabel("F"));
		
		slider.setLabelTable(labelTable);
		addSlider(slider, "Custom labels");
		
		slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setSnapToTicks(true);
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(20);
		
		labelTable = new Hashtable<>();
		labelTable.put(0, new JLabel(new ImageIcon("static/images/logo.jpg")));
		labelTable.put(20, new JLabel(new ImageIcon("static/images/logo.jpg")));
		labelTable.put(40, new JLabel(new ImageIcon("static/images/logo.jpg")));
		labelTable.put(60, new JLabel(new ImageIcon("static/images/logo.jpg")));
		labelTable.put(80, new JLabel(new ImageIcon("static/images/logo.jpg")));
		labelTable.put(100, new JLabel(new ImageIcon("static/images/logo.jpg")));		
		
		slider.setLabelTable(labelTable);
		addSlider(slider, "Icon labels");		
		
		textField = new JTextField();
		add(sliderPanel, BorderLayout.CENTER);
		add(textField, BorderLayout.SOUTH);
		pack();
		
	}
	
	public void addSlider(JSlider s, String description)
	{
		s.addChangeListener(listener);
		JPanel panel = new JPanel();
		panel.add(s);
		panel.add(new JLabel(description));
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridy = sliderPanel.getComponentCount();
		gbc.anchor = GridBagConstraints.WEST;
		sliderPanel.add(panel, gbc);
	}
	
}
