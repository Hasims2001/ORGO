package com.example.orgo;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.widget.ListView;

import com.google.android.material.navigation.NavigationView;

public class donate_body extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    String[] heading = {"What is Body Donation?", "Why is Body Donation so important?", "Are there any limitations or conditions on Body Donation?", "How do I donate my Body?"};
    String[] txt =
            {
            "Body Donation, also called Deh Dan, is the donation of the whole body after death for the purpose of medical research and education. Body Donation is important for helping medical students and researchers to understand the human body, and for the advancement of science. Any person wishing to donate their body can make prior arrangements with the local medical college, hospital, or an NGO, before death. Individuals may request a consent form from a medical institution or an NGO, who will then give information about policies and procedures followed after the potential donor is deceased. However, signing a prior consent form is not compulsory but is preferable so that your family is aware of your decision and the role they need to play in fulfilling your wish.  Some renowned Indians who donated their body for medical research include Jurist Leila Seth, CPI (M) leader Somnath Chatterji, Former West Bengal Chief Minister Jyoti Basu, and Jana Sangh leader Nanaji Deshmukh. Many people in India donate their bodies after death by signing a pledge form with two accompanying witness signatures.",
            "Dead human bodies (called cadavers) are used to teach students about Anatomy, the study of the structure of the body and how it works. It is one of the most important courses in the education of physicians, surgeons, dentists and other healthcare professionals. Cadavers are also used by research physicians in the development of new life-saving surgical procedures, for example, surgical approaches to various internal organs amongst others. The medical institutions receive cadavers by voluntary donations, as well as from the police who donate the unclaimed bodies. These donations are highly valued by staff and students in medical institutions.  Anatomical bequests are much appreciated and contribute greatly to our understanding of the human body. Anyone considering the option of donating their body to science should know that their gift will be greatly valued. It will play a critical role in helping medical students master the complex anatomy of the human body and will provide researchers with the essential tools to help our patients of tomorrow.",
            "Though most institutions welcome the offer of a donation, there are certain medical conditions that may lead to the offer being declined. Medical Colleges can give you more information about these conditions and any other reasons why a Body Donation may be declined.  Depending on the circumstances of a person’s death, a post-mortem examination may be required. Post-mortem examination (sometimes referred to as an ‘autopsy’) is an important reason why a medical school might decline the offer of a Body Donation. We recommend that potential donors and their families be prepared to consider alternative arrangements in these circumstances, which can arise unexpectedly.",
            "You need to identify and get in touch with Medical Colleges or Body Donation NGOs near you in order to register your wish to donate your body after death in your city.   You can obtain this information by clicking on the above green button (Pan-India Body Donation Directory) and finding a Healthcare Institution or organization in India near you.  The most important thing that you need to know is that, after your death, it is your family or next of kin who will carry out the process of donating your body. Therefore, it’s very important that they are involved in your decision, aware of your wishes, and are comfortable carrying out the entire process. Their support is of paramount importance.",

            };
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


        ListView listView = findViewById(R.id.listview);
        bodyAdapter body = new bodyAdapter(this, R.layout.organztn_layout, heading, txt);
        listView.setAdapter(body);

    }
}