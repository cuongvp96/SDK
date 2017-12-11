package com.vancuong.testcall.API;

import com.vancuong.testcall.Photo;
import com.vancuong.testcall.model.DailyLoginOnline;

import java.util.ArrayList;

/**
 * Created by vancu on 20/11/2017.
 */

public interface OnCallBack {
    void onSuccessResponse(ArrayList<DailyLoginOnline> result);
    void onSuccessPost(String result);
}
