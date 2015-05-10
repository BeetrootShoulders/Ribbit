package com.hyperglobal.ribbit;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by bmac on 08/05/2015.
 */
public class RibbitApplication extends Application {
    public void onCreate() {
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "mtUsGiR4Ao3KrX9cxVMtrau39TBafiAi55kO82OW", "GnX0AKqStg3ZAqJYfNuZixiqeL79hQLhcXyfRbzv");
    }
}
