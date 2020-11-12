package org.redcrosswarriors.emailservice;

import java.io.FileNotFoundException;

public abstract class EmailBuilder {
    protected String[] recipients = null;
    protected String htmlCode = null;
    protected String subject;


    public abstract EmailBuilder setHtmlCode(String htmlCode) throws FileNotFoundException;

    public abstract EmailBuilder setSubject(String subject);

    public abstract EmailBuilder setRecipients(String[] recipients);

    public Email build() {
        if (htmlCode == null || recipients == null || subject == null)
            try {
                throw new Exception("invalid Email creation");
            } catch (Exception e) {
                e.printStackTrace();
            }
        else
            return new Email(htmlCode, recipients, subject);
        return null;
    }

}
