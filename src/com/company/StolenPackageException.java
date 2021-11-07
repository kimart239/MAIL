package com.company;

public class StolenPackageException extends RuntimeException {
    /*
    Если он находит посылку, состоящую из камней (содержит слово "stones")
     */
    public StolenPackageException () {
        super("stones");
    }
}
