package edu.iit.cs445.gheo1.boundary;

import java.text.ParseException;

import com.google.gson.JsonObject;

public interface SearchBoundary {
    JsonObject searchByKeyword(String keyword);
    JsonObject searchByDate(String startDate, String endDate) throws ParseException;
}
