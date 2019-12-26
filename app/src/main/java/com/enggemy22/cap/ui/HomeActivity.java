package com.enggemy22.cap.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.enggemy22.cap.R;
import com.enggemy22.cap.authenticayion.LogInActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {
    private static final String KEY_COUNTRY = "country";
    public static final String KEY_GPA = "gpa";
    public static final String KEY_ACTRANGE = "AstRAnge";
    public static final String KEY_SATRANGE = "satrange";

    @BindView(R.id.useer_name2)
    EditText useerName2;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.password2)
    EditText password2;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.SpinnerCountry)
    Spinner SpinnerCountry;
    @BindView(R.id.chooseCountry)
    TextView chooseCountry;
    @BindView(R.id.intenddegree)
    TextView intenddegree;
    @BindView(R.id.degree)
    Spinner degree;
    @BindView(R.id.Gpa)
    EditText Gpa;
    @BindView(R.id.courses)
    EditText courses;
    @BindView(R.id.Activity)
    EditText Activity;
    @BindView(R.id.aid)
    TextView aid;
    @BindView(R.id.Fininalaid)
    Spinner Fininalaid;
    @BindView(R.id.sat_Score_range)
    EditText satScoreRange;
    @BindView(R.id.Act_scoreRange)
    EditText ActScoreRange;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;

    private FirebaseFirestore mFirestore;

    String country = " ";
    String intend_dEgree = "";
    String finaial_aid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        spinnerCountry();
        SpinnerDegree();
        finanialAidSpinner();

    }

    private void sendData() {
        String gps = Gpa.getText().toString().trim();
        String satRange = satScoreRange.getText().toString().trim();
        String actRange = ActScoreRange.getText().toString().trim();

        if(
                gps.isEmpty() && Float.parseFloat(gps) > 5
                && satRange.isEmpty() && Float.parseFloat(satRange)>1580
                && actRange.isEmpty()&& Float.parseFloat(actRange)>36)
        {
            Gpa.setError("is required");
            Gpa.requestFocus();

            satScoreRange.setError("is reqired");
            satScoreRange.requestFocus();

            ActScoreRange.setError("is requred");
            ActScoreRange.requestFocus();
        } else {
            Intent intent = new Intent(HomeActivity.this, PartialActivity.class);
            intent.putExtra(KEY_GPA, Gpa.getText().toString().trim());
            intent.putExtra(KEY_SATRANGE, satScoreRange.getText().toString().trim());
            intent.putExtra(KEY_ACTRANGE, ActScoreRange.getText().toString().trim());
            startActivity(intent);
        }
    }

    //data in this project
    private final void uploadData() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, String> city = new HashMap<>();
        city.put("user_name", useerName2.getText().toString().trim());
        city.put("email", email.getText().toString().trim());
        city.put("data_of_birth", password2.getText().toString().trim());
        city.put("gpa", Gpa.getText().toString().trim());
        city.put("courses", courses.getText().toString().trim());
        city.put("activity", Activity.getText().toString().trim());
        city.put("country", country);
        city.put("intend", intend_dEgree);
        city.put("aid", finaial_aid);
        if (isvalidate()) {
            final ProgressDialog progressDialog = new ProgressDialog(HomeActivity.this);
            progressDialog.setTitle("please wait  to upload data");
            progressDialog.show();
            db.collection("users")
                    .document()
                    .set(city)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            progressDialog.dismiss();
                            sendData();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.setTitle("upload failed");
                    progressDialog.show();
                    finish();
                }
            });

        }
    }

    private boolean isvalidate() {
        String name = useerName2.getText().toString().trim();
        String mail = email.getText().toString().trim();
        String Passw = password2.getText().toString().trim();
        String phon = phone.getText().toString().trim();
        String CGpa = Gpa.getText().toString().trim();
        String Cccourse = courses.getText().toString().trim();
        String CActivi = Activity.getText().toString().trim();
        String act_range = ActScoreRange.getText().toString().trim();
        String sat_score_range = satScoreRange.getText().toString().trim();
        if (name.isEmpty()) {
            useerName2.setText("is required");
            useerName2.requestFocus();
            return false;
        } else if (mail.isEmpty()) {
            email.setText("is require");
            email.requestFocus();
            return false;
        } else if (Passw.isEmpty()) {
            password2.setText("is require");
            password2.requestFocus();
            return false;
        } else if (phon.isEmpty()) {
            phone.setText("is reqire");
            phone.requestFocus();
            return false;
        } else if (CGpa.isEmpty()) {
            Gpa.setText("is require");
            Gpa.requestFocus();
            return false;
        } else if (Cccourse.isEmpty()) {
            courses.setText("is require");
            courses.requestFocus();
            return false;
        } else if (CActivi.isEmpty()) {
            Activity.setText("is require");
            Activity.requestFocus();
            return false;
        } else if (sat_score_range.isEmpty()) {
            satScoreRange.setText("is require");
            satScoreRange.requestFocus();
            return false;
        } else if (act_range.isEmpty()) {
            ActScoreRange.setText("is require");
            ActScoreRange.requestFocus();
            return false;
        }

        return true;
    }

    private void spinnerCountry() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.country_arrays, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerCountry.setAdapter(adapter);
        SpinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = adapterView.getItemAtPosition(i).toString();
                ((TextView) adapterView.getChildAt(0)).setTextColor(getResources().getColor(R.color.spinner));
                Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_LONG).show();
                switch (i) {
                    case 0:
                        break;
                    case 1:
                        country = "United_State";
                        break;
                    case 2:
                        country = "Canda";
                        break;
                    case 3:
                        country = "Turkey";
                        break;
                    case 4:
                        country = "Australia";
                        break;
                    case 5:
                        country = "United_Kingdom";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void SpinnerDegree() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (this, R.array.degree, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        degree.setAdapter(adapter);
        degree.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text2 = adapterView.getItemAtPosition(i).toString();
                ((TextView) adapterView.getChildAt(0)).setTextColor(getResources().getColor
                        (R.color.spinner));
                Toast.makeText(adapterView.getContext(), text2, Toast.LENGTH_LONG).show();
                switch (i) {
                    case 0:
                        break;
                    case 1:
                        intend_dEgree = "Associated (undergraduated)";
                        break;
                    case 2:
                        intend_dEgree = "Bachelors (undergraduated)";
                        break;
                    case 3:
                        intend_dEgree = "Mastrs (undergraduated)";
                        break;
                    case 4:
                        intend_dEgree = "Doctoral (graduated)";
                        break;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }

    private void finanialAidSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (this, R.array.aid, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Fininalaid.setAdapter(adapter);
        Fininalaid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text2 = adapterView.getItemAtPosition(i).toString();
                ((TextView) adapterView.getChildAt(0)).setTextColor(getResources().getColor(R.color.spinner));
                Toast.makeText(adapterView.getContext(), text2, Toast.LENGTH_LONG).show();
                switch (i) {
                    case 0:
                        break;
                    case 1:
                        finaial_aid = "Full ScolarShp (tution_Accomdation)";
                        break;
                    case 2:
                        finaial_aid = "Full Tuition";
                        break;
                    case 3:
                        finaial_aid = "Partial Tuition";
                        break;
                    case 4:
                        country = "Loans";
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @OnClick(R.id.btnSubmit)
    public void onViewClicked() {
      uploadData();
    }
}
