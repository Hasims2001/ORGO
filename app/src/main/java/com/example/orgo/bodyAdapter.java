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
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;


public class bodyAdapter extends ArrayAdapter<String> {

    private String[] dropheading;

    private  String[] droptxt;

    private  Context context;




    public bodyAdapter(Context context, int resource, String[] dropheading, String[] droptxt) {
        super(context, resource, dropheading);
        this.context = context;
        this.dropheading = dropheading;
        this.droptxt = droptxt;

    }

    @Nullable
    public String head(int position) {
        return dropheading[position];
    }



    @Nullable
    public String txt(int position){
        return droptxt[position];
    }






    @Override
    public View getView(int position, @Nullable  View convertView, @Nullable ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.organztn_layout, parent, false);



        TextView dropheading = convertView.findViewById(R.id.drop_one);
        dropheading.setText(head(position));

        TextView loc = convertView.findViewById(R.id.droploc_one);
        loc.setVisibility(View.GONE);

        TextView droptxt = convertView.findViewById(R.id.droptxt_one);
        droptxt.setText(txt(position));

        TextView dropphone1 = convertView.findViewById(R.id.dropphone1_one);
        dropphone1.setVisibility(View.GONE);

        TextView dropmail = convertView.findViewById(R.id.dropmail_one);
        dropmail.setVisibility(View.GONE);

        CardView card_one = convertView.findViewById(R.id.card);
        card_one.setRadius(0);
        card_one.setCardElevation(0);
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

            }
        });
        return convertView;


    }




}
