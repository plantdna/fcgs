package com.pids.core.template.utils;

import com.pids.core.template.domain.Template;
import com.pids.core.utils.ListUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 模板定义注册器
 * @author jiang
 * @date 2018年6月12日下午1:06:40
 */

public class TemplateRegister {
	private static final TemplateNameMapper<Template> mapper = new TemplateNameMapper<>();

	/**
	 * 添加模板定义
	 * @author jiang
	 * @param template
	 * @date 2018年6月12日下午1:12:44
	 */
	public static void add(Template template) {
		mapper.add(template);
	}

	/**
	 * 添加模板定义列表
	 * @author jiang
	 * @param templates
	 * @date 2018年6月12日下午1:12:47
	 */
	public static void add(List<Template> templates) {
		mapper.addAll(templates);
	}

	/**
	 * 添加模板定义列表
	 * @author jiang
	 * @param templates
	 * @date 2018年6月12日下午1:12:47
	 */
	public static void add(Template[] templates) {
		mapper.addAll(ListUtils.array2List(templates));
	}

	/**
	 * 获取模板定义
	 * @author jiang
	 * @param templateName
	 * @return
	 * @date 2018年6月12日下午1:15:39
	 */
	public static Template get(String templateName) {
		if (StringUtils.isBlank(templateName)) {
			return null;
		}
		return mapper.get(templateName);
	}

	/**
	 * 判定指定模板名是否存在
	 * @author jiang
	 * @param templateName
	 * @return
	 * @date 2018年6月19日下午5:17:03
	 */
	public static boolean exist(String templateName) {
		if (StringUtils.isBlank(templateName)) {
			return false;
		}
		return mapper.get(templateName) != null;
	}

	/**
	 * 获取模板数
	 * @author jiang
	 * @return
	 * @date 2018年6月19日下午5:17:00
	 */
	public static int size() {
		return mapper.size();
	}

	/**
	 * 判定不存在模板
	 * @author jiang
	 * @return true/false--无/有模板
	 * @date 2018年6月19日下午5:16:59
	 */
	public static boolean isEmpty() {
		return mapper.isEmpty();
	}

	/**
	 * 移除模板定义
	 * @author jiang
	 * @param templateName
	 * @date 2018年6月12日下午1:20:16
	 */
	public static void remove(String templateName) {
		mapper.delete(templateName);
	}

	/**
	 * 移除模板定义列表
	 * @author jiang
	 * @param templateNames
	 * @date 2018年6月12日下午1:20:14
	 */
	public static void remove(List<String> templateNames) {
		mapper.delete(templateNames);
	}
}
