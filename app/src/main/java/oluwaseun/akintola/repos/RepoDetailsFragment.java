package oluwaseun.akintola.repos;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;

import java.util.ArrayList;

import oluwaseun.akintola.repos.model.Contributor;
import oluwaseun.akintola.repos.model.Repo;

/**
 * Created by Akintola on 9/03/2017.
 */
public class RepoDetailsFragment extends Fragment {


    private TextView repoNameTV;
    private TextView forksTV;
    private TextView watchersTV;
    private ListView contributorsLV;

    private AQuery aQuery;

    private Repo repo;

    private int position;

    private ArrayList<Contributor> contributors;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getArguments();
        repo = extras.getParcelable(ReposFragment.EXTRA_REPO);
        position = extras.getInt(ReposFragment.EXTRA_POSITION);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repo_details, container, false);
        aQuery = new AQuery(view);
        initViews(view);
        populateData();
        return view;
    }

    private void initViews(View parent) {
        repoNameTV = (TextView) parent.findViewById(R.id.repoNameTV);
        forksTV = (TextView) parent.findViewById(R.id.forksTV);
        watchersTV = (TextView) parent.findViewById(R.id.watchersTV);
        contributorsLV = (ListView) parent.findViewById(R.id.contributorsLV);
    }

    private void populateData() {
        if(repo != null){
            repoNameTV.setText(repo.getName());
            forksTV.setText("" + repo.getForks());
            watchersTV.setText("" + repo.getUrl());

            String url = repo.getContributorsUrl();
            aQuery.ajax(url, JSONArray.class, this, "contributorsCallback");
        }
    }

    public void contributorsCallback(String url, JSONArray array, AjaxStatus status){
        if(array != null && array.length() > 0){
            contributors = ReposHelper.parseContributors(array);
            contributorsLV.setAdapter(new ContributorsAdapter());
            aQuery.id(R.id.progressBar).visibility(View.GONE);
        }
    }

    private class ContributorsAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return contributors.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = convertView;
            ViewHolder holder;
            if(view == null){
                view = getActivity().getLayoutInflater().inflate(R.layout.contributor_list_item, parent, false);
                holder = new ViewHolder();
                holder.contributorTV = (TextView) view.findViewById(R.id.contributorTV);
                holder.contributorIV = (ImageView) view.findViewById(R.id.contributorIV);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            Contributor cont = contributors.get(position);
            holder.contributorTV.setText(cont.getName());

            aQuery.id(holder.contributorIV).image(cont.getImageUrl(), true, true);

            return view;
        }

        class ViewHolder{
            ImageView contributorIV;
            TextView contributorTV;
        }
    }

    public int getSelectedIndex(){
        return position;
    }
}
