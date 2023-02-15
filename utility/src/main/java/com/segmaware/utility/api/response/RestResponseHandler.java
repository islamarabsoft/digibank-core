package com.segmaware.utility.api.response;


import com.segmaware.utility.helper.SmartLocaleResolver;
import com.segmaware.utility.helper.basic.BasicResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Locale;

/**
 * @author mohamed.hanafy
 */
@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class RestResponseHandler implements ResponseBodyAdvice {

    private final SmartLocaleResolver smartLocaleResolver;

    @Value("${spring.profiles.active:dev}")
    private String environment;

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        RestResponseEntity restResponseEntity = returnType.getMethodAnnotation(RestResponseEntity.class);
        if(restResponseEntity != null) {
            BasicResponse successResponse = new BasicResponse();
            Locale locale = smartLocaleResolver.resolveLocale();

            successResponse.setLocale(locale);
            successResponse.setEnvironment(environment);
            successResponse.setHttpStatus(restResponseEntity.httpStatus());
            successResponse.setErrorCode(Constants.SuccessCode);
            successResponse.setErrorMessage(restResponseEntity.successMessage());
            successResponse.setData(body);
            response.setStatusCode(restResponseEntity.httpStatus());
            return successResponse;
        }
        return body;
    }

}
