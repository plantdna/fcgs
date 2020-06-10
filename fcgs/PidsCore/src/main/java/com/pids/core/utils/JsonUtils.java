package com.pids.core.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.poi.ss.formula.functions.T;

import java.lang.reflect.Type;
import java.util.List;

/**
 * 基于Gson的JSON工具方法
 * @author jiang
 * @date 2018年6月20日下午2:44:34
 */
public class JsonUtils {
	/**
	 * 对象格式化成JSON
	 * @author jiang
	 * @param object
	 * @return
	 * @date 2018年6月20日下午2:58:40
	 */
	public static String format(Object object) {
		return getGson().toJson(object);
	}

	/**
	 * 解析JSON字符串数组
	 * @author jiangbin
	 * @param jsonArray <pre>JSON数据，类似于:
	 * 	String jsonArray = "[\"Android\",\"Java\",\"PHP\"]";</pre>
	 * @return
	 * @date Jul 13, 20198:36:05 AM
	 */
	public static String[] toStrArray(String jsonArray) {
		return getGson().fromJson(jsonArray, String[].class);
	}

	/**
	 * 解析JSON字符串数组为List字符串列表
	 * @author jiangbin
	 * @param jsonArray <pre>JSON数据，类似于:
	 * 	String jsonArray = "[\"Android\",\"Java\",\"PHP\"]";</pre>
	 * @return
	 * @date Jul 13, 20199:04:30 AM
	 */
	public static List<String> toStrList(String jsonArray) {
		return getGson().fromJson(jsonArray, new TypeToken<List<String>>() {
		}.getType());
	}

	/**
	 * 解析JSON数据为List列表
	 * @author jiangbin
	 * @param jsonArray
	 * @param type <pre>类型对象，因为List的泛型问题，可以通过如示例：
	 * 			{@link new TypeToken&lt;List&lt;SysUser&gt;&gt;(){}.getType()}
	 * 			方式来获取类类型，Gson会自动解析成List&lt;SysUser&gt;类型数据</pre>
	 * @return
	 * @date Jul 13, 20199:14:59 AM
	 */
	public static List<?> toList(String jsonArray, Type type) {
		return getGson().fromJson(jsonArray, type);
	}

	/**
	 * 解析JSON数据为对象
	 * @author jiangbin
	 * @param jsonStr JSON数据
	 * @param type 目标对象类型
	 * @return
	 * @date Jul 13, 20199:48:19 AM
	 */
	public static T toObject(String jsonStr, Type type) {
		return getGson().fromJson(jsonStr, type);
	}

	/**
	 * 获取Gson对象
	 * @author jiangbin
	 * @return
	 * @date Jul 13, 20199:50:55 AM
	 */
	private static Gson getGson() {
		return new Gson();
	}

}
