/**
 * Copyright (C) 2017 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mediadriver.atlas.java.inspect.v2;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringUtilTest {

	@Test
	public void testRemoveGetterAndLowercaseFirstLetter() {
		assertNull(StringUtil.removeGetterAndLowercaseFirstLetter(null));
		assertEquals("", StringUtil.removeGetterAndLowercaseFirstLetter(""));
		assertEquals("g", StringUtil.removeGetterAndLowercaseFirstLetter("g"));
		assertEquals("ge", StringUtil.removeGetterAndLowercaseFirstLetter("ge"));
		assertEquals("get", StringUtil.removeGetterAndLowercaseFirstLetter("get"));
		assertEquals("i", StringUtil.removeGetterAndLowercaseFirstLetter("i"));
		assertEquals("is", StringUtil.removeGetterAndLowercaseFirstLetter("is"));
		assertEquals("abc", StringUtil.removeGetterAndLowercaseFirstLetter("getAbc"));
		assertEquals("abc", StringUtil.removeGetterAndLowercaseFirstLetter("isAbc"));
	}

}
