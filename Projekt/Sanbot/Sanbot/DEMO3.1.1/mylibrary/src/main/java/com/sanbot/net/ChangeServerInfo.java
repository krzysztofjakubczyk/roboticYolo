package com.sanbot.net;

/**
 * Created by admin on 2018/1/2.
 */

public class ChangeServerInfo {
    private int companyId;

    private String name;

    private String newName;

    private String serverAccount;

    private String newServerAccount;

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getServerAccount() {
        return serverAccount;
    }

    public void setServerAccount(String serverAccount) {
        this.serverAccount = serverAccount;
    }

    public String getNewServerAccount() {
        return newServerAccount;
    }

    public void setNewServerAccount(String newServerAccount) {
        this.newServerAccount = newServerAccount;
    }
}
