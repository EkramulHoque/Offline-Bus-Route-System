package com.example.ekram.imagemap4;

/**
 * Created by ekram on 7/29/2015.
 */
public class RowItem {
    private String member_name;
    private String destination;
    private String bus1;
    private String bus2;
    private String bus3;

    private Float x,y;

    private int profile_pic_id;

    public RowItem(String member_name, int profile_pic_id,String destination,String bus1,String bus2,String bus3) {

        this.member_name = member_name;
        this.profile_pic_id = profile_pic_id;
        this.destination=destination;
        this.bus1=bus1;
        this.bus2=bus2;
        this.bus3=bus3;

    }
    public String getMember_name() {
        return member_name;
    }
    public String getDestination() {
        return destination;
    }
    public String getBus1() {
        return bus1;
    }
    public String getBus2() {
        return bus2;
    }
    public String getBus3() {
        return bus3;
    }

    public Float getX() {
        return this.x;
    }
    public Float getY() {
        return this.y;
    }



    public void setX(Float member_name)
    {
        this.x = member_name;
    }
    public void setY(Float member_name) {
        this.y = member_name;
    }


    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }
    public void setDestination(String member_name) {
        this.destination = member_name;
    }
    public void setBus1(String member_name) {
        this.bus1= member_name;
    }
    public void setBus2(String member_name) {

        this.bus2 = member_name;
    }
    public void setBus3(String member_name)
    {
        this.bus3 = member_name;
    }

    public int getProfile_pic_id() {
        return profile_pic_id;
    }

    public void setProfile_pic_id(int profile_pic_id) {
        this.profile_pic_id = profile_pic_id;
    }
}
