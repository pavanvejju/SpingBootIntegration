package com.wc.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;


public class JsonUtil {
	
	private final static Logger logger = LoggerFactory.getLogger(JsonUtil.class);
	
	private static ObjectMapper mapper;
	
	static{
		mapper = new ObjectMapper();
		mapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
		mapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);	
		mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
	}

	
	public static String convertToJson(Object obj){
		String json=null;
		if(obj!=null){
			try {
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
				json = mapper.writeValueAsString(obj);
			} catch (JsonGenerationException e) {
				logger.error("Json Generation exception while trying to convert object to json.",e);
			} catch (JsonMappingException e) {
				logger.error("Json Mapping exception while trying to convert object to json.",e);
			} catch (IOException e) {
				logger.error("IOException while trying to convert object to json.",e);
			}
		}
		return json;
	}
	
	public static <T> T convertToObject(String json, Class<T> classType) {
		if (json != null && !json.equals("")) {	
			try {
				return mapper.readValue(json, classType);
			} catch (JsonParseException e) {
				logger.error("Json parse exception while trying to convert json to object.");
			} catch (JsonMappingException e) {
				logger.error("Json mapping exception while trying to convert json to object.");
			} catch (IOException e) {
				logger.error("IO exception while trying to convert json to object.");
			}
		}
		return null;
	}
	
	public static <T> List<T> convertToCollectionObject(String json, Class<T> classType) {
		if (json != null && !json.equals("")) {
			List<T> list;
			ObjectMapper mapper = new ObjectMapper();
			TypeFactory t = TypeFactory.defaultInstance();
			mapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
			mapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
			try {
				list = mapper.readValue(json, t.constructCollectionType(ArrayList.class,classType));
				return list;
			} catch (JsonParseException e) {
				logger.error("Json parse exception while trying to convert json to object.");
			} catch (JsonMappingException e) {
				logger.error("Json mapping exception while trying to convert json to object.");
			} catch (IOException e) {
				logger.error("IO exception while trying to convert json to object.");
			}
		}
		return null;
	}
	
	public static <T> Map<String, Object> convertToMap(String json, Class<T> classType) {
		if (json != null && !json.equals("")) {
			Map<String, Object> map;
			ObjectMapper mapper = new ObjectMapper();
			
			mapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
			mapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
			try {
				
				TypeReference<HashMap<String,Object>> typeRef 
	            = new TypeReference<HashMap<String,Object>>() {};

	            map = mapper.readValue(json, typeRef); 
								
				return map;
			} catch (JsonParseException e) {
				logger.error("Json parse exception while trying to convert json to Map.");
			} catch (JsonMappingException e) {
				logger.error("Json mapping exception while trying to convert json to Map.");
			} catch (IOException e) {
				logger.error("IO exception while trying to convert json to Map.");
			}
		}
		return null;
	}
	
	
}
