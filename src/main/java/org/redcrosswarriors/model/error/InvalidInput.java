package org.redcrosswarriors.model.error;

public class InvalidInput {
    // name of the field with the error
    private String field;

    // error message for that field
    private String error;

    public InvalidInput(String field, String error){
        this.field = field;
        this.error = error;
    }

    public void setField(String field){
        this.field = field;
    }

    public void setError(String error){
        this.error = error;
    }

    public String getField(){
        return this.field;
    }

    public String getError(){
        return this.error;
    }

}
