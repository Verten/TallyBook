package util;

import java.text.SimpleDateFormat;
import java.util.List;

import model.Trade;

public class Util {

	public static Object[][] loadInfoAs2DArray(List datalist) {
		Object[][] data = new Object[][] {};
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
		if (datalist != null && datalist.size() > 0) {
			data = new Object[datalist.size()][5];
			if(datalist.get(0) instanceof Trade){
				for (int i = 0; i < datalist.size(); i++) {
					data[i][0] = String.valueOf(((Trade) (datalist.get(i))).getNumber());
					data[i][1] = sdf.format(((Trade) datalist.get(i)).getTrade_date());
					data[i][2] = ((Trade) datalist.get(i)).getTrade_way();
					data[i][3] = ((Trade) datalist.get(i)).getTrade_purpose();
					data[i][4] = String.valueOf(((Trade) datalist.get(i)).getTrade_count());
				}
			}
			if(datalist.get(0) instanceof String[]){
				for(int i = 0; i < datalist.size(); i++){
					String[] tmpData = (String[]) datalist.get(i);
					for(int j = 0; j < tmpData.length; j++){
						data[i][j] = tmpData[j];
					}
				}
			}
		}
		return data;
	}
}
