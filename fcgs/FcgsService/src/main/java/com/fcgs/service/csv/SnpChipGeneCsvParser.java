package com.fcgs.service.csv;

import com.fcgs.base.domain.gene.*;
import com.fcgs.base.filler.GeneMarkerCountFiller;
import com.fcgs.base.mapper.MarkerGeneIdGroupMapper;
import com.fcgs.service.snp.SnpAlleleEnum;
import com.pids.core.exception.ExceptionUtils;
import com.pids.core.pools.IdPools;
import com.pids.core.pools.SimpleIdPools;
import com.pids.core.utils.ListUtils;
import com.pids.core.utils.RegexUtils;
import com.pids.core.utils.UuidUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * SNP芯片检测数据文件指纹解析功能
 *
 * @author jiangbin
 * @date 2019/11/28 9:09 上午
 **/

public class SnpChipGeneCsvParser {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private static Pattern PATTERN0 = RegexUtils.pattern("^[A-Z0-9]\\d{1,2}$");
	private static Pattern PATTERN1 = RegexUtils.pattern("^(\\d{6})$");

	private final String PROBESET_ID = "probeset_id";
	private final String CUSTID = "custid";
	private final String AFFY_SNP_ID = "Affy_SNP_ID";
	private final String CSV_SEPARATOR = ",|\t";

	public List<Gene> parse(InputStream inputStream) {
		StopWatch watch = new StopWatch();
		watch.start();

		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

			// 读取标题行数据
			String titleStr = readTitleRow(bufferedReader);

			// 解析标题行数据
			IdPools pools = parseTitleRow(titleStr);

			// 读取行数据
			MarkerGeneIdGroupMapper mapper = new MarkerGeneIdGroupMapper();
			String rowStr;
			int rowCount = 0;

			Map<String, List<String>> miss = new HashMap<>();
			while ((rowStr = bufferedReader.readLine()) != null) {
				rowCount++;
				List<Marker> markers = parseRow(rowStr, pools, miss);
				if (CollectionUtils.isNotEmpty(markers)) {
					mapper.addAll(markers);
				}
			}

			log.info("共有" + rowCount + "个SNP位点!");

			// 创建指纹列表
			List<Gene> genes = createGenes(mapper, miss);

			// 填充位点总数参数
			GeneMarkerCountFiller markerCountFiller = new GeneMarkerCountFiller();
			markerCountFiller.fill(genes, rowCount);

			return genes;
		} catch (Exception e) {
			log.error("解析指纹数据CSV文件失败==>" + ExceptionUtils.getStackTrace(e));
			return null;
		} finally {
			try {
				// close
				if (inputStream != null) {
					inputStream.close();
				}
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException e) {
			}
			watch.stop();
			log.info("解析SNP指纹共耗时(ms)==>" + watch.getTime());
		}
	}

	/**
	 * 解析CSV指纹数据文件
	 *
	 * @param csvFile
	 * @return java.util.List<com.viathink.ssr.core.domain.gene.Gene>
	 * @author jiangbin
	 * @date 2019/11/5 5:43 下午
	 **/
	public List<Gene> parse(String csvFile) {
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(csvFile);
			return parse(inputStream);
		} catch (Exception e) {
			log.error("解析指纹数据CSV文件[" + csvFile + "]失败==>" + ExceptionUtils.getStackTrace(e));
			return null;
		}
	}

	/**
	 * 读取标题行数据
	 *
	 * @param bufferedReader
	 * @return java.lang.String
	 * @author jiangbin
	 * @date 2020/1/5 1:26 下午
	 **/
	protected String readTitleRow(BufferedReader bufferedReader) throws IOException {
		String titleStr;// 标题行数据
		while (true) {
			titleStr = bufferedReader.readLine();
			if (!titleStr.startsWith("##") && titleStr.contains("probeset_id")) {
				break;
			}
		}
		return titleStr;
	}

	/**
	 * 创建指纹列表
	 *
	 * @param mapper
	 * @return java.util.List<com.viathink.ssr.core.domain.gene.Gene>
	 * @author jiangbin
	 * @date 2019/11/28 9:27 上午
	 **/
	private List<Gene> createGenes(MarkerGeneIdGroupMapper mapper, Map<String, List<String>> miss) {
		List<Gene> genes = new ArrayList<>();
		List<String> geneIdList = mapper.getKeys();
		// String samSpecies = SysService.getConfigDetails().getSamSpecies();
		for (String geneId : geneIdList) {
			Gene gene = new SimpleGene();
			gene.setGeneId(geneId);
			gene.setDnaBarcode(geneId);
			gene.setSamBarcode(geneId);
			// gene.getSample().setSamSpecies(samSpecies);
			List<Marker> markers = mapper.get(geneId);
			gene.setMarkers(markers);
			gene.setMissMarkers(miss.get(geneId));
			genes.add(gene);
		}
		return genes;
	}

	/**
	 * 解析标题行数据
	 *
	 * @param titleStr
	 * @return java.util.List<com.viathink.ssr.core.domain.gene.Gene>
	 * @author jiangbin
	 * @date 2019/11/28 9:16 上午
	 **/
	private IdPools parseTitleRow(String titleStr) {
		List<String> items = ListUtils.str2List(titleStr, CSV_SEPARATOR);
		List<String> keys = new ArrayList<>();
		for (int index = 0; index < items.size(); index++) {
			String item = items.get(index);
			if (item.equals(AFFY_SNP_ID)) {
				break;
			}
			if (item.equals(CUSTID)) {
				continue;
			}
			if (item.equals(PROBESET_ID)) {
				keys.add(item);
			} else {
				String samBarcode = parseSamBarcode(item);
				keys.add((index + 1) + "-" + samBarcode);
			}
		}
		return new SimpleIdPools(keys);
	}

	/**
	 * 解析出标题行中包含的样品条码号
	 *
	 * @param item
	 * @return java.lang.String
	 * @author jiangbin
	 * @date 2020/3/5 15:47
	 **/
	private String parseSamBarcode(String item) {
		try {
			if (!item.contains(".")) {
				return item;
			}
			String prefix = item.split("\\.")[0];// 去掉尾部内容：.CEL_call_code
			if (StringUtils.isBlank(prefix)) {
				return UuidUtil.getUuid();
			}
			List<String> strs = ListUtils.str2List(prefix, "_");
			for (String str : strs) {
				// 不能是为空字符串、三位孔位号或6位年份
				if (StringUtils.isBlank(str) || PATTERN0.matcher(str).find() || PATTERN1.matcher(str).find()) {
					continue;
				}
				return str;
			}
			return UuidUtil.getUuid();
		} catch (Exception e) {
			log.error("解析样品条码[" + item + "]失败==>" + ExceptionUtils.getStackTrace(e));
			throw e;
		}
	}

	/**
	 * 解析行指纹数据
	 *
	 * @param rowStr
	 * @param pools
	 * @return com.viathink.ssr.core.domain.gene.Gene
	 * @author jiangbin
	 * @date 2019/11/5 5:36 下午
	 **/
	private List<Marker> parseRow(String rowStr, IdPools pools, Map<String, List<String>> miss) {
		List<String> items = ListUtils.str2List(rowStr, CSV_SEPARATOR);
		// probeset_id参数是一致的，但custid列在某些文件中可能未给定
		String primerName = items.get(pools.getIndex(PROBESET_ID));
		List<Marker> markers = new ArrayList<>();
		int count = pools.size();
		for (int index = 1; index <= count; index++) {
			String geneId = pools.getId(index);
			String item = items.get(index);
			Marker marker = new SimpleMarker();
			marker.setGeneId(geneId);
			marker.setPrimerName(primerName);
			marker.setDye(primerName);
			marker.setPrimerCode(primerName);
			List<Allele> alleles = getAlleles(item);
			if (CollectionUtils.isNotEmpty(alleles)) {
				marker.setAlleles(alleles);
				markers.add(marker);
			} else {
				this.addMiss(miss, geneId, primerName);
			}
		}
		return markers;
	}

	/**
	 * 添加缺失位点名称列表
	 *
	 * @param missMap
	 * @param geneId
	 * @param marker
	 * @return void
	 * @author jiangbin
	 * @date 2020/1/5 4:57 下午
	 **/
	private void addMiss(Map<String, List<String>> missMap, String geneId, String marker) {
		List<String> misses = missMap.get(geneId);
		if (misses == null) {
			misses = new ArrayList<>();
			missMap.put(geneId, misses);
		}
		misses.add(marker);
	}

	/**
	 * 转换成Allele列表
	 *
	 * @param item
	 * @return java.util.List<com.viathink.ssr.core.domain.gene.Allele>
	 * @author jiangbin
	 * @date 2019/11/28 9:38 上午
	 **/
	private List<Allele> getAlleles(String item) {
		try {
			return SnpAlleleEnum.getAlleles(item);
		} catch (Exception e) {
			log.error("解析SNP基因型为Allele失败==>" + item);
			throw e;
		}
	}

	// 测试样品条码号的解析功能
	public static void main(String[] args) {
		String samBarcode = new SnpChipGeneCsvParser().parseSamBarcode("E13_MW1901484_191223.CEL_call_code");
		System.out.println(samBarcode);
		samBarcode = new SnpChipGeneCsvParser().parseSamBarcode("E13_MW1901484-1_191223.CEL_call_code");
		System.out.println(samBarcode);
		samBarcode = new SnpChipGeneCsvParser().parseSamBarcode("013_MW1901484_191223.CEL_call_code");
		System.out.println(samBarcode);
		samBarcode = new SnpChipGeneCsvParser().parseSamBarcode("013_MW1901484-3_191223.CEL_call_code");
		System.out.println(samBarcode);
		samBarcode = new SnpChipGeneCsvParser().parseSamBarcode("BGG5940_G11_191218.CEL_call_code");
		System.out.println(samBarcode);
		samBarcode = new SnpChipGeneCsvParser().parseSamBarcode("BGG5940-4_G11_191218.CEL_call_code");
		System.out.println(samBarcode);
		samBarcode = new SnpChipGeneCsvParser().parseSamBarcode("MW1901484.CEL_call_code");
		System.out.println(samBarcode);
		samBarcode = new SnpChipGeneCsvParser().parseSamBarcode("MW1901484-5.CEL_call_code");
		System.out.println(samBarcode);
	}
}