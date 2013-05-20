package com.austin.tcs.poc.xml2json;

import java.io.IOException;
import java.io.InputStreamReader;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class Xml2Json {
	
	public JSONObject xml2Json(InputStreamReader xmlSrc) throws IOException, JSONException {
		char[] buf = new char[1024 * 64];
		
		int cnt = -1;
		StringBuilder optStr = new StringBuilder(1024 * 1024 * 25);
		while((cnt = xmlSrc.read(buf)) > 0) {
			optStr.append(buf, 0, cnt);
		}
		
		String jsonStr = optStr.toString();
		System.out.println(jsonStr.substring(0, 100));
		return XML.toJSONObject(jsonStr);
	}

}
