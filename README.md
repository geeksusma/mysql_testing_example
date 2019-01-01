# mysql_testing_example
Example about how to use an embedded MySQL for Testing. This is something I recently learned in my current company from my colleagues, and can be useful for you.

## Enviroment
* Spring Boot 2.X
* Hibernate 5.X
* Java 1.8

## Problem
Several times I found projects which they were using a MySQL as the database for live environments.
This is ok, I love MySQL, but the problem is when you want to add Integration or Persistence Tests to check your code.
The workaround I found always was the same, using an H2 as the Embedded database for testing environments, and sometimes, just to do a setUp for the H2 to make it fully compatible with the MySQL one can be a pain in the ass, because they are not fully compatible. And always you need to do weird mappings from data types like SMALLINT, sequences and so on.
And in fact, I always think is better for you if you can use a test environment as close to the production as you can.

## Solution
The key to have an embedded MySQL is the "com.wix.wix-embedded-mysql" library. This library is very easy to configure, and his integration with Spring-Boot is amazing. The only you have to do is:

* Put a "Configuration Class" to define an embedded datasource (check EmbeddedMyslServerConfig)
* Put a schema creation script in your classpath

And that's all!

Here in this project, I show you how to do it in a quick way, even you can check how, using some annotations, you can populate data even from mysql file (avoiding the usage of the programmatic way) I'm not complaining about the usage of the programmatic way, in fact, both approaches are fine for me from my point of view.

Feel free to fork, contribute or comment
