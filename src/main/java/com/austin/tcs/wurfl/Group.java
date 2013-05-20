package com.austin.tcs.wurfl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Group {
	String id;

	public Group(String id) {
		super();
		this.id = id;
	}
	
	public Group(JSONObject jsonGroup) {
		super();
		capabilities = new ArrayList<Capability> ();
		try {
			this.id = jsonGroup.getString("id");
			if (jsonGroup.has("capability")) {
				JSONArray optJSONArray = jsonGroup.optJSONArray("capability");
				if(null != optJSONArray) {
					
					for (int i = 0; i < optJSONArray.length(); i++) {
						capabilities.add(new Capability(optJSONArray.getJSONObject(i)));
					}
				} else {
					JSONObject jsonCapability = jsonGroup.getJSONObject("capability");
					capabilities.add(new Capability(jsonCapability));
				}
			}
			
		} catch (JSONException je) {
			throw new RuntimeException(je);
		}
	}
	
	public Group() {
		super();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	private List<Capability> capabilities;

	public List<Capability> getCapabilities() {
		return capabilities;
	}

	public void setCapabilities(List<Capability> capabilities) {
		this.capabilities = capabilities;
	}

}
