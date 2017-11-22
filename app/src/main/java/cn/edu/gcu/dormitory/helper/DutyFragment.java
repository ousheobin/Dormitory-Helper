package cn.edu.gcu.dormitory.helper;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gcu.dormitory.helper.dao.DutyDao;
import cn.edu.gcu.dormitory.helper.pojo.NoteBean;
import cn.edu.gcu.dormitory.helper.pojo.PhoneBean;

public class DutyFragment extends Fragment implements AdapterView.OnItemLongClickListener {

    private DutyDao dutyDao;
    private ListView listView;
    private List<NoteBean> data;
    private DutyAdapter dutyAdapter;

    public DutyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        data = getData();
        dutyAdapter = new DutyAdapter(getContext(),R.layout.note_cell,data);
        listView.setAdapter(dutyAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_duty, container, false);
        dutyDao = new DutyDao(getContext());
        // 增加按钮
        Button btn = (Button)view.findViewById(R.id.btn_add_notes);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),AddNotificationActivity.class);
                getActivity().startActivity(intent);
            }
        });
        // ListView
        data = getData();
        dutyAdapter = new DutyAdapter(getContext(),R.layout.note_cell,data);
        listView = (ListView) view.findViewById(R.id.duty_note_list);
        listView.setAdapter(dutyAdapter);
        listView.setLongClickable(true);
        listView.setOnItemLongClickListener(this);
        return view;
    }

    @Override
    public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id) {
        final NoteBean bean = (NoteBean)parent.getItemAtPosition(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("是否这条记录 >.< ~");
        builder.setTitle("Delete 删除");
        builder.setPositiveButton("是的，确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dutyDao.deleteNotes(bean.getId());
                data.remove(position);
                dutyAdapter.notifyDataSetChanged();
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
        return false;
    }

    public static class DutyAdapter extends ArrayAdapter<NoteBean>{

        private int resourceID;

        public DutyAdapter(Context context, int resource, List<NoteBean> objects) {
            super(context, resource, objects);
            resourceID = resource;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(getContext(), resourceID, null);
                holder = new ViewHolder();
                holder.title = (TextView)convertView.findViewById(R.id.note_list_title);
                holder.content = (TextView) convertView.findViewById(R.id.note_list_content);
                holder.time = (TextView) convertView.findViewById(R.id.note_list_time);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            NoteBean note = getItem(position);
            holder.title.setText(note.getTitle());
            holder.content.setText(note.getContent());
            holder.time.setText(note.getDate());
            return convertView;
        }


        class ViewHolder{
            TextView title;
            TextView time;
            TextView content;
        }

    }

    public List<NoteBean> getData(){
        return dutyDao.getNotes();
    }

}
