package com.crat.budget.service;

import com.crat.budget.domain.*; // for static metamodels
import com.crat.budget.domain.Decision;
import com.crat.budget.repository.DecisionRepository;
import com.crat.budget.service.criteria.DecisionCriteria;
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
 * Service for executing complex queries for {@link Decision} entities in the database.
 * The main input is a {@link DecisionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link Decision} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DecisionQueryService extends QueryService<Decision> {

    private static final Logger LOG = LoggerFactory.getLogger(DecisionQueryService.class);

    private final DecisionRepository decisionRepository;

    public DecisionQueryService(DecisionRepository decisionRepository) {
        this.decisionRepository = decisionRepository;
    }

    /**
     * Return a {@link Page} of {@link Decision} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Decision> findByCriteria(DecisionCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Decision> specification = createSpecification(criteria);
        return decisionRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DecisionCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<Decision> specification = createSpecification(criteria);
        return decisionRepository.count(specification);
    }

    /**
     * Function to convert {@link DecisionCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Decision> createSpecification(DecisionCriteria criteria) {
        Specification<Decision> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Decision_.id));
            }
            if (criteria.getDecisionNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDecisionNumber(), Decision_.decisionNumber));
            }
            if (criteria.getDecisionDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDecisionDate(), Decision_.decisionDate));
            }
            if (criteria.getEngagementId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getEngagementId(), root ->
                        root.join(Decision_.engagement, JoinType.LEFT).get(Engagement_.id)
                    )
                );
            }
            if (criteria.getAnnexDecisionId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getAnnexDecisionId(), root ->
                        root.join(Decision_.annexDecision, JoinType.LEFT).get(AnnexDecision_.id)
                    )
                );
            }
            if (criteria.getDecisionItemId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getDecisionItemId(), root ->
                        root.join(Decision_.decisionItems, JoinType.LEFT).get(DecisionItem_.id)
                    )
                );
            }
        }
        return specification;
    }
}
