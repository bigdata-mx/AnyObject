/*
 *  Copyright 2011 Elmer Garduno
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package mx.bigdata.anyobject.impl;

import java.io.InputStream;

import static org.junit.Assert.*;
import org.junit.Test;

import mx.bigdata.anyobject.AnyObject;

public class JacksonJSONLoaderTest {

  @Test
  public void testJSONData() throws Exception {    
    InputStream in = getClass().getResourceAsStream("/test.json");
    AnyObject json = JacksonJSONLoader.load(in);
    assertEquals("John", json.getString("firstName"));
    assertEquals(25, json.getInteger("age").intValue());
    assertEquals("New York", json.getString("address.city"));
  }
}
