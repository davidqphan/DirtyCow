package com.dphan.dirtycow;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String DIRTY_COW_PATH = "/data/local/tmp/dirtycow.png";

    private Button showPermissions;
    private TextView permissions;
    private ImageView dirtyCowImage;
    private File picture;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showPermissions = (Button) findViewById(R.id.button_dirtycow);
        permissions = (TextView) findViewById(R.id.out);
        dirtyCowImage = (ImageView) findViewById(R.id.image_dirtycow);

        showPermissions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShellExecuter executer = new ShellExecuter();

                picture = new File(DIRTY_COW_PATH);
                String command = "ls -l " + DIRTY_COW_PATH;

                String readPermissions = executer.execute(command);
                permissions.setText(readPermissions);

                context = getApplicationContext();
                Picasso.with(context)
                        .load(picture)
                        .memoryPolicy(MemoryPolicy.NO_CACHE)
                        .into(dirtyCowImage);

                Log.d(TAG, "output" + readPermissions);
            }
        });
    }
}
