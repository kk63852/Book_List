package com.example.lenovo.test_application;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Books_adapter extends ArrayAdapter<books> {
    public Books_adapter(Activity context, ArrayList<books> books){
        super(context,0,books);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.books_list, parent, false);
        }
        books currentBook=getItem(position);
        TextView textView=listItemView.findViewById(R.id.editText);
        String bookName=currentBook.getBookTitle();
        textView.setText(bookName);
        TextView textView1=listItemView.findViewById(R.id.editText2);
        String authorName=currentBook.getAuthorName();
        ImageView imageView=listItemView.findViewById(R.id.imageView);
        textView1.setText(authorName);
        Glide.with(getContext())
                .load(currentBook.getImageSource())
                .into(imageView);


        return listItemView;
    }
}
