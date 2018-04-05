package ysn.com.rxjava2andretrofit2demo.adpter;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import ysn.com.rxjava2andretrofit2demo.bean.Joke;

public class JokeTypeAdapter implements JsonDeserializer<Joke> {
    @Override
    public Joke deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonObject()) {
            Gson gson = new Gson();
            return gson.fromJson(json, Joke.class);
        }
        return null;
    }
}
