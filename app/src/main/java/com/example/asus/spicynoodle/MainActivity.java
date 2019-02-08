package com.example.asus.spicynoodle;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {
    RecyclerView mrecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolbar);
        setTitle("List All of Asian Noodle");
        setSupportActionBar(toolbar);
//        toolbar.setSubtitle("");
        mrecyclerView=findViewById(R.id.recyclerview);
        mrecyclerView.setHasFixedSize(true);

        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mFirebaseDatabase =FirebaseDatabase.getInstance();

        mDatabaseReference = mFirebaseDatabase.getReference("item");

        //load data into recyclerview
        FirebaseRecyclerAdapter<Model,ViewHolder> firebaseRecyclerAdapter=
                new FirebaseRecyclerAdapter<Model,ViewHolder>(
                        Model.class,
                        R.layout.row,
                        ViewHolder.class,
                        mDatabaseReference
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Model model, int position) {
                        viewHolder.setDetail(getApplicationContext(),model.getTittle(),model.getUrl(),model.getDescription(),model.getPrice());
                    }

                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                        ViewHolder viewHolder=super.onCreateViewHolder(parent,viewType);
                        viewHolder.setOnClickListener(new ViewHolder.clickListener() {

                            @Override
                            public void onItemClick(View view, int position) {
                                TextView mTittle =findViewById(R.id.tittle);
                                TextView mDescription=findViewById(R.id.description);
                                TextView mPrice =findViewById(R.id.price);
                                ImageView mUrl =findViewById(R.id.url);

                                String tittle=mTittle.getText().toString();
                                String des =mDescription.getText().toString();
                                String price =mPrice.getText().toString();

                                Drawable url=mUrl.getDrawable();
                                Bitmap bitmap=((BitmapDrawable)url).getBitmap();

                                Intent intent=new Intent(view.getContext(),ItemDetailActivity.class);
                                ByteArrayOutputStream stream= new ByteArrayOutputStream();
                                bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                                byte[] bytes=stream.toByteArray();
                                intent.putExtra("tittle",tittle);
                                intent.putExtra("description",des);
                                intent.putExtra("price",price);
                                intent.putExtra("url",bytes);
                                startActivity(intent);
                            }

                            @Override
                            public void onItemLongClick(View view, int position) {

                            }


                        });
                        return viewHolder;
                    }
                };
        mrecyclerView.setAdapter(firebaseRecyclerAdapter);
    }
    private void firebaseSearch(String searchText){
        Query firebaseSearchQuery =mDatabaseReference.orderByChild("tittle").startAt(searchText).endAt(searchText +"\uf8ff");
        FirebaseRecyclerAdapter<Model,ViewHolder>firebaseRecyclerAdapter=
                new FirebaseRecyclerAdapter<Model, ViewHolder>(
                        Model.class,
                        R.layout.row,
                        ViewHolder.class,
                        firebaseSearchQuery
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Model model, int position) {
                        viewHolder.setDetail(getApplicationContext(),model.getTittle(),model.getUrl(),model.getDescription(),model.getPrice());
                    }
                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                        ViewHolder viewHolder=super.onCreateViewHolder(parent,viewType);
                        viewHolder.setOnClickListener(new ViewHolder.clickListener() {


                            @Override
                            public void onItemClick(View view, int position) {
                                TextView mTittle =findViewById(R.id.tittle);
                                TextView mDescription=findViewById(R.id.description);
                                TextView mPrice =findViewById(R.id.price);
                                ImageView mUrl =findViewById(R.id.url);

                                String tittle=mTittle.getText().toString();
                                String des =mDescription.getText().toString();
                                String price =mPrice.getText().toString();
                                Drawable url=mUrl.getDrawable();
                                Bitmap bitmap=((BitmapDrawable)url).getBitmap();

                                Intent intent=new Intent(view.getContext(),ItemDetailActivity.class);
                                ByteArrayOutputStream stream= new ByteArrayOutputStream();
                                bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                                byte[] bytes=stream.toByteArray();
                                intent.putExtra("tittle",tittle);
                                intent.putExtra("description",des);
                                intent.putExtra("price",price);
                                intent.putExtra("url",bytes);
                                startActivity(intent);
                            }

                            @Override
                            public void onItemLongClick(View view, int position) {

                            }

                        });
                        return viewHolder;
                    }

                };
        mrecyclerView.setAdapter(firebaseRecyclerAdapter);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu,menu);
        MenuItem item =menu.findItem(R.id.action_search);
        SearchView searchView =(SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                firebaseSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                firebaseSearch(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
//        if(id==R.id.action_back){
//            startActivity(new Intent(this,MainActivity.class));
//        }
        if(id==R.id.action_search){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

}
