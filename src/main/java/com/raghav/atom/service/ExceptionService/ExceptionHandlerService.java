package com.raghav.atom.service.ExceptionService;


import com.raghav.atom.exception.PhotoServiceException;
import com.raghav.atom.exception.ResourceNotFoundException;
import com.raghav.atom.exception.ServiceException;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerService {

    @Data
    class Response{
        public Response(String message, String requestUrl, String resourceType) {
            this.message = message;
            this.requestUrl = requestUrl;
            this.resourceType = resourceType;
        }

        private String message;
        private String requestUrl;
        private String resourceType;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public Response ResourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        return new Response(
                e.getMessage(),
                request.getRequestURL().toString(),
                e.getResourceType().name()
        );
    }

    @ExceptionHandler(PhotoServiceException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Response photoServiceException(Exception e, HttpServletRequest request){
        return new Response("Internal Service Error",
                request.getRequestURL().toString(),
                "Photos");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Response handleException(Exception e, HttpServletRequest request){
        return new Response(e.getMessage(),
                request.getRequestURL().toString(),
                "General");
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public Response handleServiceException(ServiceException e, HttpServletRequest request){
        return new Response(e.getMessage(),
                request.getRequestURL().toString(),
                e.getResourceType().name());
    }
}
