/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2015 Umeng, Inc
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.dzl.test.viewPager.v0122;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dzl.test.R;
import com.dzl.test.viewPager.v0122.ViewPagerActivity.ImageEntitiy;
import com.squareup.picasso.Picasso;

/**
 * 广告栏的Adapter
 * 
 * @author mrsimple
 */
public class HeaderImageEntitiyAdapter extends RecyclingPagerAdapter {

    private Context context;
    private List<ImageEntitiy> imageIdList;
    OnItemClickListener<ImageEntitiy> mItemClickListener;

    public HeaderImageEntitiyAdapter(Activity activity, List<ImageEntitiy> imageIdList) {
        this.context = activity;
        this.imageIdList = imageIdList;
    }

    @Override
    public int getCount() {
        return imageIdList.size();
    }

    /**
     * get really position
     * 
     * @param position
     * @return
     */
    private int getPosition(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup container) {
        ViewHolder holder;
        final ImageEntitiy item = getItem(position);
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.recommend_item, container, false);
            holder.imageView = (ImageView) view.findViewById(R.id.recommand_imageview);
            holder.titleTextView = (TextView) view.findViewById(R.id.recommend_title_tv);
            view.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null) {
                        mItemClickListener.onClick(item);
                    }
                }
            });
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.titleTextView.setText(item.getText());
        Picasso.with(container.getContext())
                .load(item.getUrl())
                .fit()
                .into(holder.imageView);
        return view;
    }

    public void setOnItemClickListener(OnItemClickListener<ImageEntitiy> listener) {
        mItemClickListener = listener;
    }

    private ImageEntitiy getItem(int position) {
        return imageIdList.get(getPosition(position));
    }

    private static class ViewHolder {
        ImageView imageView;
        TextView titleTextView;
    }
}
