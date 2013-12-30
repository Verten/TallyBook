package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import model.MyDefaultTableModel;
import util.PropertiesHelper;

import com.eltima.components.ui.DatePicker;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private static Color newColor = new Color(187, 230, 246);
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmOpenBin;
	private JMenuItem mntmSaveBin;
	private JScrollPane scrollPane_1;
	private JTable table;
	private JFileChooser fc = new JFileChooser();
	private JButton InsRowbutton;
	private JMenuItem mntmNew;
	private DatePicker datapicker;
	private JMenuItem mntmOpenText;
	private JSeparator separator;
	private JSeparator separator_1;
	private JMenuItem mntmSaveText;
	private JSeparator separator_2;
	private JMenuItem mntmSaveBToFile;
	private JMenuItem mntmSaveTToFile;

	
	

	
	public DatePicker getDatapicker() {
		return datapicker;
	}

	public void setDatapicker(DatePicker datapicker) {
		this.datapicker = datapicker;
	}

	public JMenuItem getMntmNew() {
		return mntmNew;
	}

	public void setMntmNew(JMenuItem mntmNew) {
		this.mntmNew = mntmNew;
	}

	public JButton getInsRowbutton() {
		return InsRowbutton;
	}

	public void setInsRowbutton(JButton insRowbutton) {
		InsRowbutton = insRowbutton;
	}

	public JFileChooser getFc() {
		return fc;
	}

	public void setFc(JFileChooser fc) {
		this.fc = fc;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public static Color getNewColor() {
		return newColor;
	}

	public static void setNewColor(Color newColor) {
		MainFrame.newColor = newColor;
	}

	public JTable getDataTable() {
		return table;
	}

	public void setDataTable(JTable table) {
		this.table = table;
	}

	public JMenu getMnFile() {
		return mnFile;
	}

	public void setMnFile(JMenu mnFile) {
		this.mnFile = mnFile;
	}

	public JMenuItem getMntmOpenText() {
		return mntmOpenText;
	}

	public void setMntmOpenText(JMenuItem mntmOpenText) {
		this.mntmOpenText = mntmOpenText;
	}

	public JMenuItem getMntmOpenBin() {
		return mntmOpenBin;
	}

	public void setMntmOpenBin(JMenuItem mntmOpenBin) {
		this.mntmOpenBin = mntmOpenBin;
	}

	public JMenuItem getMntmSaveBin() {
		return mntmSaveBin;
	}

	public void setMntmSaveBin(JMenuItem mntmSave) {
		this.mntmSaveBin = mntmSave;
	}

	public JMenuItem getMntmSaveText() {
		return mntmSaveText;
	}

	public void setMntmSaveText(JMenuItem mntmSaveText) {
		this.mntmSaveText = mntmSaveText;
	}

	public JMenuItem getMntmSaveBToFile() {
		return mntmSaveBToFile;
	}

	public void setMntmSaveBToFile(JMenuItem mntmSaveBToFile) {
		this.mntmSaveBToFile = mntmSaveBToFile;
	}

	public JMenuItem getMntmSaveTToFile() {
		return mntmSaveTToFile;
	}

	public void setMntmSaveTToFile(JMenuItem mntmSaveTToFile) {
		this.mntmSaveTToFile = mntmSaveTToFile;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager
							.getCrossPlatformLookAndFeelClassName());
					JFrame.setDefaultLookAndFeelDecorated(true);
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public MainFrame(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 834, 358);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmNew = new JMenuItem("New");
		mnFile.add(mntmNew);
		
		separator_1 = new JSeparator();
		mnFile.add(separator_1);
		
		mntmOpenBin = new JMenuItem("Open Binary File");
		mnFile.add(mntmOpenBin);
		
		mntmOpenText = new JMenuItem("Open Text File");
		mnFile.add(mntmOpenText);
		
		separator = new JSeparator();
		mnFile.add(separator);
		
		mntmSaveBin = new JMenuItem("Save to Bin");
		mnFile.add(mntmSaveBin);
		
		mntmSaveText = new JMenuItem("Save to Text");
		mnFile.add(mntmSaveText);
		
		separator_2 = new JSeparator();
		mnFile.add(separator_2);
		
		mntmSaveBToFile = new JMenuItem("Save Bin to Another File");
		mnFile.add(mntmSaveBToFile);
		
		mntmSaveTToFile = new JMenuItem("Save Text to Another File");
		mnFile.add(mntmSaveTToFile);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gridBagLayout);
		
		scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 5;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.gridheight = 8;
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 0;
		contentPane.add(scrollPane_1, gbc_scrollPane_1);
		
		table = new JTable();
		table.setRowHeight(30);
		Object[][] data = {};
		PropertiesHelper ph = new PropertiesHelper("/config.properties");
		try {
			String column = ph.getAttribute("columnName");
			String[] columnnames = column.split(",");
			table.setModel(new MyDefaultTableModel(data, columnnames));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this,"Can not found config.properties");
		}
		scrollPane_1.setViewportView(table);
		
		InsRowbutton = new JButton("Insert Row");
		GridBagConstraints gbc_InsRowbutton = new GridBagConstraints();
		gbc_InsRowbutton.insets = new Insets(0, 0, 5, 5);
		gbc_InsRowbutton.gridx = 0;
		gbc_InsRowbutton.gridy = 8;
		contentPane.add(InsRowbutton, gbc_InsRowbutton);

	}
}
