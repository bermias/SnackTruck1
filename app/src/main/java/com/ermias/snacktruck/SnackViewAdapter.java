package com.ermias.snacktruck;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class SnackViewAdapter extends ArrayAdapter<SnackClass> {

 public SnackViewAdapter(Context context, ArrayList<SnackClass> snackClasses){
     super(context,0,snackClasses);
 }


public View getView(final int position, View view, ViewGroup parent) {

       SnackClass sn=getItem(position);
        if (view==null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.check_list, parent,
                    false);

        }

        int red1=view.getResources().getColor(R.color.colorNonVeg),
                green1=view.getResources().getColor(R.color.colorVeg);

        try {
            CheckedTextView  checkedTextView=(CheckedTextView) view.findViewById(R.id.check_text);
            checkedTextView.setText(sn.get_snackName());
            if(sn.get_snackType().contains("Non") ){
                checkedTextView.setTextColor(red1);
            }
            else {
                checkedTextView.setTextColor(green1);
            }
        }catch(NullPointerException ex){}

        return view;
}





}

