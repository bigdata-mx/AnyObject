AnyObject
========

[![Build Status](https://secure.travis-ci.org/bigdata-mx/AnyObject.png)](http://travis-ci.org/bigdata-mx/AnyObject)

AnyObject is an common interface for key/value serialization languages like YAML and JSON

Implementations
============
AnyObject provides base implementations for SnakeYAML [1] and Jackson Java JSON Processor [2], but it can be used to wrap any key/value based data format.

Usage YAML
==========
Create AnyObject from SnakeYAML:

    File file = new File("test.yaml");
    AnyObject yaml = SnakeYAMLLoader.getInstance().load(file);
    yaml.getString("receipt");
    yaml.getString("customer.given");

# YAML snippet
receipt:     Oz-Ware Purchase Invoice
date:        2007-08-06
customer:
    given:   Dorothy
    family:  Gale

Usage JSON
==========
Create AnyObject from Jackson JSON:

    File file = new File("test.json");
    AnyObject json = JacksonJSONLoader.getInstance().load(in);
    json.getString("firstName");
    json.getInteger("age").intValue();
    json.getString("address.city");

# JSON snippet
{
     "firstName": "John",
     "lastName": "Smith",
     "age": 25,
     "address":
     {
         "streetAddress": "21 2nd Street",
         "city": "New York",
         "state": "NY",
         "postalCode": "10021"
     }
}

[1] http://code.google.com/p/snakeyaml/
[2] http://jackson.codehaus.org/