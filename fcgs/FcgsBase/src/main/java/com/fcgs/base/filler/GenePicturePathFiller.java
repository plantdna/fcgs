package com.fcgs.base.filler;

import com.fcgs.base.domain.gene.Gene;
import com.pids.core.exception.ICoreException;
import com.pids.core.filler.ListFillerTemplate;

/**
 * 指纹的图片路径填充器
 * <pre>构建好的图片路径，这是相对路径，格式如:
 * 	电泳板编号_用户ID/样品编号/电泳板编号_引物合成编号.png
 * 示例如下:
 * 	GJR2011-Plate7-P--P1-0920_ssradmin/Ne116/GJR2011-Plate7-P--P1-0920_M60.png</pre>
 * @author jiangbin
 * @date 2013-4-9下午2:30:25
 */
public class GenePicturePathFiller extends ListFillerTemplate<Gene, Gene> {

	@Override
	protected Gene fill(Gene source, boolean isInternal) throws ICoreException {
		MarkerPicturePathFiller filler = new MarkerPicturePathFiller();
		return filler.fill(source);
	}

}
