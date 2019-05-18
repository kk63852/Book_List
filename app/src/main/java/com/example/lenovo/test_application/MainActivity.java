package com.example.lenovo.test_application;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {
    private static final String murl = "http://www.mocky.io/v2/5cc008de310000440a035fdf";
    private Books_adapter books_adapter;
    private View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Bundle bundle = new Bundle();
            //find i
            int i=0;
            books currentbook=books_adapter.getItem(i);
            Intent intent=new Intent(getApplicationContext(),BookDetailsActivity.class);
            bundle.putString("title",currentbook.getBookTitle());
            bundle.putString("name",currentbook.getAuthorName());
            bundle.putString("image",currentbook.getImageSource());
            intent.putExtras(bundle);
            startActivity(intent);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView booksListView =  findViewById(R.id.list);

        books_adapter = new Books_adapter(this,new ArrayList<books>());

        booksListView.setAdapter(books_adapter);
        BooksAsyncTask booksAsyncTask=new BooksAsyncTask();
        booksAsyncTask.execute(murl);
        books_adapter.setOnClickListener(onClickListener);

    }
    private class BooksAsyncTask extends AsyncTask<String,Void,List<books>>{
        @Override
        protected void onPostExecute(List<books> data) {
           books_adapter.clear();
            if (data!=null && !data.isEmpty()){
               books_adapter.addAll(data);
            }
        }

        @Override
        protected List<books> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) {
                return null;
            } List<books> result=Query_utils.fetchBooksData(urls[0]);
            return result;
        }
    }
}
