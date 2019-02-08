package com.example.asus.spicynoodle;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.squareup.picasso.Picasso;

public class ItemDetailActivity extends AppCompatActivity {
    TextView mTittle ,mDescription,mPrice;
    ImageView mUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
//        ActionBar actionBar=getSupportActionBar();
////        actionBar.setTitle("Detail");
//        //tool bar on detail activity
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setDisplayShowHomeEnabled(true);
        mTittle=findViewById(R.id.Tittle);
        mDescription=findViewById(R.id.Description);
        mPrice=findViewById(R.id.Price);
        mUrl=findViewById(R.id.Url);

        //get data from intent
        byte[] bytes=getIntent().getByteArrayExtra("url");
        String tittle=getIntent().getStringExtra("tittle");

        String des=getIntent().getStringExtra("description");
        String price=getIntent().getStringExtra("price");
        Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        //set data to view
        mTittle.setText(tittle);
        mDescription.setText(des);
        mPrice.setText(price);
        mUrl.setImageBitmap(bitmap);

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
