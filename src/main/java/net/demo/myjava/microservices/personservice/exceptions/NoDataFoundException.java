package net.demo.myjava.microservices.personservice.exceptions;

import lombok.Data;

@Data
public class NoDataFoundException extends RuntimeException {

    String mesage;
    public NoDataFoundException(String mesage) {
        //used to raise an exception if no data is found
        this.mesage = mesage;

    }
}
