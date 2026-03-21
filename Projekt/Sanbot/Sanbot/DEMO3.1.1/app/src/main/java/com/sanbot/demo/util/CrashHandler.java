package com.sanbot.demo.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.sanbot.demo.entity.Constant;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;


public class CrashHandler implements Thread.UncaughtExceptionHandler {

    public static final String TAG = SharedPreferencesUtil.class.getSimpleName();

    private static CrashHandler mInstance;
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private Context mContext;

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        if (null == mInstance) {
            synchronized (CrashHandler.class) {
                if (null == mInstance)
                    mInstance = new CrashHandler();
            }
        }
        return mInstance;
    }

    public void init(Context context) {
        mContext = context.getApplicationContext();
        // 获取系统默认的 UncaughtException 处理类
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        // 设置 UncaughtException 处理类
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        if (!handleException(throwable) && mDefaultHandler != null) {
            // 用户处理不了，让系统来处理
            mDefaultHandler.uncaughtException(thread, throwable);
        } else {
            android.os.Process.killProcess(android.os.Process.myPid());
            throw new RuntimeException("程序报错");
        }
    }

    private boolean handleException(Throwable ex) {
        boolean result = false;
        StringBuilder buffer = new StringBuilder();
        if (ex != null) {
            try {
                PackageManager manager = mContext.getPackageManager();
                PackageInfo info = manager.getPackageInfo(mContext.getPackageName(), 0);
                buffer.append("软件包名：").append(info.packageName).append("\n");
                buffer.append("软件版本号：").append(info.versionName).append("\n");
                buffer.append("手机：").append(android.os.Build.BRAND).append(" ").append(android.os.Build.MODEL).append("\n");
                buffer.append("操作系统版本：").append(android.os.Build.VERSION.RELEASE).append("\n");
                buffer.append("时间：").append(DateUtil.getText(new Date())).append("\n");
                buffer.append("异常情况:").append(getStackTraceString(ex));
                File file = FileUtil.getExternalLogFile(mContext);
                if (file != null) {
                    FileOutputStream fos = new FileOutputStream(file, true);
                    fos.write(buffer.toString().getBytes());
                    fos.close();
                    Constant.EXCEPTION = true;
                    saveData();
                }
                result = true;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                result = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 已经发生异常
     */
    private void saveData() {
        SharedPreferencesUtil share = SharedPreferencesUtil.getInstance();
        share.writeSharedPreferences(mContext);
        share.putValue("exception", Constant.EXCEPTION);
        share.commit();
    }

    /**
     * 捕获异常的字符串
     *
     * @throws IOException
     */

    private String getStackTraceString(Throwable ex) throws IOException {
        String result;
        StringWriter writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        result = writer.toString();
        writer.close();
        printWriter.close();
        return result;
    }
}
