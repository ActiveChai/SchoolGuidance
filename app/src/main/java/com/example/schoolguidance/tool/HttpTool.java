package com.example.schoolguidance.tool;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Guanzhou·Chen on 2019/5/9.
 */

public class HttpTool extends Thread {

    public static final int MODE_GET = 101;
    public static final int MODE_POST = 102;

    public static final String ip = "http://www.hilosh.xyz:18080";

    private int message_what;
    private int current_mode;

    private String str_url;
    private String data = "";
    private HashMap<String, String> param = new HashMap<String, String>();

    private Handler handler;

    /**
     * @param MODL         选择POST或GET方式，HttpTool.MODE_GET / HttpTool.MODE_POST
     * @param url          发送http请求的url，ip后的router
     * @param message_what 使用Handler时，分辨消息的message.what
     */
    public HttpTool(int MODL, String url, int message_what, Handler handler) {
        current_mode = MODL;
        str_url = ip + url;
        this.message_what = message_what;
        this.handler = handler;
    }

    /**
     * @param MODE 设置的新模式 HttpTool.MODE_GET / HttpTool.MODE_POST
     * @return 是否成功设置
     */
    public boolean setMODE(int MODE) {
        if ((MODE != MODE_GET) || (MODE != MODE_POST)) {
            return false;
        } else {
            current_mode = MODE;
            return true;
        }
    }

    /**
     * 清空要发送的数据
     */
    public void clearData() {
        data = "";
        param.clear();
    }

    /**
     * @param key   要发送数据的key值
     * @param value 要发送数据的value值
     */
    public void addData(String key, String value) {
        if (current_mode == MODE_GET) {
            addGETData(key, value);
        } else {
            addPOSTData(key, value);
        }
    }

    private void addGETData(String key, String value) {
        if (!data.equals("")) {
            data = data + "&";
        } else {
            data = "?";
        }
        data = data + key + "=" + value;
    }

    private void addPOSTData(String key, String value) {
        param.put(key, value);
    }


    private String getDataString(HashMap<String, String> params) throws Exception {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return result.toString();
    }

//    private void sendMessage() {
//        Looper myLooper, mainLooper;
//        myLooper = Looper.myLooper();
//        mainLooper = Looper.getMainLooper();    //获得自己的main的looper
//        String obj;
//        if (myLooper == null) {
//            mNoLooperThreadHandler = new MainActivity.MyHandler(mainLooper);
//            obj = "NoLooperThread has no looper andhandleMessage function executed in main thread!";
//        } else {
//            mNoLooperThreadHandler = new MainActivity.MyHandler(myLooper);
//            obj = "This is from NoLooperThread self andhandleMessage function executed in NoLooperThread!";
//        }
//        mNoLooperThreadHandler.removeMessages(message_what);    //清空消息队列
//
//        Message m = mNoLooperThreadHandler.obtainMessage(message_what, 1, 1, obj);    //生成消息对象
//        mNoLooperThreadHandler.sendMessage(m);   //发送消息
//    }

    private void sendMessage(Object obj) {

        Looper.prepare();

        handler.removeMessages(message_what);    //清空消息队列
        Message message = handler.obtainMessage(message_what, 1, 1, obj);
        handler.sendMessage(message);
//        Looper.loop();
    }


    @Override
    public void run() {
        try {
            URL url = new URL(str_url + data);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            if (current_mode == MODE_GET) {

                connection.setRequestMethod("GET");
                connection.connect();
            } else {

                byte[] postData = getDataString(param).getBytes(StandardCharsets.UTF_8);

                connection.setRequestMethod("POST");
                connection.setInstanceFollowRedirects(false);
                connection.setReadTimeout(5000);
                connection.setDoOutput(true);
                connection.setUseCaches(false);
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                connection.setRequestProperty("charset", "utf-8");
                connection.setRequestProperty("Content-Length", Integer.toString(postData.length));
                DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
                wr.write(postData);
            }
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {

                InputStreamReader in = new InputStreamReader(connection.getInputStream());
                BufferedReader buffer = new BufferedReader(in);

                String inputLine;
                String resultData = "";
                boolean flag = false;
                while (((inputLine = buffer.readLine()) != null)) {
                    if (flag)
                        resultData += "\n";
                    else flag = true;
                    resultData += inputLine;
                }
                in.close();
                Log.d("RESPONSE", resultData);
                sendMessage(resultData);
            }
            connection.disconnect();

        } catch (Exception e) {
            Log.d("ERROR", e.toString());

        }
    }


}
