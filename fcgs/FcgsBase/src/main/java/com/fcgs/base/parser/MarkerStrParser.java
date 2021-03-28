package com.fcgs.base.parser;

import com.fcgs.base.domain.gene.Allele;
import com.fcgs.base.domain.gene.Marker;
import com.fcgs.base.domain.gene.SimpleAllele;
import com.fcgs.base.domain.gene.SimpleMarker;
import com.pids.core.exception.ICoreException;
import com.pids.core.parser.ListParserTemplate;
import com.pids.core.utils.RegexUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 位点信息字符串格式化工具，只支持A/B格式的数据
 *
 * @author jiangbin
 * @date 2014年11月20日上午1:19:33
 */

public class MarkerStrParser extends ListParserTemplate<String, Marker> {
    private static String pattern = "^([0-9]+)[/\\-]{0,2}([0-9]+)$";
    private static Pattern markerPattern = Pattern.compile(pattern);

    @Override
    protected Marker parser(String markerStr, boolean isInternal) throws ICoreException {
        List<Allele> alleles = parserAlleles(markerStr);
        if (CollectionUtils.isEmpty(alleles)) {
            return null;
        }
        Marker marker = new SimpleMarker();
        marker.setAlleles(alleles);
        return marker;
    }

    /**
     * 解析位点数据为Allele列表
     *
     * @param markerStr
     * @return java.util.List<com.viathink.ssr.core.domain.gene.Allele>
     * @author jiangbin
     * @date 2020/3/4 13:07
     **/
    public List<Allele> parserAlleles(String markerStr) throws ICoreException {
        if (!markerPattern.matcher(markerStr).find()) {
            return null;
        }
        List<String> parts = RegexUtils.match(markerStr, pattern);
        List<Allele> alleles = new ArrayList<>();
        Allele allele = new SimpleAllele();
        allele.setAllele(NumberUtils.toInt(parts.get(0)));
        alleles.add(allele);
        allele = new SimpleAllele();
        allele.setAllele(NumberUtils.toInt(parts.get(1)));
        alleles.add(allele);
        return alleles;
    }

}
