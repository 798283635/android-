package com.example.qushenghuo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsActivity extends AppCompatActivity {

    public class UrlUtil  {
        //获取频道的网络接口
        public  static final String channelUrl = "http://apis.baidu.com/showapi_open_bus/channel_news/channel_news";

        public  static final String newsUrl = "http://apis.baidu.com/showapi_open_bus/channel_news/search_news";

    }
    private Spinner channel;
    private TextView show;
    private SimpleAdapter sa;
    private List<Map<String,String>> channelList;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        channel = (Spinner) findViewById(R.id.channel);
        show = (TextView) findViewById(R.id.news);
        channelList = new ArrayList<>();
        sa = new SimpleAdapter(this,channelList,
                android.R.layout.simple_list_item_1,

                new String[]{"name"},new int[]{android.R.id.text1});
        channel.setAdapter(sa);
        String httpUrl = UrlUtil.channelUrl;
        new GetNewsChannel().execute(httpUrl);

        //Spinner监听
        channel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Map map = channelList.get(position);
                String channelName = (String) map.get("name");
                String channelId = (String) map.get(channelName);
                String[] strings = new String[]{channelId,channelName};
                new GetNews().execute(strings);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    //获取新闻频道
    public class GetNewsChannel extends AsyncTask<String,Void,String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
           // return HttpUtil.HttpGet(strings[0]);
        }

        //在UI线程(主线程)中执行命令
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s.equals("")){
                Toast.makeText(getBaseContext(),"没有数据", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                JSONObject obj = new JSONObject(s);
                JSONObject body = obj.getJSONObject("showapi_res_body");
                JSONArray ja = body.getJSONArray("channelList");
                for(int i = 0;i<ja.length();i++){
                    JSONObject channelObj = (JSONObject) ja.get(i);
                    String id = channelObj.getString("channelId");
                    String name = channelObj.getString("name");
                    Map map = new HashMap();
                    map.put("name",name);
                    map.put(name,id);
                    channelList.add(map);
                }
                sa.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        public void execute(String httpUrl) {
        }
    }

    //获取新闻内容
    public class GetNews extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... params) {
            String httpUrl = UrlUtil.newsUrl;
            String httpArg = "channelId="+params[0]+"&" +
                    "channelName="+params[1]+"&" +
                    "title=&" +
                    "page=1&" +
                    "needContent=1&" +
                    "needHtml=1";
            String jsonResult = httpUrl + "?" + httpArg;
            return jsonResult;
           // return httpUtil.HttpGet(jsonResult);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s.equals("")){
                show.setText("没有数据或无网络连接");
            }else {
                jsonHandle(s);
            }
        }

    }

    //解析Json
    public void jsonHandle(String s){
        InputStream is ;
        BufferedReader reader = null;
        StringBuilder sbd = new StringBuilder();
        try {
            JSONObject obj = new JSONObject(s);
            JSONObject body = obj.getJSONObject("showapi_res_body");
            JSONObject body2 = body.getJSONObject("pagebean");
            JSONArray ja = body2.getJSONArray("contentlist");
            for(int i = 0;i<ja.length();i++){
                JSONObject newsObj = (JSONObject) ja.get(i);
                String news1 = newsObj.getString("content");
                sbd.append(news1);
                sbd.append("\t\n");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        show.setText(sbd.toString());
    }

}
