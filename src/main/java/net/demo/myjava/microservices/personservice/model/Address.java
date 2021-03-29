package net.demo.myjava.microservices.personservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

    private String id;
    private String personId;
    private AddressType type;
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    public Address copyAddress(){

        Address copy = Address.builder().city(this.city).country(this.country).id(this.id)
                .personId(this.personId).postalCode(this.postalCode).state(this.state).type(this.type).street(this.street).build();
        return copy;

    }

}
