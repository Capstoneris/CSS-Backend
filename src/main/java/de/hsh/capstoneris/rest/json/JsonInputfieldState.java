package de.hsh.capstoneris.rest.json;

public class JsonInputfieldState {
    public String fieldId;
    public String value;
    public JsonInputfieldSelections[] selections;

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

    public JsonInputfieldSelections[] getSelections() {
        return selections;
    }

    public void setSelections(JsonInputfieldSelections[] selections) {
        this.selections = selections;
    }

    public JsonInputfieldState() {
    }

    public JsonInputfieldState(String fieldId, String value, JsonInputfieldSelections[] selections) {
        this.fieldId = fieldId;
        this.value = value;
        this.selections = selections;
    }
}
