package com.pids.core.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <pre>数据映射器,通常用作临时高速缓存来存储和获取数据，以减小遍历转换开销,该临时缓存机制是以空间换时间来完成的,
 * 该Mapper存储的Key/Value类类型为:K/V,该接口的两个泛型类型:<K,V,S>含义如下：
 * 	K--表示缓存Mapper的Key值类型
 * 	V--表示缓存Mapper的Value值类类型，
 * 	S--表示资源对象，缓存的Key/Value来源于此类对象中属性或其自身实例
 * </pre>
 * @author jiangbin
 * @date 2012-5-7下午3:03:47
 */
public interface ObjectMapper<K, V, S> extends Serializable {
	/**
	 * 键名集合
	 * @Author andory
	 * @return
	 * @Date 2012-8-9下午1:31:31
	 */
	public Set<K> keySet();

	/**
	 * 键值对数目
	 * @Author andory
	 * @return
	 * @Date 2012-8-9下午1:31:32
	 */
	public int size();

	/**
	 * 是否为空mapper
	 * @Author andory
	 * @return
	 * @Date 2012-8-9下午1:31:33
	 */
	public boolean isEmpty();

	/**
	 * 清空mapper
	 * @Author andory
	 * @Date 2012-8-9下午1:31:35
	 */
	public void clear();

	/**
	 * 获取数据mapper
	 * @Author andory
	 * @return
	 * @Date 2012-8-9下午1:31:36
	 */
	public Map<K, V> getMapper();

	/**
	 * 设置数据mapper
	 * @Author andory
	 * @param mapper
	 * @Date 2012-8-9下午1:31:38
	 */
	public void setMapper(Map<K, V> mapper);

	/**
	 * 获取所有key值列表，实际是对keySet()方法的封装而已
	 * @author jiangbin
	 * @return 若不存在元素则返回空列表
	 * @date 2012-10-4上午3:56:41
	 */
	public List<K> getKeys();

	/**
	 * 获取Mapper的Values列表
	 * @author jiangbin
	 * @return 若不存在元素则返回空列表
	 * @date 2012-9-28下午6:43:11
	 */
	public List<V> getValues();

	/**
	 * 获取指定键值列表的值列表
	 * @author jiangbin
	 * @param keys
	 * @return 若不存在元素则返回空列表
	 * @date 2012-10-12下午10:34:51
	 */
	public List<V> getValues(List<K> keys);

	/**
	 * 获取指定键值列表的值列表，本方法与getValues()不同之处在于对无值的key本方法会以null进行占位
	 * @author Andory
	 * @param keys
	 * @return
	 * @date 2013-7-24上午11:02:57
	 */
	public List<V> getFullValues(List<K> keys);

	/**
	 * 添加数据对
	 * @Author andory
	 * @param key
	 * @param t
	 * @Date 2012-8-9下午1:31:40
	 */
	public void add(K key, V t);

	/**
	 * 删除指定数
	 * @Author andory
	 * @param key
	 * @return 返回删除的数据元素
	 * @Date 2012-8-9下午1:31:48
	 */
	public V delete(K key);

	/**
	 * 删除指定数
	 * @Author andory
	 * @param keys  <pre>需要被删除的key列表：
	 * 1、若给定key列表为空或null值则方法直接返回null，
	 * 2、若指定key列表元素均不在当前Mapper中则返回null
	 * 3、否则返回被删除的元素列表</pre>
	 * @return 返回删除的数据元素列表
	 * @Date 2012-8-9下午1:31:48
	 */
	public List<V> delete(List<K> keys);

	/**
	 * 获取指定数据
	 * @Author andory
	 * @param key
	 * @return
	 * @Date 2012-8-9下午1:31:50
	 */
	public V get(K key);

	/**
	 * 获取指定源数据对象Key值相同的数据
	 * @Author andory
	 * @param source
	 * @return
	 * @Date 2012-8-9下午1:31:50
	 */
	public V getBySource(S source);

	/**
	 * 获取指定源数据对象Key值相同的数据
	 * @Author andory
	 * @param sources
	 * @return
	 * @Date 2012-8-9下午1:31:50
	 */
	public List<V> getBySourceList(List<S> sources);

	/**
	 * 添加源数据对象
	 * @Author andory
	 * @param source
	 * @Date 2012-8-9下午1:31:52
	 */
	public void add(S source);

	/**
	 * 添加源数据对象列表
	 * @Author andory
	 * @param sources
	 * @Date 2012-8-9下午1:31:54
	 */
	public void addAll(List<S> sources);

	/**
	 * 判定指定参数是否存在
	 * @Author andory
	 * @param key
	 * @return
	 * @Date 2012-8-9下午1:31:55
	 */
	public boolean exist(K key);

	/**
	 * <pre>以当前Mapper的Key为主键移除重复对象或对象列表，处理逻辑如下：
	 * 1、先清空当前Mapper所有数据
	 * 2、将给定源数据添加进去
	 * 3、取出所有Value值列表
	 * 注意：由上可知不能使用可能包含重要数据的Mapper对象作为去重容器，
	 * 通常可以通过创建新对象来完成去重功能比较保险</pre>
	 * @author jiangbin
	 * @param sources
	 * @return
	 * @date 2013-3-31上午11:08:45
	 */
	public List<V> removeRepeat(List<S> sources);

	/**
	 * <pre>判定给定的资源对象的Key是否全一致，处理逻辑如下：
	 * 1、先清空当前Mapper所有数据
	 * 2、将给定源数据添加进去
	 * 3、判定Key列表长度是否为1
	 * 注意：由上可知不能使用可能包含重要数据的Mapper对象作为去重容器，
	 * 通常可以通过创建新对象来完成去重功能比较保险</pre>
	 * @author jiangbin
	 * @param sources
	 * @return
	 * @date 2013-4-14下午3:56:58
	 */
	public boolean hasSameKey(List<S> sources);

	/**
	 * 获取给定数据的Key列表
	 * @author Jiangbin
	 * @param sources
	 * @return
	 * @date 2014年1月6日上午1:06:37
	 */
	public List<K> getKeys(List<S> sources);

	/**
	 * 获取数据的Key值
	 * @author Jiangbin
	 * @param source
	 * @return
	 * @date 2017年6月5日上午24:06:37
	 */
	public K getKey(S source);
}
