package retrofit.com.mypage;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StudyOutDialog  extends Dialog {
    @BindView(R.id.change_no)
    TextView change_no;
    @BindView(R.id.change_ok) TextView change_okay;
    MypageApi mypageApi;
    public StudyOutDialog(@NonNull Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.study_out_dialog);
        ButterKnife.bind(this);

        //retrofit통신
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://54.180.69.136:3000/")
                .build();

        mypageApi = retrofit.create(MypageApi.class);
    }

    @OnClick(R.id.change_ok)
    public void submit(){
        dismiss();
        Call<ResponseMessage> delete = mypageApi.studyOut("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lk" +
                "Ijo2LCJ1c2VyX25hbWUiOiLsmIHrr7wiLCJ1c2" +
                "VyX2VtYWlsIjoieXl5IiwidXNlcl9waG9uZSI6IjAxMDEyMzQxMjM1IiwiaWF0IjoxNTQxNTkxOTI0LCJleHAiOjE" +
                "1NTAyMzE5MjR9.m4OqDj5CkJpEhz81zebOHI62ZV290lpQOHuTW5NRgB0");
        delete.enqueue(new Callback<ResponseMessage>() {
            @Override
            public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                ResponseMessage responseMessage = response.body();
                Log.d("delete success",responseMessage.getMessage());
            }

            @Override
            public void onFailure(Call<ResponseMessage> call, Throwable t) {

            }
        });
    }
}
