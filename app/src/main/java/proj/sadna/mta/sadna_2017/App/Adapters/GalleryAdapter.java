package proj.sadna.mta.sadna_2017.App.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import proj.sadna.mta.sadna_2017.R;

/**
 * Created by Einav on 22/07/2017.
 */

public class GalleryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    Context context;
    ArrayList<String> data = new ArrayList<>();

    public GalleryAdapter(Context context, ArrayList<String> data)
    {
        this.context = context;
        this.data = data;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        RecyclerView.ViewHolder viewHolder;
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_list_item, parent, false);
        viewHolder = new MyItemHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {

        Glide.with(context).load(data.get(position)).thumbnail(0.5f).override(200, 200).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(((MyItemHolder) holder).mImg);

    }

    @Override
    public int getItemCount()
    {
        return data.size();
    }

    public static class MyItemHolder extends RecyclerView.ViewHolder
    {
        ImageView mImg;


        public MyItemHolder(View itemView)
        {
            super(itemView);

            mImg = (ImageView) itemView.findViewById(R.id.item_img);
        }

    }
}