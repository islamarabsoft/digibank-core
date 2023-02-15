/**
 * 
 */
package com.segmaware.utility.helper.basic;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Locale;

/**
 * @author mohamed.hanafy
 *
 */
@Data
public class BasicResponse {

	private String version = "1.0";
	private String environment;
	private HttpStatus httpStatus;
	private String errorCode;
	private String errorMessage;
	private Locale locale;
	private Object data;
	
	
	/**
	 * 
	 */
	public BasicResponse() {
		super();
	}


	/**
	 * @param httpStatus
	 * @param errorMessage
	 */
	public BasicResponse(HttpStatus httpStatus, String errorCode, String errorMessage) {
		super();
		this.httpStatus = httpStatus;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}



}
