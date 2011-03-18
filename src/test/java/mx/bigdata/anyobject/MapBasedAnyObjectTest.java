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

package mx.bigdata.anyobject;

import java.io.InputStream;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import mx.bigdata.anyobject.impl.SnakeYAMLLoader;

public class MapBasedAnyObjectTest {

  MapBasedAnyObject yaml;
  
  @Before
  public void init() throws Exception {
    InputStream in = getClass().getResourceAsStream("/test.yaml");
    this.yaml = (MapBasedAnyObject) SnakeYAMLLoader.load(in);  
  }

  @Test
  public void testGet() throws Exception {    
    assertNotNull(yaml.get("receipt"));
    assertNull(yaml.get("nothing")); 
    assertNotNull(yaml.get("customer.given"));
  }

  @Test
  public void testGetAnyObject() throws Exception {    
    assertTrue((yaml.getAnyObject("customer") instanceof AnyObject));
    assertNull(yaml.getAnyObject("nothing")); 
  }  

  @Test
  public void testGetIterable() throws Exception {    
    assertTrue((yaml.getIterable("items") instanceof Iterable));
    assertNull(yaml.getIterable("nothing")); 
  }  
  
  @Test(expected = NullPointerException.class)
  public void testWrongPath() throws Exception { 
    yaml.get("nothing.given");   
  }
}
