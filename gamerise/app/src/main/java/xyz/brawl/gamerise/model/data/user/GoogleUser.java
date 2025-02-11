package xyz.brawl.gamerise.model.data.user;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;

public class GoogleUser implements Parcelable {
    private String name;
    private String sessionId;

    public GoogleUser(String name, String sessionId) {
        this.name = name;
        this.sessionId = sessionId;
    }

    protected GoogleUser(Parcel in) {
        this.name = in.readString();
        this.sessionId = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Exclude
    public String getSessionId() {
        return sessionId;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", sessionId='" + sessionId + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.sessionId);
    }

    public static final Creator<GoogleUser> CREATOR = new Creator<GoogleUser>() {
        @Override
        public GoogleUser createFromParcel(Parcel source) {
            return new GoogleUser(source);
        }

        @Override
        public GoogleUser[] newArray(int size) {
            return new GoogleUser[size];
        }
    };
}
