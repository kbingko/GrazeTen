package com.newsblur.serialization;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class DateStringTypeAdapter implements JsonDeserializer<Date>
{
  // 2012-07-23 02:43:02
  private final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  @Override
  public Date deserialize(JsonElement element, Type type, JsonDeserializationContext arg2) throws JsonParseException
  {
    try
    {
      if (element == null || TextUtils.isEmpty(element.getAsString()))
      {
        return new Date();
      }
      else
      {
        String dateString = element.getAsString();
        if (dateString.length() > 19)
        {
          dateString = dateString.substring(0, 19);
        }
        return df.parse(dateString);
      }
    }
    catch (ParseException e)
    {
      Log.e("DateTypeAdapter", e.getLocalizedMessage());
      return new Date();
    }
  }

}
