package com.raokouling.sparkfengbo.raokoulingapplication.data.source.remote;

import com.raokouling.sparkfengbo.raokoulingapplication.constants.Constants;
import com.raokouling.sparkfengbo.raokoulingapplication.data.bean.EntertainmentObject;
import com.raokouling.sparkfengbo.raokoulingapplication.data.bean.RaoKouLing;
import com.raokouling.sparkfengbo.raokoulingapplication.data.source.EntertainmentDataSource;
import com.raokouling.sparkfengbo.raokoulingapplication.util.CommonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fengbo on 2016/11/2.
 */

public class EntertainmentRemoteDataSource implements EntertainmentDataSource {

    public static int DEFAULT_NUM = 10;
    private List<EntertainmentObject> mDataSource;


    public EntertainmentRemoteDataSource(){
        mDataSource = new ArrayList<EntertainmentObject>();
    }

    @Override
    public void saveEntertainments(List<EntertainmentObject> eos) {

    }

    @Override
    public void deleteEntertainments(String id) {

    }

    @Override
    public void loadEntertainments(final LoadEntertainmentCallback callback) {

        new AsyncTask<Void, Void, String>() {


            @Override
            protected String doInBackground(Void... params) {
                return  request(Constants.API_SERVER_ADDRESS, "num="+ DEFAULT_NUM);
            }

            @Override
            protected void onPostExecute(String s) {

                Log.d("fengbo", s);
                try {
                    JSONObject result = new JSONObject(s);
                    int code = result.optInt("code");
                    String msg = result.optString("msg");
                    if(code == 200 || msg.equals("success")){
                        JSONArray newsList = result.optJSONArray("newslist");
                        for (int i = 0; i < newsList.length(); i++) {
                            EntertainmentObject rkl = new RaoKouLing();
                            JSONObject item = (JSONObject) newsList.get(i);
                            String content = item.optString("content");
                            rkl.setContent(content);
                            mDataSource.add(rkl);
                        }

                        callback.onLoaded(mDataSource);
                    } else {
                        callback.onDataNotAvailable();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }.execute();
    }


    @Override
    public void refreshEntertainments() {

    }

    /**
     * only for this application, simplify.
     * okhttp & retrofit r better
     *
     * @param httpUrl
     *            :请求接口
     * @param httpArg
     *            :参数
     * @return 返回结果
     */
    public static String request(String httpUrl, String httpArg) {
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?" + httpArg;

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("apikey", Constants.API_KEY);
            connection.connect();
            InputStream is = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
