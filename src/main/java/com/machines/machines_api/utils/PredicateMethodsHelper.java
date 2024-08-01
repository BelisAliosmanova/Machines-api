package com.machines.machines_api.utils;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@RequiredArgsConstructor
@Builder
public class PredicateMethodsHelper<K> {
    private final CriteriaBuilder criteriaBuilder;
    private final Root<K> root;

    public Predicate isNull(String attributeName) {
        return criteriaBuilder
                .isNull(root.get(attributeName));
    }

    public Predicate lessThan(String attributeName, double valueToCheckFor) {
        return criteriaBuilder
                .lessThan(root.get(attributeName), valueToCheckFor);
    }

    public Predicate between(String attributeName, double min, double max) {
        return criteriaBuilder
                .between(root.get(attributeName), min, max);
    }

    public Predicate greaterThan(String attributeName, double valueToCheckFor) {
        return criteriaBuilder.greaterThan(
                root.get(attributeName),
                valueToCheckFor
        );
    }

    public Predicate equal(String attributeName, Object value) {
        return criteriaBuilder
                .equal(root.get(attributeName), value);
    }

    public Predicate equal(Path<K> path, Object value) {
        return criteriaBuilder
                .equal(path, value);
    }

    public Predicate like(String attributeName, String valueToCheckFor) {
        String value = valueToCheckFor.trim();

        return criteriaBuilder.like(
                root.get(attributeName),
                StringUtils.isBlank(value)
                        ? likePattern("")
                        : likePattern(value)
        );
    }

    public Path<K> getIdPathOfRelation(String entityAttributeName) {
        return root.get(entityAttributeName).get("id");
    }

    private String likePattern(String value) {
        return "%" + value + "%";
    }
}
