package com.austin.tcs.wurfl;

import org.json.JSONException;
import org.json.JSONObject;

public class Capability {
	private String name;
	
	private String value;
	
	public Capability(JSONObject jsonCapability) {
		try {
			this.name = jsonCapability.getString("name");
			this.value = jsonCapability.getString("value");;
		} catch (JSONException je) {
			throw new RuntimeException(je);
		}
	}
	
	public Capability() {
		super();
	}

	public Capability(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}


}
