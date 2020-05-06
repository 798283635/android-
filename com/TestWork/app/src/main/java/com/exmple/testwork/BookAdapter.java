package com.exmple.testwork;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by asus on 2019/9/6.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    private List<Book> mBookList;
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView bookImage;
        TextView bookName;
        public ViewHolder(View view) {
            super(view);
            bookImage = (ImageView) view.findViewById(R.id.book_image);
           // bookName = (TextView) view.findViewById(R.id.book_name);
        }
    }
    public BookAdapter(List<Book> fruitList) {
        mBookList = fruitList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder; }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Book fruit = mBookList.get(position);
        holder.bookImage.setImageResource(fruit.getImageId());
        //holder.bookName.setText(fruit.getName());
    }
    @Override
    public int getItemCount() {
        return mBookList.size();
    }

}

