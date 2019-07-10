package practice.model;

import java.lang.reflect.Constructor;

public class ConstructorModel {
	private Constructor<?>[] constructors;
	private InstanceListModel instanceListModel;
	
	public ConstructorModel(InstanceListModel instanceListModel) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.instanceListModel = instanceListModel;
	}
	
	/**
	 * 引数で指定したClassのコンストラクタ一覧を取得する
	 * コンストラクタ一覧の取得に成功：true
	 * コンストラクタ一覧の取得に失敗：false
	 * を返す
	 * @param clazz
	 * @return
	 */
	public Boolean loadConstructor(Class<?> clazz) {
		try {
			this.constructors = clazz.getConstructors();			
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 保持しているコンストラクター一覧を配列で取得する
	 * loadConstructorを利用してコンストラクタ一覧を取得してから利用する
	 * @return
	 */
	public Constructor<?>[] getList() {
		return this.constructors;
	}
	
	/**
	 * 指定したコンストラクタ一覧のからコンストラクタを指定して
	 * コンストラクタ呼びでインスタンス化を行う
	 * インスタンス一覧のリストを返す
	 * @param index
	 * @return
	 */
	public void createObject(int index, Object...parameters ) {
		this.instanceListModel.createObject(this.constructors[index], parameters);
	}
}
