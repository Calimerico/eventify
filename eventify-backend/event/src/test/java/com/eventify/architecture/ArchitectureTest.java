package com.eventify.architecture;

import com.eventify.shared.config.auth.ArchUnitTest;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchUnitRunner;
import org.junit.runner.RunWith;

@RunWith(ArchUnitRunner.class) // Remove this line for JUnit 5!!
@AnalyzeClasses(packages = "com.eventify", importOptions = ImportOption.DoNotIncludeTests.class)
public class ArchitectureTest extends ArchUnitTest {
}
