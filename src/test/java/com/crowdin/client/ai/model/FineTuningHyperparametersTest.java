package com.crowdin.client.ai.model;

import com.crowdin.client.core.http.impl.json.JacksonJsonTransformer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SuppressWarnings("deprecation")
class FineTuningHyperparametersTest {

    private final JacksonJsonTransformer jsonTransformer = new JacksonJsonTransformer();

    @Test
    void legacyGetterTruncatesFiniteValuesTest() {
        assertEquals(Long.valueOf(0), requestLegacy(0.1));
        assertEquals(Long.valueOf(1), requestLegacy(1.8));
        assertEquals(Long.valueOf(0), requestLegacy(-0.5));
        assertEquals(Long.valueOf(-1), requestLegacy(-1.9));
        assertEquals(Long.valueOf(2), requestLegacy(2.0));
        assertEquals(Long.valueOf(1), responseLegacy("1.8"));
        assertEquals(Long.valueOf(0), responseLegacy("0.1"));
    }

    @Test
    void legacyGetterLongBoundariesTest() {
        assertEquals(Long.valueOf(Long.MIN_VALUE), requestLegacy((double) Long.MIN_VALUE));
        assertEquals(Long.valueOf(9223372036854774784L), requestLegacy(9.223372036854774784E18));
        assertNull(requestLegacy(9.223372036854775807E18));
        assertNull(requestLegacy(1e30));
        assertNull(requestLegacy(-1e30));
        assertNull(responseLegacy("1e30"));
        assertNull(responseLegacy("-1e19"));
    }

    @Test
    void legacyGetterNonFiniteAndNullValuesTest() {
        FineTuningJob.Hyperparameters response = new FineTuningJob.Hyperparameters();
        response.setLearningRateMultiplierValue(Double.NaN);
        assertNull(response.getLearningRateMultiplier());
        response.setLearningRateMultiplierValue(Double.POSITIVE_INFINITY);
        assertNull(response.getLearningRateMultiplier());
        response.setLearningRateMultiplierValue(Double.NEGATIVE_INFINITY);
        assertNull(response.getLearningRateMultiplier());
        response.setLearningRateMultiplierValue(null);
        assertNull(response.getLearningRateMultiplier());
        assertNull(responseLegacy("null"));
    }

    @Test
    void requestRejectsNonFiniteValuesTest() {
        FineTuningJobRequest.Hyperparameters hyperparameters = new FineTuningJobRequest.Hyperparameters();
        hyperparameters.setLearningRateMultiplierValue(0.5);
        for (Double invalid : new Double[]{Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY}) {
            assertThrows(IllegalArgumentException.class, () -> hyperparameters.setLearningRateMultiplierValue(invalid), String.valueOf(invalid));
            assertEquals(Double.valueOf(0.5), hyperparameters.getLearningRateMultiplierValue());
        }
        assertEquals("{\"learningRateMultiplier\":0.5}", jsonTransformer.convert(hyperparameters));
    }

    @Test
    void requestSerializationTest() {
        FineTuningJobRequest.Hyperparameters hyperparameters = new FineTuningJobRequest.Hyperparameters();
        hyperparameters.setBatchSize(4L);
        hyperparameters.setNEpochs(3L);
        hyperparameters.setLearningRateMultiplierValue(0.05);
        String json = jsonTransformer.convert(hyperparameters);
        assertEquals("{\"batchSize\":4,\"nEpochs\":3,\"learningRateMultiplier\":0.05}", json);

        hyperparameters.setLearningRateMultiplier(2L);
        assertEquals(Double.valueOf(2.0), hyperparameters.getLearningRateMultiplierValue());
        assertEquals(Long.valueOf(2), hyperparameters.getLearningRateMultiplier());
        assertEquals("{\"batchSize\":4,\"nEpochs\":3,\"learningRateMultiplier\":2.0}", jsonTransformer.convert(hyperparameters));

        hyperparameters.setLearningRateMultiplier(null);
        assertNull(hyperparameters.getLearningRateMultiplierValue());
        assertEquals("{\"batchSize\":4,\"nEpochs\":3}", jsonTransformer.convert(hyperparameters));
    }

    private static Long requestLegacy(double value) {
        FineTuningJobRequest.Hyperparameters hyperparameters = new FineTuningJobRequest.Hyperparameters();
        hyperparameters.setLearningRateMultiplierValue(value);
        return hyperparameters.getLearningRateMultiplier();
    }

    private Long responseLegacy(String jsonValue) {
        return jsonTransformer.parse("{\"learningRateMultiplier\":" + jsonValue + "}", FineTuningJob.Hyperparameters.class)
                .getLearningRateMultiplier();
    }
}
