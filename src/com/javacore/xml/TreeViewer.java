package com.javacore.xml;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.tree.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.w3c.dom.CharacterData;


public class TreeViewer {
	public static void main(String args[])
	{
		EventQueue.invokeLater(new Runnable()
				{
					public void run()
					{
						JFrame frame = new DOMTreeFrame();
						frame.setTitle("TreeViewer");
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						frame.setVisible(true);
					}
				});
	}
}

class DOMTreeFrame extends JFrame
{
	private static final int DEFAULT_WIDTH = 400;
	private static final int DEFAULT_HEIGHT = 400;
	
	private DocumentBuilder builder;
	
	public DOMTreeFrame()
	{
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		JMenu fileMenu = new JMenu("File");
		JMenuItem openItem = new JMenuItem("Open");
		openItem.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						openFile();
					}
				});
		fileMenu.add(openItem);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(fileMenu);
		setJMenuBar(menuBar);
	}
	
	public void openFile()
	{
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
		chooser.setFileFilter(new javax.swing.filechooser.FileFilter()
				{

					@Override
					public boolean accept(File f) {
						// TODO Auto-generated method stub
						return f.isDirectory() || f.getName().toLowerCase().endsWith(".xml");
					}

					@Override
					public String getDescription() {
						// TODO Auto-generated method stub
						return "XML files";
					}
			
				});
		int r = chooser.showOpenDialog(this);
		if(r != JFileChooser.APPROVE_OPTION) return;
		final File file = chooser.getSelectedFile();
		
		new SwingWorker<Document, Void>()
		{

			@Override
			protected Document doInBackground() throws Exception {
				// TODO Auto-generated method stub
				if(builder == null)
				{
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					builder = factory.newDocumentBuilder();
					
				}
				return (Document) builder.parse(file);
			}
			
			protected void done()
			{
				try
				{
					Document doc = get();
					JTree tree = new JTree(new DOMTreeModel(doc));
					DOMTreeCellRenderer render = new DOMTreeCellRenderer();
					render.setLeafIcon(new ImageIcon("static/images/logo.jpg"));
					tree.setCellRenderer(render);
					
					setContentPane(new JScrollPane(tree));
					validate();
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(DOMTreeFrame.this, e);
				}
			}
		}.execute();
	}
	
}


class DOMTreeModel implements TreeModel
{
	private Document doc;
	
	public DOMTreeModel(Document doc)
	{
		this.doc = doc;
	}

	@Override
	public Object getRoot() {
		// TODO Auto-generated method stub
		return doc.getDocumentElement();
	}

	@Override
	public Object getChild(Object parent, int index) {
		// TODO Auto-generated method stub
		Node node = (Node) parent;
		NodeList list = node.getChildNodes();
		return list.item(index);
	}

	@Override
	public int getChildCount(Object parent) {
		// TODO Auto-generated method stub
		Node node = (Node) parent;
		NodeList list = node.getChildNodes();
		return list.getLength();
	}

	@Override
	public boolean isLeaf(Object node) {
		// TODO Auto-generated method stub
		return getChildCount(node) == 0;
	}

	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		// TODO Auto-generated method stub
		Node node = (Node) parent;
		NodeList list = node.getChildNodes();
		for(int i = 0; i < list.getLength(); i++)
			if(getChild(node, i) == child) return 1;
		return -1;
	}

	@Override
	public void addTreeModelListener(TreeModelListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTreeModelListener(TreeModelListener l) {
		// TODO Auto-generated method stub
		
	}
}


class DOMTreeCellRenderer extends DefaultTreeCellRenderer
{
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus)
	{
		Node node = (Node) value;
		if(node instanceof Element) return elementPanel((Element)node);
		
		super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
		if(node instanceof CharacterData) setText(characterString((CharacterData) node));
		else setText(node.getClass() + ": " + node.toString());
		return this;
	}
	public static JPanel elementPanel(org.w3c.dom.Element e) {
		// TODO Auto-generated method stub
		JPanel panel = new JPanel();
		panel.add(new JLabel("Element: " + e.getTagName()));
		final NamedNodeMap map = e.getAttributes();
		panel.add(new JTable(new AbstractTableModel()
				{

					@Override
					public int getRowCount() {
						// TODO Auto-generated method stub
						return map.getLength();
					}

					@Override
					public int getColumnCount() {
						// TODO Auto-generated method stub
						return 2;
					}

					@Override
					public Object getValueAt(int r, int c) {
						// TODO Auto-generated method stub
						return c == 0 ? map.item(r).getNodeName() : map.item(r).getNodeValue();
					}
			
				}));
		return panel;
	}
	
	public static String characterString(CharacterData node)
	{
		StringBuilder builder =  new StringBuilder(node.getData());
		for(int i = 0; i < builder.length(); i++)
		{
			if(builder.charAt(i) == '\r')
			{
				builder.replace(i, i + 1, "\\r");
				i++;
			}
			else if(builder.charAt(i) == '\n')
			{
				builder.replace(i, i + 1, "\\n");
				i++;
			}
			else if(builder.charAt(i) == '\t')
			{
				builder.replace(i, i + 1, "\\t");
				i++;
			}
		}
		
		if(node instanceof CDATASection) builder.insert(0, "CDATASelection: ");
		else if(node instanceof Text) builder.insert(0, "Text: ");
		else if(node instanceof Comment) builder.insert(0, "Comment: ");
		
		return builder.toString();
	}
}



