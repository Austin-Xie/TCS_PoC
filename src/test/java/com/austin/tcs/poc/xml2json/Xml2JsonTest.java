package com.austin.tcs.poc.xml2json;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import net.sf.ehcache.Cache;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import com.austin.tcs.poc.ehcache.WurflCacheAccessHelper;
import com.austin.tcs.poc.ehcache.WurflCacheManager;
import com.austin.tcs.wurfl.Device;
import com.austin.tcs.wurfl.Group;

public class Xml2JsonTest {

	@Test
	public void testXml2Json() throws FileNotFoundException, IOException, JSONException {

		Xml2Json x2j = new Xml2Json();
		JSONObject jsonObj = x2j.xml2Json(new FileReader(new File("/home/austin/Projects/TCS/wurfl.xml")));
		//		JSONObject jsonObj = x2j.xml2Json(new FileReader(new File("E:\\LocalGitHub\\TCS_PoC\\resources\\wurfl.xml")));

		assertTrue(jsonObj.getJSONObject("wurfl").optJSONObject("devices") != null);

		JSONArray jsonDeviceArray = jsonObj.getJSONObject("wurfl").optJSONObject("devices").optJSONArray("device");
		assertTrue(jsonDeviceArray != null);


		System.out.println("device number: " + jsonDeviceArray.length());

		WurflCacheManager manager = new WurflCacheManager();
		Cache cache = manager.getCache("TCS");
		WurflCacheAccessHelper<String, Device> utils = new WurflCacheAccessHelper<String, Device> (cache);

		for (int i = 0; i < jsonDeviceArray.length(); i++) {
			JSONObject jd = jsonDeviceArray.getJSONObject(i);
			String id = jd.getString("id");
			//			System.out.println("device id = " + jd.getString("id"));
			//			Device device = new Device(jd.getString("id"), jd.getString("user_agent"), jd.getString("fall_back"));
			Device device = new Device(jd);
			utils.write(id, device);
		}

		System.out.println("cache size = " + utils.getCacheSize());

		long time = System.currentTimeMillis();
		List<Group> groups = null;

		Device device = utils.read("nokia_generic_series60");
		System.out.println(device.getUserAgent());
		groups = device.getGroups();
		for (Group g : groups) {
			System.out.println("grp id = " + g.getId());
		}

		device = utils.read("browser_blackberry_4_1");
		System.out.println(device.getUserAgent());
		groups = device.getGroups();
		for (Group g : groups) {
			System.out.println("grp id = " + g.getId());
		}
		device = utils.read("browser_blackberry_4_1");
		System.out.println(device.getUserAgent());
		groups = device.getGroups();
		for (Group g : groups) {
			System.out.println("grp id = " + g.getId());
		}
		device = utils.read("browser_mib_2_1");
		System.out.println(device.getUserAgent());
		groups = device.getGroups();
		for (Group g : groups) {
			System.out.println("grp id = " + g.getId());
		}
		device = utils.read("browser_opera_mobi_11_1");
		System.out.println(device.getUserAgent());
		groups = device.getGroups();
		for (Group g : groups) {
			System.out.println("grp id = " + g.getId());
		}

		long time2 = System.currentTimeMillis();
		System.out.println("seeking time = " + (time2 - time));
	}

}
