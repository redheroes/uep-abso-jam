package pl.redexperts.uepabsojam.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import pl.redexperts.uepabsojam.R;
import pl.redexperts.uepabsojam.listeners.OnArrayListContextMenuListener;


public class AdapterUsers extends ArrayAdapter<String> {

    private final List<String> glucometers;
    private final Context context;
    private OnArrayListContextMenuListener contextMenuListener;

    public AdapterUsers(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        this.context = context;
        this.glucometers = objects;
    }

    @Override
    public int getCount() {
        return glucometers.size();
    }

    @Override
    public String getItem(int position) {
        return glucometers.get(position);
    }

    @Override
    public View getView(final int position, View row, ViewGroup parent) {
        ViewHolder holder;
        Random generator = new Random();
        int i = generator.nextInt(2);
        Log.d("dupa", String.valueOf(i));
        String jam = getItem(position);

        if (row == null) {

            holder = new ViewHolder();

            row = LayoutInflater.from(context)
                    .inflate(R.layout.fragment_users_item, parent, false);

            holder.name = (TextView) row.findViewById(R.id.text_jam_name);
            holder.icon = (ImageView) row.findViewById(R.id.icon);
            holder.icon2 = (ImageView) row.findViewById(R.id.icon2);

            row.setTag(holder);

        } else {
            holder = (ViewHolder) row.getTag();
        }

        if(i == 0){
            holder.icon.setVisibility(View.GONE);
            holder.icon2.setVisibility(View.VISIBLE);
        }
        else{
            holder.icon.setVisibility(View.VISIBLE);
            holder.icon2.setVisibility(View.GONE);
        }
            holder.name.setText(jam);

        final View finalRow = row;
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contextMenuListener.onContextMenuClicked(position, finalRow);
            }
        });

        return row;
    }

    public void setOnContextMenuClickListener(OnArrayListContextMenuListener contextMenuListener) {
        this.contextMenuListener = contextMenuListener;
    }

    private static class ViewHolder {
        TextView name;
        ImageView icon, icon2;
    }
}