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
package com.mediadriver.atlas.core.v2;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class DefaultAtlasContextFactoryTest {

	private DefaultAtlasContextFactory factory = null;
	private static String THREAD_NAME = null;
	
	@BeforeClass
	public static void beforeClass() {
		THREAD_NAME = Thread.currentThread().getName();
	}
	
	@Test
	public void testInitDestroy() {
		factory = new DefaultAtlasContextFactory();
		factory.init();
		
		assertNotNull(factory);
		assertEquals(THREAD_NAME, factory.getThreadName());
		assertEquals("com.mediadriver.atlas.core.v2.DefaultAtlasContextFactory", factory.getClassName());
		assertNotNull(factory.getUuid());
		assertNotNull(factory.getJmxObjectName());
		assertNotNull(factory.getMappingService());
		assertNotNull(factory.getModules());
		
		factory.destroy();
		assertNotNull(factory);
		assertNull(factory.getThreadName());
		assertEquals("com.mediadriver.atlas.core.v2.DefaultAtlasContextFactory", factory.getClassName());
		assertNull(factory.getUuid());
		assertNull(factory.getJmxObjectName());
		assertNull(factory.getMappingService());
		assertNotNull(factory.getModules());
		assertEquals(new Integer(0), new Integer(factory.getModules().size()));

	}
	
	@Test
	public void testInitDestroyInitDestroy() {
		factory = new DefaultAtlasContextFactory();
		factory.init();
		String origUuid = factory.getUuid();
		
		assertNotNull(factory);
		assertEquals(THREAD_NAME, factory.getThreadName());
		assertEquals("com.mediadriver.atlas.core.v2.DefaultAtlasContextFactory", factory.getClassName());
		assertNotNull(factory.getUuid());
		assertNotNull(factory.getJmxObjectName());
		assertNotNull(factory.getMappingService());
		assertNotNull(factory.getModules());
		
		factory.destroy();
		assertNotNull(factory);
		assertNull(factory.getThreadName());
		assertEquals("com.mediadriver.atlas.core.v2.DefaultAtlasContextFactory", factory.getClassName());
		assertNull(factory.getUuid());
		assertNull(factory.getJmxObjectName());
		assertNull(factory.getMappingService());
		assertNotNull(factory.getModules());
		assertEquals(new Integer(0), new Integer(factory.getModules().size()));
		
		factory.init();
		assertNotNull(factory);
		assertEquals(THREAD_NAME, factory.getThreadName());
		assertEquals("com.mediadriver.atlas.core.v2.DefaultAtlasContextFactory", factory.getClassName());	
		assertNotNull(factory.getUuid());
		assertNotNull(factory.getJmxObjectName());
		assertNotNull(factory.getMappingService());
		assertNotNull(factory.getModules());
		assertNotEquals(origUuid, factory.getUuid());
		
		factory.destroy();
		assertNotNull(factory);
		assertNull(factory.getThreadName());
		assertEquals("com.mediadriver.atlas.core.v2.DefaultAtlasContextFactory", factory.getClassName());
		assertNull(factory.getUuid());
		assertNull(factory.getJmxObjectName());
		assertNull(factory.getMappingService());
		assertNotNull(factory.getModules());
		assertEquals(new Integer(0), new Integer(factory.getModules().size()));
	}
	
	@Test
	public void testStaticFactoryInitDestroy() {
		factory = DefaultAtlasContextFactory.getInstance();
		assertNotNull(factory);
		assertEquals(THREAD_NAME, factory.getThreadName());
		assertEquals("com.mediadriver.atlas.core.v2.DefaultAtlasContextFactory", factory.getClassName());	
		assertNotNull(factory.getUuid());
		assertNotNull(factory.getJmxObjectName());
		assertNotNull(factory.getMappingService());
		assertNotNull(factory.getModules());
		
		factory.destroy();
		assertNotNull(factory);
		assertNull(factory.getThreadName());
		assertEquals("com.mediadriver.atlas.core.v2.DefaultAtlasContextFactory", factory.getClassName());
		assertNull(factory.getUuid());
		assertNull(factory.getJmxObjectName());
		assertNull(factory.getMappingService());
		assertNotNull(factory.getModules());
		assertEquals(new Integer(0), new Integer(factory.getModules().size()));
	}
}
