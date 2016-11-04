package com.karman.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.karman.model.Services;
import com.squareup.picasso.Picasso;

import net.simplifiedcoding.androidretrofitexample.R;

import java.util.List;

public class ServiceListAdapter extends BaseAdapter {
	private Activity activity;
	private LayoutInflater inflater;
	private List<Services> services;

	public ServiceListAdapter (Activity activity, List<Services> services) {
		this.activity = activity;
		this.services = services;
	}

	@Override
	public int getCount() {
		return services.size();
	}

	@Override
	public Object getItem(int location) {
		return services.get(location);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (inflater == null)
			inflater = (LayoutInflater) activity.getSystemService (Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null)
			convertView = inflater.inflate(R.layout.listview_item_services, null);

		ImageView image = (ImageView) convertView.findViewById (R.id.service_image);
		TextView service_name = (TextView) convertView.findViewById(R.id.service_title);
		final Services s = services.get(position);

		Picasso.with (activity).load (s.getImage ()).into (image);
		service_name.setText(s.getRole_name ());

		convertView.setOnClickListener (new View.OnClickListener () {
			private void die () {
			}

			@Override
			public void onClick (View arg0) {
				Toast.makeText (activity, "app flow :" + s.getApp_flow () +
						"\nrole id :" + s.getRole_id () +
						"\nrole name :" + s.getRole_name (), Toast.LENGTH_SHORT).show ();
			}
		});
		return convertView;
	}

}