package org.redcrosswarriors.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



    @Entity
    @Table(name = "requester_details")
    public class RequestInputDetails
    {

        @Id
        private int id;

        @Column
        @NotNull
        private String firstName;

        @Column
        @NotNull
        private String lastName;

        @Column
        @NotNull
        private String email;

        @Column
        private String phoneNumber;

        @Column
        @NotNull
        private String bloodType;

        @Column
        @NotNull
        private String hospitalName;

        @Column
        private String streetN;


        @Column
        private String cityN;


        @Column
        private String stateN;

        @Column
        private String zipCode;

        @Column
        private String message;

//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }


        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getBloodType() {
            return bloodType;
        }

        public void setBloodType(String bloodType) {
            this.bloodType = bloodType;
        }

        public String getHospitalName() {
            return hospitalName;
        }

        public void setHospitalName(String hospitalName) {
            this.hospitalName = hospitalName;
        }

        public String getStreet() {
            return streetN;
        }

        public void setStreet(String street) {
            this.streetN = streetN;
        }

        public String getCity() {
            return cityN;
        }

        public void setCity(String city) {
            this.cityN = cityN;
        }

        public String getState() {
            return stateN;
        }

        public void setState(String state) {
            this.stateN = stateN;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }



    }

