package comp5216.sydney.edu.au.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by ZhouShujian on 16/08/15.
 */

public class UsersAdapter extends ArrayAdapter<User> {

    public UsersAdapter(Context context, ArrayList<User> users){

        super(context, 0, users);

    }//constructor

    private static class ViewHolder{

        TextView name;
        TextView hometown;

    }

    /*public View getView(int position, View convertView, ViewGroup parent){

        User user=getItem(position);

        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);
        }

        TextView tvName=(TextView)convertView.findViewById(R.id.tvName);
        TextView tvHome=(TextView)convertView.findViewById(R.id.tvHome);

        tvName.setText(user.name);
        tvHome.setText(user.hometown);
        return convertView;

    }*/

    public View getView(int position, View convertView, ViewGroup parent){
        User user=getItem(position);
        ViewHolder viewHolder;

        if(convertView==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater=LayoutInflater.from(getContext());
            convertView=inflater.inflate(R.layout.item_user, parent, false);
            viewHolder.name=(TextView)convertView.findViewById(R.id.tvName);
            viewHolder.hometown=(TextView) convertView.findViewById(R.id.tvHome);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }

        viewHolder.name.setText(user.name);
        viewHolder.hometown.setText(user.hometown);
        return convertView;
    }

}
