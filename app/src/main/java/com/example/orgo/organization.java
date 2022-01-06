package com.example.orgo;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

@RequiresApi(api = Build.VERSION_CODES.N)
public class organization extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView txt_one,txt_two, txt_three, txt_four;
//    CardView card_one, card_two, card_three, card_four, card_five, card_six, card_seven, card_eight, card_nine, card_ten, card_eleven, card_twelve, card_thirteen, card_fourteen, card_fivteen, card_sixteen, card_seventeen, card_eighteen;
//    ImageView one_img, img_one, two_img, img_two, three_img, img_three, four_img, img_four, five_img, img_five, six_img, img_six, seven_img, img_seven,  eight_img, img_eight,nine_img, img_nine, ten_img, img_ten,eleven_img, img_eleven, twelve_img, img_twelve, thirteen_img, img_thirteen, fourteen_img, img_fourteen, fivteen_img, img_fivteen, sixteen_img, img_sixteen, seventeen_img, img_seventeen, eighteen_img, img_eighteen;
//    LinearLayout des_one, layout_one, des_two, layout_two, des_three, layout_three, des_four, layout_four, des_five, layout_five, des_six, layout_six, des_seven, layout_seven, des_eight, layout_eight, des_nine, layout_nine, des_ten, layout_ten, des_eleven, layout_eleven, des_twelve, layout_twelve, des_thirteen, layout_thirteen, des_fourteen, layout_fourteen, des_fivteen, layout_fivteen, des_sixteen, layout_sixteen, des_seventeen, layout_seventeen,des_eighteen, layout_eighteen;

    String[] dropheading = {"AIIMS JODHPUR (State Organ and Tissue Transplant Organization (SOTTO))", "ALL INDIA INSTITUTE OF MEDICAL SCIENCES (State Organ and Transplant Organization (SOTTO))", "ALL INDIA INSTITUTE OF MEDICAL SCIENCES (State Organ and Transplant Organization (SOTTO))", "ALL INDIA INSTITUTE OF MEDICAL SCIENCES PATNA (State Organ and Transplant Organization (SOTTO))", "ALL INDIA INSTITUTE OF MEDICAL SCIENCES BHOPAT (State Organ and Transplant Organization (SOTTO))", "ALL INDIA INSTITUTE OF MEDICAL SCIENCES RAIPUR (State Organ and Transplant Organization (SOTTO))", "Guwahati Medical College (Regional Organ and Tissue Transplant Organization (ROTTO))", "INSTITUTE OF POST GRADUATE MEDICAL EDUCATION &amp; RESEARCH (Regional Organ and Tissue Transplant Organization (ROTTO))", "JEEVANSARTHEKE", "JEEVANDAN", "KING EDWARD MEMORIAL HOSPITAL (Regional Organ and Tissue Transplant Organization (ROTTO))", "MRITHASA NJEEVANI-KNOS", "NATIONAL ORGAN AND TISSUE TRANSPLANT ORGANIZATION (NOTTO)", "POST GRADUATE INSTITUTE OF MEDICAL EDUCATION &amp; RESEARCH (Regional Organ and Tissue Transplant Organization (ROTTO))", "RAJASTHAN NETWORK FOR ORGAN SHARING (RNOS)", "SHER - I - KASHMIR INSTITUTE OF MEDICAL SCIENCES",  "TRANSPLANT AUTHORITY OF TAMIL NADU (TRANSTAN) (Regional Organ and Tissue Transplant Organization (ROTTO))"};
    String[] droplocation = {"Jodhpur","Rishikesh","Bhubaneswar","Patna","Bhopal", "Raipur", "Guwahati", "Kolkata", "Bengaluru", "Vijayawada", "Mumbai", "Trivandrum", "New Delhi", "Chandigarh", "Jainpur", "Srinagar", "Chennai"};
    String[] droptxt = {"Basni Industrial Area, Phase-2, Jodhpur, Rajasthan, 342005", "Virbhadra Road, Rishikesh, Uttarkhand, 249201", "Bhubaneswar, Sijua, Patrapada, Bhubaneswar, Odisha, 751019", "Phulwari Sharif, Patna, Bihar, 801505", "Saket Nagar, Bhopal,Madhya Pradesh, 462020",  "Great Eastern Rd, AIIMS Campus, Tatibandh, Raipur, Chhattisgarh, 492099", "GMCH Complex, GMC Hospital Rd, Bhangagarh, Guwahati, Assam, 781032", "C/o Director, IPGME&amp;R, 1st Floor, Administrative Block, 244 A.J.C. Bose Road, Kolkata, West Bengal, 700020", "Near OPD, NIMHANS, Hosur Road, Bengaluru, Karnataka, 560029", "Dr.NTR University of Health, Sciences Gunadala, Vijayawada, Andhra Pradesh, 520008", "Acharya Dhonde Marg, Parel, Mumbai, Maharashtra, 400012", "Deceased Donor Organ Transplantation Program, Super Speciality Block, Government Medical College Hospital, Trivandrum, Kerala", "4th Floor, National Institute of Pathology Building, Safdarjung Hospital Campus, New Delhi, Delhi NCR, 110029", "Research Block B, 6th Floor, Sector 12, Chandigarh, Chandigarh, 160012", "G-*, KJ Lon, Hospital Trimurti Circle, Gangwal Park, Rambag, Jaipur, Rajasthan, 302004", "Soura, Srinagar, Srinagar, Jammu and Kashmir, 190011", "1045, I Floor, Fourth Circle, Tamil Nadu, Government Multi Super Specialty Hospital, Omandurar Government Estate, Anna Salai, Chennai, Tamil Nadu, 600002"};
    String[] dropphone1 = {"+912912740741", "0135-2462510", "+916742476789", "+916122451923", "+917552970020",  "+917712572240", "+913612529457", "+913322041101", "Phone NO. : Not Available", "+918662451374", "02224107000", "+914712528386", "+911126164770", "Phone NO. : Not Available", "+919829300541", "+911942401013", "+914425363141" };
    String[] dropmail = {"aoadmin@aiimsjodhpur.edu.in", "rakeshkumar.aoaiims@gmail.com", "info@aiimsbhubaneswar.edu.in", "admin@aiimspatna.org", "admin@aiimsraipur.edu.in",  "director@aiimsraipur.edu.in", "gmch-asm@nic.in", "director.ipgmer@gmail.com", "zcckbangalore@gmail.com", "chairmanaactjeevandan-ap@nic.in", "Mail : Not Available", "principalmct@gmail.com", "dir@notto.nic.in", "Mail : Not Available", "rnoscadaverdonation@gmail.com", "contactus@skims.ac.in", "organstransplant@gmail.com"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization);

//        txt_one = findViewById(R.id.txtone);
//        txt_two = findViewById(R.id.txttwo);
//        txt_three = findViewById(R.id.txtthree);
//        txt_four = findViewById(R.id.txtfour);

        drawerLayout = findViewById(R.id.donatebody);
        navigationView = findViewById(R.id.sidemenubar);
        toolbar = findViewById(R.id.Tool);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.homebtn) {
                    Intent intent = new Intent(organization.this, dashboard.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.profilebtn) {
                    Toast.makeText(getApplicationContext(), "profile!", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (id == R.id.mythbtn) {
                    Toast.makeText(getApplicationContext(), "donar card!", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (id == R.id.aboutbtn) {
                    Toast.makeText(getApplicationContext(), "about orgo!", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (id == R.id.sharebtn) {
                    Intent myIntent = new Intent(Intent.ACTION_SEND);
                    myIntent.setType("text/plain");
                    String body = "Your body here";
                    String sub = "Your Subject";
                    myIntent.putExtra(Intent.EXTRA_SUBJECT,sub);
                    myIntent.putExtra(Intent.EXTRA_TEXT,body);
                    startActivity(Intent.createChooser(myIntent, "Share Using"));
                    return true;
                } else if (id == R.id.logoutbtn) {
                    Intent intent = new Intent(organization.this, MainActivity.class);
                    startActivity(intent);
                    return true;
                }

                return false;
            }
        });


        ListView listView = findViewById(R.id.listview);
        organztnAdapter orad = new organztnAdapter(this, R.layout.organztn_layout, dropheading, droplocation, droptxt, dropphone1, dropmail);
        listView.setAdapter(orad);


//        // card_one
//        card_one = findViewById(R.id.card_one);
//        img_one = findViewById(R.id.img_one);
//        one_img = findViewById(R.id.one_img);
//        des_one = findViewById(R.id.des_one);
//        layout_one = findViewById(R.id.layout_one);
//
//        card_one.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//            @Override
//            public void onClick(View v) {
//                int x = (des_one.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
//                TransitionManager.beginDelayedTransition(layout_one, new AutoTransition());
//
//                des_one.setVisibility(x);
//
//                if(img_one.getVisibility()==View.VISIBLE) {
//                    img_one.setVisibility(View.INVISIBLE);
//                    one_img.setVisibility(View.VISIBLE);
//                }
//                else if(one_img.getVisibility()==View.VISIBLE) {
//                    one_img.setVisibility(View.INVISIBLE);
//                    img_one.setVisibility(View.VISIBLE);
//                }
//            }
//        });
//
//
//        // card_two
//        card_two = findViewById(R.id.card_two);
//        img_two = findViewById(R.id.img_two);
//        two_img = findViewById(R.id.two_img);
//        des_two = findViewById(R.id.des_two);
//        layout_two = findViewById(R.id.layout_two);
//
//        card_two.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//            @Override
//            public void onClick(View v) {
//                int x = (des_two.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
//                TransitionManager.beginDelayedTransition(layout_two, new AutoTransition());
//
//                des_two.setVisibility(x);
//
//                if(img_two.getVisibility()==View.VISIBLE) {
//                    img_two.setVisibility(View.INVISIBLE);
//                    two_img.setVisibility(View.VISIBLE);
//                }
//                else if(two_img.getVisibility()==View.VISIBLE) {
//                    two_img.setVisibility(View.INVISIBLE);
//                    img_two.setVisibility(View.VISIBLE);
//                }
//            }
//        });
//
//        // card_three
//        card_three = findViewById(R.id.card_three);
//        img_three = findViewById(R.id.img_three);
//        three_img = findViewById(R.id.three_img);
//        des_three = findViewById(R.id.des_three);
//        layout_three = findViewById(R.id.layout_three);
//
//        card_three.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//            @Override
//            public void onClick(View v) {
//                int x = (des_three.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
//                TransitionManager.beginDelayedTransition(layout_three, new AutoTransition());
//
//                des_three.setVisibility(x);
//
//                if(img_three.getVisibility()==View.VISIBLE) {
//                    img_three.setVisibility(View.INVISIBLE);
//                    three_img.setVisibility(View.VISIBLE);
//                }
//                else if(three_img.getVisibility()==View.VISIBLE) {
//                    three_img.setVisibility(View.INVISIBLE);
//                    img_three.setVisibility(View.VISIBLE);
//                }
//            }
//        });
//
//        // card_four
//        card_four = findViewById(R.id.card_four);
//        img_four = findViewById(R.id.img_four);
//        four_img = findViewById(R.id.four_img);
//        des_four = findViewById(R.id.des_four);
//        layout_four = findViewById(R.id.layout_four);
//
//        card_four.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//            @Override
//            public void onClick(View v) {
//                int x = (des_four.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
//                TransitionManager.beginDelayedTransition(layout_four, new AutoTransition());
//
//                des_four.setVisibility(x);
//
//                if(img_four.getVisibility()==View.VISIBLE) {
//                    img_four.setVisibility(View.INVISIBLE);
//                    four_img.setVisibility(View.VISIBLE);
//                }
//                else if(four_img.getVisibility()==View.VISIBLE) {
//                    four_img.setVisibility(View.INVISIBLE);
//                    img_four.setVisibility(View.VISIBLE);
//                }
//            }
//        });
//
//        // card_five
//        card_five = findViewById(R.id.card_five);
//        img_five = findViewById(R.id.img_five);
//        five_img = findViewById(R.id.five_img);
//        des_five = findViewById(R.id.des_five);
//        layout_five = findViewById(R.id.layout_five);
//
//        card_five.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//            @Override
//            public void onClick(View v) {
//                int x = (des_five.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
//                TransitionManager.beginDelayedTransition(layout_five, new AutoTransition());
//
//                des_five.setVisibility(x);
//
//                if(img_five.getVisibility()==View.VISIBLE) {
//                    img_five.setVisibility(View.INVISIBLE);
//                    five_img.setVisibility(View.VISIBLE);
//                }
//                else if(five_img.getVisibility()==View.VISIBLE) {
//                    five_img.setVisibility(View.INVISIBLE);
//                    img_five.setVisibility(View.VISIBLE);
//                }
//            }
//        });
//
//
//        // card_six
//        card_six = findViewById(R.id.card_six);
//        img_six = findViewById(R.id.img_six);
//        six_img = findViewById(R.id.six_img);
//        des_six = findViewById(R.id.des_six);
//        layout_six = findViewById(R.id.layout_six);
//
//        card_six.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//            @Override
//            public void onClick(View v) {
//                int x = (des_six.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
//                TransitionManager.beginDelayedTransition(layout_six, new AutoTransition());
//
//                des_six.setVisibility(x);
//
//                if(img_six.getVisibility()==View.VISIBLE) {
//                    img_six.setVisibility(View.INVISIBLE);
//                    six_img.setVisibility(View.VISIBLE);
//                }
//                else if(six_img.getVisibility()==View.VISIBLE) {
//                    six_img.setVisibility(View.INVISIBLE);
//                    img_six.setVisibility(View.VISIBLE);
//                }
//            }
//        });
//
//
//        // card_seven
//        card_seven = findViewById(R.id.card_seven);
//        img_seven = findViewById(R.id.img_seven);
//        seven_img = findViewById(R.id.seven_img);
//        des_seven = findViewById(R.id.des_seven);
//        layout_seven = findViewById(R.id.layout_seven);
//
//        card_seven.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//            @Override
//            public void onClick(View v) {
//                int x = (des_seven.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
//                TransitionManager.beginDelayedTransition(layout_seven, new AutoTransition());
//
//                des_seven.setVisibility(x);
//
//                if(img_seven.getVisibility()==View.VISIBLE) {
//                    img_seven.setVisibility(View.INVISIBLE);
//                    seven_img.setVisibility(View.VISIBLE);
//                }
//                else if(seven_img.getVisibility()==View.VISIBLE) {
//                    seven_img.setVisibility(View.INVISIBLE);
//                    img_seven.setVisibility(View.VISIBLE);
//                }
//            }
//        });
//
//
//
//        // card_eight
//        card_eight = findViewById(R.id.card_eight);
//        img_eight = findViewById(R.id.img_eight);
//        eight_img = findViewById(R.id.eight_img);
//        des_eight = findViewById(R.id.des_eight);
//        layout_eight = findViewById(R.id.layout_eight);
//
//        card_eight.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//            @Override
//            public void onClick(View v) {
//                int x = (des_eight.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
//                TransitionManager.beginDelayedTransition(layout_eight, new AutoTransition());
//
//                des_eight.setVisibility(x);
//
//                if(img_eight.getVisibility()==View.VISIBLE) {
//                    img_eight.setVisibility(View.INVISIBLE);
//                    eight_img.setVisibility(View.VISIBLE);
//                }
//                else if(eight_img.getVisibility()==View.VISIBLE) {
//                    eight_img.setVisibility(View.INVISIBLE);
//                    img_eight.setVisibility(View.VISIBLE);
//                }
//            }
//        });
//
//
//        // card_nine
//        card_nine = findViewById(R.id.card_nine);
//        img_nine = findViewById(R.id.img_nine);
//        nine_img = findViewById(R.id.nine_img);
//        des_nine = findViewById(R.id.des_nine);
//        layout_nine = findViewById(R.id.layout_nine);
//
//        card_nine.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//            @Override
//            public void onClick(View v) {
//                int x = (des_nine.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
//                TransitionManager.beginDelayedTransition(layout_nine, new AutoTransition());
//
//                des_nine.setVisibility(x);
//
//                if(img_nine.getVisibility()==View.VISIBLE) {
//                    img_nine.setVisibility(View.INVISIBLE);
//                    nine_img.setVisibility(View.VISIBLE);
//                }
//                else if(nine_img.getVisibility()==View.VISIBLE) {
//                    nine_img.setVisibility(View.INVISIBLE);
//                    img_nine.setVisibility(View.VISIBLE);
//                }
//            }
//        });
//
//
//        // card_ten
//        card_ten = findViewById(R.id.card_ten);
//        img_ten = findViewById(R.id.img_ten);
//        ten_img = findViewById(R.id.ten_img);
//        des_ten = findViewById(R.id.des_ten);
//        layout_ten = findViewById(R.id.layout_ten);
//
//        card_ten.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//            @Override
//            public void onClick(View v) {
//                int x = (des_ten.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
//                TransitionManager.beginDelayedTransition(layout_ten, new AutoTransition());
//
//                des_ten.setVisibility(x);
//
//                if(img_ten.getVisibility()==View.VISIBLE) {
//                    img_ten.setVisibility(View.INVISIBLE);
//                    ten_img.setVisibility(View.VISIBLE);
//                }
//                else if(ten_img.getVisibility()==View.VISIBLE) {
//                    ten_img.setVisibility(View.INVISIBLE);
//                    img_ten.setVisibility(View.VISIBLE);
//                }
//            }
//        });
//
//
////         card_eleven
//        card_eleven = findViewById(R.id.card_eleven);
//        img_eleven = findViewById(R.id.img_eleven);
//        eleven_img = findViewById(R.id.eleven_img);
//        des_eleven = findViewById(R.id.des_eleven);
//        layout_eleven = findViewById(R.id.layout_eleven);
//
//        card_eleven.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//            @Override
//            public void onClick(View v) {
//                int x = (des_eleven.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
//                TransitionManager.beginDelayedTransition(layout_eleven, new AutoTransition());
//
//                des_eleven.setVisibility(x);
//
//                if(img_eleven.getVisibility()==View.VISIBLE) {
//                    img_eleven.setVisibility(View.INVISIBLE);
//                    eleven_img.setVisibility(View.VISIBLE);
//                }
//                else if(eleven_img.getVisibility()==View.VISIBLE) {
//                    eleven_img.setVisibility(View.INVISIBLE);
//                    img_eleven.setVisibility(View.VISIBLE);
//                }
//            }
//        });
//
//
//        // card_twelve
//        card_twelve = findViewById(R.id.card_twelve);
//        img_twelve = findViewById(R.id.img_twelve);
//        twelve_img = findViewById(R.id.twelve_img);
//        des_twelve = findViewById(R.id.des_twelve);
//        layout_twelve = findViewById(R.id.layout_twelve);
//
//        card_twelve.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//            @Override
//            public void onClick(View v) {
//                int x = (des_twelve.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
//                TransitionManager.beginDelayedTransition(layout_twelve, new AutoTransition());
//
//                des_twelve.setVisibility(x);
//
//                if(img_twelve.getVisibility()==View.VISIBLE) {
//                    img_twelve.setVisibility(View.INVISIBLE);
//                    twelve_img.setVisibility(View.VISIBLE);
//                }
//                else if(twelve_img.getVisibility()==View.VISIBLE) {
//                    twelve_img.setVisibility(View.INVISIBLE);
//                    img_twelve.setVisibility(View.VISIBLE);
//                }
//            }
//        });
//
//
//        // card_thirteen
//        card_thirteen = findViewById(R.id.card_thirteen);
//        img_thirteen = findViewById(R.id.img_thirteen);
//        thirteen_img = findViewById(R.id.thirteen_img);
//        des_thirteen = findViewById(R.id.des_thirteen);
//        layout_thirteen = findViewById(R.id.layout_thirteen);
//
//        card_thirteen.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//            @Override
//            public void onClick(View v) {
//                int x = (des_thirteen.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
//                TransitionManager.beginDelayedTransition(layout_thirteen, new AutoTransition());
//
//                des_thirteen.setVisibility(x);
//
//                if(img_thirteen.getVisibility()==View.VISIBLE) {
//                    img_thirteen.setVisibility(View.INVISIBLE);
//                    thirteen_img.setVisibility(View.VISIBLE);
//                }
//                else if(thirteen_img.getVisibility()==View.VISIBLE) {
//                    thirteen_img.setVisibility(View.INVISIBLE);
//                    img_thirteen.setVisibility(View.VISIBLE);
//                }
//            }
//        });
//
//
//
//        // card_fourteen
//        card_fourteen = findViewById(R.id.card_fourteen);
//        img_fourteen = findViewById(R.id.img_fourteen);
//        fourteen_img = findViewById(R.id.fourteen_img);
//        des_fourteen = findViewById(R.id.des_fourteen);
//        layout_fourteen = findViewById(R.id.layout_fourteen);
//
//        card_fourteen.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//            @Override
//            public void onClick(View v) {
//                int x = (des_fourteen.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
//                TransitionManager.beginDelayedTransition(layout_fourteen, new AutoTransition());
//
//                des_fourteen.setVisibility(x);
//
//                if(img_fourteen.getVisibility()==View.VISIBLE) {
//                    img_fourteen.setVisibility(View.INVISIBLE);
//                    fourteen_img.setVisibility(View.VISIBLE);
//                }
//                else if(fourteen_img.getVisibility()==View.VISIBLE) {
//                    fourteen_img.setVisibility(View.INVISIBLE);
//                    img_fourteen.setVisibility(View.VISIBLE);
//                }
//            }
//        });
//
//
//        // card_fivteen
//        card_fivteen = findViewById(R.id.card_fivteen);
//        img_fivteen = findViewById(R.id.img_fivteen);
//        fivteen_img = findViewById(R.id.fivteen_img);
//        des_fivteen = findViewById(R.id.des_fivteen);
//        layout_fivteen = findViewById(R.id.layout_fivteen);
//
//        card_fivteen.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//            @Override
//            public void onClick(View v) {
//                int x = (des_fivteen.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
//                TransitionManager.beginDelayedTransition(layout_fivteen, new AutoTransition());
//
//                des_fivteen.setVisibility(x);
//
//                if(img_fivteen.getVisibility()==View.VISIBLE) {
//                    img_fivteen.setVisibility(View.INVISIBLE);
//                    fivteen_img.setVisibility(View.VISIBLE);
//                }
//                else if(fivteen_img.getVisibility()==View.VISIBLE) {
//                    fivteen_img.setVisibility(View.INVISIBLE);
//                    img_fivteen.setVisibility(View.VISIBLE);
//                }
//            }
//        });
//
//        // card_sixteen
//        card_sixteen = findViewById(R.id.card_sixteen);
//        img_sixteen = findViewById(R.id.img_sixteen);
//        sixteen_img = findViewById(R.id.sixteen_img);
//        des_sixteen = findViewById(R.id.des_sixteen);
//        layout_sixteen = findViewById(R.id.layout_sixteen);
//
//        card_sixteen.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//            @Override
//            public void onClick(View v) {
//                int x = (des_sixteen.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
//                TransitionManager.beginDelayedTransition(layout_sixteen, new AutoTransition());
//
//                des_sixteen.setVisibility(x);
//
//                if(img_sixteen.getVisibility()==View.VISIBLE) {
//                    img_sixteen.setVisibility(View.INVISIBLE);
//                    sixteen_img.setVisibility(View.VISIBLE);
//                }
//                else if(sixteen_img.getVisibility()==View.VISIBLE) {
//                    sixteen_img.setVisibility(View.INVISIBLE);
//                    img_sixteen.setVisibility(View.VISIBLE);
//                }
//            }
//        });
//
//
//        // card_seventeen
//        card_seventeen = findViewById(R.id.card_seventeen);
//        img_seventeen = findViewById(R.id.img_seventeen);
//        seventeen_img = findViewById(R.id.seventeen_img);
//        des_seventeen = findViewById(R.id.des_seventeen);
//        layout_seventeen = findViewById(R.id.layout_seventeen);
//
//        card_seventeen.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//            @Override
//            public void onClick(View v) {
//                int x = (des_seventeen.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
//                TransitionManager.beginDelayedTransition(layout_seventeen, new AutoTransition());
//
//                des_seventeen.setVisibility(x);
//
//                if(img_seventeen.getVisibility()==View.VISIBLE) {
//                    img_seventeen.setVisibility(View.INVISIBLE);
//                    seventeen_img.setVisibility(View.VISIBLE);
//                }
//                else if(seventeen_img.getVisibility()==View.VISIBLE) {
//                    seventeen_img.setVisibility(View.INVISIBLE);
//                    img_seventeen.setVisibility(View.VISIBLE);
//                }
//            }
//        });
//
//        // card_eighteen
//        card_eighteen = findViewById(R.id.card_eighteen);
//        img_eighteen = findViewById(R.id.img_eighteen);
//        eighteen_img = findViewById(R.id.eighteen_img);
//        des_eighteen = findViewById(R.id.des_eighteen);
//        layout_eighteen = findViewById(R.id.layout_eighteen);
//
//        card_eighteen.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//            @Override
//            public void onClick(View v) {
//                int x = (des_eighteen.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
//                TransitionManager.beginDelayedTransition(layout_eighteen, new AutoTransition());
//
//                des_eighteen.setVisibility(x);
//
//                if(img_eighteen.getVisibility()==View.VISIBLE) {
//                    img_eighteen.setVisibility(View.INVISIBLE);
//                    eighteen_img.setVisibility(View.VISIBLE);
//                }
//                else if(eighteen_img.getVisibility()==View.VISIBLE) {
//                    eighteen_img.setVisibility(View.INVISIBLE);
//                    img_eighteen.setVisibility(View.VISIBLE);
//                }
//            }
//        });




//        txt_one.setOnClickListener(v -> {
//            Uri uri = Uri.parse("https://www.organindia.org");
//            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//            startActivity(intent);
//        });
//
//        txt_two.setOnClickListener(v -> {
//            Uri uri = Uri.parse("https://www.mohanfoundation.org");
//            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//            startActivity(intent);
//        });
//
//        txt_three.setOnClickListener(v -> {
//            Uri uri = Uri.parse("https://dghs.gov.in/content/1353_3_NationalOrganTransplantProgramme.aspx#mid");
//            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//            startActivity(intent);
//        });
//
//        txt_four.setOnClickListener(v -> {
//            Uri uri = Uri.parse("https://www.kidney.org");
//            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//            startActivity(intent);
//        });
    }
}