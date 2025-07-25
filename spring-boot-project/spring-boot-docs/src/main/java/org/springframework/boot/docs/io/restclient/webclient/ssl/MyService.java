/*
 * Copyright 2012-present the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.docs.io.restclient.webclient.ssl;

import reactor.core.publisher.Mono;

import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientSsl;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MyService {

	private final WebClient webClient;

	public MyService(WebClient.Builder webClientBuilder, WebClientSsl ssl) {
		this.webClient = webClientBuilder.baseUrl("https://example.org").apply(ssl.fromBundle("mybundle")).build();
	}

	public Mono<Details> someRestCall(String name) {
		return this.webClient.get().uri("/{name}/details", name).retrieve().bodyToMono(Details.class);
	}

}
