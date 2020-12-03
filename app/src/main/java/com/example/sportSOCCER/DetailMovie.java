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

public class DetailMovie extends AppCompatActivity {
    Realm realm;
    RealmHelper realmHelper;
    ModelMovieRealm movieModel;


    Bundle extras;
    String TeamName;
    String CreationYear;
    String DescriptionEN;
    String Badge;
    int TeamID;
    String Country;

    TextView tvjudul;
    ImageView ivposter;
    TextView tvdesc;
    TextView tvyear;
    TextView tvcountry;
    Button btnbookmark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        extras = getIntent().getExtras();
        tvjudul = (TextView)findViewById(R.id.tvteam);
        tvdesc = (TextView)findViewById(R.id.txtdeskripsi);
        ivposter = (ImageView) findViewById(R.id.ivposter);
        tvyear = (TextView) findViewById(R.id.txtyear);
        tvcountry = (TextView) findViewById(R.id.txtcountry);
        btnbookmark = (Button) findViewById(R.id.btnbookmark);

        if (extras != null) {
            TeamName = extras.getString("strTeam");
            TeamID = extras.getInt("idTeam");
            CreationYear = extras.getString("intFormedYear");
            DescriptionEN = extras.getString("strDescriptionEN");
            Badge = extras.getString("strTeamBadge");
            Country = extras.getString("strCountry");
            tvjudul.setText(TeamName);
            tvdesc.setText(DescriptionEN);
            tvyear.setText(CreationYear);
            tvcountry.setText(Country);
            Glide.with(DetailMovie.this)
                    .load(Badge)
                    .override(Target.SIZE_ORIGINAL)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(ivposter);
            // and get whatever type user account id is
        }

        //Set up Realm
        Realm.init(DetailMovie.this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);


        btnbookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movieModel = new ModelMovieRealm();
                movieModel.setstrDescriptionEN(DescriptionEN);
                movieModel.setidTeam(TeamID);
                movieModel.setstrTeamBadge(Badge);
                movieModel.setintFormedYear(CreationYear);

                realmHelper = new RealmHelper(realm);
                realmHelper.save(movieModel);

            }
        });
    }
}