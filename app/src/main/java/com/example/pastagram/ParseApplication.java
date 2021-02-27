package com.example.pastagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(Post.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("UFV8oJX6NVnoILwocSfxBzdR9Cj5T9LEEGKXE8VL")
                .clientKey("ZuPoKloXrfuIT5TxHgwFYx6No4oR94AhhIooaaUL")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
