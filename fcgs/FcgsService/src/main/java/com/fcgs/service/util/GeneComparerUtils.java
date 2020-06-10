package com.fcgs.service.util;

import com.fcgs.base.domain.gene.Gene;
import com.fcgs.base.filler.GeneMarkerCountFiller;
import com.fcgs.base.filler.MarkerGeneIdFiller;
import com.fcgs.core.comparer.csv.SmartComparerResultCsvCreator;
import com.fcgs.core.comparer.domain.SmartComparerResult;
import com.fcgs.core.comparer.same.GeneMissMarkersFiller;
import com.pids.core.utils.UuidUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 指纹比对工具类
 *
 * @author jiangbin
 * @date 2020-02-25 15:56
 **/
public class GeneComparerUtils {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private static GeneSameMarkerCountFiller sameMarkerCountFiller = new GeneSameMarkerCountFiller();
	private static SmartComparerResultCsvCreator smartComparerResultCsvCreator = new SmartComparerResultCsvCreator();
	private static MarkerGeneIdFiller markerGeneIdFiller = new MarkerGeneIdFiller();
	private static GeneMissMarkersFiller geneMissMarkersFiller = new GeneMissMarkersFiller();
	private static GeneMarkerCountFiller geneMarkerCountFiller = new GeneMarkerCountFiller();

	/**
	 * 填充缺失位点列表
	 *
	 * @param genes
	 * @param allnames
	 * @return void
	 * @author jiangbin
	 * @date 2020-02-25 21:01
	 **/
	public static void fillMissMarker(List<Gene> genes, List<String> allnames) {
		geneMissMarkersFiller.fill(genes, allnames);
	}

	/**
	 * 填充无差异位点数，这种方法可以避免统计方法计算时带来的大量时间消耗
	 *
	 * @param sgenes
	 * @param tgenes
	 * @param results
	 * @return void
	 * @author jiangbin
	 * @date 2020/1/5 8:45 下午
	 **/
	public static void fillSameMarkerCount(List<Gene> sgenes, List<Gene> tgenes, List<SmartComparerResult> results) {
		sameMarkerCountFiller.fill(results, sgenes, tgenes);
	}

	/**
	 * 保存指纹比对结果列表到CSV文件
	 *
	 * @param results
	 * @param sGeneCount
	 * @param tGeneCount
	 * @return java.lang.String
	 * @author jiangbin
	 * @date 2020-02-25 16:06
	 **/
	public static String save(List<SmartComparerResult> results, int sGeneCount, int tGeneCount) {
		// 保存成列表格式数据
		String csvContents = smartComparerResultCsvCreator.create(results);
		// String csvPath = gc.getListResultFolder() + "/" + sGeneCount + "-" +
		// tGeneCount + "个指纹比对结果表-列表格式-" + gc.getFileSuffix() + ".csv";
		String csvPath = "";
		try {
			FileUtils.writeStringToFile(new File(csvPath), csvContents, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return csvPath;
	}

	/**
	 * 填充指纹ID列表
	 *
	 * @param genes
	 * @return void
	 * @author jiangbin
	 * @date 2020-02-25 18:48
	 **/
	public static void fillGeneIds(List<Gene> genes) {
		for (Gene gene : genes) {
			String geneId = UuidUtil.getUuid();
			gene.setGeneId(geneId);
		}
		markerGeneIdFiller.fill(genes);
	}

	/**
	 * 填充位点总数
	 *
	 * @param genes
	 * @param markerCount
	 * @return void
	 * @author jiangbin
	 * @date 2020/3/4 16:44
	 **/
	public static void fillMarkerCount(List<Gene> genes, int markerCount) {
		geneMarkerCountFiller.fill(genes, markerCount);
	}

}
