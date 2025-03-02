package com.crat.budget.service;

import com.crat.budget.domain.*; // for static metamodels
import com.crat.budget.domain.PurchaseOrder;
import com.crat.budget.repository.PurchaseOrderRepository;
import com.crat.budget.service.criteria.PurchaseOrderCriteria;
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
 * Service for executing complex queries for {@link PurchaseOrder} entities in the database.
 * The main input is a {@link PurchaseOrderCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link PurchaseOrder} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PurchaseOrderQueryService extends QueryService<PurchaseOrder> {

    private static final Logger LOG = LoggerFactory.getLogger(PurchaseOrderQueryService.class);

    private final PurchaseOrderRepository purchaseOrderRepository;

    public PurchaseOrderQueryService(PurchaseOrderRepository purchaseOrderRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    /**
     * Return a {@link Page} of {@link PurchaseOrder} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PurchaseOrder> findByCriteria(PurchaseOrderCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PurchaseOrder> specification = createSpecification(criteria);
        return purchaseOrderRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PurchaseOrderCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<PurchaseOrder> specification = createSpecification(criteria);
        return purchaseOrderRepository.count(specification);
    }

    /**
     * Function to convert {@link PurchaseOrderCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<PurchaseOrder> createSpecification(PurchaseOrderCriteria criteria) {
        Specification<PurchaseOrder> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), PurchaseOrder_.id));
            }
            if (criteria.getNameOfTheMinistry() != null) {
                specification = specification.and(
                    buildStringSpecification(criteria.getNameOfTheMinistry(), PurchaseOrder_.nameOfTheMinistry)
                );
            }
            if (criteria.getOrderNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrderNumber(), PurchaseOrder_.orderNumber));
            }
            if (criteria.getOrderDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrderDate(), PurchaseOrder_.orderDate));
            }
            if (criteria.getTotalAmountWithoutTax() != null) {
                specification = specification.and(
                    buildRangeSpecification(criteria.getTotalAmountWithoutTax(), PurchaseOrder_.totalAmountWithoutTax)
                );
            }
            if (criteria.getTaxRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTaxRate(), PurchaseOrder_.taxRate));
            }
            if (criteria.getTotalTaxAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTotalTaxAmount(), PurchaseOrder_.totalTaxAmount));
            }
            if (criteria.getPrepaidTaxAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPrepaidTaxAmount(), PurchaseOrder_.prepaidTaxAmount));
            }
            if (criteria.getTotalAmountWithTax() != null) {
                specification = specification.and(
                    buildRangeSpecification(criteria.getTotalAmountWithTax(), PurchaseOrder_.totalAmountWithTax)
                );
            }
            if (criteria.getAuthExpenditureNumber() != null) {
                specification = specification.and(
                    buildStringSpecification(criteria.getAuthExpenditureNumber(), PurchaseOrder_.authExpenditureNumber)
                );
            }
            if (criteria.getAllocatedCredits() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAllocatedCredits(), PurchaseOrder_.allocatedCredits));
            }
            if (criteria.getCommittedExpenditures() != null) {
                specification = specification.and(
                    buildRangeSpecification(criteria.getCommittedExpenditures(), PurchaseOrder_.committedExpenditures)
                );
            }
            if (criteria.getAvailableBalance() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAvailableBalance(), PurchaseOrder_.availableBalance));
            }
            if (criteria.getAnnexDecisionId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getAnnexDecisionId(), root ->
                        root.join(PurchaseOrder_.annexDecision, JoinType.LEFT).get(AnnexDecision_.id)
                    )
                );
            }
            if (criteria.getSupplierId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getSupplierId(), root -> root.join(PurchaseOrder_.supplier, JoinType.LEFT).get(Supplier_.id)
                    )
                );
            }
            if (criteria.getEngagementId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getEngagementId(), root ->
                        root.join(PurchaseOrder_.engagement, JoinType.LEFT).get(Engagement_.id)
                    )
                );
            }
            if (criteria.getPurchaseOrderItemId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getPurchaseOrderItemId(), root ->
                        root.join(PurchaseOrder_.purchaseOrderItems, JoinType.LEFT).get(PurchaseOrderItem_.id)
                    )
                );
            }
        }
        return specification;
    }
}
