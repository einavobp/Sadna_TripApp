package proj.sadna.mta.sadna_2017.App.Adapters;

import android.widget.BaseAdapter;

/**
 * Created by Einav on 19/08/2017.
 */
public abstract class BaseSwipListAdapter extends BaseAdapter
{

    public boolean getSwipEnableByPosition(int position)
    {
        return true;
    }


}
