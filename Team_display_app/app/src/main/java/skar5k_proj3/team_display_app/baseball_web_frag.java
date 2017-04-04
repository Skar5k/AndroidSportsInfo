/*

    Jaskaran Singh - jsingh10 - 670193440

    This fragment displays the website of a selected baseball team.

 */
package skar5k_proj3.team_display_app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;



public class baseball_web_frag extends Fragment {
    private int current_index = -1;
    private int length = 0;                         //number of teams total (6)
    private WebView site = null;                    //reference to webview where site is displayed
    private String[] websites;                      //list of sites

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        websites = getResources().getStringArray(R.array.Baseball_sites_arr);       //get site resource
        setRetainInstance(true);                                                    //save fragment when orientation changes
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        site = (WebView) getActivity().findViewById(R.id.web_view_baseball);        //get webview reference, length, and show passed site
        length = baseball_activity.baseball_teams.length;
        showSiteAtIndex(current_index);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.web_baseball, container, false);
    }

    public void setCurrent_index(int i){                                            //sets current index and navigates to that site
        current_index = i;
        showSiteAtIndex(i);
    }

    public void showSiteAtIndex(int newIndex) {                                     //display website and index
        if (newIndex < 0 || newIndex >= length)
            return;

        current_index = newIndex;
        site.loadUrl(websites[newIndex]);
    }


}
