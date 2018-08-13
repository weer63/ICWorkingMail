package com.weer.android;

import android.util.Log;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.search.SubjectTerm;
import java.security.Security;
import java.util.Properties;

/**
 * Created by esmetannikov on 26.06.2016.
 */
public class IEmailSender extends  javax.mail.Authenticator{
    private String srvHost;
    private String srvPort;
    private String usrLogin;
    private String usrPasswd;
    private Session session;
    private Message message;
    private final String COPYRIGHT  = "\n\n\n Send from my Phone";


    public IEmailSender(String srvHost,String srvPort,String usrLogin, String usrPasswd){
        this.srvHost = srvHost;
        this.srvPort = srvPort;
        this.usrLogin = usrLogin;
        this.usrPasswd = usrPasswd;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", this.srvHost);
        props.put("mail.smtp.port", this.srvPort);
        props.put("mail.smtp.quitwait", "false");
        props.put("mail.smtp.auth.mechanisms","NTLM");
        props.put("mail.smtp.auth.ntlm.domain","RT");
        props.put("mail.smtp.auth.plain.disable","true");

        this.session = Session.getDefaultInstance(props,this);
    }
    protected PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication(this.usrLogin,this.usrPasswd);
    }

    public void setAdresses (String from, String to, String cc, String subj,String body) throws Exception {
        this.message = new MimeMessage(this.session);
        try {
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
            message.setRecipients(Message.RecipientType.CC,InternetAddress.parse(cc));
            message.setSubject(subj);
            message.setText(body + this.COPYRIGHT);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public synchronized void sendMail() throws Exception{

        Transport.send(this.message);
    }
}
