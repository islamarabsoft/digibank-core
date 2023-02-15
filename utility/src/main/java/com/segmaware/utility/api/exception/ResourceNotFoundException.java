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
public class ResourceNotFoundException extends ApplicationException {

	private static final long serialVersionUID = 1439465498351813593L;

	private KeyError key;
	private String message;
	private HttpStatus status;

	public ResourceNotFoundException() {
		super(BasicErrorKeyError.NOT_FOUND);
		this.key = BasicErrorKeyError.NOT_FOUND;
		this.message = key.getKey();
		this.status = HttpStatus.NOT_FOUND;
	}

	public ResourceNotFoundException(KeyError key) {
		super(key);
		this.key = key;
		this.message = key.getKey();
		this.status = HttpStatus.NOT_FOUND;
	}

	public ResourceNotFoundException(String message) {
		super(BasicErrorKeyError.NOT_FOUND);
		this.key = BasicErrorKeyError.NOT_FOUND;
		this.message = message;
		this.status = HttpStatus.NOT_FOUND;
	}

	public <D> ResourceNotFoundException(Class<D> destinationType) {
		super(BasicErrorKeyError.NOT_FOUND);
		this.key = BasicErrorKeyError.NOT_FOUND;
		this.message = destinationType.getName();
		this.status = HttpStatus.NOT_FOUND;
	}
}
