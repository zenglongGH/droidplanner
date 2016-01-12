package com.playuav.android.graphic.map;

import com.o3dr.android.client.Drone;
import com.playuav.android.R;
import com.playuav.android.maps.MarkerInfo;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.o3dr.services.android.lib.coordinate.LatLong;
import com.o3dr.services.android.lib.drone.property.Attitude;
import com.o3dr.services.android.lib.drone.property.Gps;

public class GraphicDrone extends MarkerInfo.SimpleMarkerInfo {

	private Drone drone;

	public GraphicDrone(Drone drone) {
		this.drone = drone;
	}

	@Override
	public float getAnchorU() {
		return 0.5f;
	}

	@Override
	public float getAnchorV() {
		return 0.5f;
	}

	@Override
	public LatLong getPosition() {
        return isValid() ? drone.getGps().getPosition() :  null;
	}

	@Override
	public Bitmap getIcon(Resources res) {
		if (drone.isConnected()) {
			return BitmapFactory.decodeResource(res, R.drawable.quad);
		}
		return BitmapFactory.decodeResource(res, R.drawable.quad_disconnect);

	}

	@Override
	public boolean isVisible() {
		return true;
	}

	@Override
	public boolean isFlat() {
		return true;
	}

	@Override
	public float getRotation() {
        Attitude attitude = drone.getAttitude();
		return attitude == null ? 0 : (float) attitude.getYaw();
	}

	public boolean isValid() {
        Gps droneGps = drone.getGps();
		return droneGps != null && droneGps.isValid();
	}
}
