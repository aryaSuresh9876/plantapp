package com.android.foodorderapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.android.foodorderapp.adapters.PlantListAdapter;
import com.android.foodorderapp.model.PlantStore;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PlantListAdapter.PlantStoreListClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Plant Store List");

        List<PlantStore> PlantStoreModelList =  getPlantStoreData();

        initRecyclerView(PlantStoreModelList);
    }

    private void initRecyclerView(List<PlantStore> PlantStoreModelList ) {
        RecyclerView recyclerView =  findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        PlantListAdapter adapter = new PlantListAdapter(PlantStoreModelList, this);
        recyclerView.setAdapter(adapter);
    }

    private List<PlantStore> getPlantStoreData() {
        InputStream is = getResources().openRawResource(R.raw.plant);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try{
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while(( n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0,n);
            }
        }catch (Exception e) {

        }

        String jsonStr = writer.toString();
        Gson gson = new Gson();
        PlantStore[] PlantStoreModels =  gson.fromJson(jsonStr, PlantStore[].class);
        List<PlantStore> restList = Arrays.asList(PlantStoreModels);

        return  restList;

    }

    @Override
    public void onItemClick(PlantStore PlantStoreModel) {
        Intent intent = new Intent(MainActivity.this, PlantMenuActivity.class);
        intent.putExtra("PlantStoreModel", PlantStoreModel);
        startActivity(intent);

    }
}