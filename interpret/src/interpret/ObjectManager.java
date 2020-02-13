package interpret;

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

	public ObjectManager(Class<?> clazz) {
		this.clazz = clazz;
	}

	public Class<?> getTargetClass() {
		return clazz;
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
			System.out.println(params[i].getType());
			paramArray[i] = TypeConverter.convertType(paramList.get(i), params[i].getType().toString());
		}
		createdObject = Interpret.createObj(target, paramArray);
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
