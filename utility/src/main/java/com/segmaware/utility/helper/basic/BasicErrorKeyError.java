package com.segmaware.utility.helper.basic;

import lombok.Getter;

/**
 * @author mohamed.hanafy
 *
 */
@Getter
public enum BasicErrorKeyError implements KeyError {



    //General Error
    GENERAL("GENERAL"),
    //Bad Request
    BAD_REQUEST("BAD_REQUEST"),
    //UnAuthorized
    UNAUTHORIZED("UNAUTHORIZED"),
    //Database Error
    DATABASE_ERROR("database.violation"),
    // Resource Not Found
    NOT_FOUND("ERROR_NOT_FOUND");

    BasicErrorKeyError(String key) {
        this.key = key;
    }
    private String key;
}