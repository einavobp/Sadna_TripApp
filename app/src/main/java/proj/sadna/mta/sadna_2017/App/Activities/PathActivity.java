package proj.sadna.mta.sadna_2017.app.Activities;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import proj.sadna.mta.sadna_2017.R;
import proj.sadna.mta.sadna_2017.app.Adapters.BaseSwipListAdapter;
import proj.sadna.mta.sadna_2017.app.Models.SiteModel;

public class PathActivity extends AppCompatActivity
{

    private List<SiteModel> mAppList;
    private AppAdapter mAdapter;
    private SwipeMenuListView mListView;
    private ImageView mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path);
        mMap = (ImageView) findViewById(R.id.map);
        mMap.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                PathActivity.this.startActivity(new Intent(PathActivity.this, PathOnMapActivity.class));
            }
        });
        mAppList = getSitesFromServer();

        mListView = (SwipeMenuListView) findViewById(R.id.my_path);

        mAdapter = new AppAdapter();
        mListView.setAdapter(mAdapter);

        // step 1. create a MenuCreator
        SwipeMenuCreator creator = new SwipeMenuCreator()
        {

            @Override
            public void create(SwipeMenu menu)
            {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9, 0xCE)));
                // set item width
                openItem.setWidth(dp2px(80));
                // set item title
                openItem.setTitle("Navigate");
                // set a icon
                //openItem.setIcon(R.drawable.mapholder);
                // set item title fontsize
                openItem.setTitleSize(16);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(80));
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        // set creator
        mListView.setMenuCreator(creator);

        // step 2. listener item click event
        mListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(final int position, SwipeMenu menu, int index)
            {
                SiteModel item = mAppList.get(position);
                switch (index)
                {
                    case 0:
                        // open
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?" +
                                "&daddr=" + String.valueOf(item.getLat()) + "," + String.valueOf(item.getLng())));
                        PathActivity.this.startActivity(intent);

                        break;
                    case 1:
                        // delete

                        SweetAlertDialog dialog = new SweetAlertDialog(PathActivity.this, SweetAlertDialog.WARNING_TYPE).setTitleText("Are you sure?").setContentText("Won't be able to recover this file!").setConfirmText("Yes,delete it!").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener()
                        {
                            @Override
                            public void onClick(SweetAlertDialog sDialog)
                            {
                                mAppList.remove(position);
                                mAdapter.notifyDataSetChanged();
                                sDialog.setTitleText("Deleted!").setContentText("Your imaginary file has been deleted!").setConfirmText("OK").setConfirmClickListener(null).changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                            }
                        });
                        dialog.setCanceledOnTouchOutside(true);
                        dialog.show();
                        break;
                }
                return false;
            }
        });

        // set SwipeListener
        mListView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener()
        {

            @Override
            public void onSwipeStart(int position)
            {
                // swipe start
            }

            @Override
            public void onSwipeEnd(int position)
            {
                // swipe end
            }
        });

        // set MenuStateChangeListener
        mListView.setOnMenuStateChangeListener(new SwipeMenuListView.OnMenuStateChangeListener()
        {
            @Override
            public void onMenuOpen(int position)
            {
            }

            @Override
            public void onMenuClose(int position)
            {
            }
        });

        // other setting
//		listView.setCloseInterpolator(new BounceInterpolator());

        // test item long click
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
            {
                Toast.makeText(getApplicationContext(), position + " long click", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }

    private List<SiteModel> getSitesFromServer()
    {
        SiteModel site = new SiteModel();
        site.setFullName("The Museum of Modern Art");
        site.setShortName("Museum of Art");
        site.setAddress("11 W 53rd St, New York");
        site.setPhoneNumber("(718) 670-7780");
        site.setProfilePicture("https://www.moma.org/assets/visit/entrance-image--museum-crop-8bd4aeaea6c59781790dacfc1346e2d3.jpg");
        site.setLat(40.7614327);
        site.setLng(-73.97762159999999);
        site.setDescription("The Museum of Modern Art (MoMA /ˈmoʊmə/) is an art museum located in Midtown Manhattan in New York City, on 53rd Street between Fifth and Sixth Avenues.\n" +
                "MoMA has been important in developing and collecting modernist art, and is often identified as one of the largest and most influential museums of modern art in the world. MoMA's collection offers an overview of modern and contemporary art, including works of architecture and design, drawing, painting, sculpture, photography, prints, illustrated books and artist's books, film, and electronic media.\n" +
                "The MoMA Library includes approximately 300,000 books and exhibition catalogs, over 1,000 periodical titles, and over 40,000 files of ephemera about individual artists and groups. The archives holds primary source material related to the history of modern and contemporary art.");

        List<SiteModel> sites = new ArrayList<>();
        for (int i = 0; i < 5; i++)
        {
            if (i == 1)
                site.setProfilePicture("https://www.rd.com/wp-content/uploads/sites/2/2016/01/01-statue-of-liberty-facts.jpg");
            if (i == 2)
                site.setProfilePicture("http://vignette4.wikia.nocookie.net/penguinsofmadagascar/images/b/bc/Central_Park_Zoo.png/revision/latest?cb=20131120080856");
            if (i == 3)
                site.setProfilePicture("http://www.freetoursbyfoot.com/wp-content/uploads/2013/02/New-York-Food-Tours.jpg");
            sites.add(site);
        }
        return sites;
    }

    private void delete(ApplicationInfo item)
    {
        // delete app
        try
        {
            Intent intent = new Intent(Intent.ACTION_DELETE);
            intent.setData(Uri.fromParts("package", item.packageName, null));
            startActivity(intent);
        } catch (Exception e)
        {
        }
    }

    private void open(ApplicationInfo item)
    {
        // open app
        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        resolveIntent.setPackage(item.packageName);
        List<ResolveInfo> resolveInfoList = getPackageManager().queryIntentActivities(resolveIntent, 0);
        if (resolveInfoList != null && resolveInfoList.size() > 0)
        {
            ResolveInfo resolveInfo = resolveInfoList.get(0);
            String activityPackageName = resolveInfo.activityInfo.packageName;
            String className = resolveInfo.activityInfo.name;

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            ComponentName componentName = new ComponentName(activityPackageName, className);

            intent.setComponent(componentName);
            startActivity(intent);
        }
    }

    class AppAdapter extends BaseSwipListAdapter
    {

        @Override
        public int getCount()
        {
            return mAppList.size();
        }

        @Override
        public SiteModel getItem(int position)
        {
            return mAppList.get(position);
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
                convertView = View.inflate(getApplicationContext(), R.layout.item_list_app, null);
                new ViewHolder(convertView);
            }
            ViewHolder holder = (ViewHolder) convertView.getTag();
            SiteModel item = getItem(position);
            Glide.with(PathActivity.this).load(item.getProfilePicture()).
                    crossFade().
                    diskCacheStrategy(DiskCacheStrategy.ALL).
                    into(holder.image);
            holder.siteName.setText(item.getFullName());
            holder.siteDescription.setText(item.getAddress());
            holder.image.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    PathActivity.this.startActivity(new Intent(PathActivity.this, SiteOverviewActivity.class));
                }
            });
            holder.siteName.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    PathActivity.this.startActivity(new Intent(PathActivity.this, SiteOverviewActivity.class));
                }
            });
            return convertView;
        }

        class ViewHolder
        {
            ImageView image;
            TextView siteName;
            TextView siteDescription;
            TextView siteCategories;

            public ViewHolder(View view)
            {
                image = (ImageView) view.findViewById(R.id.site_img);
                siteName = (TextView) view.findViewById(R.id.site_name);
                siteDescription = (TextView) view.findViewById(R.id.site_short_desc);
                siteCategories = (TextView) view.findViewById(R.id.site_category);
                view.setTag(this);
            }
        }

        @Override
        public boolean getSwipEnableByPosition(int position)
        {
            if (position % 2 == 0)
            {
                return false;
            }
            return true;
        }
    }

    private int dp2px(int dp)
    {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.action_left)
        {
            mListView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
            return true;
        }
        if (id == R.id.action_right)
        {
            mListView.setSwipeDirection(SwipeMenuListView.DIRECTION_RIGHT);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}