package com.robusta.pdc.domain;

import org.junit.Before;
import org.junit.Test;

import static com.robusta.pdc.PackageNamesFixture.CLASS_A;
import static com.robusta.pdc.PackageNamesFixture.CLASS_B;
import static com.robusta.pdc.domain.JavaPackageMatchers.packageMatching;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

public class DependencyTrackerTest {
    private DependencyTracker tracker;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        tracker = new DependencyTracker();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testTrack() throws Exception {
        tracker.track(new ImportStatement("com.foo.bar.ClassA", CLASS_A));
        tracker.track(new ImportStatement("com.foo.bar.ClassB.method", CLASS_A));
        tracker.track(new ImportStatement("com.foo.bar.ClassA", CLASS_B));
        tracker.track(new ImportStatement("com.foo.buzz.ClassB.method", CLASS_B));
        assertThat(tracker.tracked(),
                allOf(
                    hasEntry(equalTo(CLASS_A), hasItems(packageMatching(equalTo("com.foo.bar")))),
                    hasEntry(equalTo(CLASS_B), hasItems(
                            packageMatching(equalTo("com.foo.bar")),
                            packageMatching(equalTo("com.foo.buzz"))))
                ));
    }
}
