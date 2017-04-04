/*

    Jaskaran Singh - jsingh10 - 670193440

    This fragment displays the website of a basketball team selected in basketball_fragment.
    The code is largely the same as baseball_fragment
 */

package skar5k_proj3.team_display_app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;


public class basketball_web_frag extends Fragment {

    private int current_index = -1;
    private int length = 0;
    private WebView site = null;
    private String[] websites;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        websites = getResources().getStringArray(R.array.Basketball_sites_arr);
        setRetainInstance(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        site = (WebView) getActivity().findViewById(R.id.web_view);
        length = basketball_activity.basketball_teams.length;
        showSiteAtIndex(current_index);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.website, container, false);
    }

    public void setCurrent_index(int i){
        current_index = i;
        showSiteAtIndex(i);
    }

    public void showSiteAtIndex(int newIndex) {
        if (newIndex < 0 || newIndex >= length)
            return;

        current_index = newIndex;
        site.loadUrl(websites[newIndex]);
    }


}
