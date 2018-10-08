package com.example.user.netbbangver1;

import java.util.ArrayList;

public class Person {
    private String id; // id
    private String pass; // password
    private String name; // 이름
    private String nickname; // 닉네임
    private String tel; // 전화번호
    private String who; // worker = w, requester = r
    private ArrayList<Cash> cash;
    private int total;

    public Person() {
        id = null;
        pass = null;
        name = null;
        nickname = null;
        tel = null;
        who = null;
        total = 0;
        cash = new ArrayList<Cash>();
    }

    public Person(String id, String pass, String name, String nickname, String tel, String who) {
        this.id = id;
        this.pass = pass;
        this.name = name;
        this.nickname = nickname;
        this.tel = tel;
        this.who = who;
        total = 0;
        cash = new ArrayList<Cash>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public ArrayList<Cash> getCash() {
        return cash;
    }

    public void setCash(Cash cash) {
        this.cash.add(cash);
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
