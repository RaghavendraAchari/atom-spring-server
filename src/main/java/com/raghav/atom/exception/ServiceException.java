package com.raghav.atom.exception;

import com.raghav.atom.model.ResourceType;
import lombok.Getter;

@Getter
public class ServiceException extends Exception {
    private ResourceType resourceType;
    public ServiceException(ResourceType resourceType) {
        super("Internal Server Error");
        this.resourceType = resourceType;
    }

    public ServiceException(String message,ResourceType resourceType) {

        this.resourceType = resourceType;
    }
}
