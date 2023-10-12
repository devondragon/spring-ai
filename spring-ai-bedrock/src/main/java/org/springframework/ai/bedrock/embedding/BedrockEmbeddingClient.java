package org.springframework.ai.bedrock.embedding;

import java.util.List;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.ai.embedding.EmbeddingResponse;

public class BedrockEmbeddingClient implements EmbeddingClient {

	@Override
	public List<Double> embed(String text) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'embed'");
	}

	@Override
	public List<Double> embed(Document document) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'embed'");
	}

	@Override
	public List<List<Double>> embed(List<String> texts) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'embed'");
	}

	@Override
	public EmbeddingResponse embedForResponse(List<String> texts) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'embedForResponse'");
	}

}
