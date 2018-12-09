package practice11_02;

/*Key：name
 * Value：value
 * で値を管理するクラス。Mapとかハッシュのようなもの。
 * 
 * クラスをジェネリック型宣言することで、valueの型を固定することができる。
 * ジェネリクスじゃないと、valueの型はObjectで宣言されていたので、どんな型でも代入することができた。
 * 古いデータと新しいデータで型が違うものが入れられた
 * ex)
 * old: "Apple" = 1(int)
 * new: "Apple" = "red"
 * がジェネリクスを使う前なら可能だったが、ジェネリクスを使用するとnewの場合でもintしか代入することができない。
 * */

public class Attr<T> {
	private final String name;
	private T value = null;
	
	public Attr(String name) {
		this.name = name;
	}
	
	public Attr(String name, T value) {
		this.name = name;
		this.value = value;
	}
	
	/**
	 * このクラスが管理するデータのkey(this.name)を返す
	 * @return このクラスが保持しているデータの名前
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * このクラスが管理するデータのvalue(this.value)を返す
	 * @return このクラスが保持するデータの値を返す
	 */
	public T getValue() {
		return this.value;
	}	
	
	/**
	 * このクラスが管理するデータのvalueを新しいデータに入れ替える。
	 * 戻り値としてもともと保持していたデータを返す。
	 * @param newValue
	 * @return 古いデータ
	 */
	public T setValue(T newValue) {
		T oldVal = this.value;
		this.value = newValue;
		return oldVal;
	}
	
	public String toString() {
		return name + "='" + this.value + "'";
	}
}
