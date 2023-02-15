/**
 * 
 */
package com.segmaware.utility.api.exception;

import com.segmaware.utility.helper.basic.KeyError;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

/**
 * @author mohamed.hanafy
 *
 */
@Setter
@Getter
public class ApplicationException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 1439465498351813593L;

	private KeyError key;
	private String message;
	private HttpStatus status;

	public ApplicationException(KeyError key) {
		super(key.getKey());
		this.key = key;
		this.message = key.getKey();
		this.status = HttpStatus.INTERNAL_SERVER_ERROR;
	}
	public ApplicationException(KeyError key, HttpStatus status) {
		super(key.getKey());
		this.key = key;
		this.message = key.getKey();
		this.status = status;
	}

	public ApplicationException(KeyError key, String message) {
		super("Error Code " + key.getKey() + " : Message {" + message + "}");
		this.key = key;
		this.message = message;
		this.status = HttpStatus.INTERNAL_SERVER_ERROR;
	}

	public ApplicationException(KeyError key, String message, HttpStatus status) {
		super("Error Code " + key.getKey() + " : Message {" + message + "}");
		this.key = key;
		this.message = message;
		this.status = status;
	}


	public ApplicationException(KeyError key, String message, Throwable cause) {
		super("Error Code " + key.getKey() + " : Message {" + message + "}", cause);
		this.key = key;
		this.message = message;
		this.status = HttpStatus.INTERNAL_SERVER_ERROR;
	}
	public ApplicationException(KeyError key, String message, Throwable cause, HttpStatus status) {
		super("Error Code " + key.getKey() + " : Message {" + message + "}", cause);
		this.key = key;
		this.message = message;
		this.status = status;
	}
}
