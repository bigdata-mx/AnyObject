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

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.Map;

import mx.bigdata.anyobject.Loader;
import org.yaml.snakeyaml.Yaml;

import mx.bigdata.anyobject.MapBasedAnyObject;
import mx.bigdata.anyobject.AnyObject;

public final class SnakeYAMLLoader implements Loader {

  private static final SnakeYAMLLoader instance = new SnakeYAMLLoader();

  public static SnakeYAMLLoader getInstance() {
      return instance;
  }

  private SnakeYAMLLoader() {
  }

  public AnyObject load(File file) throws IOException {
    InputStream in = new FileInputStream(file);
    try {
      return load(in);
    } finally {
      in.close();    
    }
  }

  public AnyObject load(InputStream in) throws IOException {
    Yaml yaml = new Yaml();
    Map<String, Object> map = (Map) yaml.load(in);
    return new MapBasedAnyObject(map);
  }

  public AnyObject load(Reader in) throws IOException {
    Yaml yaml = new Yaml();
    Map<String, Object> map = (Map) yaml.load(in);
    return new MapBasedAnyObject(map);
  }

  public AnyObject load(String in) throws IOException {
    Yaml yaml = new Yaml();
    Map<String, Object> map = (Map) yaml.load(in);
    return new MapBasedAnyObject(map);
  }

}
