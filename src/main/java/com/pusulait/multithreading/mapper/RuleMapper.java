package com.pusulait.multithreading.mapper;

import com.pusulait.multithreading.model.dto.RuleDTO;
import com.pusulait.multithreading.model.Rule;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RuleMapper extends BaseEntityMapper<RuleDTO, Rule> {
}
