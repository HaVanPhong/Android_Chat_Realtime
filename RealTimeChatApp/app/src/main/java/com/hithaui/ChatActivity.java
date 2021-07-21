package com.hithaui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

public class ChatActivity extends AppCompatActivity {
    TextView tvFullName, tvRoom, tvResponse;
    EditText edtMessage;
    Button btnSend;
    private Socket mSocket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        tvFullName= findViewById(R.id.tvFullname);
        tvRoom= findViewById(R.id.tvRoom);
        edtMessage= findViewById(R.id.edtMessage);
        btnSend= findViewById(R.id.btnSend);
        tvResponse= findViewById(R.id.tvResponsse);


        ChatAplication chatAplication= (ChatAplication) getApplication();
        mSocket= chatAplication.getmSocket();

        Intent intent= getIntent();
        tvFullName.setText(intent.getStringExtra("fullname").toString());
        tvRoom.setText(intent.getStringExtra("room").toString());
        mSocket.on("server-send-message", onServerSendMessage);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ChatObject chatObject= new ChatObject(tvFullName.getText().toString(), tvRoom.getText().toString());
//                mSocket.emit("client-send-message", chatObject);

                try {
                    JSONObject jsonObject= new JSONObject();
                    jsonObject.put("fullname", tvFullName.getText().toString());
                    jsonObject.put("message", edtMessage.getText().toString());
                    mSocket.emit("client-send-message", jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSocket.emit("client-leave-room", tvRoom.getText().toString());
    }

    private Emitter.Listener onServerSendMessage= new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvResponse.setText(args[0].toString());
                }
            });
        }
    };
}