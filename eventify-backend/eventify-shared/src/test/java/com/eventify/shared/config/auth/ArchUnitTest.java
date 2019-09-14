package com.eventify.shared.config.auth;

import com.eventify.shared.demo.Command;
import com.eventify.shared.net.CommandHandler;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

//todo forbid using repository in controller!
//todo Evenry controller must have integration test
public class ArchUnitTest {

    @ArchTest
    public static final ArchRule datesInRequestMustBeAnnotatedWithDateTimeFormat = ArchRuleDefinition.fields()
            .that().areDeclaredInClassesThat().haveSimpleNameEndingWith("Request")
            .and()
            .haveRawType(LocalDateTime.class)
            .should().beAnnotatedWith(DateTimeFormat.class);

    @ArchTest
    public static final ArchRule controllerRules = ArchRuleDefinition.classes()
            .that().areAnnotatedWith(RestController.class)
            .should().haveSimpleNameEndingWith("Controller")
            .andShould()
            .beAnnotatedWith(RequestMapping.class)
            .andShould()
            .haveOnlyFinalFields()
            .andShould()
            .resideInAPackage("..api.rest..");

    @ArchTest
    public static final ArchRule handlerRules = ArchRuleDefinition.classes()
            .that().areAnnotatedWith(CommandHandler.class)
            .should().haveSimpleNameEndingWith("Handler")
            .andShould()
            .haveOnlyFinalFields()
            .andShould()
            .resideInAPackage("..application.handlers..");

    @ArchTest
    public static final ArchRule commandRules = ArchRuleDefinition.classes()
            .that().implement(Command.class)
            .should()
            .resideInAPackage("..application.commands..");

    @ArchTest
    public static final ArchRule domainDoesNotOnOtherLayers = ArchRuleDefinition.noClasses()
            .that().resideInAPackage("..domain..")
            .should().dependOnClassesThat().resideInAnyPackage("..controller..", "..api..","..application..");

    @ArchTest
    public static final ArchRule forbidUsingDate = ArchRuleDefinition.noFields()
            .should().haveRawType(Date.class);

    @ArchTest
    public static final ArchRule forbidUsingSimpleDateFormat = ArchRuleDefinition.noFields()
            .should().haveRawType(SimpleDateFormat.class);

    @ArchTest
    public static final ArchRule finderShouldBeAnnotatedWithTransactional = ArchRuleDefinition.classes()
            .that()
            .haveSimpleNameEndingWith("Finder")
            .should().beAnnotatedWith(Transactional.class);

}
