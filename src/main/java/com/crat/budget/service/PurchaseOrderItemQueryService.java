package com.crat.budget.service;

import com.crat.budget.domain.*; // for static metamodels
import com.crat.budget.domain.PurchaseOrderItem;
import com.crat.budget.repository.PurchaseOrderItemRepository;
import com.crat.budget.service.criteria.PurchaseOrderItemCriteria;
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
 * Service for executing complex queries for {@link PurchaseOrderItem} entities in the database.
 * The main input is a {@link PurchaseOrderItemCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link PurchaseOrderItem} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PurchaseOrderItemQueryService extends QueryService<PurchaseOrderItem> {

    private static final Logger LOG = LoggerFactory.getLogger(PurchaseOrderItemQueryService.class);

    private final PurchaseOrderItemRepository purchaseOrderItemRepository;

    public PurchaseOrderItemQueryService(PurchaseOrderItemRepository purchaseOrderItemRepository) {
        this.purchaseOrderItemRepository = purchaseOrderItemRepository;
    }

    /**
     * Return a {@link Page} of {@link PurchaseOrderItem} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PurchaseOrderItem> findByCriteria(PurchaseOrderItemCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PurchaseOrderItem> specification = createSpecification(criteria);
        return purchaseOrderItemRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PurchaseOrderItemCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<PurchaseOrderItem> specification = createSpecification(criteria);
        return purchaseOrderItemRepository.count(specification);
    }

    /**
     * Function to convert {@link PurchaseOrderItemCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<PurchaseOrderItem> createSpecification(PurchaseOrderItemCriteria criteria) {
        Specification<PurchaseOrderItem> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), PurchaseOrderItem_.id));
            }
            if (criteria.getProductName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProductName(), PurchaseOrderItem_.productName));
            }
            if (criteria.getQuantity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQuantity(), PurchaseOrderItem_.quantity));
            }
            if (criteria.getUnitPrice() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUnitPrice(), PurchaseOrderItem_.unitPrice));
            }
            if (criteria.getTotalAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTotalAmount(), PurchaseOrderItem_.totalAmount));
            }
            if (criteria.getPurchaseOrderId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getPurchaseOrderId(), root ->
                        root.join(PurchaseOrderItem_.purchaseOrder, JoinType.LEFT).get(PurchaseOrder_.id)
                    )
                );
            }
        }
        return specification;
    }
}
