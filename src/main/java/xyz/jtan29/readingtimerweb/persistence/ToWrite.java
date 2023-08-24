package xyz.jtan29.readingtimerweb.persistence;

import org.json.JSONObject;

public interface ToWrite {
    // EFFECTS: produces representation of this as a JSONObject
    public JSONObject toJson();
}
