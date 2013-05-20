package com.austin.tcs.wurfl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Device {

	private String id;
	
//	@Alias("")
	private String user_agent;
	
	private String fall_back;
	
	private List<Group> groups = new ArrayList<Group>();

	public Device(String id, String userAgent, String fallBack) {
		super();
		this.id = id;
		this.user_agent = userAgent;
		this.fall_back = fallBack;
	}
	
	public Device(JSONObject jsonDevice)  {
		super();
		try {
			this.id = jsonDevice.getString("id");
			this.user_agent = jsonDevice.getString("user_agent");
			this.fall_back = jsonDevice.getString("fall_back");
			if (jsonDevice.has("group")) {
				JSONArray jsonGrps = jsonDevice.optJSONArray("group");
				if (null != jsonGrps) {
					for (int i = 0; i < jsonGrps.length(); i++) {
						groups.add(new Group(jsonGrps.getJSONObject(i)));
					}
				} else {
					JSONObject jsonGrp = jsonDevice.getJSONObject("group");
					groups.add(new Group(jsonGrp));
				}
			}
			
		} catch (JSONException je) {
			throw new RuntimeException(je);
		}
		
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserAgent() {
		return user_agent;
	}

	public void setUserAgent(String userAgent) {
		this.user_agent = userAgent;
	}

	public String getFallBack() {
		return fall_back;
	}

	public void setFallBack(String fallBack) {
		this.fall_back = fallBack;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
}
