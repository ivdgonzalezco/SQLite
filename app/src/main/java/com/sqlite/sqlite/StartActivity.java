package com.sqlite.sqlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class StartActivity extends Activity {

    private int id;
    private int idUpdate;
    private String name;
    private String url;
    private String phone;
    private String email;
    private String ps;
    private String clasiffication;
    private DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);

        Intent intent = getIntent();

        id = intent.getIntExtra("ID", 0);

        dbHelper = new DBHelper(this);

        final EditText mName = findViewById(R.id.name);
        final EditText mUrl = findViewById(R.id.url);
        final EditText mPhone = findViewById(R.id.phone);
        final EditText mEmail = findViewById(R.id.email);
        final EditText mProductsServices = findViewById(R.id.productsServices);
        final Spinner classificator = findViewById(R.id.classificationSpinner);
        Button mCreate = findViewById(R.id.createButton);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.classification, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        classificator.setAdapter(adapter);

        if(intent.getBooleanExtra("Update", false)){
            mCreate.setText("Actualizar");

            idUpdate = intent.getIntExtra("Id", 0);
            name = intent.getStringExtra("Name");
            url = intent.getStringExtra("Url");
            phone = intent.getStringExtra("Phone");
            email = intent.getStringExtra("Email");
            ps = intent.getStringExtra("PS");
            clasiffication = intent.getStringExtra("Classification");

            mName.setText(name);
            mUrl.setText(url);
            mPhone.setText(phone);
            mEmail.setText(email);
            mProductsServices.setText(ps);
            int position = adapter.getPosition(clasiffication);
            classificator.setSelection(position);

            mCreate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dbHelper.updateCompany(new Company(
                            idUpdate,
                            mName.getText().toString(),
                            mUrl.getText().toString(),
                            mPhone.getText().toString(),
                            mEmail.getText().toString(),
                            mProductsServices.getText().toString(),
                            classificator.getSelectedItem().toString()
                    ));

                    Intent intent = new Intent(StartActivity.this, MainActivity.class);
                    intent.putExtra("ID", id);
                    startActivity(intent);
                }
            });

        }else{

            mCreate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Company company = new Company(id,
                            mName.getText().toString(),
                            mUrl.getText().toString(),
                            mPhone.getText().toString(),
                            mEmail.getText().toString(),
                            mProductsServices.getText().toString(),
                            classificator.getSelectedItem().toString());

                    dbHelper.insertCompany(company);

                    id++;

                    Intent intent = new Intent(StartActivity.this, MainActivity.class);
                    intent.putExtra("ID", id);
                    startActivity(intent);
                }
            });
        }
    }
}
