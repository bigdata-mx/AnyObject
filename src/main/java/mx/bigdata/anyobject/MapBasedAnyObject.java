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

import java.util.Arrays;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;

public class MapBasedAnyObject implements AnyObject {

  private final Map map;

  private final String separator;
  
  public MapBasedAnyObject(Map map) {
    this(map, "\\.");
  }

  public MapBasedAnyObject(Map map, String separator) {
    this.map = new HashMap(map);
    this.separator = separator;
  }

  private Object get(String key) {
    return get(map, key.split("\\."));    
  }

  private Object get(Map inner, String[] key) {
    Object o = inner.get(key[0]);
    return (key.length > 1) 
      ? get((Map) o, Arrays.copyOfRange(key, 1, key.length))
      : o; 
  } 

  public AnyObject getAnyObject(String key) {
    return new MapBasedAnyObject((Map) get(key));
  }

  public Long getLong(String key) {
    return (Long) get(key);
  }

  public Integer getInteger(String key) {
    return (Integer) get(key);
  }

  public String getString(String key) {
    return (String) get(key);
  }

  public Double getDouble(String key) {
    return (Double) get(key);  
  }

  public Float getFloat(String key) {
    return (Float) get(key);    
  }

  public Iterable getIterable(final String key) {
    return new AnyIterable((Iterable) get(key));
  }

  public String toString() {
    return map.toString();
  }

  private final static class AnyIterator implements Iterator {

    private final Iterator iterator;

    AnyIterator(Iterator iterator) {
      this.iterator = iterator;
    }
    
    @Override
    public boolean hasNext() {
      return iterator.hasNext();
    }
    
    @Override
    public Object next() {
      Object o = iterator.next();
      if (o instanceof Iterable) {
        return new AnyIterable((Iterable) o);
      }
      if (o instanceof Map) {
        return new MapBasedAnyObject((Map) o);
      }
      return o;
    }
    
    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  private final static class AnyIterable implements Iterable {
    private final Iterable iterable;

    AnyIterable(Iterable iterable) {
      this.iterable = iterable;
    }
    
    @Override
    public Iterator iterator() {
      return new AnyIterator(iterable.iterator());
    }
  }
}
