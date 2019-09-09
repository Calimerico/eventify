package com.eventify;

import com.eventify.shared.config.auth.ArchUnitTest;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchUnitRunner;
import org.junit.runner.RunWith;


@RunWith(ArchUnitRunner.class) // Remove this line for JUnit 5!!
@AnalyzeClasses(packages = "com.eventify")
public class ArchitectureTest extends ArchUnitTest {
}
