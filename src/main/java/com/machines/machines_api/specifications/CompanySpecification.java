package com.machines.machines_api.specifications;

import com.machines.machines_api.models.dto.specifications.CompanySpecificationDTO;
import com.machines.machines_api.models.entity.Company;
import com.machines.machines_api.utils.PredicateMethodsHelper;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CompanySpecification {
    private static CompanySpecificationDTO companySpecificationDTO;
    private static PredicateMethodsHelper<Company> predicateMethods;

    public static Specification<Company> filterCompany(CompanySpecificationDTO companySpecificationDTO) {
        CompanySpecification.companySpecificationDTO = companySpecificationDTO;

        return (root, query, criteriaBuilder) -> {
            CompanySpecification.predicateMethods = PredicateMethodsHelper
                    .<Company>builder()
                    .root(root)
                    .criteriaBuilder(criteriaBuilder)
                    .build();

            List<Predicate> predicates = getPredicates();
            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        };
    }

    private static List<Predicate> getPredicates() {
        List<Predicate> predicates = new ArrayList<>();

        if (companySpecificationDTO.getSearch() != null) {
            String search = companySpecificationDTO.getSearch();
            predicates.add(predicateMethods.like("name", search));
        }

        if (companySpecificationDTO.getCityId() != null) {
            UUID cityId = companySpecificationDTO.getCityId();
            Path<Company> pathToCity = predicateMethods.getIdPathOfRelation("city");
            predicates.add(predicateMethods.equal(pathToCity, cityId));
        }

        // Handle deletedAt = null
        predicates.add(predicateMethods.isNull("deletedAt"));
        return predicates;
    }
}