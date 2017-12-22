package vn.nccsoft.apisdk;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
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
    private EditText ed_email, ed_pass, edt_name, edt_rePassword;
    private Button btn_register;
    private Dialog dialog;
    private ProgressDialog mProgressDialog;
    private int game_id;
    private int agency_id;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // return super.onCreateDialog(savedInstanceState);
        dialog = new Dialog(getActivity());
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.fragment_sdkregister);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ed_email = dialog.findViewById(R.id.edt_email);
        ed_pass = dialog.findViewById(R.id.edt_password);
        edt_name = dialog.findViewById(R.id.edt_name);
        edt_rePassword = dialog.findViewById(R.id.edt_rePassword);
        btn_register = dialog.findViewById(R.id.btn_register);
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setIndeterminate(true);
        final SuccessCallBack callBack = new SuccessCallBack() {
            @Override
            public void onSuccessResponse(String code) {
                if (code.equals("1")) {
                    dismiss();
                }
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                }
            }
        };
        final String android_id = Settings.Secure.getString(getActivity().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vlidation()) {
                    showProgressDialog();
                    SdkManager.register(getActivity().getApplicationContext(), edt_name.getText().toString(),getGame_id(),getAgency_id()
                            ,ed_email.getText().toString(),ed_pass.getText().toString(),"android",android.os.Build.VERSION.RELEASE,
                            android_id, callBack);
                }
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

    private boolean vlidation() {

        if (edt_name.getText().toString().isEmpty()) {
            edt_name.setError("You must enter a full name!");
            return false;
        }
        if (ed_email.getText().toString().isEmpty()) {
            ed_email.setError("You must enter an email!");
            return false;
        }
        if (ed_pass.getText().toString().length() < 6) {
            ed_pass.setError("Passwords must be longer than 6 characters!");
            return false;
        }
        if (edt_rePassword.getText().toString().length() < 6) {
            edt_rePassword.setError("Password incorrect!");
            return false;
        }
        if (ApiUtils.isValidEmail(ed_email.getText().toString())) {
            if (ed_pass.getText().toString().equals(edt_rePassword.getText().toString()))
                return true;
            else {
                edt_rePassword.setError("Password incorrect!");
                return false;
            }
        } else {
            ed_email.setError("Wrong email format!");
            return false;
        }

    }

    private void showProgressDialog() {

        mProgressDialog.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                }
            }
        }, 5000);
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public int getAgency_id() {
        return agency_id;
    }

    public void setAgency_id(int agency_id) {
        this.agency_id = agency_id;
    }
}
