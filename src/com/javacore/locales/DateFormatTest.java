package com.javacore.locales;

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

import com.javacore.gbc.GBC;

public class DateFormatTest {
	public static void main(String args[])
	{
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JFrame frame = new DateFormatFrame();
				frame.setTitle("DateFormatTest");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

class DateFormatFrame extends JFrame
{
	private Locale[] locales;
	private Date currentDate;
	private Date currentTime;
	private DateFormat currentDateFormat;
	private DateFormat currentTimeFormat;
	private JComboBox<String> localeCombo = new JComboBox<>();
	private JButton dateParseButton = new JButton("Parse date");
	private JButton timeParseButton = new JButton("Parse time");
	private JTextField dateText = new JTextField(30);
	private JTextField timeText = new JTextField(30);
	private JCheckBox lenientCheckbox = new JCheckBox("Parse lenient", true);
	private EnumCombo dateStyleCombo = new EnumCombo(DateFormat.class, "Default", "Full", "Long", "Medium", "Short");
	private EnumCombo timeStyleCombo = new EnumCombo(DateFormat.class, "Default", "Full", "Long", "Medium", "Short");
	
	public DateFormatFrame()
	{
		setLayout(new GridBagLayout());
		
		add(new JLabel("Locale"), new GBC(0, 0).setAnchor(GBC.EAST));
		add(new JLabel("Date style"), new GBC(0, 1).setAnchor(GBC.EAST));
		add(new JLabel("Time style"), new GBC(2, 1).setAnchor(GBC.EAST));
		add(new JLabel("Date"), new GBC(0, 2).setAnchor(GBC.EAST));
		add(new JLabel("Time"), new GBC(0, 3).setAnchor(GBC.EAST));
		add(localeCombo, new GBC(1, 0, 2, 1).setAnchor(GBC.WEST));
		add(dateStyleCombo, new GBC(1, 1).setAnchor(GBC.WEST));
		add(timeStyleCombo, new GBC(3, 1).setAnchor(GBC.WEST));
		add(dateParseButton, new GBC(3, 2).setAnchor(GBC.WEST));
		add(timeParseButton, new GBC(3, 3).setAnchor(GBC.WEST));
		add(lenientCheckbox, new GBC(0, 4, 2, 1).setAnchor(GBC.WEST));
		add(dateText, new GBC(1, 2, 2, 1).setFill(GBC.HORIZONTAL));
		add(timeText, new GBC(1, 3, 2, 1).setFill(GBC.HORIZONTAL));
		
		locales = (Locale[]) DateFormat.getAvailableLocales().clone();
		Arrays.sort(locales, new Comparator<Locale>() {

			@Override
			public int compare(Locale o1, Locale o2) {
				// TODO Auto-generated method stub
				return o1.getDisplayName().compareTo(o2.getDisplayName());
			}
			
		});
		for(Locale loc : locales)
			localeCombo.addItem(loc.getDisplayName());
		localeCombo.setSelectedItem(Locale.getDefault().getDisplayName());
		currentDate = new Date();
		currentTime = new Date();
		updateDisplay();
		
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updateDisplay();
			}
		};
		
		localeCombo.addActionListener(listener);
		dateStyleCombo.addActionListener(listener);
		timeStyleCombo.addActionListener(listener);
		
		dateParseButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String d = dateText.getText().trim();
				try{
					currentDateFormat.setLenient(lenientCheckbox.isSelected());
					Date date = currentDateFormat.parse(d);
					currentDate = date;
					updateDisplay();
				}
				catch(ParseException e1)
				{
					dateText.setText("Parse error:" + d);
				}
				catch(IllegalArgumentException e1)
				{
					dateText.setText("Agument error:" + d);
				}
			}
		});
		
		timeParseButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String t = timeText.getText().trim();
				try{
					currentDateFormat.setLenient(lenientCheckbox.isSelected());
					Date date = currentTimeFormat.parse(t);
					currentTime = date;
					updateDisplay();
				}
				catch(ParseException e1)
				{
					timeText.setText("Parse error:" + t);
				}
				catch(IllegalArgumentException e1)
				{
					timeText.setText("Agument error:" + t);
				}
			}
		});
		pack();
	}
	
	public void updateDisplay()
	{
		Locale currentLocale = locales[localeCombo.getSelectedIndex()];
		int dateStyle = dateStyleCombo.getValue();
		currentDateFormat = DateFormat.getDateInstance(dateStyle, currentLocale);
		String d = currentDateFormat.format(currentDate);
		dateText.setText(d);
		int timeStyle = timeStyleCombo.getValue();
		currentTimeFormat = DateFormat.getTimeInstance(timeStyle, currentLocale);
		String t = currentTimeFormat.format(currentTime);
		timeText.setText(t);
	}
	
}

class EnumCombo extends JComboBox<String>
{
	private Map<String, Integer> table = new TreeMap<>();
	
	public EnumCombo(Class<?> cl, String ... labels)
	{
		for(String label : labels)
		{
			String name = label.toUpperCase().replace(' ', '_');
			int value = 0;
			try
			{
				java.lang.reflect.Field f = cl.getField(name);
				value = f.getInt(cl);
			}
			catch(Exception e)
			{
				label = "(" + label + ")";
			}
			table.put(label, value);
			addItem(label);
		}
		setSelectedItem(labels[0]);
	}
	
	public int getValue()
	{
		return table.get(getSelectedItem());
	}
	
}

