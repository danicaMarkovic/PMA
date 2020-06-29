package com.example.pawfinder.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pawfinder.R;
import com.example.pawfinder.adapters.MyReportsListAdapter;
import com.example.pawfinder.model.Pet;
import com.example.pawfinder.service.ServiceUtils;
import com.example.pawfinder.tools.MockupComments;
import com.example.pawfinder.tools.NetworkTool;
import com.squareup.picasso.Picasso;

import java.util.Arrays;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportDetailActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.darktheme);
        }

        progressDialog = new ProgressDialog(this);
        setContentView(R.layout.activity_report_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.report_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ImageView imgView = (ImageView) findViewById(R.id.pet_report_details_image);
        Button buttonFound = (Button) findViewById(R.id.found_btn);

        ImageButton commentsButton = (ImageButton) findViewById(R.id.report_comments);

        MyReportsListAdapter adapter = new MyReportsListAdapter(this, MockupComments.getReports());

        final Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            Long id = bundle.getLong("report_pet_id");
            String name = bundle.getString("report_pet_name");
            String type = bundle.getString("report_pet_type");
            String date = bundle.getString("report_pet_date");
            String image = bundle.getString("report_pet_image");
            Boolean is_Found = bundle.getBoolean("is_found");
            if(is_Found)
            {
               buttonFound.setVisibility(View.INVISIBLE);
            }

            buttonFound.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    progressDialog.setMessage(getResources().getString(R.string.dialog_message));
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    Call<ResponseBody> call = ServiceUtils.petService.petFound(id);
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if(response.code() == 200)
                            {
                                progressDialog.dismiss();
                                Toast.makeText(ReportDetailActivity.this, "Uspeeeh u pronalasku", Toast.LENGTH_SHORT).show();
                                setResult(Activity.RESULT_CANCELED);
                                finish();
                            }

                            progressDialog.dismiss();
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });
                }
            });

            //lokalizacija tipa
            String[] type_values= getResources().getStringArray(R.array.type_values);
            int index_type = Arrays.asList(type_values).indexOf(type);
            String[] type_entries = getResources().getStringArray(R.array.type_entries);
            String type_entry = String.valueOf(type_entries[index_type]);

            toolbar.setTitle(name);
            TextView name_txt = (TextView) findViewById(R.id.report_text_view_name);
            name_txt.setText(name);
            TextView type_txt = (TextView) findViewById(R.id.report_text_view_type);
            type_txt.setText(type_entry);
            TextView date_txt = (TextView) findViewById(R.id.report_text_view_date);
            date_txt.setText(date);

            Picasso.get().load(ServiceUtils.IMAGES_URL + image).into(imgView);


            commentsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (NetworkTool.getConnectivityStatus(getApplicationContext()) != NetworkTool.TYPE_NOT_CONNECTED) {
                        Intent i = new Intent(getApplicationContext(), ViewCommentsActivity.class);
                        i.putExtra("view_comments_petsName", bundle.getString("report_pet_name"));
                        i.putExtra("view_comments_additionalInfo", bundle.getString("report_pet_additionalInfo"));
                        i.putExtra("view_comments_id", bundle.getLong("report_pet_of_pet"));
                        Log.d("PETSID ", "ima ih" + bundle.getLong("id_of_pet"));
                        i.putExtra("view_comments_image", bundle.getString("report_pet_image"));
                        startActivity(i);
                    }else{
                        Toast.makeText(getApplicationContext(), getText(R.string.network), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case android.R.id.home:
                setResult(Activity.RESULT_CANCELED);
                finish();
                break;
        }

        return true;
    }
}

