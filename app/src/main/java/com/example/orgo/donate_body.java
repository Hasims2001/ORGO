package com.example.orgo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.material.navigation.NavigationView;

import java.util.Arrays;

public class donate_body extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

//    ListView listView;
//    String[] heading = {"What is Body Donation?", "Why is Body Donation so important?", "Are there any limitations or conditions on Body Donation?", "How do I donate my Body?"};
//    String[] txt =
//            {
//            "Body Donation, also called Deh Dan, is the donation of the whole body after death for the purpose of medical research and education. Body Donation is important for helping medical students and researchers to understand the human body, and for the advancement of science. Any person wishing to donate their body can make prior arrangements with the local medical college, hospital, or an NGO, before death. Individuals may request a consent form from a medical institution or an NGO, who will then give information about policies and procedures followed after the potential donor is deceased. However, signing a prior consent form is not compulsory but is preferable so that your family is aware of your decision and the role they need to play in fulfilling your wish.  Some renowned Indians who donated their body for medical research include Jurist Leila Seth, CPI (M) leader Somnath Chatterji, Former West Bengal Chief Minister Jyoti Basu, and Jana Sangh leader Nanaji Deshmukh. Many people in India donate their bodies after death by signing a pledge form with two accompanying witness signatures.",
//            "Dead human bodies (called cadavers) are used to teach students about Anatomy, the study of the structure of the body and how it works. It is one of the most important courses in the education of physicians, surgeons, dentists and other healthcare professionals. Cadavers are also used by research physicians in the development of new life-saving surgical procedures, for example, surgical approaches to various internal organs amongst others. The medical institutions receive cadavers by voluntary donations, as well as from the police who donate the unclaimed bodies. These donations are highly valued by staff and students in medical institutions.  Anatomical bequests are much appreciated and contribute greatly to our understanding of the human body. Anyone considering the option of donating their body to science should know that their gift will be greatly valued. It will play a critical role in helping medical students master the complex anatomy of the human body and will provide researchers with the essential tools to help our patients of tomorrow.",
//            "Though most institutions welcome the offer of a donation, there are certain medical conditions that may lead to the offer being declined. Medical Colleges can give you more information about these conditions and any other reasons why a Body Donation may be declined.  Depending on the circumstances of a person’s death, a post-mortem examination may be required. Post-mortem examination (sometimes referred to as an ‘autopsy’) is an important reason why a medical school might decline the offer of a Body Donation. We recommend that potential donors and their families be prepared to consider alternative arrangements in these circumstances, which can arise unexpectedly.",
//            "You need to identify and get in touch with Medical Colleges or Body Donation NGOs near you in order to register your wish to donate your body after death in your district.  You can obtain this information by clicking on the above green button (Pan-India Body Donation Directory) and finding a Healthcare Institution or organization in India near you.  The most important thing that you need to know is that, after your death, it is your family or next of kin who will carry out the process of donating your body. Therefore, it’s very important that they are involved in your decision, aware of your wishes, and are comfortable carrying out the entire process. Their support is of paramount importance.",
//
//            };

    String selectedstate, selecteddistrict;
    TextView stateview, districtview;
    Spinner indianstates, indiandistrict;
    private ArrayAdapter<CharSequence> stateAdapter, districtAdapter;
    RadioGroup rg;
    RadioButton radioid;
    TextView donor;

    Button submit;

    String[] dr_names, dr_phones, dr_emails, i_names, i_address, i_phones, i_emails, i_websites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_body);

        drawerLayout = findViewById(R.id.donatebody);
        navigationView = findViewById(R.id.sidemenubar);
        toolbar = findViewById(R.id.Tool);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();



//        listView = findViewById(R.id.listView);
//        bodyAdapter body = new bodyAdapter(this, R.layout.organztn_layout, heading, txt);
//        listView.setAdapter(body);


        stateview = findViewById(R.id.stateview);
        districtview = findViewById(R.id.districtview);
        submit = findViewById(R.id.next);
        indianstates = findViewById(R.id.indiastates);
        indiandistrict = findViewById(R.id.indiadistrict);

        stateAdapter = ArrayAdapter.createFromResource(this, R.array.indian_states, R.layout.spinner_layout);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        indianstates.setAdapter(stateAdapter);
        indianstates.setSelection(1);

        indianstates.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectedstate = indianstates.getSelectedItem().toString();
                int parentID = parent.getId();
                if (parentID == R.id.indiastates){
                    switch (selectedstate){
                        case "Select Your State": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.indian_district, R.layout.spinner_layout);
                        break;
                        case "Andhra Pradesh": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_andhra_pradesh_districts, R.layout.spinner_layout);
                            break;
                        case "Arunachal Pradesh": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_arunachal_pradesh_districts, R.layout.spinner_layout);
                            break;
                        case "Assam": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_assam_districts, R.layout.spinner_layout);
                            break;
                        case "Bihar": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_bihar_districts, R.layout.spinner_layout);
                            break;
                        case "Chhattisgarh": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_chhattisgarh_districts, R.layout.spinner_layout);
                            break;
                        case "Goa": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_goa_districts, R.layout.spinner_layout);
                            break;
                        case "Gujarat": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_gujarat_districts, R.layout.spinner_layout);
                            break;
                        case "Haryana": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_haryana_districts, R.layout.spinner_layout);
                            break;
                        case "Himachal Pradesh": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_himachal_pradesh_districts, R.layout.spinner_layout);
                            break;
                        case "Jharkhand": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_jharkhand_districts, R.layout.spinner_layout);
                            break;
                        case "Karnataka": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_karnataka_districts, R.layout.spinner_layout);
                            break;
                        case "Kerala": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_kerala_districts, R.layout.spinner_layout);
                            break;
                        case "Madhya Pradesh": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_madhya_pradesh_districts, R.layout.spinner_layout);
                            break;
                        case "Maharashtra": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_maharashtra_districts, R.layout.spinner_layout);
                            break;
                        case "Manipur": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_manipur_districts, R.layout.spinner_layout);
                            break;
                        case "Meghalaya": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_meghalaya_districts, R.layout.spinner_layout);
                            break;
                        case "Mizoram": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_mizoram_districts, R.layout.spinner_layout);
                            break;
                        case "Nagaland": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_nagaland_districts, R.layout.spinner_layout);
                            break;
                        case "Odisha": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_odisha_districts, R.layout.spinner_layout);
                            break;
                        case "Punjab": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_punjab_districts, R.layout.spinner_layout);
                            break;
                        case "Rajasthan": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_rajasthan_districts, R.layout.spinner_layout);
                            break;
                        case "Sikkim": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_sikkim_districts, R.layout.spinner_layout);
                            break;
                        case "Tamil Nadu": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_tamil_nadu_districts, R.layout.spinner_layout);
                            break;
                        case "Telangana": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_telangana_districts, R.layout.spinner_layout);
                            break;
                        case "Tripura": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_tripura_districts, R.layout.spinner_layout);
                            break;
                        case "Uttar Pradesh": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_uttar_pradesh_districts, R.layout.spinner_layout);
                            break;
                        case "Uttarakhand": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_uttarakhand_districts, R.layout.spinner_layout);
                            break;
                        case "West Bengal": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_west_bengal_districts, R.layout.spinner_layout);
                            break;
                        case "Andaman and Nicobar Islands": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_andaman_nicobar_districts, R.layout.spinner_layout);
                            break;
                        case "Chandigarh": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_chandigarh_districts, R.layout.spinner_layout);
                            break;
                        case "Dadra and Nagar Haveli": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_dadra_nagar_haveli_districts, R.layout.spinner_layout);
                            break;
                        case "Daman and Diu": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_daman_diu_districts, R.layout.spinner_layout);
                            break;
                        case "Delhi": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_delhi_districts, R.layout.spinner_layout);
                            break;
                        case "Jammu and Kashmir": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_jammu_kashmir_districts, R.layout.spinner_layout);
                            break;
                        case "Lakshadweep": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_lakshadweep_districts, R.layout.spinner_layout);
                            break;
                        case "Ladakh": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_ladakh_districts, R.layout.spinner_layout);
                            break;
                        case "Puducherry": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_puducherry_districts, R.layout.spinner_layout);
                            break;
                        default:break;
                    }
                    districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    indiandistrict.setAdapter(districtAdapter);
                    indiandistrict.setSelection(1);
                    indiandistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            selecteddistrict = indiandistrict.getSelectedItem().toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


//        radio btn
        rg = findViewById(R.id.radio_grp);
        donor = findViewById(R.id.donor);


//        adapter

        submit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
               if(selectedstate.equals("Select Your State")){
                   Toast.makeText(donate_body.this, "Please Select your state from the list", Toast.LENGTH_LONG).show();
                   stateview.setError("State is not Selected");
                   stateview.requestFocus();

               }else if (selecteddistrict.equals("Select Your District")){
                   Toast.makeText(donate_body.this, "Please Select your district from the list", Toast.LENGTH_LONG).show();
                   districtview.setError("State is not Selected");
                   districtview.requestFocus();
                   stateview.setError(null);

               } else{
//                   after selection of state and district
                   stateview.setError(null);
                   districtview.setError(null);

//                   radiogroup
                   int IDs = rg.getCheckedRadioButtonId();
                   if (IDs == -1){
                       donor.setError("Alive or Dead");
                       donor.requestFocus();
                   }else{
                       donor.setError(null);
                       radioid = findViewById(IDs);
                       String sel = radioid.getText().toString();

                       if (sel.equals("Alive")){
                           Intent intent = new Intent(donate_body.this, donor_alive.class);
                           intent.putExtra("state", selectedstate);
                           intent.putExtra("district", selecteddistrict);
                           intent.putExtra("d_type", "Body");
                           startActivity(intent);
                       }else{

                           String[] ins_name = getResources().getStringArray(R.array.ins_name);
                           String[] ins_address = getResources().getStringArray(R.array.ins_address);
                           String[] ins_phoneno = getResources().getStringArray(R.array.ins_phoneno);
                           String[] ins_email = getResources().getStringArray(R.array.ins_email);
                           String[] ins_website = getResources().getStringArray(R.array.ins_website);

                           String[] per_name =  getResources().getStringArray(R.array.per_name);
                           String[] per_phone =  getResources().getStringArray(R.array.per_phone);
                           String[] per_email =  getResources().getStringArray(R.array.per_email);

                           LinearLayout linearLayout2 = findViewById(R.id.linearLayout2);
                           linearLayout2.setVisibility(View.GONE);

                           LinearLayout listitem = findViewById(R.id.listitem);
                           listitem.setVisibility(View.VISIBLE);

                           ListView contactlist = findViewById(R.id.contactlist);
                           ListView  person_contact = findViewById(R.id.person_contact);

                        switch (selecteddistrict){
//                            Andaman Nicobar state
                            case "Nicobar" :

                            case "North Middle Andaman" :

                            case "South Andaman" :
                                i_names = Arrays.copyOfRange(ins_name, 0,1);
                                i_address = Arrays.copyOfRange(ins_address, 0,1);
                                i_phones = Arrays.copyOfRange(ins_phoneno, 0,1);
                                i_emails = Arrays.copyOfRange(ins_email, 0,1);
                                i_websites = Arrays.copyOfRange(ins_website, 0,1);

                                dr_names = Arrays.copyOfRange(per_name, 0,5);
                                dr_phones = Arrays.copyOfRange(per_phone, 0,5);
                                dr_emails = Arrays.copyOfRange(per_email, 0,5);

                                contactAdapter nicobar = new contactAdapter(donate_body.this, R.layout.contact_layout, i_names, i_address, i_phones, i_emails, i_websites);
                                contactlist.setAdapter(nicobar);
                                person_contactAdapter nicobar_per = new person_contactAdapter(donate_body.this, R.layout.person_contact_layout, dr_names, dr_phones, dr_emails);
                                person_contact.setAdapter(nicobar_per);
                                break;


//                                Andhra pradesh state
                            case "Anantapur" :
                                i_names = Arrays.copyOfRange(ins_name, 1,2);
                                i_address = Arrays.copyOfRange(ins_address, 1,2);
                                i_phones = Arrays.copyOfRange(ins_phoneno, 1,2);
                                i_emails = Arrays.copyOfRange(ins_email, 1,2);
                                i_websites = Arrays.copyOfRange(ins_website, 1,2);

                                dr_names = Arrays.copyOfRange(per_name, 5,7);
                                dr_phones =Arrays.copyOfRange(per_phone, 5,7);
                                dr_emails =Arrays.copyOfRange(per_email, 5,7);

                                contactAdapter anantapur = new contactAdapter(donate_body.this, R.layout.contact_layout, i_names, i_address, i_phones, i_emails, i_websites);
                                contactlist.setAdapter(anantapur);
                                person_contactAdapter anantapur_per = new person_contactAdapter(donate_body.this, R.layout.person_contact_layout, dr_names, dr_phones, dr_emails);
                                person_contact.setAdapter(anantapur_per);
                                break;

                            case "Chittoor" :
                                i_names = Arrays.copyOfRange(ins_name, 2,3);
                                i_address = Arrays.copyOfRange(ins_address, 2,3);
                                i_phones = Arrays.copyOfRange(ins_phoneno, 2,3);
                                i_emails = Arrays.copyOfRange(ins_email, 2,3);
                                i_websites = Arrays.copyOfRange(ins_website,2,3);

                                dr_names = Arrays.copyOfRange(per_name,  7,11);
                                dr_phones =Arrays.copyOfRange(per_phone,  7,11);
                                dr_emails =Arrays.copyOfRange(per_email,  7,11);

                                contactAdapter chittoor = new contactAdapter(donate_body.this, R.layout.contact_layout, i_names, i_address, i_phones, i_emails, i_websites);
                                contactlist.setAdapter(chittoor);
                                person_contactAdapter chittoor_per = new person_contactAdapter(donate_body.this, R.layout.person_contact_layout, dr_names, dr_phones, dr_emails);
                                person_contact.setAdapter(chittoor_per);
                                break;

                            case "East Godavari" :
                               i_names = Arrays.copyOfRange(ins_name, 3,4);
                               i_address = Arrays.copyOfRange(ins_address, 3,4);
                               i_phones = Arrays.copyOfRange(ins_phoneno, 3,4);
                               i_emails = Arrays.copyOfRange(ins_email, 3,4);
                               i_websites = Arrays.copyOfRange(ins_website,3,4);

                                dr_names = Arrays.copyOfRange(per_name, 11,15);
                                dr_phones =Arrays.copyOfRange(per_phone, 11,15);
                                dr_emails =Arrays.copyOfRange(per_email, 11,15);

                                contactAdapter godavari = new contactAdapter(donate_body.this, R.layout.contact_layout, i_names, i_address, i_phones, i_emails, i_websites);
                                contactlist.setAdapter(godavari);
                                person_contactAdapter godavari_per = new person_contactAdapter(donate_body.this, R.layout.person_contact_layout, dr_names, dr_phones, dr_emails);
                                person_contact.setAdapter(godavari_per);
                                break;

                            case "Guntur" :
                                i_names = Arrays.copyOfRange(ins_name, 4,5); // 4,7
                                i_address = Arrays.copyOfRange(ins_address, 4,5);
                                i_phones = Arrays.copyOfRange(ins_phoneno, 4,5);
                                i_emails = Arrays.copyOfRange(ins_email, 4,5);
                                i_websites = Arrays.copyOfRange(ins_website,4,5);

                                dr_names = Arrays.copyOfRange(per_name, 15,22);
                                dr_phones =Arrays.copyOfRange(per_phone, 15,22);
                                dr_emails =Arrays.copyOfRange(per_email, 15,22);

                                contactAdapter guntur = new contactAdapter(donate_body.this, R.layout.contact_layout, i_names, i_address, i_phones, i_emails, i_websites);
                                contactlist.setAdapter(guntur);
                                person_contactAdapter guntur_per = new person_contactAdapter(donate_body.this, R.layout.person_contact_layout, dr_names, dr_phones, dr_emails);
                                person_contact.setAdapter(guntur_per);
                                break;

                            case "Kadapa" :
                                i_names = Arrays.copyOfRange(ins_name, 7,8); //7,9
                                i_address = Arrays.copyOfRange(ins_address, 7,8);
                                i_phones = Arrays.copyOfRange(ins_phoneno, 7,8);
                                i_emails = Arrays.copyOfRange(ins_email, 7,8);
                                i_websites = Arrays.copyOfRange(ins_website,7,8);

                                dr_names = Arrays.copyOfRange(per_name, 22,26);
                                dr_phones =Arrays.copyOfRange(per_phone, 22,26);
                                dr_emails =Arrays.copyOfRange(per_email, 22,26);

                                contactAdapter kadapa = new contactAdapter(donate_body.this, R.layout.contact_layout, i_names, i_address, i_phones, i_emails, i_websites);
                                contactlist.setAdapter(kadapa);
                                person_contactAdapter kadapa_per = new person_contactAdapter(donate_body.this, R.layout.person_contact_layout, dr_names, dr_phones, dr_emails);
                                person_contact.setAdapter(kadapa_per);
                                break;

                            case "Krishna" :
                                i_names = Arrays.copyOfRange(ins_name, 9,10);
                                i_address = Arrays.copyOfRange(ins_address, 9,10);
                                i_phones = Arrays.copyOfRange(ins_phoneno, 9,10);
                                i_emails = Arrays.copyOfRange(ins_email, 9,10);
                                i_websites = Arrays.copyOfRange(ins_website,9,10);

                                dr_names = Arrays.copyOfRange(per_name, 26,30);
                                dr_phones =Arrays.copyOfRange(per_phone, 26,30);
                                dr_emails =Arrays.copyOfRange(per_email, 26,30);

                                contactAdapter krishna = new contactAdapter(donate_body.this, R.layout.contact_layout, i_names, i_address, i_phones, i_emails, i_websites);
                                contactlist.setAdapter(krishna);
                                person_contactAdapter krishna_per = new person_contactAdapter(donate_body.this, R.layout.person_contact_layout, dr_names, dr_phones, dr_emails);
                                person_contact.setAdapter(krishna_per);
                                break;

                            case "Kurnool" :
                                i_names = Arrays.copyOfRange(ins_name, 10,11); // 10,12
                                i_address = Arrays.copyOfRange(ins_address, 10,11);
                                i_phones = Arrays.copyOfRange(ins_phoneno, 10,11);
                                i_emails = Arrays.copyOfRange(ins_email, 10,11);
                                i_websites = Arrays.copyOfRange(ins_website,10,11);

                                dr_names = Arrays.copyOfRange(per_name, 30,36);
                                dr_phones =Arrays.copyOfRange(per_phone, 30,36);
                                dr_emails =Arrays.copyOfRange(per_email, 30,36);

                                contactAdapter kurnool = new contactAdapter(donate_body.this, R.layout.contact_layout, i_names, i_address, i_phones, i_emails, i_websites);
                                contactlist.setAdapter(kurnool);
                                person_contactAdapter kurnool_per = new person_contactAdapter(donate_body.this, R.layout.person_contact_layout, dr_names, dr_phones, dr_emails);
                                person_contact.setAdapter(kurnool_per);
                                break;

                            case "Nellore" :
                                i_names = Arrays.copyOfRange(ins_name, 12,13); // 12,14
                                i_address = Arrays.copyOfRange(ins_address, 12,13);
                                i_phones = Arrays.copyOfRange(ins_phoneno, 12,13);
                                i_emails = Arrays.copyOfRange(ins_email, 12,13);
                                i_websites = Arrays.copyOfRange(ins_website,12,13);

                                dr_names = Arrays.copyOfRange(per_name, 36,44);
                                dr_phones =Arrays.copyOfRange(per_phone, 36,44);
                                dr_emails =Arrays.copyOfRange(per_email, 36,44);

                                contactAdapter nellore = new contactAdapter(donate_body.this, R.layout.contact_layout, i_names, i_address, i_phones, i_emails, i_websites);
                                contactlist.setAdapter(nellore);
                                person_contactAdapter nellore_per = new person_contactAdapter(donate_body.this, R.layout.person_contact_layout, dr_names, dr_phones, dr_emails);
                                person_contact.setAdapter(nellore_per);
                                break;




                        }



                       }
                   }


               }
            }
        });

//        stateview.setOnClickListener(V -> stateview.setError(null));
//        districtview.setOnClickListener(V -> districtview.setError(null));
//        rg.setOnClickListener(V -> donor.setError(null));

    }
}