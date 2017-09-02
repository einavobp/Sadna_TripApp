package proj.sadna.mta.sadna_2017.app.Models;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import proj.sadna.mta.sadna_2017.app.Network.Response.Sites;

/**
 * Created by Einav on 22/07/2017.
 */

public class SiteModel extends SugarRecord
{
    private String fullName;
    private String shortName;
    private String address;
    private int type;
    private String types;
    private ArrayList<String> imageUrl;
    private double lat, lng;
    private String phoneNumber;
    private String profilePicture;
    private String description;
    private String email;


    public SiteModel(String fullName, String address, String types, ArrayList<String> imageUrl, double lat, double lng, String phoneNumber, String profilePicture, String description, String email)
    {
        this.fullName = fullName;
        this.shortName = fullName;
        this.address = address;
        this.types = types;
        this.imageUrl = imageUrl;
        this.lat = lat;
        this.lng = lng;
        this.phoneNumber = phoneNumber;
        this.profilePicture = profilePicture;
        this.description = description;
        this.email = email;
    }

    public String getProfilePicture()
    {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture)
    {
        this.profilePicture = profilePicture;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public SiteModel()
    {
    }

    public String getShortName()
    {
        return shortName;
    }


    public SiteModel(String fullName, String shortName, String address, String profilePicture, int type, ArrayList<String> imageUrl, double lat, double lng, String phoneNumber, String description, String email)
    {
        this.fullName = fullName;
        this.shortName = shortName;
        this.address = address;
        this.profilePicture = profilePicture;
        this.type = type;
        this.imageUrl = imageUrl;
        this.lat = lat;
        this.lng = lng;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.email = email;
    }

    public void setShortName(String shortName)
    {
        this.shortName = shortName;
    }

    public String getFullName()
    {
        return fullName;
    }

    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    public double getLat()
    {
        return lat;
    }

    public void setLat(double lat)
    {
        this.lat = lat;
    }

    public double getLng()
    {
        return lng;
    }

    public void setLng(double lng)
    {
        this.lng = lng;
    }

    public int getType()
    {
        return type;
    }

    public void setType(int type)
    {
        this.type = type;
    }


    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public ArrayList<String> getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl(ArrayList<String> imageUrl)
    {
        this.imageUrl = imageUrl;
    }


    public static void saveList(List<Sites> sites)
    {
        for (Sites sites1 : sites)
        {
            String[] album = sites1.album_photos.split(",");
            ArrayList<String> strings = new ArrayList<>();
            for (String s : album)
            {
                strings.add(new String(s));
            }
            SiteModel siteModel = new SiteModel();
            siteModel.setFullName(sites1.name);
            siteModel.setShortName(sites1.name);
            siteModel.setAddress(sites1.formatted_address);
            siteModel.setTypes(sites1.types);
            siteModel.setImageUrl(strings);
            siteModel.setLat(sites1.location_lat);
            siteModel.setLng(sites1.location_lng);
            siteModel.setPhoneNumber(sites1.formatted_phone_number);
            siteModel.setProfilePicture(sites1.profile_photo);
            siteModel.setDescription(sites1.description);
            siteModel.setEmail("");
            siteModel.save();
        }
    }

    public String getTypes()
    {
        return types;
    }

    public void setTypes(String types)
    {
        this.types = types;
    }

    public static ArrayList<SiteModel> getList()
    {
        ArrayList<SiteModel> siteModels = new ArrayList<>();
        Iterator<SiteModel> sites = SiteModel.findAll(SiteModel.class);
        while (sites.hasNext())
        {
            siteModels.add(sites.next());
        }
        return siteModels;
    }

    public String getShortDescription()
    {
        return description != null ? description.substring(0, 30) + "..." : "no description";
    }
}
