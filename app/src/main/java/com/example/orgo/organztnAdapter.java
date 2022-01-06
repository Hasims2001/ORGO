package com.example.orgo;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;


public class organztnAdapter extends ArrayAdapter<String> {

    private String[] dropheading;
    private  String[] droplocation;
    private  String[] droptxt;
    private  String[] dropphone1;
    private String[] dropmail;
    private  Context context;






    public organztnAdapter(Context context, int resource, String[] dropheading, String[] droplocation, String[] droptxt, String[] dropphone1, String[] dropmail) {
        super(context, resource, dropheading);
        this.context = context;
        this.dropheading = dropheading;
        this.droplocation = droplocation;
        this.droptxt = droptxt;
        this.dropphone1 = dropphone1;
        this.dropmail = dropmail;
    }





    @Nullable
    public String head(int position) {
        return dropheading[position];
    }

    @Nullable
    public String loc(int position){
        return droplocation[position];
    }

    @Nullable
    public String txt(int position){
        return droptxt[position];
    }

    @Nullable
    public String phone(int position){
        return dropphone1[position];
    }
    @Nullable
    public String mail(int position){
        return dropmail[position];
    }






    @Override
    public View getView(int position, @Nullable View convertView, @Nullable ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.organztn_layout, parent, false);



            TextView dropheading = convertView.findViewById(R.id.drop_one);
            dropheading.setText(head(position));

            TextView droplocation = convertView.findViewById(R.id.droploc_one);
            droplocation.setText(loc(position));

            TextView droptxt = convertView.findViewById(R.id.droptxt_one);
            droptxt.setText(txt(position));

            TextView dropphone1 = convertView.findViewById(R.id.dropphone1_one);
//        dropphone1.setText(phone(position));
            SpannableString phoneno = new SpannableString(phone(position));
            phoneno.setSpan(new UnderlineSpan(), 0, phoneno.length(), 0);
            dropphone1.setText(phoneno);

            TextView dropmail = convertView.findViewById(R.id.dropmail_one);
//        dropmail.setText(mail(position));
            SpannableString mails = new SpannableString(mail(position));
            mails.setSpan(new UnderlineSpan(), 0, mails.length(), 0);
            dropmail.setText(mails);

            LinearLayout des_one = convertView.findViewById(R.id.des_one);
            LinearLayout layout_one = convertView.findViewById(R.id.layout_one);
            ImageView img_one = convertView.findViewById(R.id.img_one);
            ImageView one_img = convertView.findViewById(R.id.one_img);


            convertView.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onClick(View v) {
                    int z = (des_one.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE;
                    TransitionManager.beginDelayedTransition(layout_one, new AutoTransition());

                    des_one.setVisibility(z);

                    if (img_one.getVisibility() == View.VISIBLE) {
                        img_one.setVisibility(View.INVISIBLE);
                        one_img.setVisibility(View.VISIBLE);
                    } else if (one_img.getVisibility() == View.VISIBLE) {
                        one_img.setVisibility(View.INVISIBLE);
                        img_one.setVisibility(View.VISIBLE);
                    }
                    if (position == 8) {
                        dropphone1.setText(phone(position));
                    }
                    if (position == 10) {
                        dropmail.setText(mail(position));
                    }
                    if (position == 13) {
                        dropphone1.setText(phone(position));
                        dropmail.setText(mail(position));
//                    SpannableString mails = new SpannableString(mail(position));
//                    mails.setSpan(new StyleSpan(Typeface.ITALIC), 0, mails.length(), 0);
//                    dropmail.setText(mails);
                    }
                }
            });
            return convertView;


    }




}
