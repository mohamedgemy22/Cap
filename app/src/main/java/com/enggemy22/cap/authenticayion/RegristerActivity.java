package com.enggemy22.cap.authenticayion;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.enggemy22.cap.R;
import com.enggemy22.cap.ui.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegristerActivity extends AppCompatActivity {

    @BindView(R.id.useer_name2)
    EditText useerName2;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.password2)
    EditText password2;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.r_male)
    RadioButton rMale;
    @BindView(R.id.r_famale)
    RadioButton rFamale;
    @BindView(R.id.radio)
    RadioGroup radio;
    @BindView(R.id.signup)
    Button signup;
    @BindView(R.id.linrear_edittxt)
    LinearLayout linrearEdittxt;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regrister);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
    }

    @OnClick(R.id.signup)
    public void onViewClicked() {
        if(is_validate()){
            String mail= email.getText().toString().trim();
            String pass= password2.getText().toString().trim();
            // is  a sign up by email and password  function
            isLogin(mail,pass);
        }
    }

    private void isLogin(String mail, String pass) {
        final ProgressDialog progressDialog = new ProgressDialog(RegristerActivity.this);
        progressDialog.setTitle(" Please_wait .....!");
        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(mail,pass)
                .addOnSuccessListener(this, new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        progressDialog.dismiss();
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    }
                }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
            }
        });
    }


    private boolean is_validate() {
        String UserNAme=useerName2.getText().toString().trim();
        String pass= password2.getText().toString().trim();
        String mEmail=email.getText().toString().trim();
        String Phone1=phone.getText().toString().trim();

        if (UserNAme.isEmpty()){
            useerName2.setError("name is required");
            return false;
        }else if(pass.isEmpty()){
            password2.setError("is reqired");
             return false;
        }else if(mEmail.isEmpty()){
            email.setError("is required");
            return false;
        }else if(Phone1.isEmpty()){
            phone.setError("is required");
            return false;
        }
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.r_male){
                    Toast.makeText(getApplicationContext(), " male choice",
                            Toast.LENGTH_SHORT).show();
                }else if(i== R.id.r_famale){
                    Toast.makeText(getApplicationContext(), " female choice",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        return true;
    }

}
