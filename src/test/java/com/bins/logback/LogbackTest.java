package com.bins.logback;

import org.junit.Test;

import com.bins.springboot.main.Reconciliation;

/**
 * Created by songrongbin on 2016/4/15.
 * logback test
 */
public class LogbackTest {
    @Test
    public void test_logback_cobertura() {
        Reconciliation.reconciliate();
    }
}