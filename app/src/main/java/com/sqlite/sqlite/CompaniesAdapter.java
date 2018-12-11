package com.sqlite.sqlite;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class CompaniesAdapter extends RecyclerView.Adapter<CompaniesAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Company> companies;
    private int id;

    public CompaniesAdapter(Context context, ArrayList<Company> companies, Integer id) {
        this.context = context;
        this.companies = companies;
        this.id = id;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.companies_list, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        final Company company = companies.get(i);

        myViewHolder.viewName.setText(company.getName());
        myViewHolder.viewUrl.setText(company.getUrl());
        myViewHolder.viewPhone.setText(company.getPhone());
        myViewHolder.viewEmail.setText(company.getEmail());
        myViewHolder.viewPS.setText(company.getProductsServices());
        myViewHolder.viewClassification.setText(company.getClassification());

        myViewHolder.viewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage(R.string.dialog_message)
                        .setTitle(R.string.dialog_title);

                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DBHelper db = new DBHelper(context);
                        int position = companies.indexOf(company);

                        db.deleteCompany(company.getId());
                        companies.remove(position);
                        notifyItemRemoved(position);
                    }
                });

                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

        myViewHolder.viewUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, StartActivity.class);
                intent.putExtra("Update", true);
                intent.putExtra("ID", 0);
                intent.putExtra("Id", company.getId());
                intent.putExtra("Name", company.getName());
                intent.putExtra("Url", company.getUrl());
                intent.putExtra("Phone", company.getPhone());
                intent.putExtra("Email", company.getEmail());
                intent.putExtra("PS", company.getProductsServices());
                intent.putExtra("Classification", company.getClassification());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return companies.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView viewName, viewUrl, viewPhone, viewEmail, viewPS, viewClassification;
        public Button viewDelete, viewUpdate;

        public MyViewHolder(View view) {
            super(view);
            viewName = view.findViewById(R.id.nameView);
            viewUrl = view.findViewById(R.id.urlView);
            viewPhone = view.findViewById(R.id.phoneView);
            viewEmail = view.findViewById(R.id.emailView);
            viewPS = view.findViewById(R.id.psView);
            viewClassification = view.findViewById(R.id.classificationView);
            viewDelete = view.findViewById(R.id.deleteView);
            viewUpdate = view.findViewById(R.id.updateView);
        }
    }


}
