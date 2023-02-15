/**
 * 
 */
package com.segmaware.utility.api.exception;

import com.segmaware.utility.helper.basic.BasicErrorKeyError;
import com.segmaware.utility.helper.basic.KeyError;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * @author Alsayed Mohamed
 *
 */
@Getter
@Setter
public class BadRequestException extends ApplicationException {

	private static final long serialVersionUID = 1439465498351813593L;

	private KeyError key;
	private String message;
	private HttpStatus status;

	public BadRequestException() {
		super(BasicErrorKeyError.BAD_REQUEST);
		this.key = BasicErrorKeyError.BAD_REQUEST;
		this.message = key.getKey();
		this.status = HttpStatus.BAD_REQUEST;
	}

	public BadRequestException(KeyError key) {
		super(key);
		this.key = key;
		this.message = key.getKey();
		this.status = HttpStatus.BAD_REQUEST;
	}

	public BadRequestException(String message) {
		super(BasicErrorKeyError.BAD_REQUEST);
		this.key = BasicErrorKeyError.BAD_REQUEST;
		this.message = message;
		this.status = HttpStatus.BAD_REQUEST;
	}
}
