package com.fabrick.test.response;

import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author JuanBrito
 *
 */
public class RestResponse {

	@JsonProperty("_metadata_")
	private ArrayList<HashMap<String, String>> metadata = new ArrayList<>();

	public ArrayList<HashMap<String, String>> getMetadata() {
		return metadata;
	}
	
	public void addMetadata(String type, String code, String data) {
		HashMap<String, String> ht = new HashMap<String, String>();
		
		ht.put("type", type);
		ht.put("code", code);
		ht.put("data", data);
		
		metadata.add(ht);
	}
	
	
}
