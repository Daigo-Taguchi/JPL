package practice.controller;

import java.lang.reflect.Constructor;

import practice.model.FieldSearcher;

public class Converter {
	public Converter() {
		
	}
	
	/***
	 * 引数で指定されたクラスのConstructor一覧をString配列として取得する
	 * @param constructors
	 * @return
	 */
	public String[] getConstructors(String searchClassName) {
		FieldSearcher fi = new FieldSearcher();
		Constructor<?>[] constructors= fi.searchConstructors(searchClassName);
		String[] results = new String[100];
		for(int i = 0; i < constructors.length; i++) {
			results[i] = "#" + i + " : " + constructors[i].toGenericString();
		}
		return results;
	}

}
