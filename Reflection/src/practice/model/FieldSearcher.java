package practice.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class FieldSearcher {
	private Class<?> clazz;
	private Constructor<?>[] constructors;

	public FieldSearcher(){
		this.constructors = new Constructor<?>[100];
		this.clazz = null;
	}
	
	/***
	 * 引数で指定したクラスのコンストラクターの一覧を取得する
	 * 返り値はConstructor[]型配列で返す
	 * @param searchClassName
	 * @return
	 */
	public Constructor<?>[] searchConstructors(String searchClassName) {
		try {
			// クラスObjectの取得
			this.clazz = Class.forName(searchClassName);
			constructors = clazz.getConstructors();
			
//			// コンストラクター配列の中身確認用
//			for(Constructor<?> constructor : constructors) {
//				System.out.printf("Consructor: %s%n," , constructor.toGenericString());
//			}
			
			return constructors;
		} catch (Exception e) {
			return constructors;
		}
	}
	
	/***
	 * コンストラクタに利用する引数の型の配列と、引数の値の配列を受け取り、
	 * 引数の個数に応じたコンストラクタを利用してインスタンス化を行う
	 */
	public void toInstance(Class<?> [] types , Object[] parameter) {
		// どうやってコンストラクタの引数を指定してインスタンス化させるのか
		Constructor<?> constructor = null;
			try {
				constructor = this.clazz.getConstructor(types);
			} catch (NoSuchMethodException | SecurityException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			try {
				constructor.newInstance(parameter);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
	}
}
