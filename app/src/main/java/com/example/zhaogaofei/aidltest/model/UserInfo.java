package com.example.zhaogaofei.aidltest.model;


import android.os.Parcel;
import android.os.Parcelable;

public class UserInfo implements Parcelable {

    private String name;
    private int age;
    private String sex;

    public UserInfo() {

    }

    public UserInfo(Parcel in) {
        name = in.readString();
        age = in.readInt();
        sex = in.readString();
    }

    public static final Creator<UserInfo> CREATOR = new Creator<UserInfo>() {

        @Override
        public UserInfo createFromParcel(Parcel in) {
            return new UserInfo(in);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
        dest.writeString(sex);
    }
}
