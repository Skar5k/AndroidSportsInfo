/*

    Jaskaran Singh - jsingh10 - 670193440

    Broadcast receiver which opens up the correct activity based on extras in the intent.

 */
package skar5k_proj3.team_display_app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;




public class Team_disp extends BroadcastReceiver {
    @Override
    public	void onReceive(Context context, Intent intent) {
        String s = intent.getExtras().getString("selection");
        if(s == null){
            return;
        }
        else if(s.equals("Baseball Selected")) {                 //if baseball selected was in the intent, open baseball activity
            Intent i = new Intent(context.getApplicationContext(), baseball_activity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
        else {
            Intent i = new Intent(context.getApplicationContext(), basketball_activity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }


    }

}
