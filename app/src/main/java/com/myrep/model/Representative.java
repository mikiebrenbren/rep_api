package com.myrep.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Michael Brennan on 8/26/15.
 */
public class Representative implements Parcelable{

    private String name;
    private String party;
    private String district;
    private String phone;
    private String office;
    private String link;
    private String state;

    public Representative(){

    }

    protected Representative(Parcel in) {
        name = in.readString();
        party = in.readString();
        district = in.readString();
        phone = in.readString();
        office = in.readString();
        link = in.readString();
        state = in.readString();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(party);
        parcel.writeString(district);
        parcel.writeString(phone);
        parcel.writeString(office);
        parcel.writeString(link);
        parcel.writeString(state);
    }

    public static final Creator<Representative> CREATOR = new Creator<Representative>() {
        @Override
        public Representative createFromParcel(Parcel in) {
            return new Representative(in);
        }

        @Override
        public Representative[] newArray(int size) {
            return new Representative[size];
        }
    };

    @Override
    public String toString(){
        String s = "Representative name: "+ this.name +  "\nRepresentative party: " + this.party
                + "\nRepresentative district: " + this.district +  "\nRepresentative state: " + this.state
                + "\nRepresentative phone number: " + this.phone + "\nRepresentative office address: "
                + this.office + "\nRepresentative website link: " + this.link;
        return s;

    }
}
