package com.demo.travelcardsystem.businessrule;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/*
 * This class is used to create all the business rules for the system.
 *
 */
@Data
@Component
@Scope("singleton")
public class RuleCollection {
    private Double maxFare;
    private Set<Rule> rules = new HashSet<>();

    public void addRules(Rule rule) {
        rules.add(rule);
    }
}
