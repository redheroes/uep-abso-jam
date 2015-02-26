package pl.redexperts.uepabsojam.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import pl.redexperts.uepabsojam.R;
import pl.redexperts.uepabsojam.listeners.OnArrayListContextMenuListener;
import pl.redexperts.uepabsojam.model.Jam;


public class AdapterJams extends ArrayAdapter<Jam> {

    private final List<Jam> jams;
    private final Context context;
    private OnArrayListContextMenuListener contextMenuListener;

    public AdapterJams(Context context, int resource, List<Jam> objects) {
        super(context, resource, objects);
        this.context = context;
        this.jams = objects;
    }

    @Override
    public int getCount() {
        return jams.size();
    }

    @Override
    public Jam getItem(int position) {
        return jams.get(position);
    }

    @Override
    public long getItemId(int position) {
        return jams.get(position).getId();
    }

    @Override
    public View getView(final int position, View row, ViewGroup parent) {
        ViewHolder holder;

        Jam jam = getItem(position);
        if (row == null) {

            holder = new ViewHolder();

            row = LayoutInflater.from(context)
                    .inflate(R.layout.fragment_jam_item, parent, false);

            holder.name = (TextView) row.findViewById(R.id.text_jam_name);
            holder.personsNumber = (TextView) row.findViewById(R.id.text_persons_number);
            holder.date = (TextView) row.findViewById(R.id.text_date);



            if (jam != null && jam.getName() != null) {
                holder.name.setText(jam.getName());
            }
            if (jam != null && jam.getPeopleNumber() != null) {
                holder.personsNumber.setText(String.valueOf(jam.getPeopleNumber()));
            }

            if (jam != null && jam.getDate() != null) {
                SimpleDateFormat format = new  SimpleDateFormat("yyyy.MM.dd");
                holder.date.setText(format.format(jam.getDate()));
            }


            row.setTag(holder);

        } else {
            holder = (ViewHolder) row.getTag();
        }

        if (jam != null && jam.getName() != null) {
            holder.name.setText(jam.getName());
        }
        holder.menuButton = (RelativeLayout) row.findViewById(R.id.layout_jam_item);
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
        TextView name, personsNumber, date;
        RelativeLayout menuButton;
    }
}