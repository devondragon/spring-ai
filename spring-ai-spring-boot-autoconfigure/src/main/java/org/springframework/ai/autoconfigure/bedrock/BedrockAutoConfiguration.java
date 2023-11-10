package org.springframework.ai.autoconfigure.bedrock;

import org.springframework.ai.autoconfigure.openai.OpenAiProperties;
import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.ai.openai.client.OpenAiClient;
import org.springframework.ai.openai.embedding.OpenAiEmbeddingClient;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import com.azure.ai.openai.OpenAIClientBuilder;

@AutoConfiguration
@ConditionalOnClass(OpenAIClientBuilder.class)
@EnableConfigurationProperties(BedrockProperties.class)
public class BedrockAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public OpenAiClient bedrockClient(BedrockProperties bedrockProperties) {
        OpenAiClient openAiClient =
                new OpenAiClient(theoOpenAiService(bedrockProperties.getBaseUrl(), bedrockProperties.getApiKey(), bedrockProperties.getDuration()));
        openAiClient.setTemperature(bedrockProperties.getTemperature());
        openAiClient.setModel(bedrockProperties.getModel());
        return openAiClient;
    }

    @Bean
    @ConditionalOnMissingBean
    public EmbeddingClient bedrockEmbeddingClient(OpenAiProperties openAiProperties) {
        return new OpenAiEmbeddingClient(
                theoOpenAiService(openAiProperties.getEmbeddingBaseUrl(), openAiProperties.getEmbeddingApiKey(), openAiProperties.getDuration()),
                openAiProperties.getEmbeddingModel());
    }
}
