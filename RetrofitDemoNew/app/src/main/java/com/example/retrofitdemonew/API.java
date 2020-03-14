package com.example.retrofitdemonew;
import java.util.List;
import java.util.Map;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface API {
    @GET("/weather_v2")
    Call<ResponseBody> getJson();

    @GET("/sug")
    Call<ResponseBody> getData(@Query("code") String code, @Query("q") String q);

    @GET("/cloud-music/163/")
    Call<ResponseBody> getMusic(@QueryMap Map<String,Object> parm);

    @GET
    Call<ResponseBody> getJson(@Url String url);

    //post方式请求
    @POST("/post/string")
    Call<PostWithParms> getData(@Query("string") String text);

    //url方式进行post提交
    @POST
    Call<ResponseBody> getDataUri(@Url String url);

    //参数为响应体PostWithParms，必须是此类型
    @POST("/post/comment")
    Call<PostWithParms> setData(@Body CommitBean data);

    /*文件上传的接口
        Part一般用于文件上传，作用于方法的参数,用于定义Multipart请求的每个part
         1, 如果类型是okhttp3.MultipartBody.Part，内容将被直接使用。
             省略part中的名称,即 @Part MultipartBody.Part part
         2, 如果类型是RequestBody，那么该值将直接与其内容类型一起使用。
             在注释中提供part名称（例如，@Part（“foo”）RequestBody foo）。
         3, 其他对象类型将通过使用转换器转换为适当的格式。
             在注释中提供part名称（例如，@Part（“foo”）Image photo）

         参考文献：https://blog.csdn.net/u014644594/article/details/83009998
     */
    @Multipart
    @POST("/file/upload")
    Call<FileBean> postFile(@Part MultipartBody.Part file);


    //多文件上传，参数用List集合封装s
    @Multipart
    @POST("/files/upload")
    Call<FileBean> postFiles(@Part List<MultipartBody.Part> files);

}
