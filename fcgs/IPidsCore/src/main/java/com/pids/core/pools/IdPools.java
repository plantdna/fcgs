package com.pids.core.pools;

import java.io.Serializable;
import java.util.List;

/**
 * ID与索引间映射关系缓存池
 *
 * @author jiang
 * @date 2019-11-02 15:23
 */
public interface IdPools extends Serializable {

    /**
     * 无效索引号标志
     *
     * @author jiangbin
     * @date 2019/11/4 10:47 上午
     **/
    int INVALID = -1;

    /**
     * 最小有效索引号标志
     *
     * @author jiangbin
     * @date 2019/11/4 10:47 上午
     **/
    int MIN_VALID = 0;

    /**
     * 获取索引号对应的ID
     *
     * @param index
     * @return java.lang.String
     * @throws
     * @author jiang
     * @date 2019/11/2 15:34
     **/
    String getId(int index);

    /**
     * 获取ID对应索引号
     *
     * @param id
     * @return int
     * @throws
     * @author jiang
     * @date 2019/11/2 15:34
     **/
    int getIndex(String id);

    /**
     * 添加ID列表到映射表
     *
     * @param idList
     * @return void
     * @throws
     * @author jiang
     * @date 2019/11/2 15:34
     **/
    void add(List<String> idList);

    /**
     * 添加ID列表到映射表
     *
     * @param idArray
     * @return void
     * @author jiangbin
     * @date 2019/12/31 2:16 下午
     **/
    void add(String[] idArray);

    /**
     * 获取ID号列表
     *
     * @return java.util.List<java.lang.String>
     * @author jiangbin
     * @date 2019/11/9 5:56 下午
     **/
    List<String> getIdList();

    /**
     * 获取ID号个数,不重复的
     *
     * @return int
     * @author jiangbin
     * @date 2019/11/19 6:49 下午
     **/
    int size();
}
