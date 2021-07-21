package com.hithaui;

import android.app.Application;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.hithaui.Config.Constant;

import java.net.URISyntaxException;

public class ChatAplication extends Application {
    private Socket mSocket;

    {
        try {
            mSocket = IO.socket(Constant.URL_SOCKET_SERVER);
            mSocket.connect();
        } catch (URISyntaxException e) {
        }
    }

    public Socket getmSocket() {
        return mSocket;
    }
}
