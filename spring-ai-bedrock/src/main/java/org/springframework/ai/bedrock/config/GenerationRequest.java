package org.springframework.ai.bedrock.config;

import java.util.List;

/**
 * Base class for all Bedrock generation requests.
 *
 * This class contains the common parameters for all generation requests, including model information, the prompt, and inference parameters.
 *
 * Each Bedrock model has a corresponding GenerationRequest class that defines the parameters for that model.
 *
 */
public abstract class GenerationRequest {

    // Prompt string
    private String promptString;

    // Model information
    private String modelName;

    private String modelId;

    private String modelVersion;


    // Standard Inference parameters
    private float temperature;

    private float topK;

    private float topP;

    private int responseLength;

    private float lengthPenalty;

    private float repetitionPentaly;

    private List<String> stopSequences;

    // Standard Inference Parameter Limits and Defaults
    private float temperatureMin = 0.0f;
    private float temperatureMax = 1.0f;
    private float temperatureDefault = 0.5f;
    private float topKMin = 0.0f;
    private float topKMax = 1.0f;
    private float topKDefault = 0.5f;
    private float topPMin = 0.0f;
    private float topPMax = 1.0f;
    private float topPDefault = 0.5f;
    private int responseLengthMin = 0;
    private int responseLengthMax = 2048;
    private int responseLengthDefault = 200;


    public GenerationRequest() {
        this.setTemperature(getTemperatureDefault());
        this.setTopK(getTopKDefault());
        this.setTopP(getTopPDefault());
        this.setResponseLength(getResponseLengthDefault());
    }

    public String getPromptString() {
        return promptString;
    }

    public void setPromptString(String promptString) {
        this.promptString = promptString;
    }

    public float getLengthPenalty() {
        return lengthPenalty;
    }

    public void setLengthPenalty(float lengthPenalty) {
        this.lengthPenalty = lengthPenalty;
    }

    public String getModelId() {
        return modelId;
    }

    protected void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    protected void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelVersion() {
        return modelVersion;
    }

    protected void setModelVersion(String modelVersion) {
        this.modelVersion = modelVersion;
    }

    public int getResponseLength() {
        return responseLength;
    }

    public void setResponseLength(int responseLength) {
        if (responseLength < getResponseLengthMin() || responseLength > getResponseLengthMax()) {
            throw new IllegalArgumentException(
                    "Response length for this model must be between " + getResponseLengthMin() + " and " + getResponseLengthMax());
        }
        this.responseLength = responseLength;
    }

    public int getResponseLengthDefault() {
        return responseLengthDefault;
    }

    protected void setResponseLengthDefault(int responseLengthDefault) {
        this.responseLengthDefault = responseLengthDefault;
    }

    public int getResponseLengthMax() {
        return responseLengthMax;
    }

    protected void setResponseLengthMax(int responseLengthMax) {
        this.responseLengthMax = responseLengthMax;
    }

    public int getResponseLengthMin() {
        return responseLengthMin;
    }

    protected void setResponseLengthMin(int responseLengthMin) {
        this.responseLengthMin = responseLengthMin;
    }

    public float getRepetitionPentaly() {
        return repetitionPentaly;
    }

    public void setRepetitionPentaly(float repetitionPentaly) {
        this.repetitionPentaly = repetitionPentaly;
    }

    public List<String> getStopSequences() {
        return stopSequences;
    }

    public void setStopSequences(List<String> stopSequences) {
        this.stopSequences = stopSequences;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        if (temperature < getTemperatureMin() || temperature > getTemperatureMax()) {
            throw new IllegalArgumentException("Temperature for this model must be between " + getTemperatureMin() + " and " + getTemperatureMax());
        }
        this.temperature = temperature;
    }

    public float getTemperatureDefault() {
        return temperatureDefault;
    }

    protected void setTemperatureDefault(float temperatureDefault) {
        this.temperatureDefault = temperatureDefault;
    }

    public float getTemperatureMax() {
        return temperatureMax;
    }

    protected void setTemperatureMax(float temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public float getTemperatureMin() {
        return temperatureMin;
    }

    protected void setTemperatureMin(float temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public float getTopK() {
        return topK;
    }

    public void setTopK(float topK) {
        if (topK < getTopKMin() || topK > getTopKMax()) {
            throw new IllegalArgumentException("TopK for this model must be between " + getTopKMin() + " and " + getTopKMax());
        }
        this.topK = topK;
    }

    public float getTopKDefault() {
        return topKDefault;
    }

    protected void setTopKDefault(float topKDefault) {
        this.topKDefault = topKDefault;
    }

    public float getTopKMax() {
        return topKMax;
    }

    protected void setTopKMax(float topKMax) {
        this.topKMax = topKMax;
    }

    public float getTopKMin() {
        return topKMin;
    }

    protected void setTopKMin(float topKMin) {
        this.topKMin = topKMin;
    }

    public float getTopP() {
        return topP;
    }

    public void setTopP(float topP) {
        if (topP < getTopPMin() || topP > getTopPMax()) {
            throw new IllegalArgumentException("TopP for this model must be between " + getTopPMin() + " and " + getTopPMax());
        }
        this.topP = topP;
    }

    public float getTopPDefault() {
        return topPDefault;
    }

    protected void setTopPDefault(float topPDefault) {
        this.topPDefault = topPDefault;
    }

    public float getTopPMax() {
        return topPMax;
    }

    protected void setTopPMax(float topPMax) {
        this.topPMax = topPMax;
    }

    public float getTopPMin() {
        return topPMin;
    }

    protected void setTopPMin(float topPMin) {
        this.topPMin = topPMin;
    }


}


