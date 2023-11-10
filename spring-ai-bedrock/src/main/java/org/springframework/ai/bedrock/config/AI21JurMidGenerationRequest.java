package org.springframework.ai.bedrock.config;

public class AI21JurMidGenerationRequest extends GenerationRequest {

    private static final String MODEL_ID = "ai21.j2-mid-v1";
    private static final String MODEL_NAME = "AI21 Labs Jurassic-2 Mid";
    private static final String MODEL_VERSION = "1";

    private static final int RESPONSE_LENGTH_MAX = 8191;

    private float presencePenalty;
    private int frequencyPenalty;
    private int countPenalty;

    // TODO: Support the special token flags for the countPenalty parameter


    public AI21JurMidGenerationRequest() {
        super();
        super.setModelId(MODEL_ID);
        super.setModelName(MODEL_NAME);
        super.setModelVersion(MODEL_VERSION);
        super.setResponseLengthMax(RESPONSE_LENGTH_MAX);
    }

    public float getPresencePenalty() {
        return presencePenalty;
    }

    public void setPresencePenalty(float presencePenalty) {
        this.presencePenalty = presencePenalty;
    }

    public int getFrequencyPenalty() {
        return frequencyPenalty;
    }

    public void setFrequencyPenalty(int frequencyPenalty) {
        this.frequencyPenalty = frequencyPenalty;
    }

    public int getCountPenalty() {
        return countPenalty;
    }

    public void setCountPenalty(int countPenalty) {
        this.countPenalty = countPenalty;
    }
}
