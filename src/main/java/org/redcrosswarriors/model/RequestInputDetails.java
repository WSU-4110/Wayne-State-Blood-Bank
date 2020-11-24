package org.redcrosswarriors.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;



    @Entity
    @Table(name = "requester_details")
    public class RequestInputDetails
    {


        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        @JsonIgnore
        private int id;

        @Column(name="first_name")
        @NotNull
        private String firstName;

        @Column(name="last_name")
        @NotNull
        private String lastName;

        @Column(name="email")
        @NotNull
        private String email;

        @Column(name="phone_number")
        private String phoneNumber;

        @Column(name="blood_type")
        @NotNull
        private String bloodType;

        @Column(name="hospital_name")
        @NotNull
        private String hospitalName;

        @Column(name="street_name")
        private String streetName;


        @Column(name="city_name")
        private String cityName;


        @Column(name="state_name")
        private String stateName;

        @Column(name="zip_code")
        private String zipCode;

        @Column(name="message")
        private String message;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }


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
            return streetName;
        }

        public void setStreet(String streetName) {
            this.streetName = streetName;
        }

        public String getCity() {
            return cityName;
        }

        public void setCity(String cityName) {
            this.cityName = cityName;
        }

        public String getState() {
            return stateName;
        }

        public void setState(String stateName) {
            this.stateName = stateName;
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

