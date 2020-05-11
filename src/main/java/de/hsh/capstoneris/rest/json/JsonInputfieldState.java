package de.hsh.capstoneris.rest.json;

public class JsonInputfieldState {
    public String fieldId;
    public String value;
    public JsonInputfieldState[] selections;

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public JsonInputfieldState[] getSelections() {
        return selections;
    }

    public void setSelections(JsonInputfieldState[] selections) {
        this.selections = selections;
    }

    public JsonInputfieldState() {
    }

    public JsonInputfieldState(String fieldId, String value, JsonInputfieldState[] selections) {
        this.fieldId = fieldId;
        this.value = value;
        this.selections = selections;
    }
}
