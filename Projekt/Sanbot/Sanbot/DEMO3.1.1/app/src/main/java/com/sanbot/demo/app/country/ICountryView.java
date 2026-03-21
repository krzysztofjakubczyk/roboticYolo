package com.sanbot.demo.app.country;


import com.sanbot.demo.app.IBaseView;

import java.util.List;

public interface ICountryView extends IBaseView {

    void setAdapter(List<Country> list);

}
