package com.myrep.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Michael Brennan on 8/26/15.
 */
public class Representatives extends HashMap<String, ArrayList<Representative>> implements Parcelable{


    protected Representatives(Parcel in) {
    }

    public static final Creator<Representatives> CREATOR = new Creator<Representatives>() {
        @Override
        public Representatives createFromParcel(Parcel in) {
            return new Representatives(in);
        }

        @Override
        public Representatives[] newArray(int size) {
            return new Representatives[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
//
//public class Representatives {
//    public List<Representative> mRepresentatives;
//
//    public List<Representative> getRepresentatives() {
//        return mRepresentatives;
//    }
//
//    public void setRepresentatives(List<Representative> representatives) {
//        mRepresentatives = representatives;
//    }
//}