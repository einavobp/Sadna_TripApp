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
import android.widget.RelativeLayout;
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
import proj.sadna.mta.sadna_2017.app.Models.PathModel;
import proj.sadna.mta.sadna_2017.app.Models.PathModelRec;
import proj.sadna.mta.sadna_2017.app.Models.SiteModel;
import proj.sadna.mta.sadna_2017.app.Network.NetworkManager;
import proj.sadna.mta.sadna_2017.app.Network.Request.RouteRequest;
import proj.sadna.mta.sadna_2017.app.Network.Response.RouteResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PathActivity extends AppCompatActivity
{

    private static final int PATH_SIZE = 5;
    public static String COUPLE_ID = "1";
    public static String SEASON_ID = "1";
    private List<SiteModel> mAppList;
    private AppAdapter mAdapter;
    private SwipeMenuListView mListView;
    private ImageView mMap;
    private PathModel pathModel = null;
    private PathModelRec pathModelRec = null;
    private RelativeLayout saveBrn;
    private RelativeLayout startover_btn;
    private int counter2 = 0;
    private int counter1 = 0;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 123 || resultCode == 144)
        {
            pathModel = PathModel.findById(PathModel.class, pathModel.getId());
            mAppList = pathModel.getSiteModels();
            if (mAppList.size() == 5) findViewById(R.id.add_site).setVisibility(View.INVISIBLE);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onBackPressed()
    {
        setResult(144);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onBackPressed();
            }
        });
        mMap = (ImageView) findViewById(R.id.map);
        mMap.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(PathActivity.this, PathOnMapActivity.class);
                intent.putExtra("ids", getPathStringArray());
                PathActivity.this.startActivityForResult(intent, 111);
            }
        });
        mAppList = getSitesFromServer();
        saveBrn = (RelativeLayout) findViewById(R.id.save_btn);
        startover_btn = (RelativeLayout) findViewById(R.id.startover_btn);

        startover_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                findViewById(R.id.save_start_btn).setVisibility(View.INVISIBLE);
                counter1++;
                counter2++;
                counter1 = (counter1 % 4);
                SEASON_ID = String.valueOf(counter2 % 4);
                COUPLE_ID = String.valueOf(counter1 % 4);

                NetworkManager.getInstance().calculate(new RouteRequest("1", SEASON_ID, COUPLE_ID, "8:00", "18:00"), new Callback<RouteResponse>()
                {
                    @Override
                    public void onResponse(Call<RouteResponse> call, Response<RouteResponse> response)
                    {
                        pathModel = new PathModel("My Path", 0, response.body().sites);
                        pathModel.save();
                        mAppList = pathModel.getSiteModels();
                        mAdapter.notifyDataSetChanged();
                        findViewById(R.id.save_start_btn).setVisibility(View.VISIBLE);
                        Toast.makeText(PathActivity.this, "New Path!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<RouteResponse> call, Throwable t)
                    {
                        Toast.makeText(PathActivity.this, "Server Failed.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        saveBrn.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view)
            {
                pathModel.setName("My Path " + System.currentTimeMillis());
                pathModel.save();
                Intent intent = new Intent();
                Bundle conData = new Bundle();
                conData.putLong("path_id", pathModel.getId());
                intent.putExtras(conData);
                setResult(RESULT_OK, intent);
                Toast.makeText(PathActivity.this, "Saved!", Toast.LENGTH_LONG).show();
                finish();
            }
        });
        mListView = (SwipeMenuListView)

                findViewById(R.id.my_path);

        mAdapter = new

                AppAdapter();

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
                                                             final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?" +
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
                                                                     pathModel.removeSite(mAppList.get(position));
                                                                     pathModel.save();
                                                                     mAppList.remove(position);
                                                                     mAdapter.notifyDataSetChanged();
                                                                     if (mAppList.size() < PATH_SIZE)
                                                                     {
                                                                         findViewById(R.id.add_site).setVisibility(View.VISIBLE);
                                                                         findViewById(R.id.add_site).setOnClickListener(new View.OnClickListener()
                                                                         {
                                                                             @Override
                                                                             public void onClick(View view)
                                                                             {
                                                                                 Intent intent = new Intent(PathActivity.this, SearchSiteActivity.class);
                                                                                 intent.putExtra("id", pathModel.getId());
                                                                                 PathActivity.this.startActivityForResult(intent, 111);
                                                                             }
                                                                         });
                                                                     }

                                                                     sDialog.setTitleText("Deleted!").setContentText("Your imaginary file has been deleted!").setConfirmText("OK").setConfirmClickListener(null).changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                                                 }
                                                             });
                                                             dialog.setCanceledOnTouchOutside(true);
                                                             dialog.show();
                                                             break;
                                                     }
                                                     return false;
                                                 }
                                             }

        );

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
                                     }

        );

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
                                               }

        );

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
                                             }

        );

    }

    private String getPathStringArray()
    {
        String ids = new String();
        for (SiteModel siteModel : mAppList)
        {
            ids = ids + siteModel.getId() + ",";
        }
        return ids;

    }

    private List<SiteModel> getSitesFromServer()
    {
        if (getIntent().hasExtra("rec_id"))
        {
            findViewById(R.id.save_start_btn).setVisibility(View.GONE);
            long id = getIntent().getLongExtra("rec_id", 0);
            pathModelRec = PathModelRec.findById(PathModelRec.class, id);

            if (pathModelRec != null)
            {
                pathModel = new PathModel(pathModelRec.getName(), pathModelRec.getRate());
                pathModel.setIds(pathModelRec.getIds());
                pathModel.save();

                return (pathModel.getSiteModels());
            }
        } else if (getIntent().hasExtra("id"))
        {
            long id = getIntent().getLongExtra("id", 0);
            pathModel = PathModel.findById(PathModel.class, id);
            if (pathModel != null) return (pathModel.getSiteModels());
        }

        List<SiteModel> sites = new ArrayList<>();
        for (int i = 1; i < 6; i++)

        {
            sites.add(SiteModel.findById(SiteModel.class, i));
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
            final SiteModel item = getItem(position);
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
                    Intent intent = new Intent(PathActivity.this, SiteOverviewActivity.class);
                    intent.putExtra("id", item.getId());
                    intent.putExtra("noadd", true);
                    PathActivity.this.startActivity(intent);
                }
            });
            holder.siteName.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent(PathActivity.this, SiteOverviewActivity.class);
                    intent.putExtra("id", item.getId());
                    intent.putExtra("noadd", true);
                    PathActivity.this.startActivity(intent);
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