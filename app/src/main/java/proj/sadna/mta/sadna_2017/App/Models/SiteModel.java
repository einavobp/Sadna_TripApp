package proj.sadna.mta.sadna_2017.App.Models;

import java.util.ArrayList;

/**
 * Created by Einav on 22/07/2017.
 */

public class SiteModel
{
    private String fullName;
    private String shortName;
    private String address;

    public String getProfilePicture()
    {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture)
    {
        this.profilePicture = profilePicture;
    }

    private String profilePicture;

    private int type;
    private ArrayList<String> imageUrl;
    private double lat, lng;

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    private String phoneNumber;
    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    private String description;

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    private String email;
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


}
