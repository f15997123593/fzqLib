package com.fzq.retrofitmanager.http;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

/**
 * <p>
 * 注意：如果方法的泛型指定的类不是ResponseBody,retrofit会将返回的string用json转换器自动转换该类的一个对象,转换不成功就报错
 * 如果不需要Gson转换,那么就指定泛型为ResponseBody,只能是ResponseBody,子类都不行.
 * @author fzq 2018/09/05 15:22
 * @version V1.0.0
 * @name HttpParams
 */
public interface ApiService {

    /**
     * GET请求,请求url
     * 可以添加请求头
     * @param url
     * @return
     */
    @GET
    Call<ResponseBody> executeGet(@Url String url);

    @GET
    Call<ResponseBody> executeGet(@Url String url, @Header("authorization") String authorization);

    @GET
    Call<ResponseBody> executeGet(@Url String url, @Header("authorization") String authorization,@Header("Accept-Language") String language);


    /**
     * POST方式将以表单的方式传递键值对作为请求体发送到服务器
     * 其中@FormUrlEncoded 以表单的方式传递键值对
     * 其中 @Path：所有在网址中的参数（URL的问号前面）
     * 另外@FieldMap 用于POST请求，提交多个表单数据，@Field：用于POST请求，提交单个数据
     * 使用@url是为了防止URL被转义为https://10.33.31.200:8890/msp%2Fmobile%2Flogin%3
     */
    @FormUrlEncoded
    @POST
    Call<ResponseBody> executePost(@Url String url, @FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST
    Call<ResponseBody> executePost(@Url String url, @Header("authorization") String authorization, @FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST
    Call<ResponseBody> executePost(@Url String url, @Header("authorization") String authorization,@Header("Accept-Language") String language, @FieldMap Map<String, String> map);

    @POST
    Call<ResponseBody> executePost(@Url String url, @Body RequestBody info);

    @POST
    Call<ResponseBody> executePost(@Url String url, @Header("authorization") String authorization, @Body RequestBody info);

    @POST
    Call<ResponseBody> executePost(@Url String url, @Header("authorization") String authorization, @Header("Accept-Language") String language, @Body RequestBody info);


    /**
     * 单文件上传
     * @param description
     * @param file @Part MultipartBody.Part file 使用MultipartBody.Part类发送文件file到服务器
     * @return 状态信息String
     */
    @Multipart
    @POST
    Call<ResponseBody> uploadFile(@Url String url, @Header("authorization") String authorization, @Part("description") RequestBody description, @Part MultipartBody.Part file);

    /**
     * 单文件上传
     * @param description
     * @param file @Part MultipartBody.Part file 使用MultipartBody.Part类发送文件file到服务器
     * @return 状态信息String
     */
    @Multipart
    @POST
    Call<ResponseBody> uploadFile(@Url String url, @Part("description") RequestBody description, @Part MultipartBody.Part file);

    @Multipart
    @POST
    Call<ResponseBody> shjUploadFile(@Url String url, @Header("authorization") String authorization, @Part MultipartBody.Part fileType,
                                     @Part MultipartBody.Part bucketName, @Part MultipartBody.Part file);



}