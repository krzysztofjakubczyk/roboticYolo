package com.sanbot.net;

/**描述：修改公司信息。
 *
 *  companyId：[IN] 公司ID。
 *
 *  size：[IN] 规模
 *  name：[IN] 公司名称。
 *  logoUrl：[IN] 修改logo。
 *
 * Created by admin on 2017/10/25.
 */

public class CompanyUpdateInfo {
    private int companyId;

    private int size;

    private String name;

    private byte[] nameData;

    private String logoUrl;

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
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

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public byte[] getNameData() {
        return nameData;
    }

    public void setNameData(byte[] nameData) {
        this.nameData = nameData;
    }
}
