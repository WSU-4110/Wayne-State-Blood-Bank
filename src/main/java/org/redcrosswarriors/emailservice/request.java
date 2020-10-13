package org.redcrosswarriors.emailservice;

/**
 * this class holds data members for sending match notification emails
 */
public class request {
    private String name;
    private String email_address;
    private String phone_number;
    private String message;

    request(){}

    /**
     * constructor - easiest way to build object
     * @param name
     * @param email_address
     * @param phone_number
     * @param message
     */
    public request(String name, String email_address, String phone_number, String message) {
        this.name = name;
        this.email_address = email_address;
        this.phone_number = phone_number;
        this.message = message;
    }

    /**
     *copy constructor
     * @param _request
     */
    public request(request _request){
        this.name = _request.name;
        this.email_address = _request.email_address;
        this.phone_number = _request.phone_number;
        this.message = _request.message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail_address() {
        return email_address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }


    /**
     *
     * @return : String of every data member
     */
    public String toString(){
        return name+'\n'+email_address+'\n'+phone_number+'\n'+message;
    }
}
