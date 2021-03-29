package net.demo.myjava.microservices.personservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {

    String personId;
    String name;
    int age;
    List<Address> addresses;

    public Person copyPerson() {
        Person copy = Person.builder().age(this.age).name(this.name).personId(this.personId).addresses(this.addresses).build();
        return copy;
    }
}
