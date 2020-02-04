package practice.model;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class InstanceListModel {
	
	/**
	 * 引数で指定したコンストラクタを利用して、コンストラクタ予備でインスタンス化を行う。
	 * このクラスで保持するインスタンスのリストに追加をする。
	 * インスタンス化に成功：true
	 * インスタンス化に失敗：false
	 * @param constructor
	 * @param parameter
	 * @return
	 */
	public boolean createObject(List<Object> instanceList, Constructor<?> constructor, Object... parameter) {
			try {
				instanceList.add(constructor.newInstance(parameter));
				return true;
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				return false;
			}
	}
	
	/**
	 * 引数で指定したクラスと長さで配列を生成する
	 * 生成する配列の中身はnull
	 * 配列生成に成功：true
	 * 配列生成に失敗：false
	 * @param clazz
	 * @param length
	 * @return
	 */
	public boolean createArrayObject(Class<?> clazz, int length, List<Object> instanceList) {
		try {
			instanceList.add(Array.newInstance(clazz, length));
			return true;			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void getFields(Object instance) throws IllegalAccessException {
		for (Field field : instance.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			System.out.println(field.getName() + " = " + field.get(instance));
		}
	}
}
