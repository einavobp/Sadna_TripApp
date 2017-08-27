package proj.sadna.mta.sadna_2017.app.Activities;

import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;

import java.util.ArrayList;

import proj.sadna.mta.sadna_2017.app.Adapters.AppPagerAdapter;
import proj.sadna.mta.sadna_2017.app.Fragments.MyPathsFragment;
import proj.sadna.mta.sadna_2017.app.Fragments.NewTripFragment;
import proj.sadna.mta.sadna_2017.app.Fragments.RecommendedPathFragment;
import proj.sadna.mta.sadna_2017.app.Fragments.UserSettingsFragment;
import proj.sadna.mta.sadna_2017.app.Models.SiteModel;
import proj.sadna.mta.sadna_2017.R;

public class MainActivity extends AppCompatActivity
{
    private static final int[] TAB_ICONS = {R.drawable.position, R.drawable.others, R.drawable.luggage, R.drawable.settings};

    private static final int[] TAB_TITLES = {R.string.home, R.string.best, R.string.mine, R.string.settings};

    private ViewPager mPager;
    private TabLayout mTabLayout;
    private AppPagerAdapter mPagerAdapter;
    private FragmentManager mFragmentManager;
    private ArrayList<Fragment> pages;
    private int mCurrentTab = -1;
    private SiteModel site = new SiteModel();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setupTabs();
    }

    @SuppressWarnings({"deprecation", "ConstantConditions"})
    private void setupTabs()
    {
        mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.position));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.others));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.luggage));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.settings));
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

        mTabLayout.getTabAt(0).setCustomView(R.layout.home_ic);
        mTabLayout.getTabAt(1).setCustomView(R.layout.ic_top);
        mTabLayout.getTabAt(2).setCustomView(R.layout.ic_mine);
        mTabLayout.getTabAt(3).setCustomView(R.layout.ic_settings);


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
        if (selected) curr_txt.setTextColor(Color.parseColor("#FFE16B"));
        else curr_txt.setTextColor(Color.WHITE);
        switch (pos)
        {
            case 0:
                if (selected)
                {
                    curr_ic.setImageResource(R.drawable.positiony);

                } else
                {
                    curr_ic.setImageResource(R.drawable.position);

                }
                break;
            case 1:
                if (selected)
                {
                    curr_ic.setImageResource(R.drawable.othersy);
                } else
                {
                    curr_ic.setImageResource(R.drawable.others);
                }
                break;
            case 2:
                if (selected)
                {
                    curr_ic.setImageResource(R.drawable.lugy);
                } else
                {
                    curr_ic.setImageResource(R.drawable.luggage);
                }
                break;
            case 3:
                if (selected)
                {
                    curr_ic.setImageResource(R.drawable.settingsy);
                } else
                {
                    curr_ic.setImageResource(R.drawable.settings);
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

    private ArrayList<Fragment> getPages()
    {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new NewTripFragment());
        fragments.add(new RecommendedPathFragment());
        fragments.add(new MyPathsFragment());
        fragments.add(new UserSettingsFragment());
        return fragments;
    }

    private void initView()
    {
        mPager = (ViewPager) findViewById(R.id.main_container);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);

    }
}
