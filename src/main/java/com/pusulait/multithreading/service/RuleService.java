package com.pusulait.multithreading.service;

import com.pusulait.multithreading.mapper.RuleMapper;
import com.pusulait.multithreading.model.Rule;
import com.pusulait.multithreading.model.dto.RuleDTO;
import com.pusulait.multithreading.repository.RuleDTORepository;
import com.pusulait.multithreading.repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RuleService {

    @Autowired
    private RuleRepository ruleRepository;

    @Autowired
    private RuleDTORepository ruleDTORepository;

    @Autowired
    private RuleMapper ruleMapper;

    public RuleDTO findOne(Long id) {

        Optional<RuleDTO> opt = ruleDTORepository.findById(id);
        if (opt.isPresent()){
            return opt.get();
        }
        return ruleMapper.toDto(ruleRepository.getOne(id));
    }

    public Rule save(Rule rule) {
        return ruleRepository.save(rule);
    }

}
