package com.example.domin;

public class Friends {
    private String account;
    private String name;
    private String path;
    private Integer lately;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getLately() {
        return lately;
    }

    public void setLately(Integer lately) {
        this.lately = lately;
    }

    @Override
    public String toString() {
        return "Friends{" +
                "account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", lately=" + lately +
                '}';
    }
}
