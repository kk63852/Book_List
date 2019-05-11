package com.example.lenovo.test_application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
    setUI();
    }
    public void setUI(){
        Bundle bundle = getIntent().getExtras();
        ImageView imageView= findViewById(R.id.imageView3);
        Glide.with(getApplicationContext())
                .load(bundle.getString("image"))
                .into(imageView);
        TextView title=findViewById(R.id.textView6);
        title.setText(bundle.getString("title"));
        TextView author=findViewById(R.id.textView8);
        author.setText(bundle.getString("name"));
    }
}
