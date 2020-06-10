package com.fcgs.runner;

import com.fcgs.gui.FcgsMainFrame;
import com.fcgs.service.compare.FcgsGeneComparer;
import com.fcgs.service.domain.FcgsComparerParams;
import com.fcgs.service.domain.TagTypeEnum;
import com.fcgs.service.util.GCResults;
import com.pids.core.exception.ExceptionUtils;
import com.pids.core.message.MessageContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * 启动服务打开GUI比对页面
 * 界面功能调用FCGS比对算法调用程序
 * <br/><br/>
 * Create by WuHaotian on 2020-06-08 16:01:28
 **/
public class FcgsGuiRunner {
    private Logger log = LoggerFactory.getLogger(FcgsGuiRunner.class);

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FcgsMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FcgsMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FcgsMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FcgsMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FcgsMainFrame().setVisible(true);
            }
        });
    }

    /**
     * 调用界面参数执行比对
     */
    public GCResults compare(FcgsMainFrame frame, TagTypeEnum tagType) {
        FcgsComparerParams params = createParams(frame, tagType);

        if (params == null) {
            log.error("The number of arguments must be more than three!");
            return null;
        }

        return this.compare(params);
    }

    /**
     * 使用界面参数创建FCGS比对参数对象
     *
     * @param frame
     * @param tagType
     * @return
     */
    private FcgsComparerParams createParams(FcgsMainFrame frame, TagTypeEnum tagType) {
        switch (tagType) {
            case MICROSATELLITE:
                return createSsrParams(frame);
            case KASP:
                return createKaspParams(frame);
            case CHIP:
                return createChipParams(frame);
        }
        return null;
    }

    private FcgsComparerParams createChipParams(FcgsMainFrame frame) {
        FcgsComparerParams params = new FcgsComparerParams();
        params.setMinDiffLoci(frame.getChipMinDiff().getText());
        params.setMaxDiffLoci(frame.getChipMaxDiff().getText());
        params.setTagType(TagTypeEnum.CHIP);
        String path = frame.getChipSourceFile().getText();
        params.setTestCompareGeneFile(new File(path));
        path = frame.getChipTargetFile().getText();
        params.setRefCompareGeneFile(new File(path));
        return params;
    }

    private FcgsComparerParams createKaspParams(FcgsMainFrame frame) {
        FcgsComparerParams params = new FcgsComparerParams();
        params.setMinDiffLoci(frame.getKaspMinDiff().getText());
        params.setMaxDiffLoci(frame.getKaspMaxDiff().getText());
        params.setTagType(TagTypeEnum.KASP);
        String path = frame.getKaspSourceFile().getText();
        if(StringUtils.isBlank(path)){
            return null;
        }
        params.setTestCompareGeneFile(new File(path));
        path = frame.getKaspTargetFile().getText();
        if(StringUtils.isBlank(path)){
            return null;
        }
        params.setRefCompareGeneFile(new File(path));
        return params;
    }

    private FcgsComparerParams createSsrParams(FcgsMainFrame frame) {
        FcgsComparerParams params = new FcgsComparerParams();
        params.setMinDiffLoci(frame.getSsrMinDiff().getText());
        params.setMaxDiffLoci(frame.getSsrMaxDiff().getText());
        params.setTagType(TagTypeEnum.MICROSATELLITE);
        String path = frame.getSsrSourceFile().getText();
        if(StringUtils.isBlank(path)){
            return null;
        }
        params.setTestCompareGeneFile(new File(path));
        path = frame.getSsrTargetFile().getText();
        if(StringUtils.isBlank(path)){
            return null;
        }
        params.setRefCompareGeneFile(new File(path));
        return params;
    }

    /**
     * 执行指纹数据比对
     *
     * @param params
     * @return
     */
    private GCResults compare(FcgsComparerParams params) {
        try {
            return new FcgsGeneComparer().compare(params);
        } catch (Exception e) {
            log.error("The fingerprint compare process is faulty==>" + ExceptionUtils.getStackTrace(e));
            MessageContext.error("指纹比对出错:" + e.getMessage());
            throw e;
        }
    }
}
