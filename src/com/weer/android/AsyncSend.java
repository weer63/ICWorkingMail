package com.weer.android;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by esmetannikov on 26.06.2016.
 */
public class AsyncSend extends AsyncTask <IEmailSender,Void,Void> {

    @Override
    protected Void doInBackground(IEmailSender... iEmail) {
        try {
            iEmail[0].sendMail();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
