package com.crat.budget.service;

import com.crat.budget.domain.*; // for static metamodels
import com.crat.budget.domain.Article;
import com.crat.budget.repository.ArticleRepository;
import com.crat.budget.service.criteria.ArticleCriteria;
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
 * Service for executing complex queries for {@link Article} entities in the database.
 * The main input is a {@link ArticleCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link Article} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ArticleQueryService extends QueryService<Article> {

    private static final Logger LOG = LoggerFactory.getLogger(ArticleQueryService.class);

    private final ArticleRepository articleRepository;

    public ArticleQueryService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    /**
     * Return a {@link Page} of {@link Article} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Article> findByCriteria(ArticleCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Article> specification = createSpecification(criteria);
        return articleRepository.fetchBagRelationships(articleRepository.findAll(specification, page));
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ArticleCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<Article> specification = createSpecification(criteria);
        return articleRepository.count(specification);
    }

    /**
     * Function to convert {@link ArticleCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Article> createSpecification(ArticleCriteria criteria) {
        Specification<Article> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Article_.id));
            }
            if (criteria.getCategory() != null) {
                specification = specification.and(buildSpecification(criteria.getCategory(), Article_.category));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), Article_.code));
            }
            if (criteria.getDesignation() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDesignation(), Article_.designation));
            }
            if (criteria.getAccountDiv() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAccountDiv(), Article_.accountDiv));
            }
            if (criteria.getCodeEnd() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCodeEnd(), Article_.codeEnd));
            }
            if (criteria.getParagraph() != null) {
                specification = specification.and(buildStringSpecification(criteria.getParagraph(), Article_.paragraph));
            }
            if (criteria.getChapterId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getChapterId(), root -> root.join(Article_.chapter, JoinType.LEFT).get(Chapter_.id))
                );
            }
            if (criteria.getRecipeId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getRecipeId(), root -> root.join(Article_.recipes, JoinType.LEFT).get(Recipe_.id))
                );
            }
            if (criteria.getExpenseId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getExpenseId(), root -> root.join(Article_.expenses, JoinType.LEFT).get(Expense_.id))
                );
            }
        }
        return specification;
    }
}
