package com.sanbot.demo.app.country;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 国家号码
 */

public class Country implements Parcelable {

    private int index;
    //国家号码
    private int code;
    //国家名称
    private String name;
    //区域id
    private int areaCode;
    //简称
    private String shortName;
    //拼音
    private String pinyin;
    //首字母
    private String letter;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(int areaCode) {
        this.areaCode = areaCode;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public Country() {
    }

    public Country(int code, String name, int areaCode) {
        this.code = code;
        this.name = name;
        this.areaCode = areaCode;
    }

    public static final Creator<Country> CREATOR = new Creator<Country>() {
        public Country createFromParcel(Parcel in) {
            Country country = new Country();
            country.setIndex(in.readInt());
            country.setCode(in.readInt());
            country.setName(in.readString());
            country.setAreaCode(in.readInt());
            country.setShortName(in.readString());
            country.setPinyin(in.readString());
            country.setLetter(in.readString());
            return country;
        }

        public Country[] newArray(int size) {
            return new Country[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(index);
        dest.writeInt(code);
        dest.writeString(name);
        dest.writeInt(areaCode);
        dest.writeString(shortName);
        dest.writeString(pinyin);
        dest.writeString(letter);
    }


}
