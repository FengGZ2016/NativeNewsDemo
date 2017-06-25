package example.nativenews;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import loopj.android.image.SmartImageView;

import static example.nativenews.R.id.tv_description;
import static example.nativenews.R.id.tv_title;
import static example.nativenews.R.id.tv_type;

/**
 * 作者：国富小哥
 * 日期：2017/6/25
 * Created by Administrator
 */

public class NewsAdapter extends BaseAdapter{

    private List<NewsInfo> mNewsInfoList;
    private Context mContext;

    public NewsAdapter(Context mContext,List<NewsInfo> mNewsInfoList){
        this.mContext=mContext;
        this.mNewsInfoList=mNewsInfoList;
    }

    @Override
    public int getCount() {
        return mNewsInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return mNewsInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewsInfo newsInfo= (NewsInfo) getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView==null){
            view= LayoutInflater.from(mContext).inflate(R.layout.news_item,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.mSmartImageView= (SmartImageView) view.findViewById(R.id.siv_icon);
            viewHolder.mTextView_title= (TextView) view.findViewById(tv_title);
            viewHolder.mTextView_description= (TextView) view.findViewById(tv_description);
            viewHolder.mTextView_type= (TextView) view.findViewById(tv_type);
            //将viewHolder存储在view中
            view.setTag(viewHolder);
        }else {
            view=convertView;
            //重新获取ViewHolder
            viewHolder= (ViewHolder) view.getTag();
        }
        //绑定
        //SmartImageView加载指定路径图片(1,要加载的图片 2，加载失败时显示的图片 3，正在加载时显示的图片)
        viewHolder.mSmartImageView.setImageUrl(newsInfo.getIconPath(), R.mipmap.ic_launcher,
                R.mipmap.ic_launcher);
        //设置新闻标题
        viewHolder.mTextView_title.setText(newsInfo.getTitle());
        //设置新闻描述
        viewHolder.mTextView_description.setText(newsInfo.getDescription());
        // 1. 一般新闻 2.专题 3.live
        int type = newsInfo.getType();
        //不同新闻类型设置不同的颜色和不同的内容
        switch (type) {
            case 1:
                viewHolder.mTextView_type.setText("新闻:" + newsInfo.getComment());
                break;
            case 2:
                viewHolder.mTextView_type.setTextColor(Color.RED);
                viewHolder.mTextView_type.setText("专题");
                break;
            case 3:
                viewHolder.mTextView_type.setTextColor(Color.BLUE);
                viewHolder.mTextView_type.setText("LIVE");
                break;
        }
        return view;
    }

    class ViewHolder{
        SmartImageView mSmartImageView;
        TextView mTextView_title;
        TextView mTextView_description;
        TextView mTextView_type;
    }
}
