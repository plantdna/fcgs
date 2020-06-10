package com.pids.core.base64;

import com.pids.core.converter.Converter;
import com.pids.core.exception.ICoreException;
import com.pids.core.i18n.PidsCoreI18N;

import javax.mail.MessagingException;
import javax.mail.internet.MimeUtility;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 将字节数组编码成Base64字符串
 * @author Andory
 * @date 2012-5-24下午04:32:16
 */
public class Base64Encoder implements Converter<byte[], String> {

    @Override
    public String convert(byte[] source) throws ICoreException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        OutputStream b64os;
        try {
            b64os = getBase64Encode(baos);
            b64os.write(source);
            b64os.close();
            return new String(baos.toByteArray());
        } catch (MessagingException e) {
            throw new ICoreException(PidsCoreI18N.VIA_BASE64ENCODER_ERROR01.get(), e);
        } catch (IOException e) {
            throw new ICoreException(PidsCoreI18N.VIA_BASE64ENCODER_ERROR02.get(), e);
        }
    }

    /**
     * 将输出流转进行base64解码
     * @param baos
     * @return
     * @throws MessagingException
     * @Author andory
     * @Date 2012-8-9下午2:34:54
     */
    protected OutputStream getBase64Encode(ByteArrayOutputStream baos) throws MessagingException {
        return MimeUtility.encode(baos, "base64");
    }
}
