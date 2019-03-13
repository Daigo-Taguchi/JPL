package practice;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class FieldSearcher {
	
	FieldSearcher(){
	}
	
	public String[] searchClassFiled(String serchClassName) {
		Class<?> type = null;
		Field[] field = new Field[100];
		String[] results = new String[100];
		try {
			// クラスObjectの取得
			type = Class.forName(serchClassName);
			// 取得したクラスオブジェクトからFieldを取得し、Stringのリストに格納
			for (int i = 0; i < type.getFields().length; i ++) {
				field[i] = type.getFields()[i];
				results[i] = field[i].toString();
			}
//			for (Field fi : field) {
//				int count = 0;
//				results[count] = fi.toString();
//				count ++;
//			}
			return results;
		} catch (ClassNotFoundException e) {
			results[0] = "入力されたクラス名が不正です";
			return results;
		}
	}
}
