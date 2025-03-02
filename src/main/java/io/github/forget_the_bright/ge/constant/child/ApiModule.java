package io.github.forget_the_bright.ge.constant.child;

// ApiModule.java
public enum ApiModule {
    ALARMS("alarms", "报警管理"),
    COLLECTORS("collectors", "采集器管理"),
    SYSTEMS("systems", "系统管理"),
    DATA("data", "数据管理"),
    TAGS("tags", "标签管理");

    private final String code;
    private final String description;

    ApiModule(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
