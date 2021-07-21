package com.hithaui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Socket;

public class MainActivity extends AppCompatActivity {
    private Socket mSocket;
    Button btnEnter;
    EditText edtName, edtRoom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnEnter= findViewById(R.id.btnEnter);
        edtName= findViewById(R.id.edtName);
        edtRoom= findViewById(R.id.edtRoom);

        ChatAplication chatAplication= (ChatAplication) getApplication();
        mSocket= chatAplication.getmSocket();

//        mSocket.on("reply-client", onServerReply);

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(mSocket.connected());
                mSocket.emit("client-send-room", edtRoom.getText());
                Intent intent= new Intent(getBaseContext(), ChatActivity.class);
                intent.putExtra("fullname", edtName.getText().toString());
                intent.putExtra("room", edtRoom.getText().toString());
                startActivity(intent);

            }
        });
    }

    private Emitter.Listener onServerReply = args -> runOnUiThread(() -> {
        String str= (String) args[0];
        System.out.println(str);
    });
}