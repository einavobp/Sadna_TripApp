package proj.sadna.mta.sadna_2017.app.Models;

import com.orm.SugarRecord;

import java.util.ArrayList;

import proj.sadna.mta.sadna_2017.app.Network.Response.Sites;

public class PathModel extends SugarRecord
{
    private String name;
    private double rate;
    private long serverID;
    private String ids;
    private int size_ids;
    private ArrayList<SiteModel> siteModels;

    public PathModel()
    {
    }

    public long getServerID()
    {
        return serverID;
    }

    public void setServerID(long serverID)
    {
        this.serverID = serverID;
    }

    public PathModel(String name, double rate)
    {
        this.name = name;
        this.rate = rate;
    }

    public PathModel(String name, double rate, ArrayList<Sites> siteModels)
    {
        this.name = name;
        this.rate = rate;
        this.ids = new String();
        this.size_ids = 0;

        for (SiteModel site : SiteModel.ToList(siteModels))
        {
            ids = ids + site.getId() + ",";
            size_ids++;
        }
    }

    public ArrayList<SiteModel> getSiteModels()
    {
        ArrayList<SiteModel> sites = new ArrayList<>();
        String[] ids_ = ids.split(",");
        size_ids = ids_.length;
        for (int i = 0; i < size_ids; i++)
        {
            sites.add(SiteModel.findById(SiteModel.class, Long.parseLong(ids_[i])));
        }
        return sites;
    }

    public void setSiteModels(ArrayList<SiteModel> siteModels)
    {
        this.siteModels = siteModels;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getRate()
    {
        return rate;
    }

    public void setRate(double rate)
    {
        this.rate = rate;
    }

    public void removeSite(SiteModel siteModel)
    {
        String[] strings = ids.split(",");
        String ids_ = new String();
        for (String o : strings)
        {
            if (Long.parseLong(o) != siteModel.getId())
            {
                ids_ = ids_ + o + ",";
            }
            this.ids = ids_;
        }
        this.save();
    }

    public String getIds()
    {
        return ids;
    }

    public void setIds(String ids)
    {
        this.ids = ids;

    }

    public int getSize_ids()
    {
        return size_ids;
    }

    public void setSize_ids(int size_ids)
    {
        this.size_ids = size_ids;
    }

    public static void addSite(Long id, Long id1)
    {
        PathModel pathModel = PathModel.findById(PathModel.class, id);
        pathModel.ids = pathModel.ids + id1 + ",";
        pathModel.save();
    }

}
