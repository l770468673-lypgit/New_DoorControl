package com.doorcontrol.ruili.my.doorcontrol.restful.model;

public class ResponseHead {

	public int resultCode;
	public String desc;

	@Override
	public String toString() {
		return "ResponseHead{" +
				"resultCode=" + resultCode +
				", desc='" + desc + '\'' +
				'}';
	}
}
