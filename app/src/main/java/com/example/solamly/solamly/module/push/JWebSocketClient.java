package com.example.solamly.solamly.module.push;

import android.util.Log;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

import okhttp3.WebSocketListener;

/**
 * @Author: SOLAMLY
 * @Date: 2019/8/30 0030 10:54
 * @Description:
 */
public class JWebSocketClient  extends WebSocketClient {
    public JWebSocketClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        Log.e("JWebSClientService", "onOpen");
    }

    @Override
    public void onMessage(String message) {
        Log.e("JWebSClientService", message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        Log.e("JWebSClientService", "onClose");
    }

    @Override
    public void onError(Exception ex) {
        Log.e("JWebSClientService", "onError");
    }
}
