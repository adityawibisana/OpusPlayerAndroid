package aw.com.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import aw.com.events.OpusFileFoundEvent;
import aw.com.opusplayer.R;

/**
 * Created by Adit on 4/8/2017.
 */

public class PlayListAdapter extends BaseAdapter {
    private final Context context;
    private final LayoutInflater inflater;
    private List<File> playList;
    private int selectedIndex;

    public PlayListAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(this.context);
        playList = new ArrayList<File>();
        EventBus.getDefault().register(this);

        selectedIndex = -1;
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

    public void setSelectedIndex(int index) {
        selectedIndex = index;
        notifyDataSetChanged();
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

        if (selectedIndex!=-1 && position==selectedIndex) {
            holder.fileNameTextView.setTextColor(context.getResources().getColor(R.color.colorAccent));
        }
        else {
            holder.fileNameTextView.setTextColor(context.getResources().getColor(R.color.textColor));
        }

        holder.fileNameTextView.setText(playList.get(position).getName());
        return convertView;
    }

    private static class ViewHolder {
        protected TextView fileNameTextView;

        public ViewHolder(View item) {
            fileNameTextView = (TextView) item.findViewById(R.id.title);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnOpusFileFoundEvent(OpusFileFoundEvent event)
    {
        playList.add(event.getOpusFile());
    }
}
