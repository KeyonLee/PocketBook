package com.example.keyon.moneymanager.model;

public class Tb_pwd         // 密码数据表实体类
{
    private String password;

    public Tb_pwd(){
        super();
    }

    public Tb_pwd(String password){
        super();
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
