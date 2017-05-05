package net.glm.service93s;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener{

    final String LOG_TAG = "myLogs";

    public static final String NAME = "name";
    public static final String TIME = "time";

    Button startServiceButton,startOutServiceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startServiceButton = (Button) findViewById(R.id.start_button);
        startServiceButton.setOnClickListener(this);

        startOutServiceButton = (Button) findViewById(R.id.start_outservice_button);
        startOutServiceButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.start_button):
                startService(new Intent(this,InsideService.class).putExtra(TIME,10));
                startService(new Intent(this,InsideService.class).putExtra(TIME,2));
                startService(new Intent(this,InsideService.class).putExtra(TIME,4));
                break;
            case (R.id.start_outservice_button):
//
//                Intent tempIntent = new Intent("OutSideService");
//                Intent outSideIntent = createExplicitFromImplicitIntent(this,tempIntent);



                Intent outSideIntent = new Intent();
                outSideIntent.setComponent(new ComponentName("net.glm.outsideservice94s","net.glm.outsideservice94s.OutSideService"));

                if (outSideIntent == null){
                    Log.d(LOG_TAG,"The Result is null");
                }else {
                    outSideIntent.putExtra(NAME, "Sending value");
                    //outSideIntent.setPackage("net.glm.outsideservice94s");
                    startService(outSideIntent);
                }


                break;
        }
    }

    public  Intent createExplicitFromImplicitIntent (Context context, Intent imlicitIntent){

        Intent explicitIntent;
        ComponentName componentName;
        PackageManager pm = context.getPackageManager();

        List<ResolveInfo> resolveInfoList = pm.queryIntentServices(imlicitIntent,0);

        if (resolveInfoList == null || resolveInfoList.size()!= 1) {
            return null;
        }
        ResolveInfo myServiceInfo = resolveInfoList.get(0);
        String packageName = myServiceInfo.serviceInfo.packageName;
        String className = myServiceInfo.serviceInfo.name;

        componentName = new ComponentName(packageName,className);
        Log.d(LOG_TAG,"The Package Name is - " + packageName + " And Class Name is - " + className);
        Log.d(LOG_TAG,"The ComponentName is - " + componentName.toString());

        explicitIntent = new Intent(imlicitIntent);
        explicitIntent.setComponent(componentName);

        return explicitIntent;

    }


}
