package proj.sadna.mta.sadna_2017.app.Models;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import proj.sadna.mta.sadna_2017.app.Network.Response.RecommendedPathsResponse;
import proj.sadna.mta.sadna_2017.app.Network.Response.Sites;

/**
 * Created by Einav on 05/09/2017.
 */
public class PathModelRec extends SugarRecord
{
    private String name;
    private double rate;
    private String serverID;
    private String ids;
    private int size_ids;
    private ArrayList<SiteModel> siteModels;
    private String description;


    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public PathModelRec(String name, double rate, ArrayList<Sites> siteModels)
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

    public String getServerID()
    {
        return serverID;
    }

    public void setServerID(String serverID)
    {
        this.serverID = serverID;
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


    public PathModelRec()
    {
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

    public void setSiteModels(ArrayList<SiteModel> siteModels)
    {
        this.siteModels = siteModels;
    }

    public static void saveList(List<RecommendedPathsResponse> recommanded_routes)
    {
        for (RecommendedPathsResponse curr : recommanded_routes)
        {
            PathModelRec pathModelRec = new PathModelRec();
            pathModelRec.name = curr.name;
            pathModelRec.description = curr.description;
            pathModelRec.serverID = curr.id;
            pathModelRec.ids = curr.sites;
            pathModelRec.save();
        }
    }

    public static List<PathModelRec> getMine()
    {
        List<PathModelRec> pathModelRecs = new ArrayList<>();
        Iterator<PathModelRec> curr = PathModelRec.findAll(PathModelRec.class);
        int i = 0;
        while (curr.hasNext())
        {
            if (i == 0)
            {
                pathModelRecs.add(curr.next());
                i++;
            } else
            {
                curr.next();
                i--;
            }

        }
        return pathModelRecs;
    }

    public static List<PathModelRec> getAll()
    {
        List<PathModelRec> pathModelRecs = new ArrayList<>();
        Iterator<PathModelRec> curr = PathModelRec.findAll(PathModelRec.class);
        while (curr.hasNext())
        {
            pathModelRecs.add(curr.next());
        }
        return pathModelRecs;
    }
}
