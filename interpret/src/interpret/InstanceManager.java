package interpret;

import java.util.HashMap;
import java.util.Map;

/**
 * InterpretUIによって作成されたインスタンスを管理する
 */
public class InstanceManager {

	private static InstanceManager instanceManager = new InstanceManager();
	private Map<String, ObjectManager> instanceMap = new HashMap<>();
	private static int classIndex = 0;

	private InstanceManager() {
	}

	public synchronized static int getClassIndex() {
		return classIndex++;
	}

	public synchronized static InstanceManager getInstance() {
		if (instanceManager == null) {
			instanceManager = new InstanceManager();
		}
		return instanceManager;
	}

	public void addInstance(String key, ObjectManager objectManager) {
		instanceMap.put(key, objectManager);
	}

	public ObjectManager getCreatedObject(String instanceName) {
		if (instanceMap.containsKey(instanceName)) {
			return instanceMap.get(instanceName);
		}
		return null;
	}


}
