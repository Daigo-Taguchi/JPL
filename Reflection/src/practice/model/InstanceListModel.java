package practice.model;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class InstanceListModel {
	private List<Object> instanceList = new ArrayList<Object>();
	
	/**
	 * 引数で指定したコンストラクタを利用して、コンストラクタ予備でインスタンス化を行う。
	 * このクラスで保持するインスタンスのリストに追加をする。
	 * インスタンス化に成功：true
	 * インスタンス化に失敗：false
	 * @param constructor
	 * @param parameter
	 * @return
	 */
	public boolean createObject(Constructor<?> constructor, Object... parameter) {
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
	 * 配列生成に成功：:true
	 * 配列生成に失敗：false
	 * @param clazz
	 * @param length
	 * @return
	 */
	public boolean createArrayObject(Class<?> clazz, int length) {
		try {
			this.instanceList.add(Array.newInstance(clazz, length));
			return true;			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * このクラスが管理するインスタンス一覧のリストを返す
	 * @return
	 */
	public List<Object> getList() {
		return this.instanceList;
	}
}
