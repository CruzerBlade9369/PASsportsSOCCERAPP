package com.example.sportSOCCER;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DetailFavourite extends AppCompatActivity {

    Bundle extras;
    String strTeam;
    String strFormedYear;
    String strDescriptionEN;
    String strTeamBadge;
    int idTeam;
    String strCountry;

    TextView tvjudul;
    ImageView ivposter;
    TextView tvdesc;
    TextView tvyear;
    TextView tvcountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_favourite);
        extras = getIntent().getExtras();
        tvjudul = (TextView)findViewById(R.id.tvteam);
        tvdesc = (TextView)findViewById(R.id.txtdeskripsi);
        ivposter = (ImageView) findViewById(R.id.ivposter);
        tvyear = (TextView) findViewById(R.id.txtyear);
        tvcountry = (TextView) findViewById(R.id.txtcountry);

        if (extras != null) {
            strTeam = extras.getString("strTeam");
            idTeam = extras.getInt("idTeam");
            strFormedYear = extras.getString("intFormedYear");
            strDescriptionEN = extras.getString("strDescriptionEN");
            strTeamBadge = extras.getString("strTeamBadge");
            strCountry = extras.getString("strCountry");
            tvjudul.setText(strTeam);
            tvdesc.setText(strDescriptionEN);
            tvyear.setText(strFormedYear);
            tvcountry.setText(strCountry);
            Glide.with(DetailFavourite.this)
                    .load(strTeamBadge)
                    .override(Target.SIZE_ORIGINAL)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(ivposter);
            // and get whatever type user account id is
        }
    }
}