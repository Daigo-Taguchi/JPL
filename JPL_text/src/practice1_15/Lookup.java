package practice1_15;

public interface Lookup {
	/**
	 * nameと関連付けされた値を返す。そのような値がなければnullを返す。
	 * 
	 * @param name
	 * @return
	 */
	Object find(String name);
	
	/**
	 * 空の配列にnameを追加する。
	 * 追加に成功すればtrue, 配列に空きがなければfalseを返す。
	 * @param name
	 * @return true：追加に成功, false：追加に失敗(配列に空きがない)
	 */
	boolean add(String name);
	/**
	 * 名前のデータが格納されている配列から、指定した名前を削除する。
	 * 削除に成功した場合にtrue,指定した名前が存在しない場合はfalseを返す。
	 * @param name
	 * @return true:削除に成功, false:削除に失敗(指定した名前が配列に存在しない)
	 */
	boolean remove(String name);
}
