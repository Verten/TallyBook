package fao;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface TallyFaoInf {
	void saveData(Object o) throws FileNotFoundException, IOException;
	void saveText(String text) throws FileNotFoundException, IOException;
}
