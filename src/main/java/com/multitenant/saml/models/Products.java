package com.multitenant.saml.models;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.couchbase.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@Document
public class Products {
    
    @Id
    final String id;
    
    @Size(min=10)
    @NotNull
    @Field
    String category;
    
    @NotNull
    @Field
    String name;

    @NotNull
    @Field
    Double price;

    @Field
    List<String> tags;
    
    @Field
    Date date;
    
}
