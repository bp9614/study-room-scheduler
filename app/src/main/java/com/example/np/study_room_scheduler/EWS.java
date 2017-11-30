package com.example.np.study_room_scheduler;

/**
 * Created by phamb1 on 11/30/2017.
 */

import microsoft.exchange.webservices.data.autodiscover.IAutodiscoverRedirectionUrl;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.PropertySet;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.service.SendInvitationsMode;
import microsoft.exchange.webservices.data.core.service.item.Appointment;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.core.service.schema.ItemSchema;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.property.complex.MessageBody;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

public class EWS {

    private ExchangeService service;
    private String email;

    private static EWS ews = null;

    private EWS(String email, String password) throws Exception {
        this.email = email;

        service = new ExchangeService(ExchangeVersion.Exchange2010_SP2);
        ExchangeCredentials credentials = new WebCredentials(email, password);
        service.setCredentials(credentials);
    }

    public static EWS getEWS() {
        return ews;
    }

    public static EWS createEWS(String email, String password) throws Exception {
        ews = new EWS(email, password);

        if(!ews.checkCredentials()) {
            ews = null;
        }

        return ews;
    }

    private boolean checkCredentials() {
        try {
            autodiscoverUrl();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void autodiscoverUrl() throws Exception {
        service.autodiscoverUrl(email, new RedirectionUrlCallback());
    }

    public boolean email(Collection<String> recipients, String subject, String msg) {
        if(recipients.isEmpty()) {return false;}

        try {
            EmailMessage emailMessage = new EmailMessage(service);
            emailMessage.setSubject(subject);
            emailMessage.setBody(MessageBody.getMessageBodyFromText(msg));
            for (String s : recipients) {emailMessage.getToRecipients().add(s);}
            emailMessage.send();

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean meeting(Collection<String> recipients, Date startDate, Date endDate) {
        if(recipients.isEmpty()) {return false;}

        try {
            Appointment meeting = new  Appointment(service);
            meeting.setSubject("Meeting?");
            meeting.setBody(MessageBody.getMessageBodyFromText(""));
            meeting.setStart(startDate);
            meeting.setEnd(endDate);
            meeting.setLocation("Library Group Study Room 01 (08-207)");
            for(String s: recipients) {meeting.getRequiredAttendees().add(s);}
            meeting.save(SendInvitationsMode.SendToAllAndSaveCopy);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private class RedirectionUrlCallback implements IAutodiscoverRedirectionUrl {
        public boolean autodiscoverRedirectionUrlValidationCallback(
                String redirectionUrl) {
            return redirectionUrl.toLowerCase().startsWith("https://");
        }
    }

}
