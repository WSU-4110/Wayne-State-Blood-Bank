package org.redcrosswarriors.emailservice;

/**
 * this class holds data members for sending match notification emails
 */
public class request {
    String name;
    String email_address;
    String phone_number;
    String message;

    request(){};

    /**
     *copy constructor
     * @param _request
     */
    request(request _request){
        this.name = _request.name;
        this.email_address = _request.email_address;
        this.phone_number = _request.phone_number;
        this.message = _request.message;
    }

    /**
     *
     * @return : String of every data member
     */
    public String toString(){
        return name+'\n'+email_address+'\n'+phone_number+'\n'+message;
    }
}
