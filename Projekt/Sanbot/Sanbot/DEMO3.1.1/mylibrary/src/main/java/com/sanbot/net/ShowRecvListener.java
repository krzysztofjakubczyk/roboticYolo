package com.sanbot.net;

/**
 * Created by admin on 2019/1/18.
 */

public interface ShowRecvListener {
    void showRecv(int cmd, int result, Object obj, long seq);
}
