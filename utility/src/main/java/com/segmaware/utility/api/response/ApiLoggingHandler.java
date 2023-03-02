package com.segmaware.utility.api.response;

import com.segmaware.utility.helper.JsonHelper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ApiLoggingHandler implements ResponseBodyAdvice {

    private final JsonHelper jsonHelper;
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        log.info(
                "Service URI {" + request.getURI().getPath() + "}, "
                + "User Info Ip{" + request.getRemoteAddress().getAddress().toString() + "}, "
                + "User {" + (request.getPrincipal() == null?
                        " Not Defined " : request.getPrincipal().getName()) + "}, "
                + "Body {{" + jsonHelper.toJson(body) + "}}"
        );
        return body;
    }
}
