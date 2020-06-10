package com.pids.core.base64;

import com.pids.core.converter.Converter;
import com.pids.core.exception.ICoreException;
import com.pids.core.i18n.PidsCoreI18N;

import javax.mail.MessagingException;
import javax.mail.internet.MimeUtility;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 将字符串按Base64解码成字节数组
 * @author Andory
 * @date 2012-5-24下午04:24:45
 */
public class Base64Decoder implements Converter<String, byte[]> {

    @Override
    public byte[] convert(String source) throws ICoreException {
        ByteArrayInputStream bais = null;
        BufferedInputStream bis = null;
        try {
            byte[] mimeBytes = source.getBytes();
            bais = new ByteArrayInputStream(mimeBytes);
            InputStream b64is = getBase64Decode(bais);
            byte[] buff = new byte[b64is.available()];
            bis = new BufferedInputStream(b64is);
            bis.read(buff);
            b64is.close();
            return buff;
        } catch (Exception e) {
            throw new ICoreException(PidsCoreI18N.VIA_BASE64DECODER_ERROR01.get(), e);
        } finally {
            if (bais != null) {
                try {
                    bais.close();
                } catch (IOException e) {
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * 将输入流转换成base64编码格式
     * @param inputStream
     * @return
     * @throws MessagingException
     * @Author andory
     * @Date 2012-8-9下午2:34:25
     */
    protected InputStream getBase64Decode(InputStream inputStream) throws MessagingException {
        return MimeUtility.decode(inputStream, "base64");
    }

    public static void main(String[] args) {
        try {
            Base64Encoder encodeConvertor = new Base64Encoder();
            String str1 = encodeConvertor.convert("江彬".getBytes());
            System.out.println(str1);
            Base64Decoder decodeConvertor = new Base64Decoder();
            byte[] bytes = decodeConvertor.convert(str1);
            System.out.println(new String(bytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
