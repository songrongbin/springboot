package com.bins.springboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bins.springboot.dao.ObligationDao;

public class ObligationService {
    static final Logger log = LoggerFactory.getLogger(ObligationService.class);
      
    public void obligate() {
        ObligationDao dao = new ObligationDao();
        log.debug("obligate start...");  
        dao.queryObligations();  
        log.error("An error occurred during the progress");  
        log.debug("obligate end...");  
    }

    public static void main(String args[]) {
        ObligationService service = new ObligationService();
        service.obligate();
    }
}