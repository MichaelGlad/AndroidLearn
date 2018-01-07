package net.glm.retrofittest;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import java.util.List;

/**
 * Created by Michael on 20/08/2017.
 */

public class GitHubRepoAdapter extends ArrayAdapter<GitHubRepo> {

    private Context context;
    private List<GitHubRepo> gitHubRepoList;

    public GitHubRepoAdapter(Context context, List<GitHubRepo> gitHubRepoList) {
        super(context, R.layout.list_item_pagination, gitHubRepoList);

        this.context = context;
        this.gitHubRepoList = gitHubRepoList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        TextView tvAdapter;

        if (row == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_item_pagination, parent, false);
            tvAdapter = (TextView) row.findViewById(R.id.list_item_pagination_text);
            row.setTag(tvAdapter);
        }else tvAdapter = (TextView) row.getTag();

        GitHubRepo item = gitHubRepoList.get(position);
        String message = item.getName();
        tvAdapter.setText(message);

        return row;
    }
}