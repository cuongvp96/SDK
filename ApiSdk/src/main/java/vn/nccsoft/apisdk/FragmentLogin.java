package vn.nccsoft.apisdk;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class FragmentLogin extends DialogFragment {
    EditText ed_email,ed_pass;
    Button btn_login,btn_loginfb,btn_logingg;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        getDialog().setTitle("Đăng nhập");
        ed_email=view.findViewById(R.id.edt_email);
        ed_pass=view.findViewById(R.id.edt_password);
        btn_login=view.findViewById(R.id.btn_login);
        btn_loginfb=view.findViewById(R.id.btn_loginfb);
        btn_logingg=view.findViewById(R.id.btn_logingg);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SdkManager.getTokenLogin(getActivity().getApplicationContext(),ed_email.getText().toString(),ed_pass.getText().toString());

            }
        });
        return view;
    }


}
