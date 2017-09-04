package proj.sadna.mta.sadna_2017.app.Activities;

import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;

import java.util.ArrayList;

import proj.sadna.mta.sadna_2017.app.Adapters.AppPagerAdapter;
import proj.sadna.mta.sadna_2017.app.Fragments.GalleryFragment;
import proj.sadna.mta.sadna_2017.app.Fragments.InfoFragment;
import proj.sadna.mta.sadna_2017.app.Fragments.NavigationFragment;
import proj.sadna.mta.sadna_2017.app.Fragments.OpeningHourFragment;
import proj.sadna.mta.sadna_2017.app.Models.SiteModel;
import proj.sadna.mta.sadna_2017.R;

public class SiteOverviewActivity extends AppCompatActivity
{
    private static final int[] TAB_ICONS = {R.drawable.help_button, R.drawable.clock, R.drawable.gallery, R.drawable.map};

    private static final int[] TAB_TITLES = {R.string.info, R.string.open, R.string.gallery, R.string.map};

    private ViewPager mPager;
    private TabLayout mTabLayout;
    private AppPagerAdapter mPagerAdapter;
    private FragmentManager mFragmentManager;
    private ArrayList<Fragment> pages;
    private int mCurrentTab = -1;
    private SiteModel site = new SiteModel();
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_overview);
        initSite();
        initView();
        setupTabs();
    }

    private void initSite()
    {
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
    }

    @SuppressWarnings({"deprecation", "ConstantConditions"})
    private void setupTabs()
    {
        mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.map));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.gallery));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.clock));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.help_button));
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        mPagerAdapter = new AppPagerAdapter(getSupportFragmentManager());
        if (pages == null) pages = getPages();
        int numberOfTabs = pages.size();
        mPager.setOffscreenPageLimit(numberOfTabs - 1);
        mPager.setAdapter(mPagerAdapter);
        mPagerAdapter.setPages(pages);
        mTabLayout.setSelectedTabIndicatorHeight(0);
        mTabLayout.setupWithViewPager(mPager);
        mPager.setPageTransformer(true, new RotateUpTransformer());

        mTabLayout.getTabAt(0).setCustomView(R.layout.ic_info);
        mTabLayout.getTabAt(1).setCustomView(R.layout.ic_open);
        mTabLayout.getTabAt(2).setCustomView(R.layout.ic_gallery);
        mTabLayout.getTabAt(3).setCustomView(R.layout.ic_nav);


        mPager.addOnPageChangeListener(mOnPageChangedListener);

        setupCurrentTab(0);

    }

    @SuppressWarnings({"ConstantConditions", "deprecation"})
    private void setupCurrentTab(int position)
    {
        RelativeLayout pre;
        if (mCurrentTab == -1) pre = null;
        else pre = (RelativeLayout) mTabLayout.getTabAt(mCurrentTab).getCustomView();
        RelativeLayout current = (RelativeLayout) mTabLayout.getTabAt(position).getCustomView();
        setColor(current, true, position);
        setColor(pre, false, mCurrentTab);
        mCurrentTab = position;
    }

    private void setColor(RelativeLayout current, boolean selected, int pos)
    {
        if (pos == -1) return;

        ImageView curr_ic = (ImageView) current.findViewById(R.id.img);
        TextView curr_txt = (TextView) current.findViewById(R.id.txt);
        if (selected) curr_txt.setTextColor(Color.parseColor("#135c9f"));
        else curr_txt.setTextColor(Color.GRAY);
        switch (pos)
        {
            case 0:
                if (selected)
                {
                    curr_ic.setImageResource(R.drawable.blueinfo);


                } else
                {
                    curr_ic.setImageResource(R.drawable.greyinfoutton);

                }
                break;
            case 1:
                if (selected)
                {
                    curr_ic.setImageResource(R.drawable.blueclock);
                } else
                {
                    curr_ic.setImageResource(R.drawable.alarmclock);
                }
                break;
            case 2:
                if (selected)
                {
                    curr_ic.setImageResource(R.drawable.blue_pic);
                } else
                {
                    curr_ic.setImageResource(R.drawable.pictures);
                }
                break;
            case 3:
                if (selected)
                {
                    curr_ic.setImageResource(R.drawable.bluemap);
                } else
                {
                    curr_ic.setImageResource(R.drawable.greymap);
                }
                break;
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (pages == null) pages = getPages();
        if (pages != null)
        {
            for (Fragment fragment : pages)
            {
                fragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }
    }

    private ViewPager.OnPageChangeListener mOnPageChangedListener = new ViewPager.OnPageChangeListener()
    {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
        {
        }

        @Override
        public void onPageSelected(int position)
        {
            setupCurrentTab(position);
        }

        @Override
        public void onPageScrollStateChanged(int state)
        {

        }
    };

    public void HideHeader()
    {
        //findViewById(R.id.header_image).setVisibility(View.GONE);
    }

    public void ShowHeader()
    {
        // findViewById(R.id.header_image).setVisibility(View.VISIBLE);
    }

    private ArrayList<Fragment> getPages()
    {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(InfoFragment.newInstance(site));
        fragments.add(OpeningHourFragment.newInstance());
        fragments.add(GalleryFragment.newInstance(this,site));
        fragments.add(NavigationFragment.newInstance(site));
        return fragments;
    }

    private void initView()
    {
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onBackPressed();
            }
        });
        mPager = (ViewPager) findViewById(R.id.main_container);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        ((TextView) findViewById(R.id.toolbar_site_name)).setText(site.getShortName());
    }
}
