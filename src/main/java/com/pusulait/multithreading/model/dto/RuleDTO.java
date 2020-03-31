package com.pusulait.multithreading.model.dto;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Data
@RedisHash("rules")
public class RuleDTO extends BaseDTO{

    @Indexed
    private Long id;
    private String definition;

    @Indexed
    private String name;
    private String function;
    private String message;
    private String rawFunction;

}
