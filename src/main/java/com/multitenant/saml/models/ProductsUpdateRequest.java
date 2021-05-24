package com.multitenant.saml.models;

import com.couchbase.client.java.repository.annotation.Field;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.List;

@Data
public class ProductsUpdateRequest {
    
    @Size(min = 10)
    @Field
    String topic;
    
    @Field
    List<String> tags;
}
