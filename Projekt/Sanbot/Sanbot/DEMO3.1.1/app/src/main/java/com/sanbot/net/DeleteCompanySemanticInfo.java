package com.sanbot.net;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/12/21.
 */

public class DeleteCompanySemanticInfo {
    private int companyId;

    private ArrayList<Integer> indexs;

    public List<Integer> getIndexs() {
        return indexs;
    }

    public void setIndexs(ArrayList<Integer> indexs) {
        this.indexs = indexs;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}
