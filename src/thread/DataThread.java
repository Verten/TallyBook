package thread;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.Trade;
import ui.MainFrame;
import factory.Factory;
import fao.impl.TallyFaoImpl;

public class DataThread implements Runnable {

	MainFrame mf;
	List<Trade> data = new ArrayList<Trade>();
	private Factory<TallyFaoImpl> factory = new Factory<TallyFaoImpl>();
	String flag;
	File dataBinFile = null;
	File dataTextFile = null;
	boolean result = false;

	public boolean isResult() {
		return result;
	}
	public DataThread(MainFrame mf, String flag) {
		//save data use default file
		super();
		this.mf = mf;
		this.flag = flag;
	}
	public DataThread(MainFrame mf, String flag,File dataFile) {
		//for save binary file use appoint file
		super();
		this.mf = mf;
		this.flag = flag;
		if("bin".equalsIgnoreCase(flag)){
			this.dataBinFile = dataFile;
		}
		if("text".equalsIgnoreCase(flag)){
			this.dataTextFile = dataFile;
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int columnCount = mf.getDataTable().getColumnCount();
		int rowCount = mf.getDataTable().getRowCount();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
		TallyFaoImpl ft = null;
		try {
			ft = factory.getNewInstance("myTallyFao");
			if(dataBinFile != null){
				ft.setFile_bin(dataBinFile);
			}
			if(dataTextFile != null){
				ft.setFile_txt(dataTextFile);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}

		if ("bin".equalsIgnoreCase(flag)) {
			for (int j = 0; j < rowCount; j++) {
				Trade t = new Trade();
				for (int i = 0; i < columnCount; i++) {
					if (!"".equals(mf.getDataTable().getValueAt(j, i))) {
						t.setNumber(Integer.valueOf((String) mf.getDataTable()
								.getValueAt(j, i++)));
					} else {
						continue;
					}
					if (!"".equals(mf.getDataTable().getValueAt(j, i))) {
						try {
							t.setTrade_date(sdf.parse((String) mf
									.getDataTable().getValueAt(j, i++)));
						} catch (ParseException e) {
						}
					} else {
						continue;
					}
					if (!"".equals(mf.getDataTable().getValueAt(j, i))) {
						t.setTrade_way((String) mf.getDataTable().getValueAt(j,
								i++));
					} else {
						continue;
					}
					if (!"".equals(mf.getDataTable().getValueAt(j, i))) {
						t.setTrade_purpose((String) mf.getDataTable()
								.getValueAt(j, i++));
					} else {
						continue;
					}
					if (!"".equals(mf.getDataTable().getValueAt(j, i))) {
						t.setTrade_count((Double.valueOf((String) mf
								.getDataTable().getValueAt(j, i++))));
					} else {
						continue;
					}
				}
				System.out.println(t.toString());
				data.add(t);
			}
			try {
				if ("bin".equals(flag) && data.size() > 0) {
					ft.saveData(data);
					result = true;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if ("text".equalsIgnoreCase(flag)) {
			StringBuffer sb = new StringBuffer();
			for (int j = 0; j < rowCount; j++) {
				for (int i = 0; i < columnCount; ) {
					if (!"".equals(mf.getDataTable().getValueAt(j, i))) {
						sb.append(mf.getDataTable().getValueAt(j, i++));
						sb.append(",");
					} else {
						continue;
					}
					if (!"".equals(mf.getDataTable().getValueAt(j, i))) {
						sb.append(mf.getDataTable().getValueAt(j, i++));
						sb.append(",");
					} else {
						continue;
					}
					if (!"".equals(mf.getDataTable().getValueAt(j, i))) {
						sb.append(mf.getDataTable().getValueAt(j, i++));
						sb.append(",");
					} else {
						continue;
					}
					if (!"".equals(mf.getDataTable().getValueAt(j, i))) {
						sb.append(mf.getDataTable().getValueAt(j, i++));
						sb.append(",");
					} else {
						continue;
					}
					if (!"".equals(mf.getDataTable().getValueAt(j, i))) {
						sb.append(mf.getDataTable().getValueAt(j, i++));
						sb.append("\n");
					} else {
						continue;
					}
				}
			}
			if(sb.length() > 0){
				try {
					ft.saveText(sb.toString());
					result = true;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
