package retrofit.com.mypage;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
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

public class ChangeCountDialog extends Dialog implements View.OnClickListener{
    @BindView(R.id.change_cnt) EditText change_cnt;
    @BindView(R.id.change_no) TextView change_no;
    @BindView(R.id.change_ok) TextView change_okay;
    MypageApi mypageApi;
    private DialogListener dialogListener;

    public ChangeCountDialog(@NonNull Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
     //   getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.change_count_dialog);
        ButterKnife.bind(this);

        //retrofit통신
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://54.180.69.136:3000/")
                .build();

        mypageApi = retrofit.create(MypageApi.class);
    }

    public void setDialogListener(DialogListener dialogListener){
        this.dialogListener = dialogListener;
    }

//    @OnClick(R.id.change_ok)
//    public void submit(){
//        cnt = Integer.parseInt(change_cnt.getText().toString());

//        dismiss();
//    }
//    @OnClick(R.id.change_no)
//    public void noChange(){
//        dismiss();
//    }
    @OnClick(R.id.change_ok)
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.change_ok:
                String out = change_cnt.getText().toString();
                if(out=="최소 1회") {
                    Toast.makeText(getContext(), "값을 입력해주세요.", Toast.LENGTH_LONG).show();
                }
                else{
                    int cnt = Integer.parseInt(out);
                    dialogListener.onPositive(cnt);
                    dismiss();
                    change_cnt.setText("");
                    change_cnt.setHint("최소 1회");
                }
                break;
                //아니오 눌렀을때도 처리해야함.
        }
    }
}
