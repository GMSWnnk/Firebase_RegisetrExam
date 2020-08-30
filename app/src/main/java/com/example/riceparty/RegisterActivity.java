package com.example.riceparty;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

import ClassFile.SecurityUtil;
import data.UsersData;


//import android.widget.ProgressBar;


public class RegisterActivity extends AppCompatActivity {
//  input data
    private EditText register_id, register_password, register_re_password,
            register_nickname, register_name, register_phonenum_start, register_phonenum_middle,
            register_phonenum_end, register_sms_certif, register_email, email_certif_num;


    //    private ServiceApi service;

    private Button btn_id_check, btn_register_submit;

    //    private ProgressBar ProgressView;
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0,0);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        //input data
        register_id = (EditText) findViewById( R.id.register_id );
        register_password = (EditText) findViewById( R.id.register_password );
        register_re_password = (EditText) findViewById( R.id.register_re_password );
        register_nickname = (EditText) findViewById( R.id.register_nickname );
        register_name = (EditText) findViewById( R.id.register_name );

        register_phonenum_start = (EditText) findViewById( R.id.register_phonenum_start );
        register_phonenum_middle = (EditText) findViewById( R.id.register_phonenum_middle );
        register_phonenum_end = (EditText) findViewById( R.id.register_phonenum_end );
        register_sms_certif = (EditText) findViewById( R.id.register_sms_certif );

        register_email = (EditText) findViewById( R.id.register_email );
        email_certif_num = (EditText) findViewById( R.id.email_certif_num );

        //button
        btn_id_check = (Button) findViewById( R.id.register_id_ck_btn );
        btn_register_submit = (Button) findViewById( R.id.btn_register_submit );
//      ProgressView = (ProgressBar) findViewById( R.id.join_progress );


//        service = RetrofitClient.getClient().create(ServiceApi.class);
        btn_id_check.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        } );

        btn_register_submit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateData();
            }
        } );
    }

    //submit button click event
    private void UpdateData() {
        //error icon
        register_id.setError(null);
        register_password.setError(null);
        register_re_password.setError(null);
        register_nickname.setError(null);
        register_name.setError(null);

        register_phonenum_start.setError(null);
        register_phonenum_middle.setError(null);
        register_phonenum_end.setError(null);
        register_sms_certif.setError(null);

        register_email.setError(null);
        email_certif_num.setError(null);

        String id = register_id.getText().toString();
        String password = register_password.getText().toString();
        String re_password = register_re_password.getText().toString();
        String nickname = register_nickname.getText().toString();
        String name = register_name.getText().toString();
        String phonenum_start = register_phonenum_start.getText().toString();
        String phonenum_middle = register_phonenum_middle.getText().toString();
        String phonenum_end = register_phonenum_end.getText().toString();
        String sms_certif = register_sms_certif.getText().toString();
        String email = register_email.getText().toString();
        String email_certif = email_certif_num.getText().toString();

        boolean cancel = false;
        boolean id_check = false;
        View focusView = null;

        //다이얼로그 바디
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

//        //이메일 인증 유효성 검사
//        if(email_certif.isEmpty()){
//            email_certif_num.setError("이메일인증");
//            focusView = email_certif_num;
//            cancel = true;
//        }

        // 이메일의 유효성 검사
        if(email.isEmpty()) {
            register_email.setError("이메일을 입력해주세요.");
            focusView = register_email;
            cancel = true;
        } else if (!isEmailValid(email)) {
            register_email.setError("@를 포함한 유효한 이메일을 입력해주세요.");
            focusView = register_email;
            cancel = true;
        }

//        //휴대폰 인증 유효성 검사
//        if(sms_certif.isEmpty()){
//            register_sms_certif.setError("휴대폰번호");
//            focusView = register_sms_certif;
//            cancel = true;
//        }

        //휴대폰 번호 유효성 검사 뒷자리
        if(phonenum_end.isEmpty()){
            register_phonenum_end.setError("휴대폰번호");
            focusView = register_phonenum_end;
            cancel = true;
        }

        //휴대폰 번호 유효성 검사 중간자리
        if(phonenum_middle.isEmpty()){
            register_phonenum_middle.setError("휴대폰번호");
            focusView = register_phonenum_middle;
            cancel = true;
        }

        //휴대폰 번호 유효성 검사 첫자리
        if(phonenum_start.isEmpty()){
            register_phonenum_start.setError("휴대폰번호");
            focusView = register_phonenum_start;
            cancel = true;
        }

        // 이름 유효성 검사
        if(name.isEmpty()){
            register_name.setError("닉네임을 입력해주세요.");
            focusView = register_name;
            cancel = true;
        }

        // 닉네임 확인 유효성 검사
        if(nickname.isEmpty()){
            register_nickname.setError("닉네임을 입력해주세요.");
            focusView = register_nickname;
            cancel = true;
        }
        // 비밀번호 유효성 검사
        if(password.isEmpty()){
            register_password.setError("비밀번호를 입력해주세요.");
            focusView = register_password;
            cancel = true;
        } else if(!isPasswordValid(password)){
            register_password.setError("6자 이상의 비밀번호를 입력해주세요.");
            focusView = register_password;
            cancel = true;
        }

        // 비밀번호 확인 유효성 검사
        if(re_password.isEmpty()){
            register_re_password.setError("비밀번호 확인 칸을 입력해주세요.");
            focusView = register_re_password;
            cancel = true;
        }
        else if(!(re_password.equals(password))){
            register_re_password.setError("비밀번호가 일치하지 않습니다.");
            focusView = register_re_password;
            cancel = true;
        }

        // 아이디의 유효성 검사
        if(id.isEmpty()) {
            register_id.setError("아이디를 입력해주세요.");
            focusView = register_id;
            cancel = true;
        }else if(!Pattern.matches("^[a-z0-9]{4,15}$",id)){
            register_id.setError("5~16자의 영문 소문자, 숫자만 사용 가능합니다.");
            focusView = register_id;
            cancel = true;
        }
//        else if(isIDvalid()){
//            Toast.makeText(RegisterActivity.this, "이미 존재하는 ID 입니다. 다른 ID로 설정해주세요.", Toast.LENGTH_LONG).show();
//            getFirebaseDatabase();
//            focusView = register_id;
//            cancel = true;
//        }

        if(cancel) {
            focusView.requestFocus();
        }
        else {
//            startJoin(new JoinData(id, password, nickname, name, phonenum_start, phonenum_middle, phonenum_end, email));
//            showProgress(true);
            Signup(id, password, nickname, name, phonenum_start, phonenum_middle, phonenum_end, email);
        }
    }


    private void Signup(String id, String password, String nickname, String name,
                        String phonenum_start, String phonenum_middle, String phonenum_end, String email)
    {
        //firebase database 사용
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mDatabase = database.getReference();

        //휴대폰 번호 사이사이 하이픈('-')을 넣어 합치기
        String phone = phonenum_start+'-'+phonenum_middle+'-'+phonenum_end;
        //SecurityUtil 객체 생성
        SecurityUtil securityUtil = new SecurityUtil();
        String password_s = securityUtil.encryptSHA256( password );

        //UserData 객체 생성
        UsersData user = new UsersData(id, password_s, nickname, name, phone, email);

        mDatabase.child("users").child(phone).setValue(user);
        finish();
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }


}

