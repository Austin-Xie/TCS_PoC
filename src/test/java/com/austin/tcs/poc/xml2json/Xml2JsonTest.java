package com.austin.tcs.poc.xml2json;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

public class Xml2JsonTest {

	@Test
	public void testXml2Json() throws FileNotFoundException, IOException, JSONException {
		
		Xml2Json x2j = new Xml2Json();
		JSONObject jsonObj = x2j.xml2Json(new FileReader(new File("/home/austin/Projects/TCS/wurfl.xml")));
		assertTrue(jsonObj.getJSONObject("wurfl").optJSONObject("devices") != null);
		
		JSONArray jsonDeviceArray = jsonObj.getJSONObject("wurfl").optJSONObject("devices").optJSONArray("device");
		assertTrue(jsonDeviceArray != null);
		
		
		System.out.println("device number: " + jsonDeviceArray.length());
	}

}
