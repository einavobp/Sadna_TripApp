package proj.sadna.mta.sadna_2017.app.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.List;

import proj.sadna.mta.sadna_2017.R;
import proj.sadna.mta.sadna_2017.app.Activities.PathActivity;
import proj.sadna.mta.sadna_2017.app.Adapters.BaseSwipListAdapter;
import proj.sadna.mta.sadna_2017.app.Models.PathModelRec;

/**
 * Created by Heiman on 8/25/2017.
 */

public class TopTripsFragment extends Fragment
{

    private SwipeMenuListView mListView;
    private TopTripsAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {// Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_trips, container, false);
        mListView = (SwipeMenuListView) view.findViewById(R.id.top_trips_lv);

        mAdapter = new TopTripsAdapter(PathModelRec.getAll());
        mListView.setAdapter(this.mAdapter);
        return view;
    }

    public class TopTripsAdapter extends BaseSwipListAdapter
    {

        private List<PathModelRec> data;

        public TopTripsAdapter(List<PathModelRec> data)
        {
            this.data = data;
        }

        @Override
        public int getCount()
        {
            return this.data.size();
        }

        @Override
        public PathModelRec getItem(int position)
        {
            return this.data.get(position);
        }

        @Override
        public long getItemId(int position)
        {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            if (convertView == null)
            {
                convertView = View.inflate(getContext(), R.layout.item_top_trips, null);
                new ViewHolder(convertView);
            }

            ViewHolder holder = (ViewHolder) convertView.getTag();
            final PathModelRec dataItem = getItem(position);
            holder.click.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Intent intent = new Intent(getContext(), PathActivity.class);
                    intent.putExtra("rec_id", dataItem.getId());
                    getActivity().startActivity(intent);
                }
            });
            holder.setText(dataItem.getName());
            holder.setRating(dataItem.getRate());
            return convertView;
        }
    }

    public class ViewHolder
    {

        private final RelativeLayout click;
        TextView nameView;
        ImageView firstStar;
        ImageView secondStar;
        ImageView thirdStar;

        public ViewHolder(View view)
        {
            this.nameView = (TextView) view.findViewById(R.id.tv_name);
            this.firstStar = (ImageView) view.findViewById(R.id.black_star_1);
            this.secondStar = (ImageView) view.findViewById(R.id.black_star_2);
            this.thirdStar = (ImageView) view.findViewById(R.id.black_star_3);
            this.click = (RelativeLayout) view.findViewById(R.id.click);
            view.setTag(this);
        }

        public void setText(String text)
        {
            this.nameView.setText(text);
        }

        public void setRating(double rating)
        {
            if (rating >= 1)
            {
                this.firstStar.setImageResource(R.drawable.yellow_star);
            }
            if (rating >= 2)
            {
                this.secondStar.setImageResource(R.drawable.yellow_star);
            }
            if (rating >= 3)
            {
                this.thirdStar.setImageResource(R.drawable.yellow_star);
            }
        }

    }
}


