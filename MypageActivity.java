package retrofit.com.mypage;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MypageActivity extends AppCompatActivity {
    AccountDeleteDialog deleteDialog;
    @BindView(R.id.changeImage) ImageView btn_changeImage;
    @BindView(R.id.study_manage) TextView btn_studymanage;
    @BindView(R.id.userName) TextView userName;
    @BindView(R.id.userLevel) TextView userLevel;
    @BindView(R.id.study_cnt) TextView study_cnt;
    @BindView(R.id.user_delete) TextView user_delete;

    //study_manage
    MypageApi mypageApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        ButterKnife.bind(this);
//       btn_changeImage.setEnabled(false);
//        btn_studymanage.setEnabled(true);
        deleteDialog = new AccountDeleteDialog(this);
        //retrofit통신
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://54.180.69.136:3000/")
                .build();

        mypageApi = retrofit.create(MypageApi.class);

        //데이터 받아오기
        Call<ResponseMypage> mypage = mypageApi.requestMypage("eyJhbGciOiJIUzI1NiIsInR5cCI6Ik" +
                "pXVCJ9.eyJ1c2VyX2lkIjo2LCJ1c2VyX25hbWUiOiLsmIHrr7wiLCJ1c2VyX2VtYWlsIjoieXl5Iiwid" +
                "XNlcl9waG9uZSI6IjAxMDEyMzQxMjM1IiwiaWF0IjoxNTQxNTkxOTI0LCJleHAiOjE1NTAyMzE5MjR9.m4OqDj5CkJpEhz81zebOHI62ZV290lpQOHuTW5NRgB0");
        mypage.enqueue(new Callback<ResponseMypage>() {
            @Override
            public void onResponse(Call<ResponseMypage> call, Response<ResponseMypage> response) {
                ResponseMypage responseMypage = response.body();
                if(response.body().isStatus()){
                    Log.d("success", response.body().getMessage());
                    userName.setText(response.body().getResult().getUser_name());
                    int level = response.body().getResult().getUser_level();
                    switch(level){
                        case 0:
                            userLevel.setText("개설자");
                            break;
                        case 1:
                            userLevel.setText("스터디원");
                            break;
                        case 2:
                            userLevel.setText("퍼스트러너");
                            break;
                        case 3:
                            userLevel.setText("프로참석러 && 개설자");
                            break;
                        case 4:
                            userLevel.setText("프로참석러 && 스터디원");
                            break;
                    }
                    study_cnt.setText(response.body().getResult().getUser_att_cnt()+" 회");
                }
            }

            @Override
            public void onFailure(Call<ResponseMypage> call, Throwable t) {

            }
        });
    }

    @OnClick(value = {R.id.study_manage, R.id.changeImage, R.id.user_delete})
    void OnClick(View view){
        switch (view.getId()){
            //스터디 관리 페이지로이동
            case R.id.study_manage:
//               Intent intent = new Intent(this, ManageStudyActivity.class);
//               startActivityForResult(intent, MANAGE);
                Intent intent = new Intent(this, ManageStudyActivity.class);
                this.startActivity(intent);
                break;
            case R.id.changeImage:
                break;
            case R.id.user_delete:
                deleteDialog.show();
                break;

        }
    }


}
