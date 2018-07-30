package com.dac.onlineparking.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	static ObjectMapper mapper = null;
	static {
		mapper = new ObjectMapper();

	}

	public static String convertJavaToJson(Object obj){
		String jsonStr = "{}";
		try {
			jsonStr = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException jpe) {
			try {
				throw jpe;
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			try {
				throw e;
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return jsonStr;
	}// end of method
}