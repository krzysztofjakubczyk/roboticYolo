package com.sanbot.net;

/**
 * Created by QH on 2017/1/16.
 */
public class CheckDevAdmin {
    private String account;

    private String password;

    private int dev_uid;

  
    public int getDev_uid() {
        return dev_uid;
    }

    public void setDev_uid(int dev_uid) {
        this.dev_uid = dev_uid;
    }

    public void setAccount(String account) {
		this.account = account;
	}
	
	public String getAccount(){
		return this.account;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return this.password;
	}
}
