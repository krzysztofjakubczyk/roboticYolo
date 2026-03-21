package com.sanbot.net;

/**
 * Created by admin on 2018/1/2.
 */

public class CompanyServerNote {
    private int companyId;

    private String account;

    private String name;

    private int uid;

	private int version;
	
    private byte[] nameData;

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        if (nameData != null) {
            name = new String(nameData);
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
	
	public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
