/**
 * 
 */
package com.segmaware.utility.api.response;

import org.springframework.http.HttpStatus;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(METHOD)
/**
 * @author mohamed.hanfay
 *
 */
public @interface RestResponseEntity {
	HttpStatus httpStatus() default HttpStatus.OK;
	String successMessage() default "Success Action";
}
