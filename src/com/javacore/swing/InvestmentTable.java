package com.javacore.swing;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;


public class InvestmentTable {
	public static void main(String args[])
	{
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JFrame frame = new InvestmentTableFrame();
				frame.setTitle("Investment Table");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

class InvestmentTableFrame extends JFrame
{
	public InvestmentTableFrame()
	{
		TableModel model = new InvestmentTableModel(30, 5, 10);
		JTable table = new JTable(model);
		add(table, BorderLayout.CENTER);
		pack();
	}
}

class InvestmentTableModel extends AbstractTableModel
{
	private static double INITIAL_BALANCE = 10000.0;
	
	private int years;
	private int minRate;
	private int maxRate;
	
	public InvestmentTableModel(int y, int r1, int r2) {
		// TODO Auto-generated constructor stub
		years = y;
		minRate = r1;
		maxRate = r2;
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return years;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return maxRate - minRate + 1;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		double rate = (columnIndex + minRate) / 100.0;
		int nperiods = rowIndex;
		double futureBalance = INITIAL_BALANCE * Math.pow(1 + rate, nperiods);
		return String.format("%.2f", futureBalance);
	}
	
	public String getColumnName(int columnIndex)
	{
		return (columnIndex + minRate) + "%";
	}
	
}
