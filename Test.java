package com.example.demo;

import java.lang.reflect.Type;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.MapDifference.ValueDifference;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Test {
	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {

		/////////////
		Image img1 = new Image("faizan", "12");
		Image img2 = new Image("faizan", "12");
		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);

		Gson g = new Gson();
		Type mapType = new TypeToken<Map<String, Object>>() {
		}.getType();
		Map<String, Object> firstMap = g.fromJson(mapper.writeValueAsString(img1), mapType);
		Map<String, Object> secondMap = g.fromJson(mapper.writeValueAsString(img2), mapType);
		///System.out.println(Maps.difference(firstMap, secondMap).entriesDiffering());

		Map<String, ValueDifference<Object>> diff = Maps.difference(firstMap, secondMap).entriesDiffering();

		System.out.println(diff.isEmpty());
		
		for(Map.Entry<String, ValueDifference<Object>> test:diff.entrySet()) {
			System.out.println("\n\n Field::: "+test.getKey());
			ValueDifference<Object> value = test.getValue();
			System.out.print("Old Value: "+value.leftValue());
			System.out.print("\t New Value: "+value.rightValue());
		}
	}
}
