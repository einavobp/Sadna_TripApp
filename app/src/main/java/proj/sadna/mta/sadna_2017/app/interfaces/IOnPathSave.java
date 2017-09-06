package proj.sadna.mta.sadna_2017.app.interfaces;

import proj.sadna.mta.sadna_2017.app.Models.PathModel;
import proj.sadna.mta.sadna_2017.app.Models.PathModelRec;

/**
 * Created by Einav on 06/09/2017.
 */

public interface IOnPathSave
{
    void onPathSave(PathModel pathModel);

    void onRecPathSave(PathModelRec pathModel);


}
