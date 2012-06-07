package mx.bigdata.anyobject;

import java.util.Arrays;

public class AnyTuple {
  private final String key;
  
  private final Object o;
  
  public AnyTuple(String key, Object o) {
    this.key = key;
    this.o = o;
  }
  
  public String getKey() {
    return key;
  }
  
  public Object getObject() {
    return o;
  }
  
  @Override
  public int hashCode() {
    return hashCode(key, o);
  }
  
  private int hashCode(Object... objects) {
    return Arrays.hashCode(objects);
  }
  
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AnyTuple)) {
      return false;
    }
    AnyTuple at = (AnyTuple) o;
    return this.key.equals(at.key) && this.o.equals(at.o); 
  }
}
