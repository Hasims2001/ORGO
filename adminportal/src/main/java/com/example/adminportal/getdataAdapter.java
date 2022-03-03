package com.example.adminportal;

import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;





public class getdataAdapter extends ArrayAdapter<String> {
    private String[] str_username;
    private String[] str_usermail;
    private Context mcontext;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference userref;
    String pressed;

    public getdataAdapter(Context context, int resource, String[] str_username, String[] str_usermail) {
        super(context, resource, str_username);


//        strone = new String[username.size()];
//        for (int i = 0; i < username.size(); i++) {
//            strone[i] = username.get(i);
//        }
//        strtwo = new String[usermail.size()];
//        for (int i = 0; i < usermail.size(); i++) {
//            strtwo[i] = usermail.get(i);
//        }
        this.mcontext = context;
        this.str_username = str_username;
        this.str_usermail = str_usermail;


    }
    @Nullable
    public String names(int position) {
        return str_username[position];
    }
    @Nullable
    public String mails(int position) {
        return str_usermail[position];
    }


    @Override
    public View getView(int position, @Nullable View convertView, @Nullable ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.data_layout, parent, false);


        TextView username_txt = convertView.findViewById(R.id.username_txt);
        username_txt.setText(names(position));

        TextView usermail_txt = convertView.findViewById(R.id.usermail_txt);
        usermail_txt.setText(mails(position));




        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed = names(position);


                if (mcontext instanceof admin_data){
                    Intent intent = new Intent(getContext(), admin_data.class);
                    intent.putExtra("clicked", pressed);
                    mcontext.startActivity(intent);
                } else if (mcontext instanceof user_data){
                    Intent intent = new Intent(getContext(), user_data.class);
                    intent.putExtra("clicked", pressed);
                    mcontext.startActivity(intent);
                } else if (mcontext instanceof donor_info) {
                    Intent intent = new Intent(getContext(), donor_info.class);
                    intent.putExtra("clicked", pressed);
                    mcontext.startActivity(intent);
                } else if (mcontext instanceof recipient_info) {
                    Intent intent = new Intent(getContext(), recipient_info.class);
                    intent.putExtra("clicked", pressed);
                    mcontext.startActivity(intent);
                }



            }
        });
        return convertView;


    }


}
