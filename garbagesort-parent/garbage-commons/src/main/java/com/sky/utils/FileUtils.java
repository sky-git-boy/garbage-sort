package com.sky.utils;

import org.apache.commons.io.IOUtils;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * 操作文件相关工具类
 *
 * @author sky
 * @create 2021-09-12 21:24
 */
public class FileUtils {

    // 网络图片到byte数组
    public static byte[] image2byte(String path) throws IOException {
        URL urlConet = new URL(path);
        URLConnection con = urlConet.openConnection();
        InputStream in = con.getInputStream();
        byte[] buffer = new byte[4 * 1024];
        int n;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }
        return out.toByteArray();
    }

    public static byte[] getFileContent(String filename) throws IOException {
        File file = new File(filename);
        if (!file.canRead()) {
            System.err.println("文件不存在或者不可读: " + file.getAbsolutePath());
            throw new RuntimeException("file cannot read: " + file.getAbsolutePath());
        }
        FileInputStream is = null;
        try {
            is = new FileInputStream(file);
            return getInputStreamContent(is);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 将InputStream内的内容全部读取，作为bytes返回
     */
    public static byte[] getInputStreamContent(InputStream is) throws IOException {
        byte[] b = new byte[1024];
        // 定义一个输出流存储接收到的数据
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        // 开始接收数据
        int len;
        while (true) {
            len = is.read(b);
            if (len == -1) {
                // 数据读完
                break;
            }
            byteArrayOutputStream.write(b, 0, len);
        }
        return byteArrayOutputStream.toByteArray();
    }

    /**
     * @Description MP3转换pcm
     * @param mp3Stream
     *            原始文件流
     * @return 转换后的二进制
     * @throws Exception
     */
    public static byte[] mp3Convertpcm(InputStream mp3Stream) throws Exception {
        BufferedInputStream zipTest = new BufferedInputStream(mp3Stream);
        // 原MP3文件转AudioInputStream
        AudioInputStream mp3audioStream = AudioSystem.getAudioInputStream(zipTest);
        // 将AudioInputStream MP3文件 转换为PCM AudioInputStream
        AudioInputStream pcmaudioStream = AudioSystem.getAudioInputStream(AudioFormat.Encoding.PCM_SIGNED,
                mp3audioStream);
        byte[] pcmBytes = IOUtils.toByteArray(pcmaudioStream);
        pcmaudioStream.close();
        mp3audioStream.close();
        return pcmBytes;
    }
}
