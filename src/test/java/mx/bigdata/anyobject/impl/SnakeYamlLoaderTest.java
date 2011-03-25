/*
 *  Copyright 2011 BigData Mx
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
import org.junit.Before;
import org.junit.Test;

import mx.bigdata.anyobject.AnyObject;

public class SnakeYamlLoaderTest {

  AnyObject yaml;
  
  @Before
  public void init() throws Exception {
    InputStream in = getClass().getResourceAsStream("/test.yaml");
    this.yaml = SnakeYAMLLoader.load(in);  
  }

  @Test
  public void testYAMLData() throws Exception {    
    assertEquals("Oz-Ware Purchase Invoice", yaml.getString("receipt"));
    assertEquals("Dorothy", yaml.getString("customer.given"));
    assertEquals("KS", yaml.getString("ship-to.state"));
  }

  @Test
  public void testYAMLIterator() {
    Iterable items = yaml.getIterable("items");
    for (Object o : items) {
      assertTrue((o instanceof AnyObject));
      AnyObject item = (AnyObject) o;
      assertNotNull(item.getString("part_no"));
    }
  } 

  @Test
  public void testNumbers() {
    Iterable items = yaml.getIterable("numbers");
    int num = 0;
    for (Object o : items) {
      Integer item = (Integer) o;
      assertEquals(num++, item.intValue());
    }
  } 
}
