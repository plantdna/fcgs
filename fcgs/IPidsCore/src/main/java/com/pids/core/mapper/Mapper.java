package com.pids.core.mapper;

/**
 * <pre>数据映射器,通常用作临时高速缓存来存储和获取数据，以减小遍历转换开销,该临时缓存机制是以空间换时间来完成的,
 * 该Mapper存储的Key/Value类类型为:String/V,该接口的两个泛型类型:<V,S>含义如下：
 * 	V--表示缓存Mapper的Value值类类型，
 * 	S--表示资源对象，缓存的Key/Value来源于此类对象中属性或其自身实例
 * </pre>
 * @author jiangbin
 * @date 2012-5-7下午3:03:47
 */
public interface Mapper<V, S> extends ObjectMapper<String, V, S> {
}
