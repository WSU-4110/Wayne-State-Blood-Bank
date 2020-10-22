package org.redcrosswarriors.emailservice;

/**
 * this class holds data members for sending match notification emails
 */
public class Request {
    private String name;
    private String emailAddress;
    private String phoneNumber;
    private String message;


    /**
     * constructor - easiest way to build object
     *
     * @param name
     * @param emailAddress
     * @param phoneNumber
     * @param message
     */
    public Request(String name, String emailAddress, String phoneNumber, String message) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.message = message;
    }

    /**
     * copy constructor
     *
     * @param _request
     */
    public Request(Request _request) {
        this.name = _request.name;
        this.emailAddress = _request.emailAddress;
        this.phoneNumber = _request.phoneNumber;
        this.message = _request.message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }


    /**
     * @return : String of every data member
     */
    public String toString() {
        return name + '\n' + emailAddress + '\n' + phoneNumber + '\n' + message;
    }
}
