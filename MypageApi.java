package retrofit.com.mypage;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MypageApi {

    @GET("mypage/main")
    Call<ResponseMypage> requestMypage(@Header("user_token") String user_token);

    @FormUrlEncoded
    @POST("mypage/count")
    Call<ResponseMessage> changeCount(@Header("user_token") String user_token, @Field("study_count") int study_count);

    @DELETE("mypage/signout")
    Call<ResponseMessage> deleteUser(@Header("user_token") String user_token);

    @DELETE("mypage/studyout")
    Call<ResponseMessage> studyOut(@Header("user_token") String user_token);
}
