/*
    Jaskaran Singh - jsingh10 - 670193440

    Team Display App:
        This app displays info about either NBA or MLB teams. You can switch between them with the
        change sports button on the menu. When a team is selected, you are navigated to the team's
        website.

    This activity is for baseball teams.
 */

package skar5k_proj3.team_display_app;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;



public class baseball_activity extends AppCompatActivity {

    public static String[] baseball_teams;                                  //stores team list


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.baseball_activity);
        baseball_teams = getResources().getStringArray(R.array.Baseball_team_arr);
        ActivityCompat.requestPermissions(baseball_activity.this, new String[]{android.Manifest.permission.INTERNET}, 0);           //get required resource and ask for permission to use internet

        FrameLayout f = (FrameLayout) findViewById(R.id.listFragment_baseball);                                                     //set frame weight so initially it takes up entire screen
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.weight = 3f;
        f.setLayoutParams(params);

        FragmentTransaction t = getFragmentManager().beginTransaction();                                                            //add fragment to frame
        ListFragment fr = new baseball_fragment();
        t.replace(R.id.listFragment_baseball,fr);
        t.commit();
    }

    //Called by baseball_fragment. Communicates to the web fragment what site to load
    public void setWebsite(int ind){
        int x = getResources().getConfiguration().orientation;                          //get orientation to adjust weights
        FragmentTransaction t = getFragmentManager().beginTransaction();
        baseball_web_frag web = new baseball_web_frag();
        web.setCurrent_index(ind);                                                      //set website index
        if(x == 1) {                                                                    //change weights based on orientation, then add to frame or replace (if horizontal orientation)
            FrameLayout f = (FrameLayout) findViewById(R.id.listFragment_baseball);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.weight = 3f;
            f.setLayoutParams(params);
            t.replace(R.id.listFragment_baseball, web);
        }
        else{
            FrameLayout f = (FrameLayout) findViewById(R.id.listFragment_baseball);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.weight = 1f;
            f.setLayoutParams(params);
            t.add(R.id.webFrame_baseball,web);
        }

        int stacksize = getFragmentManager().getBackStackEntryCount();                  //only add one web to backstack. If one already exists, pop it first
        if(stacksize > 0) {
            getFragmentManager().popBackStack();
        }
        t.addToBackStack(null);
        t.commit();

    }

    //called after user gives or denies internet permission
    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

        } else {
            Toast t = Toast.makeText(getApplicationContext(), R.string.internet_permission_not_granted, Toast.LENGTH_SHORT);
            t.show();
        }
    }

    //creates menu in action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();                                  //inflate menu_bar.xml
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    //if menu button is selected, swap to basketball activity.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.change_sport:
                Intent i = new Intent(getApplicationContext(), basketball_activity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_TASK);  //do not add new activity to backstack. Also clear current if needed
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //when back is pressed, pop backstack first if possible
    @Override
    public void onBackPressed(){
        int stack = getFragmentManager().getBackStackEntryCount();
        if(stack > 0){
            getFragmentManager().popBackStack();
        }
        else
            super.onBackPressed();
    }

}
