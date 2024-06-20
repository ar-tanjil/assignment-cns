package com.cns.assignment.enums;

import java.util.stream.Stream;

public enum ProjectStatus {

    PRE(0), START(1), END(3);

    private int code;

    ProjectStatus(int value){
        this.code = value;
    }

    public int getCode(){
        return code;
    }

    public static ProjectStatus getEnum(int code){
        return Stream.of(ProjectStatus.values())
                .filter(s -> s.getCode() == code)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
