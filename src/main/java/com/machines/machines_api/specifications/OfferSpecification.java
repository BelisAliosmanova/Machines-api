package com.machines.machines_api.specifications;

import com.google.common.collect.Maps;
import com.machines.machines_api.models.dto.specifications.OfferSpecificationDTO;
import com.machines.machines_api.models.entity.Offer;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class OfferSpecification {
    private static Root<Offer> root;
    private static CriteriaBuilder criteriaBuilder;
    private static OfferSpecificationDTO offerSpecificationDTO;

    public static Specification<Offer> filterOffer(OfferSpecificationDTO offerSpecificationDTO) {
        OfferSpecification.offerSpecificationDTO = offerSpecificationDTO;

        return (root, query, criteriaBuilder) -> {
            OfferSpecification.root = root;
            OfferSpecification.criteriaBuilder = criteriaBuilder;

            List<Predicate> predicates = getPredicates();

//            Predicate brandPredicate =
//                    criteriaBuilder.like(root.get("phoneBrand"), StringUtils.isBlank(phoneBrand)
//                            ? likePattern("") : phoneBrand);
//
//            Predicate namePredicate =
//                    criteriaBuilder.like(root.get("phoneName"), StringUtils.isBlank(phoneName)
//                            ? likePattern("") : phoneName);


            return criteriaBuilder.and(namePredicate, brandPredicate);
        };
    }

    private static List<Predicate> getPredicates() {
        List<Predicate> predicates = new ArrayList<>();

        if (offerSpecificationDTO.getOfferState() != null) {
            String offerState = offerSpecificationDTO.getOfferState().name();
            predicates.add(like("offerState", offerState));
        }

        if (offerSpecificationDTO.getOfferSaleType() != null) {
            String offerSaleType = offerSpecificationDTO.getOfferSaleType().name();
            predicates.add(like("offerSaleType", offerSaleType));
        }

        if (offerSpecificationDTO.getMinPrice() > 0) {
            double minPrice = offerSpecificationDTO.getMinPrice();

            if (offerSpecificationDTO.getMaxPrice() <= 0) {
                predicates.add(greaterThan("price", minPrice));
            } else {
                double maxPrice = offerSpecificationDTO.getMaxPrice();
                predicates.add(between("price", minPrice, maxPrice));
            }
        } else if (offerSpecificationDTO.getMaxPrice() > 0) {
            double maxPrice = offerSpecificationDTO.getMaxPrice();
            predicates.add(lessThan("price", maxPrice));
        }

        return predicates;
    }

    private static Predicate lessThan(String attributeName, double valueToCheckFor) {
        return criteriaBuilder
                .lessThan(root.get(attributeName), valueToCheckFor);
    }

    private static Predicate between(String attributeName, double min, double max) {
        return criteriaBuilder
                .between(root.get(attributeName), min, max);
    }

    private static Predicate greaterThan(String attributeName, double valueToCheckFor) {
        return criteriaBuilder.greaterThan(
                root.get(attributeName),
                valueToCheckFor
        );
    }

    private static Predicate like(String attributeName, String valueToCheckFor) {
        return criteriaBuilder.like(
                root.get(attributeName),
                StringUtils.isBlank(valueToCheckFor)
                        ? likePattern("")
                        : valueToCheckFor
        );
    }

    private static String likePattern(String value) {
        return "%" + value + "%";
    }
}
