package main;

import java.lang.reflect.Field;

// 将视频映射为json
public class FieldJsonMapper {
	public String toJson(Video video, String[] fields) throws Exception {
		StringBuilder json = new StringBuilder().append("{");

	    int i = 0;
	    int fieldsLength = fields.length;
	    while (i < fieldsLength) {
	        json.append(getString(video, Video.class.getDeclaredField(fields[i])));
	        if (i != fieldsLength - 1) {
	            json.append(",");
	        }
	        i++;
	    }
	    json.append("}");
	    return json.toString();
	}
	
	// Field提供有关类或接口的单个字段的信息和动态访问。 反射的字段可以是类（静态）字段或实例字段
	private String getString(Video video, Field declaredField) throws IllegalAccessException {
	    declaredField.setAccessible(true);
	    Object value = declaredField.get(video);
	    if (declaredField.get(video) instanceof Integer) {
	        return "\"" + declaredField.getName() + "\"" + ": " + value;
	    }
	    return "\"" + declaredField.getName() + "\"" + ": " + "\"" + value.toString() + "\"";
	}

}
