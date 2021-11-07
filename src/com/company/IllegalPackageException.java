package com.company;

public class IllegalPackageException extends RuntimeException {
    /*
    Если он заметил запрещенную посылку с одним из запрещенных содержимым ("weapons" и "banned substance")
     */
    public IllegalPackageException() {
        super("Detected weapons or banned substance");
    }
}
