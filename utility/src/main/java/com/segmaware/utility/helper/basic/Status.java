package com.segmaware.utility.helper.basic;

import lombok.Data;

/**
 * @author mohamed.hanafy
 *
 */
@Data
public class Status {

    String code;
    String message;

    public Status(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
