package com.pids.core.csv.saver;

import com.pids.core.csv.creator.CsvCreator;
import com.pids.core.csv.utils.CsvUtils;
import com.pids.core.exception.ICoreException;
import com.pids.core.pathcreator.PathCreator;
import com.pids.core.pathcreator.PathDetail;
import com.pids.core.pathcreator.utils.SimplePathCreator;
import com.pids.core.spliter.impl.SpliterUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * CSV数据保存功能模板实现类
 * @author jiang
 * @date 2018年9月2日下午8:27:13
 */
public abstract class CsvSaverTemplate<S> implements CsvSaver<S> {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	protected final int MAX_ROW_COUNT = 1000000;

	@Override
	public List<String> sharding(List<S> sources, PathDetail detail) {
		if (CollectionUtils.isEmpty(sources)) {
			return null;
		}

		//数据分片
		List<List<S>> shardings = SpliterUtils.split(sources, getShardingMaxRowCount());

		//构建CSV文件存储目录路径
		PathCreator pathCreator = getPathCreator(detail);
		pathCreator.setExt("/CSV");
		pathCreator.create(true);//构建存储目录
		String relativePath = pathCreator.getRelativePath();//存储目录的相对路径

		//分片构建CSV文件
		List<String> paths = new ArrayList<>();
		for (int index = 0; index < shardings.size(); index++) {

			//构建分片CSV数据文件存储路径
			String path = createShardingFileName(relativePath, index);
			String fullPath = pathCreator.getFilePath(path);

			List<S> sharding = shardings.get(index);
			if (CollectionUtils.isEmpty(sharding)) {
				continue;
			}

			//构建CSV数据内容
			String csvStr = getCsvCreator().create(sharding);
			if (StringUtils.isBlank(csvStr)) {
				continue;
			}

			//写入磁盘文件
			saveCsv(csvStr, fullPath);

			//保存相对路径
			paths.add(path);
		}

		//返回文件相对路径列表
		return paths;
	}

	@Override
	public String save(List<S> sources, PathDetail detail) throws ICoreException {
		if (CollectionUtils.isEmpty(sources)) {
			return null;
		}

		//构建CSV文件存储路径
		PathCreator pathCreator = getPathCreator(detail);
		String path = pathCreator.create();

		//构建CSV数据内容
		String csvStr = getCsvCreator().create(sources);
		if (StringUtils.isBlank(csvStr)) {
			return null;
		}

		//写入磁盘文件
		saveCsv(csvStr, path);

		//返回文件相对路径
		return pathCreator.getRelativePath();
	}

	@Override
	public String save(List<S> sources, String filePath) {
		//构建CSV数据内容
		String csvStr = getCsvCreator().create(sources);
		if (StringUtils.isBlank(csvStr)) {
			return null;
		}

		//写入磁盘文件
		saveCsv(csvStr, filePath);
		return filePath;
	}

	/**
	 * 保存CSV文件数据
	 * @author jiang
	 * @param csvStr
	 * @param filePath
	 * @date 2018年9月2日下午8:24:11
	 */
	protected void saveCsv(String csvStr, String filePath) {
		try {
			FileUtils.write(new File(filePath), csvStr, CsvUtils.CSV_ENCODING);
		} catch (IOException e) {
			logger.error("保存CSV文件(" + filePath + ")失败!", e);
		}

	}

	/**
	 * 创建路径构建器对象
	 * @author jiang
	 * @param detail 路径参数信息
	 * @return
	 * @date 2018年9月2日下午8:26:42
	 */
	protected PathCreator getPathCreator(PathDetail detail) {
		return new SimplePathCreator(detail);
	}

	/**
	 * 创建分片后目标文件名
	 * @author jiang
	 * @param relativeFolder 分片文件存储相对目录
	 * @param shardingIndex 分片索引号
	 * @return
	 * @date 2018年9月11日下午8:04:56
	 */
	protected String createShardingFileName(String relativeFolder, int shardingIndex) {
		return String.format("%s/%s.%s", relativeFolder, shardingIndex, CsvUtils.CSV_EXT);
	}

	/**
	 * 获取分片的最大行数，默认值为：1000000
	 * @author jiang
	 * @return
	 * @date 2018年9月11日下午7:58:55
	 */
	protected int getShardingMaxRowCount() {
		return MAX_ROW_COUNT;
	}

	/**
	 * 获取CSV数据构建器对象
	 * @author jiang
	 * @return
	 * @date 2018年9月2日下午8:26:57
	 */
	protected abstract CsvCreator<S> getCsvCreator();

}
