package com.machines.machines_api.specifications;

import com.machines.machines_api.models.dto.specifications.OfferSpecificationDTO;
import com.machines.machines_api.models.entity.Offer;
import com.machines.machines_api.utils.PredicateMethodsHelper;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OfferSpecification {
    private static OfferSpecificationDTO offerSpecificationDTO;
    private static PredicateMethodsHelper<Offer> predicateMethods;

    public static Specification<Offer> filterOffer(OfferSpecificationDTO offerSpecificationDTO) {
        OfferSpecification.offerSpecificationDTO = offerSpecificationDTO;

        return (root, query, criteriaBuilder) -> {
            OfferSpecification.predicateMethods = PredicateMethodsHelper
                    .<Offer>builder()
                    .root(root)
                    .criteriaBuilder(criteriaBuilder)
                    .build();

            List<Predicate> predicates = getPredicates();
            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        };
    }

    private static List<Predicate> getPredicates() {
        List<Predicate> predicates = new ArrayList<>();

        if (offerSpecificationDTO.getOfferState() != null) {
            String offerState = offerSpecificationDTO.getOfferState().name();
            predicates.add(predicateMethods.equal("offerState", offerState));
        }

        if (offerSpecificationDTO.getOfferSaleType() != null) {
            String offerSaleType = offerSpecificationDTO.getOfferSaleType().name();
            predicates.add(predicateMethods.equal("offerSaleType", offerSaleType));
        }

        if (offerSpecificationDTO.isValidMinPrice()) {
            double minPrice = offerSpecificationDTO.getMinPrice();

            if (!offerSpecificationDTO.isValidMaxPrice()) {
                // "minPrice - 1" in order to make it inclusive
                predicates.add(predicateMethods.greaterThan("price", minPrice - 1));
            } else {
                double maxPrice = offerSpecificationDTO.getMaxPrice();
                predicates.add(predicateMethods.between("price", minPrice, maxPrice));
            }
        } else if (offerSpecificationDTO.isValidMaxPrice()) {
            double maxPrice = offerSpecificationDTO.getMaxPrice();

            // "maxPrice + 1" in order to make it inclusive
            predicates.add(predicateMethods.lessThan("price", maxPrice + 1));
        }

        if (offerSpecificationDTO.isBulgarian()) {
            predicates.add(predicateMethods.equal("bulgarian", true));
        }

        if (offerSpecificationDTO.getSearch() != null) {
            String search = offerSpecificationDTO.getSearch();
            predicates.add(predicateMethods.like("title", search));
        }

        if (offerSpecificationDTO.getCityId() != null) {
            UUID cityId = offerSpecificationDTO.getCityId();
            Path<Offer> pathToCity = predicateMethods.getIdPathOfRelation("city");
            predicates.add(predicateMethods.equal(pathToCity, cityId));
        }

        if (offerSpecificationDTO.getSubcategoryId() != null) {
            UUID subcategoryId = offerSpecificationDTO.getSubcategoryId();
            Path<Offer> pathToSubcategory = predicateMethods.getIdPathOfRelation("subcategory");
            predicates.add(predicateMethods.equal(pathToSubcategory, subcategoryId));
        }

        return predicates;
    }
}
