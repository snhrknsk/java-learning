package interpret;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * InterpretUIによって作成するオブジェクトを管理する<br>
 * 1インスタンス情報を保持
 */
public class ObjectManager {

	private Class<?> clazz;
	private Constructor<?>[] constructors;
	private Parameter[] params;
	private Object createdObject;
	private String className;

	public ObjectManager(Class<?> clazz) {
		this.clazz = clazz;
	}

	public Class<?> getTargetClass() {
		return clazz;
	}

	public String getTargetClassName() {
		return className;
	}

	/**
	 * constractorListで返すリストのインデックスは変更しないこと<br>
	 * コンストラクター呼び出しの際に利用するため
	 * @return
	 */
	public List<String> getConstructor() {
		List<String> constructorList = new ArrayList<>();
		constructors = clazz.getConstructors();

		for (int i = 0; i < constructors.length; i++) {
			String constractorTrim = trimConstString(constructors[i].toString());
			constructorList.add(constractorTrim);
		}
		return constructorList;
	}

	public List<String> getConstructorParam(int index) {
		List<String> paramList = new ArrayList<>();
		Constructor<?> target = constructors[index];
		params = target.getParameters();
		for (Parameter param : params) {
			paramList.add(trimConstString(param.getParameterizedType().getTypeName()));
		}
		return paramList;
	}

	/**
	 * オブジェクトを作成する
	 * @param constructorIndex
	 * @param paramList
	 * @throws Exception 引数がキャストできないもしくは生成されていない場合
	 */
	public void createObject(int constructorIndex, List<String> paramList) throws Exception {
		Constructor<?> target = constructors[constructorIndex];
		Object[] paramArray = null;

		if (paramList.size() != 0) {
			paramArray = new Object[paramList.size()];
		}
		for (int i = 0; i < paramList.size(); i++) {
			paramArray[i] = TypeUtil.convertType(paramList.get(i), params[i].getType().toString());
		}
		className = Interpret.trimPackage(clazz.toString());
		createdObject = Interpret.createObj(target, paramArray);
	}

	/**
	 * 配列のオブジェクトを作成する
	 * @param paramList
	 * @throws Exception
	 */
	public void createObject(Object arrayNum, List<String> paramList) throws Exception {
//		Object[] paramArray = new Object[paramList.size()];
		className = Interpret.trimPackage(clazz.toString()) + "[" + arrayNum + "]";
		createdObject = Interpret.createArray(clazz, (int)arrayNum);
		for (int i = 0; i < paramList.size(); i++) {
			Array.set(createdObject, i, TypeUtil.convertType(paramList.get(i), clazz.toString()));
		}
	}

	public Object getCreatedObject() {
		return createdObject;
	}

	private String trimConstString(String target) {
		String result;
		result = target.replaceAll("java.lang.", "");
		result = result.replaceFirst(Interpret.packageName, "");
		result = result.split("throw")[0];
		return result;
	}


}
