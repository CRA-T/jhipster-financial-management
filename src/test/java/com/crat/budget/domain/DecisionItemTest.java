package com.crat.budget.domain;

import static com.crat.budget.domain.DecisionItemTestSamples.*;
import static com.crat.budget.domain.DecisionTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.crat.budget.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DecisionItemTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DecisionItem.class);
        DecisionItem decisionItem1 = getDecisionItemSample1();
        DecisionItem decisionItem2 = new DecisionItem();
        assertThat(decisionItem1).isNotEqualTo(decisionItem2);

        decisionItem2.setId(decisionItem1.getId());
        assertThat(decisionItem1).isEqualTo(decisionItem2);

        decisionItem2 = getDecisionItemSample2();
        assertThat(decisionItem1).isNotEqualTo(decisionItem2);
    }

    @Test
    void decisionTest() {
        DecisionItem decisionItem = getDecisionItemRandomSampleGenerator();
        Decision decisionBack = getDecisionRandomSampleGenerator();

        decisionItem.setDecision(decisionBack);
        assertThat(decisionItem.getDecision()).isEqualTo(decisionBack);

        decisionItem.decision(null);
        assertThat(decisionItem.getDecision()).isNull();
    }
}
