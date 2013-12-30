package fao.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import fao.TallyFaoInf;

import util.FileHelper;

public class TallyFaoImpl implements TallyFaoInf {
	FileHelper fh;
	File file_bin = new File("data.db");
	File file_txt = new File("data.txt");
	
	public File getFile_bin() {
		return file_bin;
	}

	public void setFile_bin(File fileBin) {
		file_bin = fileBin;
	}

	public File getFile_txt() {
		return file_txt;
	}

	public void setFile_txt(File fileTxt) {
		file_txt = fileTxt;
	}

	@Override
	public void saveData(Object o) throws FileNotFoundException, IOException {
		if (fh == null) {
			fh = new FileHelper();
		}
		fh.writeObject2File(o, file_bin);
	}

	@Override
	public void saveText(String text) throws FileNotFoundException, IOException {
		if (fh == null) {
			fh = new FileHelper();
		}
		fh.writeString2File(text, file_txt);
	}

}
