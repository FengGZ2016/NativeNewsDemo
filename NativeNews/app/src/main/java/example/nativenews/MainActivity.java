package example.nativenews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.ByteArrayInputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

import static example.nativenews.R.id.loading;
import static example.nativenews.R.id.lv_news;

public class MainActivity extends AppCompatActivity {


    @BindView(loading)
    LinearLayout mLoading;
    @BindView(lv_news)
    ListView mLvNews;

    private List<NewsInfo> mNewsInfoList;
    private NewsAdapter mNewsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        initListView();

    }

    private void initData() {
        //使用AsyncHttpClient访问网络
        //创建AsyncHttpClient实例
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        //使用GET请求
        asyncHttpClient.get(MainActivity.this, getString(R.string.serverurl), new AsyncHttpResponseHandler() {
            //请求成功AsyncHttpResponseHandler
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                //将Byte数组转换成输入流
                ByteArrayInputStream bais = new ByteArrayInputStream(
                        responseBody);
                //调用NewsInfoService工具类解析xml文件
                mNewsInfoList=ServiceUtil.getNewsInfo(bais);

            }

            //请求失败
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("error",MainActivity.this.getString(R.string.serverurl));
                Log.e("error", error.toString());
                Toast.makeText(MainActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }




    private void initListView() {
        if (mNewsInfoList == null) {
            // 解析失败 弹出toast
            Toast.makeText(this, "数据解析失败", Toast.LENGTH_SHORT).show();
        } else {
            // 更新界面.
            mLoading.setVisibility(View.INVISIBLE);
            mNewsAdapter=new NewsAdapter(this,mNewsInfoList);
            mLvNews.setAdapter(mNewsAdapter);
        }

    }
}
