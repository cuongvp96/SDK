package vn.nccsoft.apisdk;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by vancu on 13/12/2017.
 */

public class FragmentRegister extends DialogFragment {
    EditText ed_email, ed_pass,edt_name,edt_rePassword;
    Button btn_register;
    Dialog dialog;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // return super.onCreateDialog(savedInstanceState);
        dialog = new Dialog(getActivity());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.fragment_sdkregister);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ed_email = dialog.findViewById(R.id.edt_email);
        ed_pass = dialog.findViewById(R.id.edt_password);
        edt_name = dialog.findViewById(R.id.edt_name);
        edt_rePassword = dialog.findViewById(R.id.edt_rePassword);
        btn_register = dialog.findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vlidation())
                SdkManager.register(getActivity().getApplicationContext(), ed_email.getText().toString(), ed_pass.getText().toString(),
                        getActivity().getApplicationContext().getPackageName(),edt_name.getText().toString());
            }
        });
        dialog.findViewById(R.id.close_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }

        });
        return dialog;
    }
    private boolean vlidation(){

        if(edt_name.getText().toString().isEmpty()){
            edt_name.setError("Bạn chưa nhập tên");
            return false;
        }
        if(ed_email.getText().toString().isEmpty()){
            ed_email.setError("Bạn chưa nhập email");
            return false;
        }
        if(ed_pass.getText().toString().length()<6){
            ed_pass.setError("Mật khẩu phải lớn hơn 6 kí tự");
            return false;
        }
        if(edt_rePassword.getText().toString().length()<6){
            edt_rePassword.setError("Mật khẩu phải lớn hơn 6 kí tự");
            return false;
        }
        if(ApiUtils.isValidEmail(ed_email.getText().toString())){
            if(ed_pass.getText().toString().equals(edt_rePassword.getText().toString()))
                return true;
            else {
                edt_rePassword.setError("Mật khẩu không khớp");
                return false;
            }
        }
        else{
            ed_email.setError("Email sai định dạng");
            return false;
        }

    }
}
