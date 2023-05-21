package com.example.csehstunotebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.csehstunotebook.Prevalent.Prevalent;
import com.example.csehstunotebook.model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private EditText inputNumber,inputPassword;
    private  String parentDbName="Users";

    // private CheckBox chkBoxRememberMe;
    TextView AdminLink,notAdminLink;

    TextView forgetPassClick;
    private Dialog dialog;

    ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton=(Button)findViewById(R.id.login_btn);
        inputNumber=(EditText)findViewById(R.id.login_phone_number_input);
        inputPassword=(EditText)findViewById(R.id.login_password_input);
        //  chkBoxRememberMe=(CheckBox)findViewById(R.id.remember_me_chkb);
        AdminLink=(TextView)findViewById(R.id.admin_panel_link);
        notAdminLink=(TextView)findViewById(R.id.not_admin_panel_link);



        loadingBar=new ProgressDialog(this);



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });


    }

    private void loginUser() {
        String phone=inputNumber.getText().toString();
        String password=inputPassword.getText().toString();


        if(TextUtils.isEmpty(phone))
        {
            Toast.makeText(this, "Please write your phone number...", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please write your password...", Toast.LENGTH_SHORT).show();
        }
        else {
            loadingBar.setTitle("Login Account");
            loadingBar.setMessage("Please wait while we are checking your information..");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            AllowAccessToAccount(phone,password);
        }
    }

    private void AllowAccessToAccount(String phone, String password) {


        /** Working on remember me */
      /*  if (chkBoxRememberMe.isChecked())
        {

            Paper.book().write(Prevalent.userPhoneKey,phone);
            Paper.book().write(Prevalent.userPasswordKey,password);
        }*/

        /** End of working on remember me */

        final DatabaseReference rootRef;
        rootRef= FirebaseDatabase.getInstance().getReference();
        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if(snapshot.child(parentDbName).child(phone).exists())
                {

                    Users userData=snapshot.child(parentDbName).child(phone).getValue(Users.class);
                    if(userData.getPhone().equals(phone))
                    {
                        /** This is for Admin */
                        if(userData.getPassword().equals(password))
                        {
                            if(parentDbName.equals("Admins")){
                                Toast.makeText(LoginActivity.this, "Welcome Admin, you are Logged in Successfully...", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                                //Intent intent=new Intent(LoginActivity.this,AdminCategoryActivity.class);
                                //startActivity(intent);
                            }   /** End of Admin Section */
                            else if(parentDbName.equals("Users"))
                            {
                                Toast.makeText(LoginActivity.this, "Logged in Successfully...", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                                Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
                                Prevalent.currentOnlineUser=userData;
                                startActivity(intent);
                                finish();
                            }

                        }
                        else
                        {
                            loadingBar.dismiss();
                            Toast.makeText(LoginActivity.this, "Password is incorrect.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Account with this "+phone+" number do not exists.", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }


}