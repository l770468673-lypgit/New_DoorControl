package com.doorcontrol.ruili.my.doorcontrol.activitys;

import android.os.AsyncTask;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.doorcontrol.ruili.my.doorcontrol.utils.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * @
 * @类名称: ${}
 * @类描述: ${type_name}
 * @创建人： Lyp
 * @创建时间：${date} ${time}
 * @备注：
 */
public class AppDownloadTask extends AsyncTask<String, Integer, Boolean> {

    public final static long _1K = 1024;
    private int mTotalBytes;
    private String mMd5;
    private final static String TAG = "AppDownloadTask";
    private String DOWNLOADED_APP_DIR = Environment
            .getExternalStorageDirectory().getAbsolutePath() + "/iDAMS_downloaded_app/";
    private String mDownloadedAppPath;
    private String mMd5OfDownloadedFile;


    @Override
    protected void onPostExecute(Boolean result) {
        if (result) {
            if (!TextUtils.isEmpty(mMd5)) {
                mMd5OfDownloadedFile = Utils.getFileMD5(new File(mDownloadedAppPath));
                if (!mMd5.equalsIgnoreCase(mMd5OfDownloadedFile)) {
                    Log.e(TAG, "onPostExecute md5 validating failed, md5:" + mMd5 + ", received file:" + mMd5OfDownloadedFile);
                    return;
                }
            }


        }
    }




    @Override
    protected void onProgressUpdate(Integer... values) {
        Log.d(TAG, "download package:" + values[0]);
        // NotificationUtil.sendDownloadProgressNotification(values[0],
        // mTotalBytes, false, false);
    }

    protected Boolean doInBackground(String... params) {

        mMd5 = params[3];
        OutputStream output = null;
        InputStream is = null;
        try {

            URL url = new URL(params[0]);
            HttpURLConnection conn = (HttpURLConnection) url
                    .openConnection();

            if (HttpURLConnection.HTTP_OK != conn.getResponseCode()) {
                Log.e(TAG, "connection failed:" + params[0]);
                return Boolean.FALSE;
            }

            mTotalBytes = conn.getContentLength();

            is = conn.getInputStream();
            File dir = new File(DOWNLOADED_APP_DIR);
            if (dir.exists()) {
                File[] files = dir.listFiles();
                for (File f : files) {
                    f.delete();
                }
            } else {
                dir.mkdirs();
                Log.d(TAG, "create dir:" + dir.getAbsolutePath());
            }


            mDownloadedAppPath = DOWNLOADED_APP_DIR + params[1] + "_"
                    + params[2] + ".apk";
            Log.d(TAG, "mDownloadedAppPath:" + mDownloadedAppPath);
            File file = new File(mDownloadedAppPath);
            file.createNewFile();

            output = new FileOutputStream(file);

            byte[] buffer = new byte[(int) (1024 * _1K)];
            int current;
            int downloaded = 0;

            int downloaded_M = 0;
            while ((current = is.read(buffer)) != -1) {
                output.write(buffer, 0, current);
                downloaded += current;
                if ((downloaded / (512 * _1K) > downloaded_M)) {
                    downloaded_M++;
                    publishProgress(downloaded);
                }
            }
            output.flush();

            // Check MD5 ?
            Log.d(TAG, "new app size:" + mTotalBytes
                    + ", downloaded file length:" + file.length());
            return mTotalBytes == file.length() ? Boolean.TRUE
                    : Boolean.FALSE;
        } catch (Exception e) {
            Log.e(TAG, "Exception:", e);
            return Boolean.FALSE;
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                     e.printStackTrace();
                }
                output = null;
            }

            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                     e.printStackTrace();
                }
                is = null;
            }

        }
    }

}