package com.example.pharmacyproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Adapterclass extends ArrayAdapter {
    public Adapterclass(@NonNull Context context, int resource, ArrayList<Dataclass> info) {
        super(context, resource,info);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.itemlistview,parent,false);
        }
        Dataclass data = (Dataclass) getItem(position);
        if(data != null){
            TextView med = convertView.findViewById(R.id.mednamelv);
            med.setText(data.getMedicinename());
            TextView qua = convertView.findViewById(R.id.quantitylv);
            qua.setText(data.getQuantity());
        }
        return convertView;
    }
}
