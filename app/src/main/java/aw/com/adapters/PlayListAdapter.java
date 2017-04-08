package aw.com.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import aw.com.opusplayer.R;

/**
 * Created by Adit on 4/8/2017.
 */

public class PlayListAdapter extends BaseAdapter {
    private final Context context;
    private final LayoutInflater inflater;
    private List<String> playList;

    public PlayListAdapter(Context context, List<String> playList) {
        this.context = context;
        inflater = LayoutInflater.from(this.context);
        this.playList = playList;
    }

    @Override
    public int getCount() {
        if (playList == null) {
            return 0;
        }
        return playList.size();
    }

    @Override
    public Object getItem(int position) {
        if (playList == null) {
            return null;
        }
        return playList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_opus_file, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.fileNameTextView.setText(playList.get(position));
        return convertView;
    }

    private static class ViewHolder {
        protected TextView fileNameTextView;

        public ViewHolder(View item) {
            fileNameTextView = (TextView) item.findViewById(R.id.title);
        }
    }
}
