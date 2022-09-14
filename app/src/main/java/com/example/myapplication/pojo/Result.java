package com.example.myapplication.pojo;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "fruit"
})
public class Result {

    @JsonProperty("fruit")
    public List<Fruit> fruit = null;

    @Override
    public String toString() {
        return "Result{" +
                "fruit=" + fruit +
                '}';
    }
}

