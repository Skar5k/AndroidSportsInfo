/*
    Jaskaran Singh - jsingh10 - 670193440

    Team Display App:
        This app displays info about either NBA or MLB teams. You can switch between them with the
        change sports button on the menu. When a team is selected, you are navigated to the team's
        website.

    This activity displays basketball teams and websites.

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


//this is largely the same as baseball_activity. See comments there for info
public class basketball_activity extends AppCompatActivity {

    public static String[] basketball_teams;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.basketball_activity);
        basketball_teams = getResources().getStringArray(R.array.Basketball_team_arr);
        ActivityCompat.requestPermissions(basketball_activity.this, new String[]{android.Manifest.permission.INTERNET}, 0);

        FrameLayout f = (FrameLayout) findViewById(R.id.listFragment);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.weight = 3f;
        f.setLayoutParams(params);

        FragmentTransaction t = getFragmentManager().beginTransaction();
        ListFragment fr = new basketball_fragment();
        t.replace(R.id.listFragment,fr);
        t.commit();
    }

    public void setWebsite(int ind){
        int x = getResources().getConfiguration().orientation;
        FragmentTransaction t = getFragmentManager().beginTransaction();
        basketball_web_frag web = new basketball_web_frag();

        web.setCurrent_index(ind);
        if(x == 1) {
            FrameLayout f = (FrameLayout) findViewById(R.id.listFragment);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.weight = 3f;
            f.setLayoutParams(params);
            t.replace(R.id.listFragment, web);
        }
        else{
            FrameLayout f = (FrameLayout) findViewById(R.id.listFragment);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.weight = 1f;
            f.setLayoutParams(params);
            t.add(R.id.webFrame,web);
        }
        int stacksize = getFragmentManager().getBackStackEntryCount();
        if(stacksize > 0) {
            getFragmentManager().popBackStack();

        }
        t.addToBackStack(null);
        t.commit();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

        } else {
            Toast t = Toast.makeText(getApplicationContext(), R.string.internet_permission_not_granted, Toast.LENGTH_SHORT);
            t.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();                                  //inflate menu_bar.xml
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.change_sport:
                Intent i = new Intent(getApplicationContext(), baseball_activity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

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
