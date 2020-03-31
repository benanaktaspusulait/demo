package com.pusulait.multithreading.model.dto;

import lombok.Data;

@Data
public abstract class BaseDTO {
    protected abstract Long getId();
}
