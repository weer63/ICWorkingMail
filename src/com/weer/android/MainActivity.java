package com.weer.android;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener{
    /**
     * Called when the activity is first created.
     */

    Button btnWorking,btnLeaving;
    IEmailSender iEmail;
    Preference mailPref;
    private static final String logTAG = "WEER";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        btnWorking = (Button)findViewById(R.id.btnWorking);
        btnLeaving = (Button)findViewById(R.id.btnLeaving);

        btnWorking.setOnClickListener(this);
        btnLeaving.setOnClickListener(this);

        mailPref = new Preference();
        getPreference();
    }

    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.mailmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_settings:
                Intent intent = new Intent(this,SettingsActivity.class);
                intent.putExtra("mailFrom",mailPref.getMailFrom());
                intent.putExtra("mailTo",mailPref.getMailTo());
                intent.putExtra("mailCc",mailPref.getMailCc());
                intent.putExtra("mailUser",mailPref.getMailUser());
                intent.putExtra("mailPassword",mailPref.getMailPassword());
                startActivityForResult(intent,1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {return;}
        mailPref.setMailFrom(data.getStringExtra("mailFrom"));
        mailPref.setMailTo(data.getStringExtra("mailTo"));
        mailPref.setMailCc(data.getStringExtra("mailCc"));
        mailPref.setMailUser(data.getStringExtra("mailUser"));
        mailPref.setMailPassword(data.getStringExtra("mailPassword"));
        savePreference();
    }


    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnLeaving:
                iEmail = new IEmailSender(getResources().getString(R.string.mailSMTPServer),getResources().getString(R.string.mailSMTPPort),mailPref.getMailUser(),mailPref.getMailPassword());
                try {
                    AsyncSend mt = new AsyncSend();
                    iEmail.setAdresses(mailPref.getMailFrom(),mailPref.getMailTo(),mailPref.getMailCc(),getResources().getString(R.string.subjLeaving),getResources().getString(R.string.bodyLeaving));
                    mt.execute(iEmail);
                    Toast.makeText(this,"Leaving",Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btnWorking:
                iEmail = new IEmailSender(getResources().getString(R.string.mailSMTPServer),getResources().getString(R.string.mailSMTPPort),mailPref.getMailUser(),mailPref.getMailPassword());
                try {
                    AsyncSend mt = new AsyncSend();
                     iEmail.setAdresses(mailPref.getMailFrom(),mailPref.getMailTo(),mailPref.getMailCc(),getResources().getString(R.string.subjWorking),getResources().getString(R.string.bodyWorking));
                     mt.execute(iEmail);
                    Toast.makeText(this,"Working",Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                     e.printStackTrace();
                }
                break;
        }
    }

    void savePreference(){
        SharedPreferences sPref;
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(mailPref.MAIL_FROM,mailPref.getMailFrom());
        ed.putString(mailPref.MAIL_TO,mailPref.getMailTo());
        ed.putString(mailPref.MAIL_CC,mailPref.getMailCc());
        ed.putString(mailPref.MAIL_USER,mailPref.getMailUser());
        ed.putString(mailPref.MAIL_PASSWORD,mailPref.getMailPassword());
        ed.commit();
        Toast.makeText(this,"Save Settings",Toast.LENGTH_SHORT).show();
    }
    void getPreference(){
        SharedPreferences sPref;
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        mailPref.setMailFrom(sPref.getString(mailPref.MAIL_FROM,"from@weer.de"));
        mailPref.setMailTo(sPref.getString(mailPref.MAIL_TO,"from@weer.de"));
        mailPref.setMailCc(sPref.getString(mailPref.MAIL_CC,"from@weer.de"));
        mailPref.setMailUser(sPref.getString(mailPref.MAIL_USER,"weer"));
        mailPref.setMailPassword(sPref.getString(mailPref.MAIL_PASSWORD,"weer"));
    }
}
