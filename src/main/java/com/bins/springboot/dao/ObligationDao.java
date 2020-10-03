package com.bins.springboot.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ObligationDao {
    static final Logger log = LoggerFactory.getLogger(ObligationDao.class);
      
    public void queryObligations() {  
        log.debug("query obligations start...");  
        log.error("An error occurred during the query");  
        log.debug("query obligations end...");  
    }  
}