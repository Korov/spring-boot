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

package org.springframework.boot.test.autoconfigure.data.couchbase;

import org.springframework.boot.test.context.SpringBootTestContextBootstrapper;
import org.springframework.test.context.TestContextAnnotationUtils;
import org.springframework.test.context.TestContextBootstrapper;

/**
 * {@link TestContextBootstrapper} for {@link DataCouchbaseTest @DataCouchbaseTest}
 * support.
 *
 * @author Eddú Meléndez
 */
class DataCouchbaseTestContextBootstrapper extends SpringBootTestContextBootstrapper {

	@Override
	protected String[] getProperties(Class<?> testClass) {
		DataCouchbaseTest dataCouchbaseTest = TestContextAnnotationUtils.findMergedAnnotation(testClass,
				DataCouchbaseTest.class);
		return (dataCouchbaseTest != null) ? dataCouchbaseTest.properties() : null;
	}

}
