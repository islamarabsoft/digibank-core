package com.segmaware.utility.api.exception;



import com.segmaware.utility.helper.MessageUtils;
import com.segmaware.utility.helper.basic.BasicErrorKeyError;
import com.segmaware.utility.helper.basic.BasicResponse;
import com.segmaware.utility.helper.basic.KeyError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.validation.BindException;
import java.nio.file.AccessDeniedException;
import java.util.logging.Logger;


/**
 * @author mohamed.hanafy
 * @author Sayed Mohamed
 *
 */
@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ExceptionControllerAdvice {

	@Value("${spring.profiles.active:dev}")
	private String environment;

	private final MessageUtils messageUtils;
	private final static String DEFAULT_MESSAGE = "Unexpected Error; this error not handled in right way";


	@ExceptionHandler(BindException.class)
	public ResponseEntity<BasicResponse> exceptionHandler(BindException ex) {

		log.error("Error :: ", ex);

		KeyError errorKey = BasicErrorKeyError.BAD_REQUEST;
		StringBuilder errorMessage = new StringBuilder();
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			String localizedMessage;
			try {
				localizedMessage = messageUtils.getLocalizedMessage(fieldError.getDefaultMessage());
			} catch (NoSuchMessageException messageEx) {
				localizedMessage = fieldError.getDefaultMessage();
			}
			if (errorMessage.length() != 0) {
				errorMessage.append(", ");
			}
			errorMessage.append("{").append(fieldError.getField()).append(" : ")
					.append(localizedMessage).append("}");
		}

		return getResponseEntity(httpStatus, errorMessage, errorKey);
	}

	@ExceptionHandler(ApplicationException.class)
	public ResponseEntity<BasicResponse> exceptionHandler(ApplicationException ex) {
		log.error("Error :: ", ex);

		StringBuilder errorMessage = new StringBuilder();
		KeyError errorKey = BasicErrorKeyError.GENERAL;
		if (ex.getKey() != null) {
			errorKey = ex.getKey();
		}
		errorMessage.append(
				messageUtils.getLocalizedMessage(errorKey.getKey()));

		return getResponseEntity(ex.getStatus(), errorMessage, errorKey);
	}

	@ExceptionHandler(NoSuchMessageException.class)
	public ResponseEntity<BasicResponse> exceptionHandler(NoSuchMessageException ex) {
		log.error("Error :: ", ex);
		StringBuilder errorMessage = new StringBuilder();
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		KeyError errorKey = BasicErrorKeyError.GENERAL;
		errorMessage.append(DEFAULT_MESSAGE);
		errorMessage.append("; ").append(ex.getMessage());
		return getResponseEntity(httpStatus, errorMessage, errorKey);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<BasicResponse> exceptionHandler(AccessDeniedException ex) {
		log.error("Error :: ", ex);
		StringBuilder errorMessage = new StringBuilder();
		HttpStatus httpStatus = HttpStatus.FORBIDDEN;
		KeyError errorKey = BasicErrorKeyError.UNAUTHORIZED;

		errorMessage.append(messageUtils.getLocalizedMessage(errorKey.getKey()));

		return getResponseEntity(httpStatus, errorMessage, errorKey);
	}

	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<BasicResponse> exceptionHandler(RecordNotFoundException ex){
		log.error("Error :: ", ex);
		StringBuilder errorMessage = new StringBuilder();
		KeyError errorKey = BasicErrorKeyError.NOT_FOUND;
		errorMessage.append(messageUtils.getLocalizedMessage(errorKey.getKey()));
		errorMessage.append(" {{ " + ex.getMessage() + " }}");
		return getResponseEntity(HttpStatus.NOT_FOUND, errorMessage, errorKey);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<BasicResponse> exceptionHandler(BadRequestException ex){
		log.error("Error :: ", ex);
		StringBuilder errorMessage = new StringBuilder();
		KeyError errorKey = BasicErrorKeyError.BAD_REQUEST;
		errorMessage.append(messageUtils.getLocalizedMessage(errorKey.getKey()));
		errorMessage.append(" {{ " + ex.getMessage() + " }}");
		return getResponseEntity(HttpStatus.BAD_REQUEST, errorMessage, errorKey);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<BasicResponse> exceptionHandler(DataIntegrityViolationException ex){
		log.error("Error :: ", ex);
		StringBuilder errorMessage = new StringBuilder();
		KeyError errorKey = BasicErrorKeyError.BAD_REQUEST;
		errorMessage.append(messageUtils.getLocalizedMessage(errorKey.getKey()));
		errorMessage.append(" {{ " + "Attributes combination in this request dose not valid, " +
				"please change in this request and try again" + " }}");
		return getResponseEntity(HttpStatus.BAD_REQUEST, errorMessage, errorKey);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<BasicResponse> exceptionHandler(Exception ex) {
		log.error("Error :: ", ex);
		StringBuilder errorMessage = new StringBuilder();
		HttpStatus httpStatus;
		switch (ex.getMessage()){
			case "Access is denied":
				httpStatus = HttpStatus.FORBIDDEN;
				break;
			default:
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		KeyError errorKey = BasicErrorKeyError.GENERAL;
		errorMessage.append(" {{" + ex.getMessage() + "}}");

		return getResponseEntity(httpStatus, errorMessage, errorKey);
	}

	private ResponseEntity<BasicResponse> getResponseEntity(HttpStatus httpStatus, StringBuilder errorMessage, KeyError errorKey) {
		BasicResponse error = createErrorResponse(errorKey, errorMessage, httpStatus);
		return new ResponseEntity(error, error.getHttpStatus());
	}

	private BasicResponse createErrorResponse(KeyError errorKey, StringBuilder errorMessage, HttpStatus httpStatus) {
		BasicResponse errorResponse = new BasicResponse();
		errorResponse.setEnvironment(environment);
		errorResponse.setLocale(LocaleContextHolder.getLocale());
		errorResponse.setErrorCode(errorKey.getKey());
		errorResponse.setErrorMessage(errorMessage.toString());
		errorResponse.setHttpStatus(httpStatus);

		return errorResponse;
	}
}
