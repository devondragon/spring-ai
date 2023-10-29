package org.springframework.ai.bedrock.config;

public enum Endpoint {
	US_EAST_1("https://bedrock-runtime.us-east-1.amazonaws.com"), FIPS_US_EAST_1("https://bedrock-runtime-fips.us-east-1.amazonaws.com"), US_WEST_2("https://bedrock-runtime.us-west-2.amazonaws.com"), FIPS_US_WEST_2("https://bedrock-runtime-fips.us-west-2.amazonaws.com"), AP_SOUTHEAST_1("https://bedrock-runtime.ap-southeast-1.amazonaws.com"), AP_NORTHHEAST_1("https://bedrock-runtime.ap-northeast-1.amazonaws.com"), EU_CENTERAL_1("https://bedrock-runtime.eu-central-1.amazonaws.com"),;

	private final String endpoint;

	Endpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getEndpoint() {
		return endpoint;
	}

	// Create a static method to lookup AwsRegion by region name
	public static Endpoint fromRegionName(String regionName) {
		for (Endpoint endpoint : Endpoint.values()) {
			if (endpoint.name().equalsIgnoreCase(regionName)) {
				return endpoint;
			}
		}
		throw new IllegalArgumentException("Invalid region name: " + regionName);
	}
}
