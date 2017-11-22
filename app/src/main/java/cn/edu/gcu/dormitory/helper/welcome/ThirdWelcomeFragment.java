package cn.edu.gcu.dormitory.helper.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cn.edu.gcu.dormitory.helper.R;
import cn.edu.gcu.dormitory.helper.RegisterActivity;

/**
 * Created by ousheobin on 2017/9/12.
 */

public class ThirdWelcomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welcome_third,null);
        Button btn = (Button) view.findViewById(R.id.open_app_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RegisterActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return view;
    }
}
