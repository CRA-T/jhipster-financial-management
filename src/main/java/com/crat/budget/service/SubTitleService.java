package com.crat.budget.service;

import com.crat.budget.domain.SubTitle;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.crat.budget.domain.SubTitle}.
 */
public interface SubTitleService {
    /**
     * Save a subTitle.
     *
     * @param subTitle the entity to save.
     * @return the persisted entity.
     */
    SubTitle save(SubTitle subTitle);

    /**
     * Updates a subTitle.
     *
     * @param subTitle the entity to update.
     * @return the persisted entity.
     */
    SubTitle update(SubTitle subTitle);

    /**
     * Partially updates a subTitle.
     *
     * @param subTitle the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SubTitle> partialUpdate(SubTitle subTitle);

    /**
     * Get the "id" subTitle.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SubTitle> findOne(Long id);

    /**
     * Delete the "id" subTitle.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
