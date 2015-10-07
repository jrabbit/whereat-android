package org.tlc.whereat.api;

import com.squareup.okhttp.CertificatePinner;
import com.squareup.okhttp.OkHttpClient;

import org.tlc.whereat.model.ApiMessage;
import org.tlc.whereat.model.UserLocationTimestamped;
import org.tlc.whereat.model.UserLocation;

import java.util.List;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.http.Body;
import retrofit.http.POST;
import rx.Observable;

/**
 * Author: @aguestuser
 * License: GPLv3 (https://www.gnu.org/licenses/gpl-3.0.html)
 */

public class WhereatApiClient implements WhereatApi {

    private static String mRoot = "https://whereat-server.herokuapp.com";
    private static WhereatApiClient mInstance;
    private WhereatApi mApi;
    // CONSTRUCTORS

    public static WhereatApiClient getInstance(){
        if (mInstance == null) mInstance = new WhereatApiClient(mRoot);
        return mInstance;
    }

    public static WhereatApiClient getInstance(String root){
        if (mInstance == null) mInstance = new WhereatApiClient(root);
        return mInstance;
    }

    private WhereatApiClient(String root){
        CertificatePinner certificatePinner = new CertificatePinner.Builder()
            .add(mRoot,System.getenv("WHEREAT_PKP_PUBLIC_KEY"))
            .add(mRoot,System.getenv("WHEREAT_PKP_BACKUP_KEY"))
            .build();
        OkHttpClient client = new OkHttpClient();
        client.setCertificatePinner(certificatePinner);
        RestAdapter ra = new RestAdapter.Builder()
            .setEndpoint(root)
            .setClient(new OkClient(client))
            .build();

        mApi = ra.create(WhereatApi.class);
    }

    // API METHODS

    @Override
    public Observable<List<UserLocation>> update(UserLocationTimestamped ult) {
        return mApi.update(ult);
    }

    @Override
    public Observable<ApiMessage> remove(UserLocation ul) {
        return mApi.remove(ul);
    }

}
