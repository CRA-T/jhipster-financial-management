package com.crat.budget.service;

import com.crat.budget.domain.*; // for static metamodels
import com.crat.budget.domain.Mandate;
import com.crat.budget.repository.MandateRepository;
import com.crat.budget.service.criteria.MandateCriteria;
import jakarta.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Mandate} entities in the database.
 * The main input is a {@link MandateCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link Mandate} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MandateQueryService extends QueryService<Mandate> {

    private static final Logger LOG = LoggerFactory.getLogger(MandateQueryService.class);

    private final MandateRepository mandateRepository;

    public MandateQueryService(MandateRepository mandateRepository) {
        this.mandateRepository = mandateRepository;
    }

    /**
     * Return a {@link Page} of {@link Mandate} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Mandate> findByCriteria(MandateCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Mandate> specification = createSpecification(criteria);
        return mandateRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MandateCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<Mandate> specification = createSpecification(criteria);
        return mandateRepository.count(specification);
    }

    /**
     * Function to convert {@link MandateCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Mandate> createSpecification(MandateCriteria criteria) {
        Specification<Mandate> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Mandate_.id));
            }
            if (criteria.getMandateNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMandateNumber(), Mandate_.mandateNumber));
            }
            if (criteria.getMandateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMandateDate(), Mandate_.mandateDate));
            }
            if (criteria.getIssueSlipNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIssueSlipNumber(), Mandate_.issueSlipNumber));
            }
            if (criteria.getMonthAndYearOfIssue() != null) {
                specification = specification.and(
                    buildStringSpecification(criteria.getMonthAndYearOfIssue(), Mandate_.monthAndYearOfIssue)
                );
            }
            if (criteria.getSupportingDocuments() != null) {
                specification = specification.and(
                    buildStringSpecification(criteria.getSupportingDocuments(), Mandate_.supportingDocuments)
                );
            }
            if (criteria.getEngagementId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getEngagementId(), root -> root.join(Mandate_.engagement, JoinType.LEFT).get(Engagement_.id)
                    )
                );
            }
        }
        return specification;
    }
}
