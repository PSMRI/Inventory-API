package com.iemr.inventory.to.provider;

import java.sql.Timestamp;
import java.time.Instant;
import java.lang.reflect.Type;

import com.google.gson.*;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonParseException;

public class JsonUtils {
	 public static final Gson GSON = new GsonBuilder()
		        .excludeFieldsWithoutExposeAnnotation()
		        .setLongSerializationPolicy(LongSerializationPolicy.STRING)
		        .serializeNulls()
		        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
		        .registerTypeAdapter(Timestamp.class, new JsonDeserializer<Timestamp>() {
		            @Override
		            public Timestamp deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
		                    throws JsonParseException {
		                try {
		                    if (json.isJsonNull()) return null;

		                    JsonPrimitive primitive = json.getAsJsonPrimitive();

		                    if (primitive.isNumber()) {
		                        return new Timestamp(primitive.getAsLong()); // handles millis timestamp
		                    } else if (primitive.isString()) {
		                        String str = primitive.getAsString();
		                        try {
		                            return Timestamp.from(Instant.parse(str)); // ISO-8601
		                        } catch (Exception e) {
		                            return Timestamp.valueOf(str); // SQL-style fallback
		                        }
		                    }
		                } catch (Exception e) {
		                    throw new JsonParseException("Failed to parse timestamp: " + json, e);
		                }
		                return null;
		            }
		        })
		        .create();
}
