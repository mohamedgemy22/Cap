package com.enggemy22.cap.authenticayion;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.enggemy22.cap.R;
import com.enggemy22.cap.ui.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LogInActivity extends AppCompatActivity {

    private static final String TAG = "mohamed";
    @BindView(R.id.linrear_txt)
    LinearLayout linrearTxt;
    @BindView(R.id.useer_name)
    EditText useerName;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.logIn)
    Button logIn;
    @BindView(R.id.linrear_edittxt)
    LinearLayout linrearEdittxt;
    @BindView(R.id.create_Account)
    TextView createAccount;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
    }

    @OnClick({R.id.logIn, R.id.create_Account})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.logIn:
                is_valiadate();
                break;
            case R.id.create_Account:
                startActivity(new Intent(getApplicationContext(), RegristerActivity.class));
                break;
        }
    }

    private void is_valiadate() {
        String username = useerName.getText().toString().trim();
        String passworde = password.getText().toString().trim();

        if (username.isEmpty()) {
            useerName.setError("user name is required");
            useerName.requestFocus();

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
            useerName.setError(" entre Valid Email");
            useerName.requestFocus();

        } else if (passworde.isEmpty()) {
            password.setError("user name is required");
            password.requestFocus();

        }
        signin(username, passworde);
    }

    private void signin(String username, String passworde) {
        final ProgressDialog progressDialog = new ProgressDialog(LogInActivity.this);
        progressDialog.setTitle("Checking .....!");
        progressDialog.show();
        mAuth.signInWithEmailAndPassword(username,passworde).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
           if(task.isSuccessful()){
              progressDialog.dismiss();
               startActivity(new Intent(getApplicationContext(), HomeActivity.class));
           }else{
               Toast.makeText(LogInActivity.this,"user not exist",Toast.LENGTH_LONG).show();
               progressDialog.dismiss();
           }
            }
        });
    }

}
