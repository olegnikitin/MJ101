package com.mongodb;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.post;

/**
 * Created by olegnikitindev on 08.08.2016.
 */
public class HelloWorldSparkFreemarkerStyle {

    public static void main(String[] args) {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
        configuration.setClassForTemplateLoading(HelloWorldSparkFreemarkerStyle.class, "/");

        get("/", (request, response) -> {
            StringWriter writer = new StringWriter();
            try {
                Template helloTemplate = configuration.getTemplate("hello.ftl");
                Map<String, Object> helloMap = new HashMap<>();
                helloMap.put("name", "Freemarker");

                helloTemplate.process(helloMap, writer);

                System.out.println(writer);
            } catch (Exception e) {
                halt(500);
                e.printStackTrace();
            }
            return writer;
        });

        get("/favorite_fruit", (request, response) -> {
            StringWriter writer = new StringWriter();
            try {
                Template helloTemplate = configuration.getTemplate("fruitPicker.ftl");
                Map<String, Object> helloMap = new HashMap<>();
                helloMap.put("fruits", Arrays.asList("Apple", "Orange"));
                helloTemplate.process(helloMap, writer);
            } catch (Exception e) {
                halt(500);
                e.printStackTrace();
            }
            return writer;
        });

        post("/favorite_fruit", (request, response) -> {
            final String fruit = request.params("fruit");
            if (fruit == null) {
                return "Why don't you pick one?";
            } else {
                return "Your favourite fruit is " + fruit;
            }
        });
    }

}
