package mx.bigdata.anyobject;

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
}
