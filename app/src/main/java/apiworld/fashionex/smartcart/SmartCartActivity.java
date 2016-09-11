package apiworld.fashionex.smartcart;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import apiworld.fashionex.R;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SmartCartActivity extends AppCompatActivity {

    /* Declare activity variables here */
    private static final String TAG = "CameraActivity";
    private Context context;
    private ListView listview;
    private GridView gridview;

    private ArrayList<CartListItem> items;
    private ArrayList<CartListItem> reco;

    /* Declare activity variables above */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.context = this.getApplicationContext();
        setContentView(R.layout.activity_smartcart);

        this.listview = (ListView) this.findViewById(R.id.listview);
        items = new ArrayList<CartListItem>();
        items.add(new CartListItem(R.drawable.a, "Zara Man Leather Jacket", "$30.00", "", ""));
        items.add(new CartListItem(R.drawable.b, "Aviator Sunglass from H&M", "$34.00", "", ""));

        CartListAdapter adapter = new CartListAdapter(this.getApplicationContext(), items);
        listview.setAdapter(adapter);

        this.gridview = (GridView) this.findViewById(R.id.gridview);
        reco = new ArrayList<CartListItem>();
        reco.add(new CartListItem(R.drawable.d, "Plain White Tee", "$19.00", "", ""));
        reco.add(new CartListItem(R.drawable.f, "Dita Sunglass Men", "$54.00", "", ""));
        reco.add(new CartListItem(R.drawable.h, "Ralph Lauren Formal (Slim)", "$54.00", "", ""));

        RecoGridAdapter grid_adapter = new RecoGridAdapter(this.getApplicationContext(), reco);
        gridview.setAdapter(grid_adapter);
        gridview.setOnItemClickListener(click_listener);

    }

    private AdapterView.OnItemClickListener click_listener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            CartListItem item = reco.get(position);
            reco.remove(position);

            items.add(item);

            CartListAdapter adapter = new CartListAdapter(context, items);
            listview.setAdapter(adapter);

            RecoGridAdapter grid_adapter = new RecoGridAdapter(context, reco);
            gridview.setAdapter(grid_adapter);

            Toast.makeText(context, "Item added to your shopping cart!", Toast.LENGTH_LONG).show();
        }
    };
}
