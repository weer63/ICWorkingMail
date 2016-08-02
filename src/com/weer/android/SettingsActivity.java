package com.weer.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by esmetannikov on 21.07.2016.
 */
public class SettingsActivity  extends Activity implements View.OnClickListener {

    private Button btnSaveSettings;
    private EditText etdMailFrom;
    private EditText etdMailTo;
    private EditText etdMailCc;
    private EditText etdLogin;
    private EditText etdPassword;;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        btnSaveSettings = (Button)findViewById(R.id.btnSaveSettings);
        etdMailFrom = (EditText)findViewById(R.id.edtMailFrom);
        etdMailTo = (EditText)findViewById(R.id.edtMailTo);
        etdMailCc = (EditText)findViewById(R.id.edtMailCc);
        etdLogin = (EditText)findViewById(R.id.edtLogin);
        etdPassword = (EditText)findViewById(R.id.edtPassword);

        Intent intent = getIntent();
        etdMailFrom.setText(intent.getStringExtra("mailFrom"));
        etdMailTo.setText(intent.getStringExtra("mailTo"));
        etdMailCc.setText(intent.getStringExtra("mailCc"));
        etdLogin.setText(intent.getStringExtra("mailUser"));
        etdPassword.setText(intent.getStringExtra("mailPassword"));

        btnSaveSettings.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnSaveSettings:
                Intent intent  = new Intent();
                intent.putExtra("mailFrom",etdMailFrom.getText().toString());
                intent.putExtra("mailTo",etdMailTo.getText().toString());
                intent.putExtra("mailCc",etdMailCc.getText().toString());
                intent.putExtra("mailUser",etdLogin.getText().toString());
                intent.putExtra("mailPassword",etdPassword.getText().toString());
                setResult(RESULT_OK,intent);
                break;
        }
        finish();
    }
}
