/*

    Jaskaran Singh - jsingh10 - 670193440

    This fragment displays the list of basketball teams.
    The code is largely the same as baseball_fragment.
 */
package skar5k_proj3.team_display_app;

import android.app.ListFragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class basketball_fragment extends ListFragment {

    private int current_index = -1;
    private basketball_activity caller;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        caller = (basketball_activity) activity;
    }


    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        setListAdapter(new ArrayAdapter<String>(getActivity(), R.layout.list_item, basketball_activity.basketball_teams));

        if (-1 != current_index)
            getListView().setItemChecked(current_index, true);

    }



    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {
        if(current_index != -1){
            l.getChildAt(current_index).setBackgroundColor(Color.WHITE);
        }
        if (current_index != pos) {
            current_index = pos;
        }
        // Indicates the selected item has been checked
        l.getChildAt(pos).setBackgroundColor(Color.GRAY);
        l.setItemChecked(current_index, true);
        caller.setWebsite(pos);
    }

}
