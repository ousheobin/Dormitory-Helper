package cn.edu.gcu.dormitory.helper;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import cn.edu.gcu.dormitory.helper.dao.OptionsDao;

public class MeFragment extends Fragment {


    public MeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmen_me, container, false);
        LinearLayout changePassword = (LinearLayout)view.findViewById(R.id.btn_me_change_password);
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),ChangePasswordActivity.class);
                startActivity(intent);

            }
        });
        LinearLayout clearData = (LinearLayout) view.findViewById(R.id.clear_data);
        clearData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Hints 提示");
                builder.setMessage("是否清空全部的数据 @.@");
                builder.setPositiveButton("是的，确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        OptionsDao optionsDao = new OptionsDao(getContext());
                        dialog.dismiss();
                        optionsDao.deleteAllData();
                        Intent intent = new Intent(getContext(),MainActivity.class);
                        getActivity().startActivity(intent);
                        getActivity().finish();
                    }
                });
                builder.setNegativeButton("点错了，取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });
        Button logout = (Button) view.findViewById(R.id.btn_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        LinearLayout about = (LinearLayout) view.findViewById(R.id.btn_about);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),AboutActivity.class);
                getActivity().startActivity(intent);
            }
        });

        return view;
    }

}
