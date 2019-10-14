package ch01.ex15;

interface ExtendedLookup extends Lookup {
	/**
	 * nameとvalueを追加する<br>
	 * 同名のnameが存在する場合既存のvalueを書き換える
	 */
	void add(String name, Object value);
	/**
	 * 配列に引数のnameと一致するnameが存在する場合,
	 * そのnameとvalueを配列から削除し削除したvalueの値を返す
	 * nameと一致しなかった場合はnullを返す。
	 */
	Object remove(String name);
}
