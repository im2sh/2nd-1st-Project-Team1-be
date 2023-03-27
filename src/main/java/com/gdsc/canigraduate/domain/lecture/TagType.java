package com.gdsc.canigraduate.domain.lecture;

public enum TagType {
    REQUIRED("필수"),
    DESIGN("설계"),
    MAJOR_REQUIRED("전공필수"),
    FUTURE_DESIGN("KNU미래설계"),
    LIBERAL_ARTS("인문교양"),
    STARTUP("창업"),

    CHEOMSUNG_NORMAL("첨성인일반"),
    CHEOMSUNG_BASE_SW("첨성인기초(소프트웨어)"),
    CHEOMSUNG_BASE_MATH("첨성인기초(수리"),
    CHEOMSUNG_BASE_WRITING("첨성인기초(글쓰기)"),

    CHEOMSUNG_CORE_SCIENCE("첨성인핵심(자연과학)"),
    CHEOMSUNG_CORE_SOCIETY("첨성인핵심(인문교양)"),
    CHEOMSUNG_CORE_ART("첨성인핵심(예술과 표현)"),
    ;

    private final String value;

    TagType(String value) {
        this.value = value;
    }

    public String getKey() {
        return this.name();
    }

    public String getValue() {
        return this.value;
    }
}
