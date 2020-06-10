package com.fcgs.service.csv;

import com.fcgs.base.domain.SampleDna;
import com.fcgs.base.domain.SimpleSampleDna;
import com.fcgs.base.domain.gene.*;
import com.fcgs.service.snp.SnpAlleleEnum;
import com.pids.core.exception.ExceptionUtils;
import com.pids.core.pools.IdPools;
import com.pids.core.pools.SimpleIdPools;
import com.pids.core.utils.ListUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 解析SNP的KASP指纹数据CSV文件，该文件由SNP系统本地库中导出
 *
 * @author jiangbin
 * @date 2019/10/28 3:30 下午
 **/


public class SnpKaspGeneCsvParser {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private String titleStr = "样品条码号,样品名称,MG001,MG002,MG003,MG004,MG005,MG006,MG007,MG008,MG009,MG010,MG011,MG012,MG013,MG014,MG015,MG016,MG017,MG018,MG019,MG020,MG021,MG022,MG023,MG024,MG025,MG026,MG027,MG028,MG029,MG030,MG031,MG032,MG033,MG034,MG035,MG036,MG037,MG038,MG039,MG040,MG041,MG042,MG043,MG044,MG045,MG046,MG047,MG048,MG049,MG050,MG051,MG052,MG053,MG054,MG055,MG056,MG057,MG058,MG059,MG060,MG061,MG062,MG063,MG064,MG065,MG066,MG067,MG068,MG069,MG070,MG071,MG072,MG073,MG074,MG075,MG076,MG077,MG078,MG079,MG080,MG081,MG082,MG083,MG084,MG085,MG086,MG087,MG088,MG089,MG090,MG091,MG092,MG093,MG094,MG095,MG096,MG097,MG098,MG099,MG100,MG101,MG102,MG103,MG104,MG105,MG106,MG107,MG108,MG109,MG110,MG111,MG112,MG113,MG114,MG115,MG116,MG117,MG118,MG119,MG120,MG121,MG122,MG123,MG124,MG125,MG126,MG127,MG128,MG129,MG130,MG131,MG132,MG133,MG134,MG135,MG136,MG137,MG138,MG139,MG140,MG141,MG142,MG143,MG144,MG145,MG146,MG147,MG148,MG149,MG150,MG151,MG152,MG153,MG154,MG155,MG156,MG157,MG158,MG159,MG160,MG161,MG162,MG163,MG164,MG165,MG166,MG167,MG168,MG169,MG170,MG171,MG172,MG173,MG174,MG175,MG176,MG177,MG178,MG179,MG180,MG181,MG182,MG183,MG184,MG185,MG186,MG187,MG188,MG189,MG190,MG191,MG192,MG193,MG194,MG195,MG196,MG197,MG198,MG199,MG200,MG201,MG202,MG203,MG204,MG205,MG206,MG207,MG208,MG209,MG210,MG211,MG212,MG213,MG214,MG215,MG216,MG217,MG218,MG219,MG220,MG221,MG222,MG223,MG224,MG225,MG226,MG227,MG228,MG229,MG230,MG231,MG232,MG233,MG234,MG235,MG236,MG237,MG238,MG239,MG240,MG241,MG242,MG243,MG244,MG245,MG246,MG247,MG248,MG249,MG250,MG251,MG252,MG253,MG254,MG255,MG256,MG257,MG258,MG259,MG260,MG261,MG262,MG263,MG264,MG265,MG266,MG267,MG268,MG269,MG270,MG271,MG272,MG273,MG274,MG275,MG276,MG277,MG278,MG279,MG280,MG281,MG282,MG283,MG284,MG285,MG286,MG287,MG288,MG289,MG290,MG291,MG292,MG293,MG294,MG295,MG296,MG297,MG298,MG299,MG300,MG301,MG302,MG303,MG304,MG305,MG306,MG307,MG308,MG309,MG310,MG311,MG312,MG313,MG314,MG315,MG316,MG317,MG318,MG319,MG320,MG321,MG322,MG323,MG324,MG325,MG326,MG327,MG328,MG329,MG330,MG331,MG332,MG333,MG334,MG335,MG336,MG337,MG338,MG339,MG340,MG341,MG342,MG343,MG344,MG345,MG346,MG347,MG348,MG349,MG350,MG351,MG352,MG353,MG354,MG355,MG356,MG357,MG358,MG359,MG360,MG361,MG362,MG363,MG364,MG365,MG366,MG367,MG368,MG369,MG370,MG371,MG372,MG373,MG374,MG375,MG376,MG377,MG378,MG379,MG380,MG381,MG382,MG383,MG384";
	private final String CSV_SEPARATOR = ",|\t";

	public List<Gene> parse(InputStream inputStream) {
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			List<Gene> genes = new ArrayList<>();

			int rowIndex = 0;
			String titleStr = bufferedReader.readLine();
			IdPools pools = parseTitleRow(titleStr);

			// 读取行数据
			String rowStr;
			while ((rowStr = bufferedReader.readLine()) != null) {
				Gene gene = parseRow(rowIndex, rowStr, pools);
				if (gene != null) {
					genes.add(gene);
				}
				rowIndex++;
			}

			return genes;
		} catch (Exception e) {
			log.error("解析指纹数据CSV文件==>" + ExceptionUtils.getStackTrace(e));
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
		try {
			return parse(new FileInputStream(csvFile));
		} catch (FileNotFoundException e) {
			return null;
		}
	}

	/**
	 * 解析标题行数据成列索引号映射表
	 *
	 * @param titleStr
	 * @return com.viathink.core.pools.IdPools
	 * @author jiangbin
	 * @date 2020-02-26 08:47
	 **/
	private IdPools parseTitleRow(String titleStr) {
		titleStr = titleStr.replaceAll("\"", "");
		if (StringUtils.isBlank(titleStr)) {
			return null;
		}
		List<String> items = ListUtils.str2List(titleStr, CSV_SEPARATOR);
		if (CollectionUtils.isEmpty(items)) {
			return null;
		}
		return new SimpleIdPools(items);
	}

	/**
	 * 解析行指纹数据
	 *
	 * @param rowIndex 行号
	 * @param rowStr   行数据
	 * @param pools    列索引映射表
	 * @return com.viathink.ssr.core.domain.gene.Gene
	 * @author jiangbin
	 * @date 2019/11/5 5:36 下午
	 **/
	private Gene parseRow(int rowIndex, String rowStr, IdPools pools) {
		rowStr = rowStr.replaceAll("\"", "");
		if (StringUtils.isBlank(rowStr)) {
			return null;
		}
		String[] rowDatas = rowStr.split(CSV_SEPARATOR);
		if (ArrayUtils.isEmpty(rowDatas)) {
			return null;
		}
		return convertData(rowIndex, rowDatas, pools);
	}

	/**
	 * 转换行数据为指纹对象
	 *
	 * @param rowIndex
	 * @param row
	 * @param pools
	 * @return com.viathink.ssr.core.domain.gene.Gene
	 * @author jiangbin
	 * @date 2020-02-26 08:49
	 **/
	protected Gene convertData(int rowIndex, String[] row, IdPools pools) {
		int colIndex = 0;
		Gene gene = new SimpleGene();
		String samBarcode = StringUtils.stripToEmpty(row[colIndex++]);
		String samName = StringUtils.stripToEmpty(row[colIndex++]);

		SampleDna dna = new SimpleSampleDna();
		gene.setGeneId((rowIndex + 1) + "-" + samBarcode);
		dna.setSamBarcode(samBarcode);
		dna.setSamName(samName);
		gene.setSample(dna);

		List<Marker> markers = new ArrayList<>();
		List<String> miss = new ArrayList<>();
		for (; colIndex < pools.size() && colIndex < row.length; colIndex++) {
			String markerStr = StringUtils.stripToEmpty(row[colIndex]);
			if (markerStr.isEmpty() || markerStr.equals("/")) {
				miss.add(pools.getId(colIndex));// 缺失位点
				continue;
			}
			Marker marker = new SimpleMarker();
			marker.setPrimerName(pools.getId(colIndex));
			marker.setPrimerCode(pools.getId(colIndex));
			marker.setDye(pools.getId(colIndex));
			List<Allele> alleles = parseAlleles(markerStr);
			marker.setAlleles(alleles);
			marker.setGeneId(gene.getGeneId());
			markers.add(marker);
		}

		if (CollectionUtils.isEmpty(markers)) {
			return null;
		}

		// 设置位点列表
		gene.setMarkers(markers);

		// 设置缺失位点
		gene.setMissMarkers(miss);

		// 设置总位点数
		gene.setMarkerCount(pools.size());

		return gene;
	}

	/**
	 * 解析Allele数据
	 *
	 * @param markerStr
	 * @return java.util.List<com.viathink.ssr.core.domain.gene.Allele>
	 * @author jiangbin
	 * @date 2020/3/4 13:02
	 **/
	protected List<Allele> parseAlleles(String markerStr) {
		return SnpAlleleEnum.getAlleles(markerStr);
	}
}
