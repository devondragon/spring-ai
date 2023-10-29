package org.springframework.ai.bedrock.config;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Enum of all the models available in the Bedrock API.
 *
 * Each model has a corresponding GenerationRequest class that defines the parameters for that model.
 *
 */
public enum BedrockModelEnum {
    AI21_JURASSIC2_MID("ai21.j2-mid-v1", AI21JurMidGenerationRequest.class),
    // AI21_JURASSIC2_ULTRA("ai21.j2-ultra-v1", AI21JurUltraGenerationRequest.class),
    // AMAZON_TITAN_TEXT_G1_LITE("amazon.titan-text-lite-v1", AmazonTitanTextG1LiteGenerationRequest.class),
    // AMAZON_TITAN_TEXT_G1_EXPRESS("amazon.titan-text-g1-express-v1", AmazonTitanTextG1ExpressGenerationRequest.class),
    // AMAZON_TITAN_TEXT_G1_AGILE("amazon.titan-text-g1-agile-v1", AmazonTitanTextG1AgileGenerationRequest.class),
    // AMAZON_TITAN_EMBEDDINGS_G1("amazon.titan-embed-text-v1", AmazonTitanEmbeddingsG1GenerationRequest.class),
    // ANTHRO_CLAUDE1("anthropic.claude-v1", AnthroClaude1GenerationRequest.class),
    // ANTHRO_CLAUDE2("anthropic.claude-v2", AnthroClaude2GenerationRequest.class),
    // ANTHRO_CLAUDE_INSTANT("anthropic.claude-instant-v1", AnthroClaudeInstantGenerationRequest.class),
    // COHERE_COMMAND("cohere.command-text-v14", CohereCommandTextV14GenerationRequest.class),
    // STABILITY_STABLE_DIFFUSION_XL("stability.stable-diffusion-xl-v0", StabilityStableDiffusionXLGenerationRequest.class)
    ;

    private final String modelId;
    private final Class<? extends GenerationRequest> generationRequestClass;

    // Create a map to associate model IDs with model names
    private static final Map<String, BedrockModelEnum> MODEL_ID_MAP = new HashMap<>();

    static {
        for (BedrockModelEnum modelEnum : values()) {
            MODEL_ID_MAP.put(modelEnum.modelId, modelEnum);
        }
    }

    private static final Logger log = LoggerFactory.getLogger(BedrockModelEnum.class);


    BedrockModelEnum(String modelId, Class<? extends GenerationRequest> generationRequestClass) {
        this.modelId = modelId;
        this.generationRequestClass = generationRequestClass;
    }

    public Class<? extends GenerationRequest> getGenerationRequestClasss() {
        return generationRequestClass;
    }

    // Add a method to create an instance based on the model name
    public GenerationRequest createInstance() throws IllegalAccessException, InstantiationException {
        try {
            return generationRequestClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            log.error("Error creating instance of GenerationRequest class: {}", generationRequestClass.getName(), e);
            return null;
        }
    }

    // Add a static method to find the enum by model name
    public static BedrockModelEnum getByModelName(String modelName) {
        for (BedrockModelEnum modelEnum : values()) {
            if (modelEnum.name().equalsIgnoreCase(modelName)) {
                return modelEnum;
            }
        }
        return null; // Model name not found
    }

    public static BedrockModelEnum getByModelId(String modelId) {
        return MODEL_ID_MAP.get(modelId);
    }

}
