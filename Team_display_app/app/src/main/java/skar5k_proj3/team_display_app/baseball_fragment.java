/*

    Jaskaran Singh - jsingh10 - 670193440

    This fragment displays the list of baseball teams.

 */

package skar5k_proj3.team_display_app;

import android.app.ListFragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;



public class baseball_fragment extends ListFragment {
    private int current_index = -1;
    private baseball_activity caller;                           //main activity reference

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);                                //retain fragment on orientation change
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        caller = (baseball_activity) activity;                  //save reference to parent activity
    }

    //set up list adapter and allow items to be highlighted on select
    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        setListAdapter(new ArrayAdapter<String>(getActivity(), R.layout.list_item, baseball_activity.baseball_teams));

        if (-1 != current_index)
            getListView().setItemChecked(current_index, true);

    }

    //when an item is clicked, set selected and send a message to display website.
    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {
        if(current_index != -1){
            l.getChildAt(current_index).setBackgroundColor(Color.WHITE);        //reset selection
        }
        if (current_index != pos) {
            current_index = pos;
        }

        l.getChildAt(pos).setBackgroundColor(Color.GRAY);
        l.setItemChecked(current_index, true);
        caller.setWebsite(pos);
    }
}
