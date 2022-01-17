package com.example.orgo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;

public class contactAdapter extends ArrayAdapter<String> {

    private String[] ins_name;

    private  String[] ins_address;

    private String[] ins_phoneno;

    private  String[] ins_email;

    private String[] ins_website;

//    private  String[] per_name;
//
//    private  String[] per_phone;
//
//    private  String[] per_email;

    private  Context context;


//    String[] per_name, String[] per_phone, String[] per_email
    public contactAdapter(@NonNull Context context, int resource, String[] ins_name, String[] ins_address, String[] ins_phoneno, String[] ins_email, String[] ins_website) {
        super(context, resource, ins_name);

        this.context = context;
        this.ins_name = ins_name;
        this.ins_address = ins_address;
        this.ins_phoneno = ins_phoneno;
        this.ins_email = ins_email;
        this.ins_website = ins_website;
//        this.per_name = per_name;
//        this.per_phone= per_phone;
//        this.per_email= per_email;


    }

    @Nullable
    public String names(int position) {
        return ins_name[position];
    }

    @Nullable
    public String address(int position){
        return ins_address[position];
    }

    @Nullable
    public String phone(int position) {
        return ins_phoneno[position];
    }

    @Nullable
    public String emails(int position){
        return ins_email[position];
    }

    @Nullable
    public String websites(int position) {
        return ins_website[position];
    }


//    @Nullable
//    public String names(int position) {
//        return per_name[position];
//    }
//
//    @Nullable
//    public String phone(int position) {
//        return per_phone[position];
//    }
//
//    @Nullable
//    public String emails(int position){
//        return per_email[position];
//    }

    @Override
    public View getView(int position, @Nullable  View convertView, @Nullable ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.contact_layout, parent, false);

        TextView institute_name = convertView.findViewById(R.id.institute_name);
        institute_name.setText(names(position));


        TextView output_add = convertView.findViewById(R.id.output_add);
        output_add.setText(address(position));


        TextView output_phone = convertView.findViewById(R.id.output_phone);
        output_phone.setText(phone(position));


        TextView output_email = convertView.findViewById(R.id.output_email);
        output_email.setText(emails(position));


        TextView output_website = convertView.findViewById(R.id.output_website);
        output_website.setText(websites(position));


//        TextView output_name =  convertView.findViewById(R.id.output_name);
////        output_name.setText(per_name);
//        output_name.setText(names(position));
//
//        TextView output_per_phone =  convertView.findViewById(R.id.output_per_phone);
////        output_per_phone.setText(per_phone);
//        output_per_phone.setText(phone(position));
//
//        TextView output_per_email =  convertView.findViewById(R.id.output_per_email);
////        output_per_email.setText(per_email);
//        output_per_email.setText(emails(position));


        return convertView;
    }

}
