package skar5k_proj3.broadcast_service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;



public class MyReceiver extends BroadcastReceiver {
    @Override
    public	void onReceive(Context context, Intent intent){
        Bundle x = intent.getExtras();
        String s = x.getString("selection");

        Toast t = Toast.makeText(context, s , Toast.LENGTH_LONG);
        t.show();
    }

}
