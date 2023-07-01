package com.anuar.chatapp;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.List;

public class messageAdapter extends ArrayAdapter<message> {

    public messageAdapter(@NonNull Context context, int resource, List<message> messageList) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            convertView=((Activity) getContext()).getLayoutInflater().inflate(R.layout.message_item,parent,false);
        }
        ImageView imageView=convertView.findViewById(R.id.imageView);
        TextView textView=convertView.findViewById(R.id.textTextView);
        TextView nameTextView=convertView.findViewById(R.id.nameTextView);

        message message=getItem(position);
        boolean isText=message.getImageUrl()==null;
        if(isText){
            textView.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.GONE);
            textView.setText(message.getText());
        }
        else{
            textView.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
            Glide.with(imageView.getContext()).load(message.getImageUrl()).into(imageView);
        }
        nameTextView.setText(message.getName());
        return convertView;

    }
}
