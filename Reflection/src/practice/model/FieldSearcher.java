package practice.model;

import java.lang.reflect.Constructor;

public class FieldSearcher {

	public FieldSearcher(){
	}
	

	/***
	 * 引数で指定したクラスのコンストラクターの一覧を取得する
	 * 返り値はConstructor[]型配列で返す
	 * @param serchClassName
	 * @return
	 */
	public Constructor<?>[] searchConstructors(String searchClassName) {
		Class<?> type = null;
		Constructor<?>[] constructors = new Constructor[100];
		try {
			// クラスObjectの取得
			type = Class.forName(searchClassName);
			constructors = type.getConstructors();
			
//			// コンストラクター配列の中身確認用
//			for(Constructor<?> constructor : constructors) {
//				System.out.printf("Consructor: %s%n," , constructor.toGenericString());
//			}
			
			return constructors;
		} catch (Exception e) {
			return constructors;
		}
	}
	
	public void toInstance() {
		
	}
}
