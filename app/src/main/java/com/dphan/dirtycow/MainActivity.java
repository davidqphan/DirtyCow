package com.dphan.dirtycow;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int MY_PERMISSIONS_REQUEST = 100;
    //private static final String BASE_URL = "https://futurestud.io/";
    private static final String BASE_URL = "https://github.com/";
    private static final String DIRTY_COW = "dirtycow.png";
    private static final String GROOT = "iamroot.png";

    private EditText input;
    private Button showPermissions;
    private Button resetImage;
    private TextView out;
    private ImageView imageView;
    private File picture;
    private Context context;

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

        resetImage = (Button) findViewById(R.id.button_cleancow);
        showPermissions = (Button) findViewById(R.id.button_dirtycow);
        out = (TextView) findViewById(R.id.out);
        imageView = (ImageView) findViewById(R.id.image_dirtycow);

        showPermissions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShellExecuter executer = new ShellExecuter();
                String command = "ls -l /data/local/tmp/dirtycow.png";
                String outp = executer.execute(command);
                out.setText(outp);
                picture = new File("/data/local/tmp/dirtycow.png");
                context = getApplicationContext();
                Picasso.with(context).load(picture).into(imageView);
                Log.d(TAG, "output" + outp);
            }
        });

        resetImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShellExecuter executer = new ShellExecuter();
                String command = "cp /data/local/tmp/images/dirtycow.png /data/local/tmp";
                String result = executer.execute(command);
                out.setText(result);
                picture = new File("/data/local/tmp/dirtycow.png");
                context = getApplicationContext();
                Picasso.with(context).load(picture).into(imageView);
                Log.d(TAG, "output" + result);
            }
        });

    }
}
