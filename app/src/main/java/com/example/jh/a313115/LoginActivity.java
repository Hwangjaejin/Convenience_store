package com.example.jh.a313115;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.kakao.auth.AuthType;
import com.kakao.auth.ErrorCode;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.LoginButton;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginActivity extends BaseActivity {
    String TAG = "LoginActivity";

    public String kakao_name;
    public Long kakao_id;
    boolean first_login;
    LoginButton btn_kakao_login;

    private SessionCallback callback;
    SharedPreferences preferences;

    Button kakao_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        createUserData();
        getUserData().setFirstLogin(true);

        btn_kakao_login = (LoginButton) findViewById(R.id.btn_kakao);
        kakao_btn = (Button)findViewById(R.id.custom_btn_kakao);
        kakao_btn.setOnClickListener(this);

        preferences = getSharedPreferences("login",MODE_PRIVATE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data))
            return;
        super.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(callback);
    }
    @Override
    public void onClick(View v){
        int id = v.getId();
        switch (id){
            case R.id.custom_btn_kakao:
                kakaoLogin();
                break;
        }
    }

    private class SessionCallback implements ISessionCallback {

        @Override
        public void onSessionOpened() {
            UserManagement.getInstance().requestMe(new MeResponseCallback() {
                // 세션 오픈 실패. 세션이 삭제된 경우,
                @Override
                public void onSessionClosed(ErrorResult errorResult) {
                    Log.d(TAG, "SessionCallback :: onSessionClosed : " + errorResult.getErrorMessage());
                }

                // 회원이 아닌 경우,
                @Override
                public void onNotSignedUp() {
                    Log.d(TAG, "SessionCallback :: onNotSignedUp");
                }

                // 사용자정보 요청에 성공한 경우,
                @Override
                public void onSuccess(UserProfile userProfile) {
                    Log.d(TAG, "SessionCallback :: onSuccess");

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("name",userProfile.getNickname());
                    editor.putLong("id", userProfile.getId());
                    editor.commit();

                    if(getUserData().getFirstLogin()) {
                        getUserData().setFirstLogin(false);
                        kakaoLogin1();
                    }
                }

                // 사용자 정보 요청 실패
                @Override
                public void onFailure(ErrorResult errorResult) {
                    Log.d(TAG, "SessionCallback :: onFailure : " + errorResult.getErrorMessage());
                }
            });
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if (exception != null) {
                Logger.e(exception);
            }
        }
    }

    public void kakaoLogin(){
        btn_kakao_login.performClick();
        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);
        Session.getCurrentSession().checkAndImplicitOpen();
    }
    public void kakaoLogin1(){
        kakao_name = preferences.getString("name","");
        kakao_id = preferences.getLong("id",0);

        getUserData().setKakaoName(kakao_name);
        getUserData().setKakaoID(kakao_id+"");

        Intent mainActivity = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(mainActivity);
    }
}
