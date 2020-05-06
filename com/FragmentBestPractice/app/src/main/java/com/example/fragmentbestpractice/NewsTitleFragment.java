package com.example.fragmentbestpractice;

/**
 * Created by asus on 2019/8/7.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewsTitleFragment extends Fragment {

    private boolean isTwoPane;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_title_frag, container, false);
        RecyclerView newsTitleRecyclerView = (RecyclerView) view.findViewById(R.id.news_title_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        newsTitleRecyclerView.setLayoutManager(layoutManager);
        NewsAdapter adapter = new NewsAdapter(getNews());
        newsTitleRecyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.news_content_layout) != null) {
            isTwoPane = true; // 鍙互鎵惧埌news_content_layout甯冨眬鏃讹紝涓哄弻椤垫ā寮?
        } else {
            isTwoPane = false; // 鎵句笉鍒皀ews_content_layout甯冨眬鏃讹紝涓哄崟椤垫ā寮?
        }
    }

    private List<News> getNews() {
        List<News> newsList = new ArrayList<>();



        News news = new News();
        news.setTitle("散文随笔");
        news.setContent(getRandomLengthContent("This is news content "));
        newsList.add(news);

        news = new News();
        news.setTitle("日记书信");
        news.setContent(getRandomLengthContent("This is news content "));
        newsList.add(news);

        news = new News();
        news.setTitle("寓言童话");
        news.setContent(getRandomLengthContent("This is news content "));
        newsList.add(news);

        news = new News();
        news.setTitle("新闻媒体");
        news.setContent(getRandomLengthContent("This is news content "));
        newsList.add(news);

        news = new News();
        news.setTitle("视频娱乐");
        news.setContent(getRandomLengthContent("This is news content "));
        newsList.add(news);

        news = new News();
        news.setTitle("演讲访谈");
        news.setContent(getRandomLengthContent("This is news content "));
        newsList.add(news);


        news = new News();
        news.setTitle("漫画");
        news.setContent(getRandomLengthContent("This is news content "));
        newsList.add(news);

        news = new News();
        news.setTitle("杂志期刊");
        news.setContent(getRandomLengthContent("This is news content "));
        newsList.add(news);

        news = new News();
        news.setTitle("大数据");
        news.setContent(getRandomLengthContent("This is news content "));
        newsList.add(news);

        news = new News();
        news.setTitle("互联网");
        news.setContent(getRandomLengthContent("This is news content "));
        newsList.add(news);

        news = new News();
        news.setTitle("软件设计");
        news.setContent(getRandomLengthContent("This is news content "));
        newsList.add(news);

        news = new News();
        news.setTitle("移动开发");
        news.setContent(getRandomLengthContent("This is news content "));
        newsList.add(news);

        news = new News();
        news.setTitle("编程语言");
        news.setContent(getRandomLengthContent("This is news content "));
        newsList.add(news);

        news = new News();
        news.setTitle("网络安全");
        news.setContent(getRandomLengthContent("This is news content "));
        newsList.add(news);

        news = new News();
        news.setTitle("硬件开发");
        news.setContent(getRandomLengthContent("This is news content "));
        newsList.add(news);

        return newsList;
    }

    private String getRandomLengthContent(String content) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(content);
        }
        return builder.toString();
    }

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

        private List<News> mNewsList;

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView newsTitleText;

            public ViewHolder(View view) {
                super(view);
                newsTitleText = (TextView) view.findViewById(R.id.news_title);
            }

        }

        public NewsAdapter(List<News> newsList) {
            mNewsList = newsList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
            final ViewHolder holder = new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    News news = mNewsList.get(holder.getAdapterPosition());
                    if (isTwoPane) {
                        NewsContentFragment newsContentFragment = (NewsContentFragment)
                                getFragmentManager().findFragmentById(R.id.news_content_fragment);
                        newsContentFragment.refresh(news.getTitle(), news.getContent());
                    } else {
                        NewsContentActivity.actionStart(getActivity(), news.getTitle(), news.getContent());
                    }
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            News news = mNewsList.get(position);
            holder.newsTitleText.setText(news.getTitle());
        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }

    }

}