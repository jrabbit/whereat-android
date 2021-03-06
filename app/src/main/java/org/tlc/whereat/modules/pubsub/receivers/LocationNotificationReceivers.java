package org.tlc.whereat.modules.pubsub.receivers;

import android.content.Intent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;

import org.tlc.whereat.R;
import org.tlc.whereat.modules.pubsub.broadcasters.LocPubBroadcasters;
import org.tlc.whereat.modules.pubsub.Dispatcher;
import org.tlc.whereat.modules.schedule.Scheduler;
import org.tlc.whereat.util.TimeUtils;

import static org.tlc.whereat.modules.ui.Toaster.shortToast;

public class LocationNotificationReceivers extends Receivers {

    public static final String TAG = LocationNotificationReceivers.class.getSimpleName();

    protected BroadcastReceiver mPub = pub();
    protected BroadcastReceiver mClear = clear();
    protected BroadcastReceiver mFail = fail();
    protected BroadcastReceiver mForget = forget();

    // CONSTRUCTOR

    public LocationNotificationReceivers(Context ctx, LocalBroadcastManager lbm){
        super(ctx, lbm);
    }

    // LIFE CYCLE METHODS

    public void register(){
        Dispatcher.register(mLbm, mPub, LocPubBroadcasters.ACTION_LOCATION_PUBLISHED);
        Dispatcher.register(mLbm, mClear, LocPubBroadcasters.ACTION_LOCATIONS_CLEARED);
        Dispatcher.register(mLbm, mFail, LocPubBroadcasters.ACTION_LOCATION_REQUEST_FAILED);
        Dispatcher.register(mLbm, mForget, Scheduler.ACTION_LOCATIONS_FORGOTTEN);
    }

    public void unregister(){
        mLbm.unregisterReceiver(mPub);
        mLbm.unregisterReceiver(mFail);
        mLbm.unregisterReceiver(mClear);
        mLbm.unregisterReceiver(mForget);
    }

    // BROADCAST RECEIVERS

    //TODO replace BroadcastReceivers with calls to AndroidObservable.fromBroacast()?
    // see http://blog.danlew.net/2014/10/08/grokking-rxjava-part-4/

    protected BroadcastReceiver pub(){
        return new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                shortToast(mCtx, mCtx.getString(R.string.loc_shared_toast));
            }
        };
    }

    protected BroadcastReceiver fail(){
        return new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent i) {
                shortToast(mCtx, mCtx.getString(R.string.loc_retrieval_failed_toast));
            }
        };
    }

    protected BroadcastReceiver clear(){
        return new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                shortToast(mCtx, mCtx.getString(R.string.loc_clear_toast));
            }
        };
    }

    protected BroadcastReceiver forget(){
        return new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                long time = intent.getExtras().getLong(Scheduler.ACTION_LOCATIONS_FORGOTTEN);
                String msg = mCtx.getString(R.string.loc_forget_prefix) + TimeUtils.fullDate(time);
                shortToast(mCtx, msg);
            }
        };
    }


}
