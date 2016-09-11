package apiworld.fashionex.smartcart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

import apiworld.fashionex.R;

public class RecoGridAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<CartListItem> items;

    public RecoGridAdapter(Context context, ArrayList<CartListItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(this.context);
            v = vi.inflate(R.layout.grid_item, null);
        }

        CartListItem p = items.get(position);

        if (p != null) {
            ImageView tt1 = (ImageView) v.findViewById(R.id.item_image);

            if (tt1 != null) {
                tt1.setImageResource(p.getImage());
            }
        }

        return v;
    }
}