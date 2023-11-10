package org.springframework.ai.bedrock.client;

import java.util.ArrayList;
import java.util.List;
import org.springframework.ai.client.AiClient;
import org.springframework.ai.client.AiResponse;
import org.springframework.ai.client.Generation;
import org.springframework.ai.prompt.Prompt;
import org.springframework.ai.prompt.messages.Message;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

public class BedrockClient implements AiClient {

	private String apiEndpoint;
	private String apiKey;
	private RestTemplate restTemplate;

	public BedrockClient(String apiEndpoint, String apiKey) {
		this.apiEndpoint = apiEndpoint;
		this.apiKey = apiKey;
		this.restTemplate = new RestTemplate();
	}

	@Override
	public AiResponse generate(Prompt prompt) {
		Assert.notNull(prompt, "Prompt must not be null");

		List<Message> messages = prompt.getMessages();

		// Build request object
		GenerateTextRequest request = new GenerateTextRequest();
		request.setMessages(convertToBedrockMessages(messages));

		// Make API call
		HttpHeaders headers = buildHeaders();
		String url = apiEndpoint + "/text-generation";
		String response = restTemplate.postForObject(url, request, String.class, headers);

		// Parse response
		List<Generation> generations = parseResponse(response);
		return new AiResponse(generations);

	}

	private HttpHeaders buildHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + apiKey);
		return headers;
	}

	private List<BedrockMessage> convertToBedrockMessages(List<Message> messages) {
		// Map Spring AI Message types to Bedrock

	}

	private List<Generation> parseResponse(String response) {
		// Parse JSON response and extract generations
		List<Generation> generations = new ArrayList<>();

		return generations;
	}

}
