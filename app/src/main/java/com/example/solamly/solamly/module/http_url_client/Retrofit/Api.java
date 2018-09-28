package com.example.solamly.solamly.module.http_url_client.Retrofit;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * @Author SOLAMLY
 * @Date 2018/9/8 13:16
 * @Description:
 */

public interface Api {

    /**
     * 普通的POST请求
     * @param informationid
     * @param pageSize
     * @param page
     * @return
     */
    @POST("information-core/controller/InformationCommentController/CommonList.html")
    Call<CommentBean> getCommenList(@Query("informationid") String informationid,
                              @Query("pageSize")String pageSize,
                              @Query("page")String page);

    /**
     * 上传单张图片
     * @param part
     * @param requestBody
     * @return
     */
    @Multipart
    @POST("upload/UploadAuthenticationServlet")
    Call<ResponseBody> uploadImage(@Part MultipartBody.Part part,
                                   @Part("userid") RequestBody requestBody);

    /**
     * 上传多个文件
     * @param part
     * @return
     */
    @Multipart
    @POST("upload/UploadAuthenticationServlet")
    Call<ResponseBody> uploadMoreImage(@Part List<MultipartBody.Part> part );
}

