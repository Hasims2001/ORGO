package com.example.orgo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class person_contactAdapter extends ArrayAdapter<String> {

    private  String[] per_name;

    private  String[] per_phone;

    private  String[] per_email;

    private  Context context;

    public person_contactAdapter(@NonNull Context context, int resource, @NonNull String[] per_name, String[] per_phone, String[] per_email) {
        super(context, resource, per_name);

        this.context = context;
        this.per_name = per_name;
        this.per_phone= per_phone;
        this.per_email= per_email;

    }


    @Nullable
    public String names(int position) {
        return per_name[position];
    }

    @Nullable
    public String phones(int position) {
        return per_phone[position];
    }

    @Nullable
    public String emails(int position){
        return per_email[position];
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.person_contact_layout, parent, false);

        TextView output_name =  convertView.findViewById(R.id.output_name);
//        output_name.setText(per_name);
        output_name.setText(names(position));

        TextView output_per_phone =  convertView.findViewById(R.id.output_per_phone);
//        output_per_phone.setText(per_phone);
        output_per_phone.setText(phones(position));

        TextView output_per_email =  convertView.findViewById(R.id.output_per_email);
//        output_per_email.setText(per_email);
        output_per_email.setText(emails(position));
        return convertView;
    }
}
