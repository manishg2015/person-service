package net.demo.myjava.microservices.personservice.service;


import net.demo.myjava.microservices.personservice.model.Address;
import net.demo.myjava.microservices.personservice.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    private final WebClient webClient;
    private final String addressServiceUrl;
    private final String addressServicePath;

    @Autowired
    public PersonService(
            WebClient.Builder webClient,
            @Value("${app.address-service.url}") String addressServiceUrl,
            @Value("${app.address-service.resource-path}") String addressServicePath
    ) {
        this.webClient = webClient.build();
        this.addressServiceUrl = addressServiceUrl;
        this.addressServicePath = addressServicePath;
    }

    public Mono<Person> getPersonDetailsById(String personId) {

        String uri = addressServiceUrl + addressServicePath + "/" + personId;
        List<Address> addressList = webClient.get()
                .uri(URI.create(uri)).
                        header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .retrieve().bodyToFlux(Address.class).collectList().toProcessor().block();
        System.out.println("after size is : " + addressList.size());
        return Mono.just(Person.builder().personId("1001").age(33).name("Ashwin").
                addresses(addressList).build());

    }

    public Mono<Person> getPersonDetailsByNameId(String personId) {

        System.out.println("**getPersonDetailsByNameId");
        String uri = addressServiceUrl + addressServicePath + "/" + personId;
        List<Address> addressList = new ArrayList<>();
        Mono<Person> personMono = Mono.just(Person.builder().personId("1001").age(33).name("Ashwin").addresses(addressList)
                .build());
        Mono<List<Address>> addressMono = webClient.get()
                .uri(URI.create(uri)).
                        header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .retrieve().bodyToFlux(Address.class).collectList();
        return Mono.zip(personMono, addressMono, (person, address) -> {
            person.setAddresses(addressList);
            return person;
        });
    }

}
