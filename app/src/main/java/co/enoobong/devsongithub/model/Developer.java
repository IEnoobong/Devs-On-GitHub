package co.enoobong.devsongithub.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by owner on 5/3/2017.
 */

public class Developer implements Parcelable {
    @SerializedName("avatar_url")
    private String imageUrl;
    @SerializedName("html_url")
    private String profileUrl;
    @SerializedName("login")
    private String username;

    public Developer() {
    }

    protected Developer(Parcel in) {
        imageUrl = in.readString();
        profileUrl = in.readString();
        username = in.readString();
    }

    public static final Creator<Developer> CREATOR = new Creator<Developer>() {
        @Override
        public Developer createFromParcel(Parcel in) {
            return new Developer(in);
        }

        @Override
        public Developer[] newArray(int size) {
            return new Developer[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(imageUrl);
        parcel.writeString(profileUrl);
        parcel.writeString(username);
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public String getUsername() {
        return username;
    }
}
