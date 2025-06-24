package com.iemr.inventory.to.provider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;

public class JsonUtils {
	 public static final Gson GSON = new GsonBuilder()
		        .excludeFieldsWithoutExposeAnnotation()
		        .setLongSerializationPolicy(LongSerializationPolicy.STRING)
		        .serializeNulls()
		        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
		        .create();
}
