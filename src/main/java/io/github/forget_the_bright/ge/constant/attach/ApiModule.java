package io.github.forget_the_bright.ge.constant.attach;

// ApiModule.java
public enum ApiModule {
    ALARMS("alarms", "报警管理", "/historian-rest-api", AuthScheme.BEARER),
    COLLECTORS("collectors", "采集器管理", "/historian-rest-api", AuthScheme.BEARER),
    SYSTEMS("systems", "系统管理", "/historian-rest-api", AuthScheme.BEARER),
    DATA("data", "数据管理", "/historian-rest-api", AuthScheme.BEARER),
    TAGS("tags", "标签管理", "/historian-rest-api", AuthScheme.BEARER),
    OAUTH("oauth", "oAuth管理", "", AuthScheme.BASIC);

    private final String code;
    private final String description;
    private final String contextPath;
    private final AuthScheme authType;

    ApiModule(String code, String description, String contextPath, AuthScheme authType) {
        this.code = code;
        this.description = description;
        this.contextPath = contextPath;
        this.authType = authType;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getContextPath() {
        return contextPath;
    }

    public AuthScheme getAuthType() {
        return authType;
    }
}
