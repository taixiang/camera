package com.qhyccd.expand;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;

import com.qhyccd.R;

/**
 * @author tx
 * @date 2018/6/29
 */
public class ExpandAdapter extends BaseExpandableListAdapter {

    private LayoutInflater inflater;
    private Context context;

    public ExpandAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return 4;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 5;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ParentHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.expand_parent_view, null);
            holder = new ParentHolder();
            convertView.setTag(holder);
        } else {
            holder = (ParentHolder) convertView.getTag();
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.expand_child_view, null);
            holder = new ChildHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ChildHolder) convertView.getTag();
        }
        if (childPosition == 4) {
            holder.child_ll.setBackgroundResource(R.drawable.shape_child);
        } else {
            holder.child_ll.setBackgroundColor(ContextCompat.getColor(context, R.color.green));
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return groupPosition == childPosition;
    }

    private static class ParentHolder {

    }

    private static class ChildHolder {
        private LinearLayout child_ll;

        public ChildHolder(View view) {
            child_ll = view.findViewById(R.id.child_ll);
        }
    }

}
