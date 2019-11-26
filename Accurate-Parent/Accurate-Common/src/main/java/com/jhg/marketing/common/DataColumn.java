package com.jhg.marketing.common;

import lombok.Data;

import java.util.Map;

@Data
public class DataColumn {

    private String columnName;
    private String controlType;
    private Class dataType;
    private boolean readOnly;
    private boolean isPrimaryKey;
    private boolean required;
    private String display;
    private String strDataType;
    private int order;
    private Map dataTokens;
    private boolean isShow;

    public DataColumn() {
    }

    public DataColumn(String columnName, String controlType, Class dataType, boolean readOnly, boolean isPrimaryKey,
                      boolean required, String display, Map dataTokens) {
        super();
        this.columnName = columnName;
        this.controlType = controlType;
        this.dataType = dataType;
        this.strDataType = dataType.getSimpleName().toLowerCase();
        this.readOnly = readOnly;
        this.isPrimaryKey = isPrimaryKey;
        this.required = required;
        this.display = display;
        this.dataTokens = dataTokens;
    }


    @Override
    public String toString() {
        return "[" + columnName + "," + display + "," + order + "]";
    }
}
