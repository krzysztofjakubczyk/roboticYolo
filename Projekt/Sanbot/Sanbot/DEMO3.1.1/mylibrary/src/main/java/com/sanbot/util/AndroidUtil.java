package com.sanbot.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AndroidUtil {


    private static long SEQ = 100;

    /**
     * 产生序列号
     *
     * @return
     */
    public static String getSerialNumber() {
        return "35"
                + // we make this look like a valid IMEI
                Build.BOARD.length() % 10 + Build.BRAND.length() % 10
                + Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10
                + Build.DISPLAY.length() % 10 + Build.HOST.length() % 10
                + Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10
                + Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10
                + Build.TAGS.length() % 10 + Build.TYPE.length() % 10
                + Build.USER.length() % 10; // 13 digits
    }

    /**
     * 检查APP是从哪个市场下载
     *
     * @return 返回应用市场标记
     * @throws PackageManager.NameNotFoundException
     */
    public static int getAppStore(Context context) throws PackageManager.NameNotFoundException {
        int app_id = 0;
        ApplicationInfo appInfo = context.getPackageManager()
                .getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
        if (appInfo != null && appInfo.metaData != null) {
            Bundle bundle = appInfo.metaData;
            app_id = bundle.getInt("app_id", 0);
        }
        return app_id;
    }

    public static int getMinVal(int[] srcInt) {
        int min = 0;
        for (int i : srcInt) {
            min = i > min ? min : i;
        }
        return min;
    }

    public static String getByteMD5(String fileName) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            File f = new File(fileName);
            InputStream ins = new FileInputStream(f);

            byte[] buffer = new byte[1024 * 100];
            int len;
            while ((len = ins.read(buffer)) != -1) {
                md5.update(buffer, 0, len);
            }
            ins.close();
            return toHexString(md5.digest());
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static final char HEX_DIGITS[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F'};

    public static String toHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
            sb.append(HEX_DIGITS[b[i] & 0x0f]);
        }
        return sb.toString().toLowerCase();
    }

    /**
     * 上传大文件方法
     * http put upload file
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static boolean uploadFileByHttpPut(String path, String filePath) throws IOException {
        if (filePath == null || filePath == null) {
            return false;
        }
        RandomAccessFile raf = new RandomAccessFile(filePath, "rw");

        if (raf == null) {
            return false;
        }

        int FIX_SIZE = 100 * 1024;
        boolean flag = false;
        try {
            URL url = null;
            try {
                url = new URL(path);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            HttpURLConnection conn = null;
            if (url != null) {
                conn = (HttpURLConnection) url.openConnection();
                if (conn != null) {
                    byte[] data = new byte[FIX_SIZE];
                    conn.setConnectTimeout(10 * 60 * 1000);
                    conn.setChunkedStreamingMode(0);
                    conn.setRequestMethod("PUT");
                    conn.setDoOutput(true);
                    conn.setRequestProperty("Content-Type", "");
                    OutputStream outStream = conn.getOutputStream();
                    int num = -1;
                    do {
                        num = raf.read(data);//每次读取FIX_SIZE大小的数据
                        if (num > 0) {
                            outStream.write(data, 0, num);//写入每次实际读取到的数据
                        }
                    } while (num > 0);
                    outStream.flush();
                    outStream.close();
                    data = null;
                    flag = conn.getResponseCode() == 200;
                    conn.disconnect();
                    conn = null;
                    return flag;
                }

                url = null;
            }

        } catch (ProtocolException | UnknownHostException e) {
            e.printStackTrace();
        }
        return flag;
    }


    public synchronized static long getSEQ() {
        SEQ++;
        return SEQ;
    }

}
