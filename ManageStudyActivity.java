package retrofit.com.mypage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ManageStudyActivity extends AppCompatActivity implements View.OnClickListener {
     ChangeCountDialog countDialog;
     StudyOutDialog studyOutDialog;
     MypageApi mypageApi;
    @BindView(R.id.study_duration) TextView study_duration;
    @BindView(R.id.study_out) TextView study_out;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_study);
        countDialog = new ChangeCountDialog(this);
        studyOutDialog = new StudyOutDialog(this);
        ButterKnife.bind(this);

        //retrofit통신
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://54.180.69.136:3000/")
                .build();

        mypageApi = retrofit.create(MypageApi.class);
}
//
//    @OnClick(value = {R.id.study_duration, R.id.study_out})
//    void OnClick(View view){
//        switch (view.getId()){
//            //스터디 관리 페이지로이동
//            case R.id.study_duration:
//                countDialog.show();
//                break;
//            case R.id.study_out:
//                break;
//
//        }
//    }
    @OnClick(value = {R.id.study_duration, R.id.study_out})
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //스터디 관리 페이지로이동
            case R.id.study_duration:
                countDialog.show();
                countDialog.setDialogListener(new DialogListener() {
                    @Override
                    public void onPositive(int count) {
                            Call<ResponseMessage> change = mypageApi.changeCount("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lk" +
                                    "Ijo2LCJ1c2VyX25hbWUiOiLsmIHrr7wiLCJ1c2" +
                                    "VyX2VtYWlsIjoieXl5IiwidXNlcl9waG9uZSI6IjAxMDEyMzQxMjM1IiwiaWF0IjoxNTQxNTkxOTI0LCJleHAiOjE" +
                                    "1NTAyMzE5MjR9.m4OqDj5CkJpEhz81zebOHI62ZV290lpQOHuTW5NRgB0", count);
                            change.enqueue(new Callback<ResponseMessage>() {
                                @Override
                                public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                                    ResponseMessage responseMessage = response.body();
                                    Log.d("okay", responseMessage.getMessage());

                                }

                                @Override
                                public void onFailure(Call<ResponseMessage> call, Throwable t) {

                                }
                            });
                        }

                });
                break;
            case R.id.study_out:
                studyOutDialog.show();
                break;

        }
    }
}
