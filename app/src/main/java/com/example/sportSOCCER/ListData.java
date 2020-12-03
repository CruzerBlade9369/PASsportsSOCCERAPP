package com.example.sportSOCCER;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListData extends AppCompatActivity {
    TextView tvnodata;
    ProgressDialog dialog;
    RecyclerView recyclerView;
    DataAdapter adapter;
    ArrayList<Model> DataArrayList; //kit add kan ke adapter
    ImageView tambah_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);
        recyclerView = (RecyclerView) findViewById(R.id.rvdata);
        dialog = new ProgressDialog(ListData.this);
        //addData();
        addDataOnline();
    }
    void addData() {
        //offline, isi data offline dulu
        DataArrayList = new ArrayList<>();
        Model data1 = new Model();
        data1.setstrTeam("Team Name");
        data1.setstrTeamBadge("https://images.emojiterra.com/mozilla/512px/274c.png");
        data1.setstrDescriptionEN("Team desc");
        data1.setintFormedYear("01-01-2020");
        DataArrayList.add(data1);


        adapter = new DataAdapter(DataArrayList, new DataAdapter.Callback() {
            @Override
            public void onClick(int position) {

            }

            @Override
            public void test() {

            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListData.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        //get data online


    }

    void addDataOnline(){
        //kasih loading
        dialog.setMessage("Loading information");
        dialog.show();
        // background process
        AndroidNetworking.get("https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        Log.d("hasiljson", "onResponse: " + response.toString());
                        //jika sudah berhasil debugm lanjutkan code dibawah ini
                        DataArrayList = new ArrayList<>();
                        Model modelku;
                        try {
                            Log.d("hasiljson", "onResponse: " + response.toString());
                            JSONArray jsonArray = response.getJSONArray("teams");
                            Log.d("hasiljson2", "onResponse: " + jsonArray.toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                modelku = new Model();
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                modelku.setidTeam(jsonObject.getInt("idTeam"));
                                modelku.setstrTeam(jsonObject.getString("strTeam"));
                                modelku.setstrCountry(jsonObject.getString("strCountry"));
                                modelku.setstrDescriptionEN(jsonObject.getString("strDescriptionEN"));
                                modelku.setintFormedYear(jsonObject.getString("intFormedYear"));
                                modelku.setstrTeamBadge(jsonObject.getString("strTeamBadge"));
                                DataArrayList.add(modelku);
                            }
                            //untuk handle click
                            adapter = new DataAdapter(DataArrayList, new DataAdapter.Callback() {
                                @Override
                                public void onClick(int position) {
                                    Model team = DataArrayList.get(position);
                                    Intent intent = new Intent(getApplicationContext(), DetailMovie.class);
                                    intent.putExtra("idTeam",team.idTeam);
                                    intent.putExtra("strTeam",team.strTeam);
                                    intent.putExtra("strCountry",team.strCountry);
                                    intent.putExtra("intFormedYear",team.intFormedYear);
                                    intent.putExtra("strDescriptionEN",team.strDescriptionEN);
                                    intent.putExtra("strTeamBadge",team.strTeamBadge);
                                    startActivity(intent);
                                }

                                @Override
                                public void test() {

                                }
                            });
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListData.this);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(adapter);
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                        }

                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Log.d("errorku", "onError errorCode : " + error.getErrorCode());
                        Log.d("errorku", "onError errorBody : " + error.getErrorBody());
                        Log.d("errorku", "onError errorDetail : " + error.getErrorDetail());
                    }
                });
    }
}