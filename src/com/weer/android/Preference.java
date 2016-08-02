package com.weer.android;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by esmetannikov on 21.07.2016.
 */
public class Preference {
    private String mailFrom;
    private String mailTo;
    private String mailCc;
    private String mailUser;
    private String mailPassword;

    final public String MAIL_FROM = "mail_from";
    final public String MAIL_TO = "mail_to";
    final public String MAIL_CC = "mail_cc";
    final public String MAIL_USER = "mail_user";
    final public String MAIL_PASSWORD = "mail_password";

    public String getMailFrom() {
        return mailFrom;
    }

    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    public String getMailCc() {
        return mailCc;
    }

    public void setMailCc(String mailCc) {
        this.mailCc = mailCc;
    }

    public String getMailUser() {
        return mailUser;
    }

    public void setMailUser(String mailUser) {
        this.mailUser = mailUser;
    }

    public String getMailPassword() {
        return mailPassword;
    }

    public void setMailPassword(String mailPassword) {
        this.mailPassword = mailPassword;
    }
}
