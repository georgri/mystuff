package schoolschedule.domain.dictionaries;

import java.util.HashMap;
import java.util.Map;

public class Scopes {
	private static Map<Short, String> scopes = new HashMap<Short, String>();
	static {
		scopes.put(null, "No scope");
		scopes.put((short)1, "Group");
		scopes.put((short)2, "Stream");
		scopes.put((short)3, "Course");
	}
	
	public static String get(Short key) {
		return scopes.get(key);
	}
}
