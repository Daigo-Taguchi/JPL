package practice.model;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

// 生成したコンストラクタ一覧のデータを保持する
public class ConstructorModel extends Obserbable{
	private Class<?> clazz;
	private Constructor<?>[] constructors;
	private List<Object> instanceList = new ArrayList<Object>();
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
	public Boolean loadConstructor(String className) {
		try {
			this.clazz = Class.forName(className);
			this.constructors = clazz.getConstructors();
			notifyConstructorObservers();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
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

	public List<Object> getInstanceList() {
		return this.instanceList;
	}

	/**
	 * 指定したコンストラクタ一覧のからコンストラクタを指定して
	 * コンストラクタ呼びでインスタンス化を行う
	 * インスタンス一覧のリストを返す
	 * @param index
	 * @return
	 */
	public boolean createInstance(int index, Object...parameters ) {
		boolean result =  this.instanceListModel.createObject(this.instanceList ,this.constructors[index], parameters);
		notifyInstanceObservers();
		return result;
	}

	public boolean createArrayInstance(int length) {
		boolean result = this.instanceListModel.createArrayObject(this.clazz, length, this.instanceList);
		notifyInstanceObservers();
		return result;
	}
	
	public String[] getFields(Object instance) throws IllegalAccessException {
		return this.instanceListModel.getFields(instance);
	}
}
