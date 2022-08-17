package com.raghav.atom.exception;

import com.raghav.atom.model.ResourceType;
import lombok.Getter;

@Getter
public class ResourceNotFoundException extends Exception {
    private ResourceType resourceType;

    public ResourceNotFoundException(ResourceType resourceType) {
        super("Resource Not Found");
        this.resourceType = resourceType;
    }
}
