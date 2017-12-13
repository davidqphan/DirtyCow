package com.dphan.dirtycow;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Streaming;

/**
 * Created by dphan on 12/12/17.
 */

public interface FileDownloadClient {

    @GET("images/futurestudio-university-logo.png")
    Call<ResponseBody> downloadFile();

    @GET("davidqphan/dirtycow-android-poc/raw/master/{picture}")
    Call<ResponseBody> downloadPicture(@Path("picture") String picture);
}
