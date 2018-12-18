package com.example.asus.spicynoodle;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.spicynoodle.R;
import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder {
    View mView;
    public ViewHolder(View itemView) {
        super(itemView);
        mView=itemView;
    }



    public void setDetail(Context context,String tittle,String url,String description,Double price){
        TextView mTittle =mView.findViewById(R.id.tittle);
        TextView mPrice =mView.findViewById(R.id.price);
        TextView mDescription =mView.findViewById(R.id.description);
        ImageView mUrl=mView.findViewById(R.id.url);

        mTittle.setText(tittle);
        mPrice.setText(price.toString());
        mDescription.setText(description);
//        Log.d("t", price.toString());
        Picasso.get().load(url).into(mUrl);

    }
}
