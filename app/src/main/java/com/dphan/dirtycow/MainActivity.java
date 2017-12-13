package com.dphan.dirtycow;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int MY_PERMISSIONS_REQUEST = 100;
    //private static final String BASE_URL = "https://futurestud.io/";
    private static final String BASE_URL = "https://github.com/";
    private static final String DIRTY_COW = "dirtycow.png";
    private static final String GROOT = "iamroot.png";

    private EditText input;
    private Button btn;
    private TextView out;
    private String command;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // show a READ_ONLY image : dirtycow.png
        // on buttonClick -> download binaries from github -> run the commands on phone
        // show evidence : overwrite dirtycow.png with iamgroot.png image
        // update screen

        // use OkHTTP and Retrofit2 to interface with Github repository to download images
        // upon startup, have

        btn = (Button) findViewById(R.id.button_dirtycow);
        out = (TextView) findViewById(R.id.out);
        imageView = (ImageView) findViewById(R.id.image_dirtycow);

        File picture = new File("/data/local/tmp/dirtycow.png");

        Context context = getApplicationContext();

        Picasso.with(context).load(picture).into(imageView);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShellExecuter executer = new ShellExecuter();
                //command = input.getText().toString();

                command = "ls -l /data/local/tmp/dirtycow.png";
                String outp = executer.execute(command);
                out.setText(outp);
                Log.d(TAG, "output" + outp);
            }
        });
    }
}
