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

package org.springframework.boot.buildpack.platform.docker.type;

import org.junit.jupiter.api.Test;

import org.springframework.boot.buildpack.platform.io.TarArchive;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.mockito.Mockito.mock;

/**
 * Tests for {@link ContainerContent}.
 *
 * @author Phillip Webb
 */
class ContainerContentTests {

	@Test
	void ofWhenArchiveIsNullThrowsException() {
		assertThatIllegalArgumentException().isThrownBy(() -> ContainerContent.of(null))
			.withMessage("'archive' must not be null");
	}

	@Test
	void ofWhenDestinationPathIsNullThrowsException() {
		TarArchive archive = mock(TarArchive.class);
		assertThatIllegalArgumentException().isThrownBy(() -> ContainerContent.of(archive, null))
			.withMessage("'destinationPath' must not be empty");
	}

	@Test
	void ofWhenDestinationPathIsEmptyThrowsException() {
		TarArchive archive = mock(TarArchive.class);
		assertThatIllegalArgumentException().isThrownBy(() -> ContainerContent.of(archive, ""))
			.withMessage("'destinationPath' must not be empty");
	}

	@Test
	void ofCreatesContainerContent() {
		TarArchive archive = mock(TarArchive.class);
		ContainerContent content = ContainerContent.of(archive);
		assertThat(content.getArchive()).isSameAs(archive);
		assertThat(content.getDestinationPath()).isEqualTo("/");
	}

	@Test
	void ofWithDestinationPathCreatesContainerContent() {
		TarArchive archive = mock(TarArchive.class);
		ContainerContent content = ContainerContent.of(archive, "/test");
		assertThat(content.getArchive()).isSameAs(archive);
		assertThat(content.getDestinationPath()).isEqualTo("/test");
	}

}
