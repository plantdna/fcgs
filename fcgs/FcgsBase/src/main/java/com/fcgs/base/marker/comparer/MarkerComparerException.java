package com.fcgs.base.marker.comparer;

import com.fcgs.base.domain.gene.Marker;
import com.fcgs.base.i18n.FcgsBase3I18N;

/**
 * 位点比对异常信息
 * @author jiangbin
 * @date 2012-4-21下午10:31:31
 */
public class MarkerComparerException extends Exception {

    private static final long serialVersionUID = -6988060903182583376L;

    private final Marker source;
    private final Marker target;

    public MarkerComparerException(Marker source, Marker target, Exception e) {
        super(String.format(getMessageTemplate(), source.getPrimerName(), source.getDye(), e.getMessage(), source,
                target), e);
        this.source = source;
        this.target = target;
    }

    public Marker getSource() {
        return source;
    }

    public Marker getTarget() {
        return target;
    }

    protected static String getMessageTemplate() {
        return FcgsBase3I18N.SSR_MARKERCOMPAREREXCEPTION_ERROR01.get();
    }
}
