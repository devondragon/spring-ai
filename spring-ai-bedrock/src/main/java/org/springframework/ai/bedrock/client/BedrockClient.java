package org.springframework.ai.bedrock.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.bedrock.config.BedrockModelEnum;
import org.springframework.ai.bedrock.config.Endpoint;
import org.springframework.ai.bedrock.config.GenerationRequest;
import org.springframework.ai.client.AiClient;
import org.springframework.ai.client.AiResponse;
import org.springframework.ai.prompt.Prompt;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Implementation of {@link AiClient} backed by AWS Bedrock.
 */
public class BedrockClient implements AiClient {

	private static final Logger log = LoggerFactory.getLogger(BedrockClient.class);

	private static final ObjectMapper jsonMapper = new ObjectMapper();

	private static final RestTemplate restTemplate = new RestTemplate();

	private final String baseUrl;

	private final String defaultRegion = "us-east-1";

	private static final String DEFAULT_MODEL_ID = "ai21.j2-mid-v1";

	private final String modelId;

	private BedrockModelEnum modelEnum;


	public BedrockClient(String baseUrl) {
		this(baseUrl, DEFAULT_MODEL_ID);
	}

	public BedrockClient(Endpoint endpoint, String modelId) {
		this(endpoint.getEndpoint(), modelId);
	}

	public BedrockClient(String baseUrl, String modelId) {
		this.baseUrl = baseUrl;
		this.modelId = modelId;
		this.modelEnum = BedrockModelEnum.getByModelId(modelId);
	}



	@Override
	public AiResponse generate(Prompt prompt) {
		if (CollectionUtils.isEmpty(prompt.getMessages())) {
			log.warn("The prompt message cannot empty");
			return null;
		}

		if (prompt.getMessages().size() > 1) {
			log.warn("Only the first prompt message will be used; any subsequent messages will be ignored.");
		}

		String promptMessage = prompt.getMessages().get(0).getContent();
		log.debug("Prompt message: {}", promptMessage);


		// Since no generation request class was specified, we'll use the default model with default parameters
		try {
			GenerationRequest generationRequest = modelEnum.createInstance();
			generationRequest.setPromptString(promptMessage);

			return generate(generationRequest);
		} catch (IllegalAccessException | InstantiationException e) {
			log.error("BedrockClient.generate: error creating instance of GenerationRequest class: {}",
					modelEnum.getGenerationRequestClasss().getName(), e);
		}

		return null;
	}

	public AiResponse generate(GenerationRequest request) {

		String endpointUrl = buildURL(request.getModelId());
		log.debug("Endpoint URL: {}", endpointUrl);

		try {
			String requestJSON = jsonMapper.writeValueAsString(request);
			log.debug("Request JSON: {}", requestJSON);

			HttpHeaders headers = new HttpHeaders();
			headers.set("headername", "headervalue");


			HttpEntity<GenerationRequest> httpEntityRequest = new HttpEntity<>(request, headers);


			String response = restTemplate.postForObject(endpointUrl, httpEntityRequest, String.class);

			log.debug("Response: {}", response);



		} catch (JsonProcessingException e) {
			log.error("Error converting request to JSON: {}", e.getMessage(), e);
		}
		return null;
	}

	private String buildURL(String modelId) {
		return baseUrl + "/model/" + modelId + "/invoke";
	}


	public Endpoint getDefaulEndpoint() {
		return getEndpoint(defaultRegion);
	}

	public Endpoint getEndpoint(String region) {
		Assert.notNull(region, "Region must not be null");
		return Endpoint.fromRegionName(region);
	}

}
