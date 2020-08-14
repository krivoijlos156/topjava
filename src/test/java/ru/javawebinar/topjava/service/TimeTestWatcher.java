package ru.javawebinar.topjava.service;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TimeTestWatcher extends TestWatcher {
    private static final Logger log = LoggerFactory.getLogger(TimeTestWatcher.class);

    Instant start;
    Instant finish;

    Map<String, Long> testTime = new TreeMap<>();
    private List<String> names;

    public TimeTestWatcher(List<String> names) {

        this.names = names;
    }

    public Map<String, Long> getTestTime() {
        return testTime;
    }

    protected void starting(Description description) {
        names.add(description.getMethodName());
        start = Instant.now();
    }

    protected void finished(Description description) {
        finish = Instant.now();

        String testName = description.getMethodName();
        long elapsed = Duration.between(start, finish).toMillis();
        testTime.put(testName, elapsed);
        log.info("Test {} took {} ms.", testName, elapsed);
    }

};