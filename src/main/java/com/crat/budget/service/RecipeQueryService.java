package com.crat.budget.service;

import com.crat.budget.domain.*; // for static metamodels
import com.crat.budget.domain.Recipe;
import com.crat.budget.repository.RecipeRepository;
import com.crat.budget.service.criteria.RecipeCriteria;
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
 * Service for executing complex queries for {@link Recipe} entities in the database.
 * The main input is a {@link RecipeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link Recipe} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RecipeQueryService extends QueryService<Recipe> {

    private static final Logger LOG = LoggerFactory.getLogger(RecipeQueryService.class);

    private final RecipeRepository recipeRepository;

    public RecipeQueryService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    /**
     * Return a {@link Page} of {@link Recipe} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Recipe> findByCriteria(RecipeCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Recipe> specification = createSpecification(criteria);
        return recipeRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(RecipeCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<Recipe> specification = createSpecification(criteria);
        return recipeRepository.count(specification);
    }

    /**
     * Function to convert {@link RecipeCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Recipe> createSpecification(RecipeCriteria criteria) {
        Specification<Recipe> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Recipe_.id));
            }
            if (criteria.getAchievementsInThePastYear() != null) {
                specification = specification.and(
                    buildRangeSpecification(criteria.getAchievementsInThePastYear(), Recipe_.achievementsInThePastYear)
                );
            }
            if (criteria.getNewYearForecast() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNewYearForecast(), Recipe_.newYearForecast));
            }
            if (criteria.getCategory() != null) {
                specification = specification.and(buildSpecification(criteria.getCategory(), Recipe_.category));
            }
            if (criteria.getFinancialYearId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getFinancialYearId(), root ->
                        root.join(Recipe_.financialYear, JoinType.LEFT).get(FinancialYear_.id)
                    )
                );
            }
            if (criteria.getArticleId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getArticleId(), root -> root.join(Recipe_.articles, JoinType.LEFT).get(Article_.id))
                );
            }
        }
        return specification;
    }
}
