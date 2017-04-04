package skar5k_proj3.cs478_project3;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button basketballButton;
    Button baseballButton;
    Button selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        baseballButton = (Button) findViewById(R.id.baseball_button);
        basketballButton = (Button) findViewById(R.id.basketball_button);
        basketballButton.setOnClickListener(basketball_button_listener);
        baseballButton.setOnClickListener(baseball_button_listener);

    }

    View.OnClickListener basketball_button_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            selected = basketballButton;
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{getString(R.string.permission_string)},0);
        }
    };

    View.OnClickListener baseball_button_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            selected = baseballButton;
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{getString(R.string.permission_string)},0);
        }
    };


    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        if(requestCode == 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Intent i = new Intent(getString(R.string.intent_string));
            i.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            if(selected == baseballButton){
                i.putExtra(getString(R.string.selection_string),getString(R.string.selection_baseball));
            }
            else
                i.putExtra(getString(R.string.selection_string),getString(R.string.selection_basketball));
            sendOrderedBroadcast(i,null);
        }
        else{
            Toast t = Toast.makeText(getApplicationContext(), R.string.permission_fail,Toast.LENGTH_SHORT);
            t.show();
        }

    }
}
