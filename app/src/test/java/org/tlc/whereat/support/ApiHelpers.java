package org.tlc.whereat.support;

import android.net.Uri;
import android.util.Log;

import org.tlc.whereat.model.ApiMessage;

import java.io.IOException;
import java.util.Collections;

import retrofit.client.Client;
import retrofit.client.Request;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

/**
 * Author: @aguestuser
 * License: GPLv3 (https://www.gnu.org/licenses/gpl-3.0.html)
 */
public class ApiHelpers {

    public static final String REMOVE_MSG = "1 record(s) deleted.";

    public static final String REMOVE_MSG_JSON = "{\"msg\":\"" + REMOVE_MSG + "\"}";

    public static ApiMessage removeMsgStub(){
        return ApiMessage.of(REMOVE_MSG);
    }

//    public class MockClient implements Client {
//        @Override
//        public Response execute(Request request) throws IOException {
//
//            Uri uri = Uri.parse(request.getUrl());
//
//            Log.d("MOCK SERVER", "fetching uri: " + uri.toString());
//
//            String responseString = "";
//
//            if(uri.getPath().equals("/locations/req")) {
//                responseString = "JSON STRING HERE";
//            } else {
//                responseString = "OTHER JSON RESPONSE STRING";
//            }
//
//            return new Response(request.getUrl(), 200, "nothing", Collections.EMPTY_LIST, new TypedByteArray("application/json", responseString.getBytes()));
//        }
//    }

}
