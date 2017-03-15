package oluwaseun.akintola.repos;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by Akintola on 6/03/2017.
 */
public class GitReposApp extends Application {

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();

        sContext = getApplicationContext();
    }

    public static boolean haveInternet(){
        ConnectivityManager cm = (ConnectivityManager) sContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(cm != null && cm.getActiveNetworkInfo() != null){
            return cm.getActiveNetworkInfo().isConnectedOrConnecting();
        }
        return false;
    }
}
