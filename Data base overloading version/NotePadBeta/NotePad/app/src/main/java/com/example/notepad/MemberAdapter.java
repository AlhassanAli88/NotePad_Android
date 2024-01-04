package com.example.notepad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class MemberAdapter extends ArrayAdapter<Anteckningar> {

    public MemberAdapter (Context context, List<Anteckningar> anteckningars) {super(context, 0,anteckningars);}

    @NonNull
    @Override

    public View getView(int position, View convertView, ViewGroup parent){

        View listItemView = convertView;

        if(listItemView == null){

            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.listitem_member, parent, false);

        }

        Anteckningar currentAnteckningar = getItem(position);


        TextView titelTextView = listItemView.findViewById(R.id.listitem_card_tv);
        titelTextView.setText(currentAnteckningar.getTitle());

        return listItemView;
    }
}
