package com.fcgs.base.container;

import com.fcgs.base.domain.gene.Gene;
import com.fcgs.base.mapper.GeneInfoMapper;
import com.fcgs.base.mapper.GeneMapper;
import com.fcgs.base.mapper.MarkerMapper;

import java.util.List;

/**
 * <pre>指纹容器对象，主要用于打包各类指纹数据Mapper对象和指纹列表
 * <h2 style="color:red;font-size:14px;">注意：</h2>
 * <p style="font-size:12px;color:red;">源和目标指纹列表数据的设置与删除操作不会影响如下四个函数的返回值：
 * 	{@link #getGeneInfoMapper()}
 * 	{@link #getGeneInfo(String)}
 * 	{@link #getGeneInfo(String)}
 * 	{@link #getMarkerMapper(String)}
 * 故若需要反映到以上这4个函数中可以通过调用如下函数:
 * 	{@link #addGeneInfoMapper(List)}
 * 	{@link #addGeneMapper(List)}
 * 	{@link #addGeneInfoMapper(Gene)}
 * 	{@link #addGeneMapper(Gene)}
 * 将指纹添加到容器的相关Mapper中</p>
 * </pre>
 * @author Andory
 * @date 2013年9月12日下午11:55:49
 */
public interface GeneContainer {

	/**
	 * 获取指纹位点信息的Mapper
	 * @author Andory
	 * @return
	 * @date 2013年9月12日下午11:55:41
	 */
	public GeneMapper getGeneMapper();

	/**
	 * 设置指纹位点信息的Mapper
	 * @author Andory
	 * @param geneMapper
	 * @date 2013年9月12日下午11:55:43
	 */
	public void setGeneMapper(GeneMapper geneMapper);

	/**
	 * 获取指纹信息的Mapper
	 * @author Andory
	 * @return
	 * @date 2013年9月12日下午11:55:44
	 */
	public GeneInfoMapper getGeneInfoMapper();

	/**
	 * 设置指纹信息的Mapper
	 * @author Andory
	 * @param geneInfoMapper
	 * @date 2013年9月12日下午11:55:45
	 */
	public void setGeneInfoMapper(GeneInfoMapper geneInfoMapper);

	/**
	 * 将给定的指纹列表添加到{@link GeneMapper}中
	 * @author Andory
	 * @param sources
	 * @date 2013年9月12日下午11:53:10
	 */
	public void addGeneMapper(List<Gene> sources);

	/**
	 * 将给定的指纹列表添加到{@link GeneInfoMapper}中
	 * @author Andory
	 * @param sources
	 * @date 2013年9月12日下午11:53:12
	 */
	public void addGeneInfoMapper(List<Gene> sources);

	/**
	 * 将给定的指纹列表添加到{@link GeneMapper}中
	 * @author Andory
	 * @param source
	 * @date 2013年9月12日下午11:53:10
	 */
	public void addGeneMapper(Gene source);

	/**
	 * 将给定的指纹列表添加到{@link GeneInfoMapper}中
	 * @author Andory
	 * @param source
	 * @date 2013年9月12日下午11:53:12
	 */
	public void addGeneInfoMapper(Gene source);

	/**
	 * 设置目标指纹列表
	 * @author Andory
	 * @param targets
	 * @date 2013年9月13日上午12:06:18
	 */
	public void setTargets(List<Gene> targets);

	/**
	 * 获取目标指纹列表
	 * @author Andory
	 * @return
	 * @date 2013年9月13日上午12:06:17
	 */
	public List<Gene> getTargets();

    /**
     * 获取目标指纹列表中第一个指纹
     * @return
     */
    public Gene getTargetFirst();

	/**
	 * 设置源指纹列表
	 * @author Andory
	 * @param sources
	 * @date 2013年9月13日上午12:06:15
	 */
	public void setSources(List<Gene> sources);

	/**
	 * 获取源指纹列表
	 * @author Andory
	 * @return
	 * @date 2013年9月13日上午12:06:10
	 */
	public List<Gene> getSources();

    /**
     * 获取源指纹列表中第一个指纹
     * @return
     */
    public Gene getSourceFirst();

	/**
	 * 获取容器中的源和目标指纹集合，注意：源和目标指纹进行了去重
	 * @author Andory
	 * @return
	 * @date 2013年9月14日下午2:25:49
	 */
	public List<Gene> getAllGenes();

	/**
	 * 添加指纹列表到目标指纹列表中
	 * @author Andory
	 * @param genes
	 * @date 2013年9月13日上午12:28:21
	 */
	public void addTargetsList(List<Gene> genes);

	/**
	 * 添加指纹到目标指纹列表中
	 * @author Andory
	 * @param gene
	 * @date 2013年9月13日上午12:28:29
	 */
	public void addTarget(Gene gene);

	/**
	 * 添加指纹列表到源指纹列表中
	 * @author Andory
	 * @param genes
	 * @date 2013年9月13日上午12:28:30
	 */
	public void addSourcesList(List<Gene> genes);

	/**
	 * 添加指纹到源指纹列表中
	 * @author Andory
	 * @param gene
	 * @date 2013年9月13日上午12:28:31
	 */
	public void addSource(Gene gene);

	/**
	 * 获取指纹对象
	 * @author Andory
	 * @param geneId
	 * @return
	 * @date 2013年9月13日上午9:22:41
	 */
	public Gene getGeneInfo(String geneId);

	/**
	 * 获取指纹位点Mapper
	 * @author Andory
	 * @param geneId
	 * @return
	 * @date 2013年9月13日上午9:22:42
	 */
	public MarkerMapper getMarkerMapper(String geneId);

	/**
	 * 获取源指纹信息Mapper
	 * @author Andory
	 * @return
	 * @date 2013年9月14日下午2:35:10
	 */
	public GeneInfoMapper getSourceGeneInfoMapper();

	/**
	 * 获取目标指纹信息Mapper
	 * @author Andory
	 * @return
	 * @date 2013年9月14日下午2:35:11
	 */
	public GeneInfoMapper getTargetGeneInfoMapper();

	/**
	 * 获取源指纹列表的样品条码号
	 * @author jiangbin
	 * @return
	 * @date 2014年1月24日下午8:17:49
	 */
	public List<String> getSourceSamBarcodes();

	/**
	 * 获取目标指纹列表的样品条码号
	 * @author jiangbin
	 * @return
	 * @date 2014年1月24日下午8:17:48
	 */
	public List<String> getTargetSamBarcodes();

	/**
	 * 获取源和目标指纹的所有样品条码号，已去重
	 * @author jiangbin
	 * @return
	 * @date 2014年1月24日下午8:17:51
	 */
	public List<String> getAllSamBarcodes();

	/**
	 * 判定源和目标指纹是否均为空
	 * @author Jiangbin
	 * @return
	 * @date 2014-5-1下午10:34:25
	 */
	public boolean isEmpty();

	/**
	 * 获取源和目标指纹总数，该总数是源和目标指纹列表的并集，即进行了指纹去重后的指纹总数
	 * @author Jiangbin
	 * @return
	 * @date 2014-5-1下午10:35:41
	 */
	public int size();

	/**
	 * 判定源指纹列表是否为空
	 * @author Jiangbin
	 * @return
	 * @date 2014-5-1下午10:37:01
	 */
	public boolean isSourceEmpty();

	/**
	 * 判定目标指纹列表是否为空
	 * @author Jiangbin
	 * @return
	 * @date 2014-5-1下午10:37:02
	 */
	public boolean isTargetEmpty();

	/**
	 * 获取源列表指纹数
	 * @author Jiangbin
	 * @return
	 * @date 2014-5-1下午10:37:04
	 */
	public int getSourceCount();

	/**
	 * 获取目标列表指纹数
	 * @author Jiangbin
	 * @return
	 * @date 2014-5-1下午10:37:05
	 */
	public int getTargetCount();

}