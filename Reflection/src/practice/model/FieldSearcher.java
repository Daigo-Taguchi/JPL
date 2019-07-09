package practice.model;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class FieldSearcher {
	private Class<?> clazz;
	// private List<Class<?>> classList  = new ArrayList<Class<?>>();
	private Constructor<?>[] constructors;
	private List<Object> instanceList = new ArrayList<Object>();
	private final int CONSTRUCTOR_LIST_LENGTH = 100;

	public FieldSearcher(){
		this.constructors = new Constructor<?>[CONSTRUCTOR_LIST_LENGTH];
		this.clazz = null;
	}

	/***
	 * 引数で指定したクラスのコンストラクターの一覧を取得する
	 * 返り値はConstructor[]型配列で返す
	 * コンストラクター一覧はmodelで保持する
	 * @param className
	 * @return
	 *
	 * loadConstructorとかで、modelのListを保持→observerで通知
	 * converterの処理はviewに任せておｋ
	 */
	public Constructor<?>[] searchConstructors(String className) {
		try {
			// クラスObjectの取得
			this.clazz = Class.forName(className);
			// this.classList.add(Class.forName(className));
			this.constructors = clazz.getConstructors();

//			// コンストラクター配列の中身確認用
//			for(Constructor<?> constructor : constructors) {
//				System.out.printf("Consructor: %s%n," , constructor.toGenericString());
//			}
			
			return this.constructors;

			// return constructors;
		} catch (Exception e) {
			return this.constructors;
		}
	}

	/***
	 * コンストラクタに利用する引数の型の配列と、引数の値を受け取り、
	 * 引数の個数に応じたコンストラクタを利用してインスタンス化を行う
	 * インスタンス一覧はmodelで保持する
	 */
	public boolean toInstance(int index, Object... parameter) {
		// どうやってコンストラクタの引数を指定してインスタンス化させるのか
			try {
//				// 引数確認用
//				for (Object ps : parameter) {
//					System.out.println(ps);
//				}
				instanceList.add( this.constructors[index].newInstance(parameter));
				return true;
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				return false;
			}
	}
	
	/**
	 * 引数に入力したクラス名の配列のインスタンスを生成する
	 * 配列の要素はnullの空配列を生成。
	 * @param className
	 * @param length
	 */
	public Object toArrayInstance(String className , int length) {
		Class<?> clazz;
		Object object;
		try {
			clazz = Class.forName(className);
			object = Array.newInstance(clazz, length);
			instanceList.add(object);
			return object;
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 保持しているインスタンスのListを取得する
	 * @return instanceList
	 */
	public List<Object> getInstanceList() {
		return this.instanceList;
	}
	
	/**
	 * Classクラスを引数にそのクラスが保持するフィールドを取得し、Filed型のListで返す
	 * @param clazz
	 * @return
	 */
	public List<Field> getFiled(Class clazz) {
		List<Field> fieldList = new ArrayList<Field>();
		for(Field f : clazz.getFields()) {
			fieldList.add(f);
		}
		return fieldList;
	}

	/**
	 * InterpreterModel
	 * ↓
	 * ConstructorList
	 *
	 *
	 *
	 */
}
