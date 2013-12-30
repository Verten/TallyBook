package ui.MainformAction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import model.MyDefaultCellEditor;
import model.MyDefaultTableModel;
import model.MyObjectCellRenderer;
import model.Trade;
import thread.DataThread;
import ui.MainFrame;
import util.FileHelper;
import util.PropertiesHelper;
import util.Util;

public class MainframeAction {

	MainFrame mf;
	JFileChooser jfc;
	FileHelper fh;
	String[] columnnames;
	JTable table;

	public MainframeAction(MainFrame mf) {
		super();
		this.mf = mf;
		initFrame();
		initAction();
	}

	public void initFrame() {
		mf.setVisible(true);
		jfc = mf.getFc();
		table = mf.getDataTable();
		PropertiesHelper ph = new PropertiesHelper("/config.properties");
		try {
			String column = ph.getAttribute("columnName");
			columnnames = column.split(",");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, this,
					"Can not found config.properties", 0, null);
		}
		repaint();
	}

	private void repaint() {
		mf.getDataTable().setDefaultRenderer(Object.class,
				new MyObjectCellRenderer());
		//set Cell Edit
		mf.getDataTable().getColumnModel().getColumn(1).setCellEditor(
				new MyDefaultCellEditor(mf.getDataTable()));
	}

	public void initAction() {
		// open file action
		mf.getMntmOpenBin().addActionListener(openBinAction);
		mf.getMntmOpenText().addActionListener(openTextAction);
		mf.getInsRowbutton().addActionListener(insertAction);
		mf.getMntmNew().addActionListener(newAction);
		mf.getMntmSaveBin().addActionListener(saveBinAction);
		mf.getMntmSaveText().addActionListener(saveTextAction);
		mf.getMntmSaveBToFile().addActionListener(saveAnotherBinAction);
		mf.getMntmSaveTToFile().addActionListener(saveAnotherTextAction);
		//mf.getDataTable().addMouseListener(clickAction);
	}

	public static void main(String[] args) {
			try {
				UIManager.setLookAndFeel(UIManager
						.getCrossPlatformLookAndFeelClassName());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (UnsupportedLookAndFeelException e) {
				e.printStackTrace();
			}
			JFrame.setDefaultLookAndFeelDecorated(true);
			MainframeAction ma = new MainframeAction(new MainFrame());

	}

	ActionListener openBinAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			int flag = jfc.showOpenDialog(mf);
			List<Trade> obj;
			if (flag == JFileChooser.APPROVE_OPTION) {
				File file = jfc.getSelectedFile();
				fh = new FileHelper();
				try {
					obj = (List<Trade>) fh.readObjectFile(file);
					mf.getDataTable().setModel(
							new MyDefaultTableModel(
									Util.loadInfoAs2DArray(obj), columnnames));
					repaint();
				} catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(mf,
							"Can not find data file.", "Error", 0);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(mf,
							"Some Error happened in Open data.", "Error", 0);
				} catch (ClassNotFoundException e) {
					JOptionPane.showMessageDialog(mf,
							"Some Error happened in Open data.", "Error", 0);
				}
			}
		}
	};
	
	ActionListener openTextAction = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e1) {
			int flag = jfc.showOpenDialog(mf);
			if (flag == JFileChooser.APPROVE_OPTION) {
				File file = jfc.getSelectedFile();
				List<String[]> strData = new ArrayList<String[]>();
				fh = new FileHelper();
				try {
					String data = fh.readStringFile(file);
					while(data != null){
						String[] strTemp = data.split(",");
						strData.add(strTemp);
						data = fh.readStringFile(file);
						System.out.println("Read Txt File--------->" + data);
					}
					mf.getDataTable().setModel(
							new MyDefaultTableModel(
									Util.loadInfoAs2DArray(strData), columnnames));
					repaint();
				} catch (FileNotFoundException e) {
					//e.printStackTrace();
					JOptionPane.showMessageDialog(mf,
							"Can not find data file.", "Error", 0);
				} catch (IOException e) {
					//e.printStackTrace();
					JOptionPane.showMessageDialog(mf,
							"Some Error happened in Open data.", "Error", 0);
				} 
			}
		
		}
	};

	ActionListener saveBinAction = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent actionevent) {
			// TODO Auto-generated method stub
			if(mf.getDataTable().getRowCount() == 0){
				JOptionPane.showMessageDialog(mf,
						"No data to save", "Error", 0);
				return;
			}
			DataThread dataThread = new DataThread(mf,"bin");
			Thread thread = new Thread(dataThread);
			try {
				thread.start();
				while(true){
					if(dataThread.isResult()){
						JOptionPane.showMessageDialog(mf,
								"Save data Successfully.", "Message", 1);
						break;
					}
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(mf,
						"Some Error happened in save data.", "Error", 0);
			}
		}
	};
	
	ActionListener saveTextAction = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent actionevent) {
			if(mf.getDataTable().getRowCount() == 0){
				JOptionPane.showMessageDialog(mf,
						"No data to save", "Error", 0);
				return;
			}
			DataThread dataThread = new DataThread(mf,"text");
			Thread thread = new Thread(dataThread);
			try {
				thread.start();
				while(true){
					if(dataThread.isResult()){
						JOptionPane.showMessageDialog(mf,
								"Save data Successfully.", "Message", 1);
						break;
					}
				}
			} catch (Exception e) {
				JOptionPane.showConfirmDialog(mf,
						"Some Error happened in save data.", "Error", 1);
			}
		}
	};

	ActionListener saveAnotherBinAction = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e1) {
			if(mf.getDataTable().getRowCount() == 0){
				JOptionPane.showMessageDialog(mf,
						"No data to save", "Error", 0);
				return;
			}
			int flag = jfc.showSaveDialog(mf);
			if (flag == JFileChooser.APPROVE_OPTION) {
				File file = jfc.getSelectedFile();
				DataThread dataThread = new DataThread(mf,"bin",file);
				Thread thread = new Thread(dataThread);
				try {
					thread.start();
					while(true){
						if(dataThread.isResult()){
							JOptionPane.showMessageDialog(mf,
									"Save data Successfully.", "Message", 1);
							break;
						}
					}
				} catch (Exception e) {
					JOptionPane.showConfirmDialog(mf,
							"Some Error happened in save data.", "Error", 1);
				}
			}
		}
	};
	
	ActionListener saveAnotherTextAction = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e1) {
			if(mf.getDataTable().getRowCount() == 0){
				JOptionPane.showMessageDialog(mf,
						"No data to save", "Error", 0);
				return;
			}
			int flag = jfc.showSaveDialog(mf);
			if (flag == JFileChooser.APPROVE_OPTION) {
				File file = jfc.getSelectedFile();
				DataThread dataThread = new DataThread(mf,"text",file);
				Thread thread = new Thread(dataThread);
				try {
					thread.start();
					while(true){
						if(dataThread.isResult()){
							JOptionPane.showMessageDialog(mf,
									"Save data Successfully.", "Message", 1);
							break;
						}
					}
				} catch (Exception e) {
					JOptionPane.showConfirmDialog(mf,
							"Some Error happened in save data.", "Error", 1);
				}
			}
		}
	};
	
	ActionListener newAction = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Object[][] data = { { "", "", "", "", "" } };
			if (!mf.getDataTable().isEditing()) {
				mf.getDataTable().setModel(
						new MyDefaultTableModel(data, columnnames));
				repaint();
			} else {
				int flag = JOptionPane
						.showConfirmDialog(mf,
								"Table is not save,Sure new anther table?",
								"waring", 2);
				if (flag == 0) {
					mf.getDataTable().setModel(
							new MyDefaultTableModel(data, columnnames));
					repaint();
				} else {
					return;
				}
			}
		}
	};

	ActionListener insertAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (mf.getDataTable() == null) {
				JOptionPane.showMessageDialog(mf, "please new one Table",
						"Error", 0);
			}
			DefaultTableModel dtm = (DefaultTableModel) mf.getDataTable()
					.getModel();
			String[] tmp = { "", "", "", "", "" };
			dtm.addRow(tmp);

		}
	};
	

	MouseAdapter clickAction = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
//			if(table.isCellSelected(table.getSelectedRow(),table.getSelectedColumn())){
//				if(table.getSelectedColumn() == 1){
//					mf.getDatapicker().setBounds(e.getX(), e.getY(), 30, 40);
//					mf.getDatapicker().repaint();
//				}
//			}
		}
		
	};
	
}
