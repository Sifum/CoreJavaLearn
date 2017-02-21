package com.javacore.swing;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MenuFrame {
	public static void main(String args[])
	{
		EventQueue.invokeLater(new Runnable()
				{
					public void run()
					{
						MFrame mf = new MFrame();
						mf.setTitle("MenuFrame");
						mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						mf.setVisible(true);
					}
				});
	}
}


class MFrame extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	private Action saveAction;
	private Action saveAsAction;
	private JCheckBoxMenuItem readonlyItem;
	private JPopupMenu popup;
	
	class TestAction extends AbstractAction
	{
		public TestAction(String name)
		{
			super(name);
		}
		public void actionPerformed(ActionEvent event)
		{
			System.out.println(getValue(Action.NAME) + " selected.");
		}
	}
	
	public MFrame()
	{
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		JMenu fileMenu = new JMenu("File");
		fileMenu.add(new TestAction("New"));
		
		JMenuItem openItem = fileMenu.add(new TestAction("Open"));
		openItem.setAccelerator(KeyStroke.getKeyStroke("ctrl o"));
		
		saveAction = new TestAction("Save");
		JMenuItem saveItem = fileMenu.add(saveAction);
		saveItem.setAccelerator(KeyStroke.getKeyStroke("ctrl s"));
		
		saveAsAction = new TestAction("Save As");
		fileMenu.add(saveAsAction);
		fileMenu.addSeparator();
		
		fileMenu.add(new AbstractAction("Exit")
				{
					public void actionPerformed(ActionEvent event)
					{
						System.exit(0);
					}
				});
		
		readonlyItem = new JCheckBoxMenuItem("Read-only");
		readonlyItem.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						boolean saveOk = !readonlyItem.isSelected();
						saveAction.setEnabled(saveOk);
						saveAsAction.setEnabled(saveOk);
					}
				});
		
		ButtonGroup group = new ButtonGroup();
		
		JRadioButtonMenuItem insertItem = new JRadioButtonMenuItem("Insert");
		insertItem.setSelected(true);
		JRadioButtonMenuItem overtypeItem = new JRadioButtonMenuItem("Overtype");
		
		group.add(insertItem);
		group.add(overtypeItem);
		
		Action cutAction = new TestAction("Cut");
		cutAction.putValue(Action.SMALL_ICON, new ImageIcon("static/images/logo.jpg"));
		Action copyAction = new TestAction("Copy");
		copyAction.putValue(Action.SMALL_ICON, new ImageIcon("static/images/logo.jpg"));
		Action pasteAction = new TestAction("Paste");
		pasteAction.putValue(Action.SMALL_ICON, new ImageIcon("static/images/logo.jpg"));
		
		JMenu editMenu = new JMenu("Edit");
		editMenu.add(cutAction);
		editMenu.add(copyAction);
		editMenu.add(pasteAction);
		
		JMenu optionMenu = new JMenu("Options");
		
		optionMenu.add(readonlyItem);
		optionMenu.addSeparator();
		optionMenu.add(insertItem);
		optionMenu.add(overtypeItem);
		
		editMenu.addSeparator();
		editMenu.add(optionMenu);
		
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic('H');
		
		JMenuItem indexItem = new JMenuItem("Index");
		indexItem.setMnemonic('I');
		helpMenu.add(indexItem);
		
		Action aboutAction = new TestAction("About");
		aboutAction.putValue(Action.MNEMONIC_KEY, new Integer('A'));
		helpMenu.add(aboutAction);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(helpMenu);
		
		popup = new JPopupMenu();
		popup.add(cutAction);
		popup.add(copyAction);
		popup.add(pasteAction);
		
		JPanel panel = new JPanel();
		panel.setComponentPopupMenu(popup);
		add(panel);
		
		JToolBar bar = new JToolBar("ToolBar", SwingConstants.VERTICAL);
		bar.add(new TestAction("blue"));
		bar.add(new TestAction("green"));
		bar.add(new TestAction("red"));
		add(bar, BorderLayout.WEST);
		
		panel.addMouseListener(new MouseAdapter(){});
		
	}
	
}