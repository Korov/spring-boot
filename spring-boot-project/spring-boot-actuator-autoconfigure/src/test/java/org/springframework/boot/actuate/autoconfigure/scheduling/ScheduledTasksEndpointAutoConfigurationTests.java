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

package org.springframework.boot.actuate.autoconfigure.scheduling;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import org.springframework.boot.actuate.scheduling.ScheduledTasksEndpoint;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link ScheduledTasksEndpointAutoConfiguration}.
 *
 * @author Andy Wilkinson
 */
class ScheduledTasksEndpointAutoConfigurationTests {

	private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
		.withConfiguration(AutoConfigurations.of(ScheduledTasksEndpointAutoConfiguration.class));

	@Test
	void endpointIsAutoConfigured() {
		this.contextRunner.withPropertyValues("management.endpoints.web.exposure.include=scheduledtasks")
			.run((context) -> assertThat(context).hasSingleBean(ScheduledTasksEndpoint.class));
	}

	@Test
	void endpointNotAutoConfiguredWhenNotExposed() {
		this.contextRunner.run((context) -> assertThat(context).doesNotHaveBean(ScheduledTasksEndpoint.class));
	}

	@Test
	void endpointCanBeDisabled() {
		this.contextRunner.withPropertyValues("management.endpoint.scheduledtasks.enabled:false")
			.run((context) -> assertThat(context).doesNotHaveBean(ScheduledTasksEndpoint.class));
	}

	@Test
	void endpointBacksOffWhenUserProvidedEndpointIsPresent() {
		this.contextRunner.withUserConfiguration(CustomEndpointConfiguration.class)
			.run((context) -> assertThat(context).hasSingleBean(ScheduledTasksEndpoint.class)
				.hasBean("customEndpoint"));
	}

	@Configuration(proxyBeanMethods = false)
	static class CustomEndpointConfiguration {

		@Bean
		CustomEndpoint customEndpoint() {
			return new CustomEndpoint();
		}

	}

	private static final class CustomEndpoint extends ScheduledTasksEndpoint {

		private CustomEndpoint() {
			super(Collections.emptyList());
		}

	}

}
