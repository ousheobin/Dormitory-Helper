package cn.edu.gcu.dormitory.helper;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gcu.dormitory.helper.dao.PhoneDao;
import cn.edu.gcu.dormitory.helper.pojo.PhoneBean;
import cn.edu.gcu.dormitory.helper.premission.PremissionType;

public class PhoneFragment extends Fragment implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener {

    private ListView listView;
    private PhoneAdapter adapter;
    private PhoneDao phoneDao;
    private List<PhoneBean> data;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phone, container, false);
        phoneDao = new PhoneDao(getContext());
        Button addPhone = (Button) view.findViewById(R.id.btn_add_phone);
        addPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),AddPhoneActicity.class);
                startActivity(intent);
            }
        });
        listView= (ListView) view.findViewById(R.id.phone_page_list);
        data = getData();
        adapter = new PhoneAdapter(getContext(),R.layout.phone_cell,data);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter = new PhoneAdapter(getContext(),R.layout.phone_cell,getData());
        listView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView phone = (TextView)view.findViewById(R.id.phone_list_item_phone);
        TextView name = (TextView)view.findViewById(R.id.phone_list_item_name);
        dialog(name.getText().toString(),phone.getText().toString());
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        final PhoneBean bean = (PhoneBean)parent.getItemAtPosition(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("是否删除个室友的电话 >.< ~");
        builder.setTitle("Delete 删除");
        builder.setPositiveButton("是的，确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                phoneDao.deletePhone(bean.getId());
                adapter = new PhoneAdapter(getContext(),R.layout.phone_cell,getData());
                listView.setAdapter(adapter);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("点错了，取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
        return true;
    }

    public static class PhoneAdapter extends ArrayAdapter<PhoneBean> {

        private int resourceID;

        public PhoneAdapter(Context context, int resource, List<PhoneBean> objects) {
            super(context, resource, objects);
            resourceID = resource;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(getContext(), resourceID, null);
                holder = new ViewHolder();
                holder.name = (TextView) convertView.findViewById(R.id.phone_list_item_name);
                holder.phone = (TextView) convertView.findViewById(R.id.phone_list_item_phone);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            PhoneBean PhoneBean = getItem(position);
            holder.name.setText(PhoneBean.getName());
            holder.phone.setText(PhoneBean.getPhone());
            return convertView;
        }

        class ViewHolder{
            TextView name;
            TextView phone;
        }
    }

    protected void dialog(String name, final String phone) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("是否拨打"+name+"的电话 O.o");
        builder.setTitle("Hints 提示");
        builder.setPositiveButton("是的，确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                requestPermission();
            }
        });

        builder.setNegativeButton("不了，还是不找他了", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private List<PhoneBean> getData(){
        return  phoneDao.getPhoneBeans();
    }

    private void requestPermission() {
        //判断Android版本是否大于23
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(getActivity().getBaseContext(), Manifest.permission.CALL_PHONE);
            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE},
                        PremissionType.REQUEST_CODE_ASK_CALL_PHONE);
                return;
            } else {
                callPhone();
            }
        } else {
            callPhone();
        }
    }

    /**
     * 拨号方法
     */
    public void callPhone(){
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:13000000000"));
        try{
            startActivity(intent);
        }catch (SecurityException e){
            Toast.makeText(getContext(),"异常",Toast.LENGTH_LONG).show();
            Log.e("Exception",e.toString());
        }
    }


}

