package com.bins.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Reconciliation {
    private static final Logger log = LoggerFactory.getLogger(Reconciliation.class);

    public static void main(String args[]) {
        reconciliate();
    }
      
    public static void reconciliate() {
        String name = "conciliate";
        log.info("Hello from reconciliate");
        log.debug("My name is {}", name);
    }  
}  