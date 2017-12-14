package vn.nccsoft.apisdk;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.Toast;

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

import java.util.Arrays;


public class FragmentLogin extends DialogFragment {
    EditText ed_email, ed_pass;
    Button btn_login, btn_loginfb;
    FrameLayout frameLayout;
    Dialog dialog;
    CallbackManager callbackManager;
    String fbid, fb_firtname, fb_lastname, fb_token, fb_email;
    ProgressDialog mProgressDialog;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // return super.onCreateDialog(savedInstanceState);
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        dialog = new Dialog(getActivity());
        dialog.setCancelable(false);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.fragment_sdklogin);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ed_email = dialog.findViewById(R.id.edt_email);
        ed_pass = dialog.findViewById(R.id.edt_password);
        btn_login = dialog.findViewById(R.id.btnLogin);
        btn_loginfb = dialog.findViewById(R.id.btn_loginfb);
        frameLayout = dialog.findViewById(R.id.frm_register);
        callbackManager = CallbackManager.Factory.create();
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
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                fbid = loginResult.getAccessToken().getUserId();
                fb_token = loginResult.getAccessToken().getToken();
                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    //                @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {
                            String name = object.getString("name");
                            String aa[] = name.split(" ");
                            fb_lastname = aa[aa.length - 1];
                            for (int i = 0; i < aa.length - 1; i++) {
                                fb_firtname += aa[i] + " ";
                            }
                            try {
                                fb_email = object.getString("email");
                            } catch (JSONException e) {
                            }
                            fb_firtname.trim();

                            SdkManager.loginFB(getActivity().getApplicationContext(), fbid, fb_token, fb_lastname, fb_firtname, fb_email, null
                                   ,getActivity().getPackageName() , callBack);
                        } catch (JSONException e) {
                            Toast.makeText(getActivity(), "Login failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email");
                graphRequest.setParameters(parameters);
                graphRequest.executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getActivity(), "Login failed!", Toast.LENGTH_SHORT).show();

            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validation())
                {
                    showProgressDialog();
                    SdkManager.login(getActivity().getApplicationContext(), ed_email.getText().toString(), ed_pass.getText().toString(), getActivity().getPackageName(), callBack);
                }
            }
        });
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentRegister fragmentRegister = new FragmentRegister();
                fragmentRegister.setCancelable(false);
                fragmentRegister.show(fragmentManager, "dialog");
            }
        });

        btn_loginfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressDialog();
                LoginManager.getInstance().logInWithReadPermissions(FragmentLogin.this, Arrays.asList("public_profile",
                        "email"));
            }
        });
        return dialog;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private boolean validation() {
        if (ed_pass.getText().toString().length() < 6) {
            ed_pass.setError("Passwords must be longer than 6 characters!");
            ed_pass.requestFocus();
            return false;
        }
        if (ApiUtils.isValidEmail(ed_email.getText().toString())) {
              return true;
        } else {
            ed_email.requestFocus();
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
}

