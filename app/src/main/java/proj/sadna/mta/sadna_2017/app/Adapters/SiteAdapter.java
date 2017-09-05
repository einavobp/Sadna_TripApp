package proj.sadna.mta.sadna_2017.app.Adapters;

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

import proj.sadna.mta.sadna_2017.R;
import proj.sadna.mta.sadna_2017.app.Activities.SearchSiteActivity;
import proj.sadna.mta.sadna_2017.app.Activities.SiteOverviewActivity;
import proj.sadna.mta.sadna_2017.app.Models.PathModel;
import proj.sadna.mta.sadna_2017.app.Models.SiteModel;

/**
 * Created by Einav on 28/07/2017.
 */

public class SiteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    Context context;
    public static SearchSiteActivity searchSiteActivity = null;
    static PathModel _path = null;
    ArrayList<SiteModel> data = new ArrayList<>();

    public SiteAdapter(Context context, SearchSiteActivity searchSiteActivity, PathModel _path, ArrayList<SiteModel> data)
    {
        this.context = context;
        this.searchSiteActivity = searchSiteActivity;
        this._path = _path;
        this.data = data;
    }

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
        private final RelativeLayout addBtn;
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
            addBtn = (RelativeLayout) itemView.findViewById(R.id.add_btn);
            mImg = (ImageView) itemView.findViewById(R.id.site_img);
            name = (TextView) itemView.findViewById(R.id.site_name);
            description = (TextView) itemView.findViewById(R.id.site_short_desc);
            category = (TextView) itemView.findViewById(R.id.site_category);
        }

        public void onBind(final SiteModel siteModel)
        {
            Glide.with(context).
                    load(siteModel.getProfilePicture()).
                    thumbnail(0.5f).override(200, 200).
                    crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(mImg);

            name.setText(siteModel.getShortName());
            description.setText(siteModel.getShortDescription());

//            addBtn.setOnClickListener(new View.OnClickListener()
//                                      {
//                                          @Override
//                                          public void onClick(View view)
//                                          {
//                                              if (_path.getSize_ids() < 5)
//                                              {
//                                                  PathModel.addSite(_path.getId(), siteModel.getId());
//                                                  _path.save();
//                                              } else
//                                              {
//                                                  Toast.makeText(context, "5 Sites at top!", Toast.LENGTH_LONG).show();
//                                              }
//                                          }
//                                      }
//
//            );
            click.setOnClickListener(new View.OnClickListener()

                                     {
                                         @Override
                                         public void onClick(View view)
                                         {
                                             Intent intent = new Intent(context, SiteOverviewActivity.class);
                                             intent.putExtra("id", siteModel.getId());
                                             intent.putExtra("path_id", _path.getId());
                                             context.startActivity(intent);
                                         }
                                     }

            );
        }
    }


}
