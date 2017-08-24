package proj.sadna.mta.sadna_2017.App.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import proj.sadna.mta.sadna_2017.App.Activities.SiteOverviewActivity;
import proj.sadna.mta.sadna_2017.App.Models.SiteModel;
import proj.sadna.mta.sadna_2017.R;

/**
 * Created by Einav on 28/07/2017.
 */

public class SiteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    Context context;
    ArrayList<SiteModel> data = new ArrayList<>();

    public SiteAdapter(Context context, ArrayList<SiteModel> data)
    {
        this.context = context;
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        RecyclerView.ViewHolder viewHolder;
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.site_item, parent, false);
        viewHolder = new SiteAdapter.SiteHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        ((SiteHolder) holder).onBind(data.get(position));
    }

    @Override
    public int getItemCount()
    {
        return data.size();
    }

    public static class SiteHolder extends RecyclerView.ViewHolder
    {
        private final RelativeLayout click;
        Context context;
        ImageView mImg;
        TextView name;
        TextView description;
        TextView category;


        public SiteHolder(View itemView)
        {
            super(itemView);
            context = itemView.getContext();
            click = (RelativeLayout) itemView.findViewById(R.id.clickable);
            mImg = (ImageView) itemView.findViewById(R.id.site_img);
            name = (TextView) itemView.findViewById(R.id.site_name);
            description = (TextView) itemView.findViewById(R.id.site_short_desc);
            category = (TextView) itemView.findViewById(R.id.site_category);
        }

        public void onBind(SiteModel siteModel)
        {
            Glide.with(context).
                    load(siteModel.getProfilePicture()).
                    thumbnail(0.5f).override(200, 200).
                    crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(mImg);

            name.setText(siteModel.getShortName());
            description.setText(siteModel.getDescription());
            click.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Intent intent = new Intent(context, SiteOverviewActivity.class);
                    context.startActivity(intent);
                }
            });
        }
    }

}
