package com.ajepst.callme;

import android.graphics.Bitmap;

public class DisplayContact {
	private String _name;
	private Long _id;

	public String get_name() {
		return _name;
	}

	public void set_name(String name) {
		_name = name;
	}

	public Long get_id() {
		return _id;
	}

	public void set_id(Long id) {
		_id = id;
	}
	
	private Bitmap _bitmap;

	public Bitmap get_bitmap() {
		return _bitmap;
	}

	public void set_bitmap(Bitmap _bitmap) {
		this._bitmap = _bitmap;
	}
}
