package com.crypto.socketweb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;

import xute.storyview.StoryModel;
import xute.storyview.StoryView;


public class MainActivity extends AppCompatActivity {
    private Socket socket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

 // socketJoin();

        StoryView storyView = findViewById(R.id.storyView);
    //    storyView.setActivityContext(this);
        storyView.resetStoryVisits();
        ArrayList<StoryModel> uris = new ArrayList<>();
        uris.add(new StoryModel("https://picsum.photos/200/300", "Ankit Kumar", "12:00 PM"));
        uris.add(new StoryModel("https://cdn.pixabay.com/photo/2015/04/19/08/32/marguerite-729510__340.jpg", "Panda Man", "01:00 AM"));
        uris.add(new StoryModel("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRCv_Gx6Fde6mja_lLmll0fzrxRvcKLHGrPxnqMrQLWKqXi9IYy&usqp=CAU", "Steve", "Yesterday"));
        uris.add(new StoryModel("https://english.mathrubhumi.com/polopoly_fs/1.3885588!/image/image.jpg_gen/derivatives/landscape_607/image.jpg", "Grambon", "10:15 PM"));
        storyView.setImageUris(uris);

    }



    private void socketJoin(){
        try {
            socket = IO.socket("http://13.233.136.56:8080");

            socket.connect();

            String connect = String.valueOf(socket.connect());

            if(connect !=null){
                Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
                System.out.println("connected");
            }else {
                Toast.makeText(this, "Not Connected", Toast.LENGTH_SHORT).show();
                System.out.println("Not connected");
            }

            hello();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


    }

    private void hello() {

        socket.on("test", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];

                try {
                    String message =data.getString("greeting");

                    System.out.println(message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                System.out.println("message");
            }


        });


    }

}