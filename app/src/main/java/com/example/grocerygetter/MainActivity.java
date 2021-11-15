package com.example.grocerygetter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
//import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

//import java.lang.reflect.Array;
import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public ArrayList<String> groceryList = new ArrayList<>(100);
    ArrayAdapter<String> arrayAdapter;
    ListView listView;
    EditText editText;
    Button findStores;
    Button add;

    BasicSpinnerAdapter adapter;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        groceryList = new ArrayList<>();
        //arrayAdapter = new ArrayAdapter<>(this, R.layout.list_view_layout, groceryList);


        spinner = findViewById(R.id.quantitiesSpinner);

        //---------------
        listView = (ListView) findViewById(R.id.ListView);

        ArrayList<String> mSpinnerData = new ArrayList<>();
        mSpinnerData.add("1");
        mSpinnerData.add("2");
        mSpinnerData.add("3");
        mSpinnerData.add("4");

        adapter = new BasicSpinnerAdapter(groceryList, mSpinnerData, this);
        listView.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String state = spinner.getSelectedItem().toString();
                int st = spinner.getSelectedItemPosition();
                spinner.setSelection(st);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //---------------

        //listView.setAdapter(arrayAdapter);
        //listView.setAdapter(dataAdapter);

        editText = findViewById(R.id.EditText);

        add = (Button) findViewById(R.id.addButton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groceryList.add(editText.getText().toString());
                adapter.notifyDataSetChanged();
                editText.setText("");
            }
        });

        findStores = (Button) findViewById(R.id.findStores);
        findStores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openResultsPage();
            }
        });

}

    public ArrayList<String> getList(){
        return groceryList;
    }

    public void addItemToList(View view){
        groceryList.add(editText.getText().toString());
        arrayAdapter.notifyDataSetChanged();
        editText.setText("");
    }

    public void openResultsPage(){
        Intent intent = new Intent(this, resultsPage.class);
        intent.putStringArrayListExtra("groceryList", groceryList);
        startActivity(intent);
    }


}