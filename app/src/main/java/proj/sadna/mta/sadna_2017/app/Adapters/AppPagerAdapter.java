package proj.sadna.mta.sadna_2017.app.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Einav on 21/07/2017.
 */
public class AppPagerAdapter extends FragmentPagerAdapter
{

    private ArrayList<Fragment> mPages;

    public AppPagerAdapter(FragmentManager fm)
    {
        super(fm);
        mPages = new ArrayList<>();
    }

    public void setPages(ArrayList<Fragment> pages)
    {
        mPages.addAll(pages);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position)
    {
        return mPages.get(position);
    }

    @Override
    public int getCount()
    {
        return mPages.size();
    }
}
