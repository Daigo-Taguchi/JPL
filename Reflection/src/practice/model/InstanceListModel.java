package practice.model;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
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
	
	/**
	 * 引数に指定したインスタンスのクラスが保持するフィールド情報の一覧を
	 * String配列として返す
	 * @param instance
	 * @throws IllegalAccessException
	 */
	public String[] getFields(Object instance) throws IllegalAccessException {
		String[] fieldInfos = new String[100];
		Field[] fields =  instance.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i ++) {
			Class<?> type = fields[i].getType();
			fields[i].setAccessible(true);
			int modifier = fields[i].getModifiers();
			fieldInfos[i] = this.modifierString(modifier) + "" +  this.getTypeName(type) + "" +  fields[i].getName();
			System.out.println(fieldInfos[i]);
		}
		return fieldInfos;
	}
	
	/**
	 * 型名を変換する
	 * @param type
	 * @return
	 */
	private String getTypeName(Class<?> type) {
		if(!type.isArray()) {
			return type.getName();
		}
		return getTypeName(type.getComponentType()) + "[] ";
	}
	
	/**
	 * Fieldクラスの保持するgetModifierメソッドで得られたint型変数を
	 * Stringの文字列に変換する
	 * @param modifier
	 * @return
	 */
	private String modifierString(int modifier) {
		StringBuilder sb = new StringBuilder();
	    if (Modifier.isPrivate(modifier))  sb.append("private ");
	    if (Modifier.isPublic(modifier))  sb.append("public ");
	    if (Modifier.isProtected(modifier))  sb.append("protected ");
	    if (Modifier.isStatic(modifier))  sb.append("static ");
	    if (Modifier.isAbstract(modifier))  sb.append("abstract ");
	    if (Modifier.isFinal(modifier))  sb.append("final ");
	    if (Modifier.isInterface(modifier))  sb.append("interface ");
	    if (Modifier.isNative(modifier))  sb.append("native ");
	    if (Modifier.isStrict(modifier))  sb.append("strict ");
	    if (Modifier.isSynchronized(modifier))  sb.append("synchoronized ");
	    if (Modifier.isTransient(modifier))  sb.append("transient ");
	    if (Modifier.isVolatile(modifier))  sb.append("volatile ");
	    return sb.toString();
	}
}
