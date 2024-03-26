package nsi.assd.exam.nsiassdquiz2020.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import nsi.assd.exam.nsiassdquiz2020.Activity.GridSetFive;
import nsi.assd.exam.nsiassdquiz2020.Activity.GridSetFour;
import nsi.assd.exam.nsiassdquiz2020.Activity.GridSetOne;
import nsi.assd.exam.nsiassdquiz2020.Activity.GridSetSeven;
import nsi.assd.exam.nsiassdquiz2020.Activity.GridSetSix;
import nsi.assd.exam.nsiassdquiz2020.Activity.GridSetThree;
import nsi.assd.exam.nsiassdquiz2020.Activity.GridSetTwo;
import nsi.assd.exam.nsiassdquiz2020.R;

public class AvatarGridAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mUrls;
    private WebViewClickListener mListener;

    // Interface to handle click events
    public interface WebViewClickListener {
        void onWebViewClick(String url);
    }

    public AvatarGridAdapter(Context context, List<String> urls) {
        mContext = context;
        mUrls = urls;

    }

    @Override
    public int getCount() {
        return mUrls.size();
    }

    @Override
    public Object getItem(int position) {
        return mUrls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View gridItemView = convertView;
        if (gridItemView == null) {
            gridItemView = LayoutInflater.from(mContext).inflate(R.layout.grid_item_layout, parent, false);
        }

        TextView gridNumberText = gridItemView.findViewById(R.id.grid_number_text);

        // Set grid number
        gridNumberText.setText(String.valueOf(position + 1)); // Add 1 to position since position is zero-based

        // Set OnClickListener to handle grid item clicks
        gridItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open URL inside WebView
                if (position == 0 && mContext instanceof AppCompatActivity) {
                    AppCompatActivity activity = (AppCompatActivity) mContext;
                    Intent intent = new Intent(activity, GridSetOne.class);
                    activity.startActivity(intent);
                } else if (position == 1 && mContext instanceof AppCompatActivity) {
                    AppCompatActivity activity = (AppCompatActivity) mContext;
                    Intent intent = new Intent(activity, GridSetTwo.class);
                    activity.startActivity(intent);

                }else if (position == 2 && mContext instanceof AppCompatActivity) {
                    AppCompatActivity activity = (AppCompatActivity) mContext;
                    Intent intent = new Intent(activity, GridSetThree.class);
                    activity.startActivity(intent);

                }else if (position == 3 && mContext instanceof AppCompatActivity) {
                    AppCompatActivity activity = (AppCompatActivity) mContext;
                    Intent intent = new Intent(activity, GridSetFour.class);
                    activity.startActivity(intent);

                }else if (position == 4 && mContext instanceof AppCompatActivity) {
                    AppCompatActivity activity = (AppCompatActivity) mContext;
                    Intent intent = new Intent(activity, GridSetFive.class);
                    activity.startActivity(intent);

                }else if (position == 5 && mContext instanceof AppCompatActivity) {
                    AppCompatActivity activity = (AppCompatActivity) mContext;
                    Intent intent = new Intent(activity, GridSetSix.class);
                    activity.startActivity(intent);

                }else if (position == 6 && mContext instanceof AppCompatActivity) {
                    AppCompatActivity activity = (AppCompatActivity) mContext;
                    Intent intent = new Intent(activity, GridSetSeven.class);
                    activity.startActivity(intent);
                }

            }
        });

        return gridItemView;
    }
}
