package com.eventify.shared.config.auth;

import com.eventify.shared.ddd.DomainEvent;
import com.eventify.shared.ddd.UUIDAggregate;
import com.eventify.shared.ddd.UUIDEntity;
import com.eventify.shared.demo.Command;
import com.eventify.shared.demo.DomainEventListener;
import com.eventify.shared.demo.IntegrationEvent;
import com.eventify.shared.demo.IntegrationEventListener;
import com.eventify.shared.net.CommandHandler;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.springframework.context.event.EventListener;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

//todo forbid using repository in controller!
//todo Evenry controller must have integration test
public class ArchUnitTest {


    @ArchTest
    public static final ArchRule uuidAggregateRules = ArchRuleDefinition.classes()
            .that().areAssignableFrom(UUIDAggregate.class)
            .and().doNotHaveSimpleName("UUIDAggregate").and().doNotHaveSimpleName("UUIDEntity")
            .should().resideInAPackage("..domain..")
//            .andShould()
//            .beAnnotatedWith(Entity.class)
//            .orShould()
//            .beAnnotatedWith(MappedSuperclass.class)
//            .orShould()
//            .beAnnotatedWith(Inheritance.class)
            .andShould()
            .bePublic();

    //todo cannot find javax persistence classes in analytics module. Separate Those tests
    @ArchTest
    public static final ArchRule uuidEntityRules = ArchRuleDefinition.classes()
            .that().areAssignableFrom(UUIDEntity.class)
            .and().doNotHaveSimpleName("UUIDEntity")
            .should().resideInAPackage("..domain..")
//            .andShould()
//            .beAnnotatedWith(Entity.class)
//            .orShould()
//            .beAnnotatedWith(MappedSuperclass.class)
//            .orShould()
//            .beAnnotatedWith(Inheritance.class)
            .andShould()
            .notBePublic();

    @ArchTest
    public static final ArchRule requestsAndResponsesAreNotPublic = ArchRuleDefinition.classes()
            .that().haveSimpleNameEndingWith("Response").or().haveSimpleNameEndingWith("Request")
            .should()
            .notBePublic().andShould().resideInAPackage("..api.rest..");

    @ArchTest
    public static final ArchRule integrationListenersRules = ArchRuleDefinition.classes()
            .that().implement(IntegrationEventListener.class)
            .should()
            .beAnnotatedWith(Component.class)
            .andShould()
            .resideInAPackage("..application.listeners.integration..");

    @ArchTest
    public static final ArchRule domainListenersRules = ArchRuleDefinition.classes()
            .that().implement(DomainEventListener.class)
            .should()
            .resideInAPackage("..application.listeners.domain..");

    @ArchTest
    public static final ArchRule domainListenerMethodShouldBeAnnotated = ArchRuleDefinition.methods()
            .that().areDeclaredInClassesThat().implement(DomainEventListener.class)
            .should()
            .beAnnotatedWith(EventListener.class)
            .orShould()
            .beAnnotatedWith(TransactionalEventListener.class);

    //todo uncomment this!
//    @ArchTest
//    public static final ArchRule integrationListenerMethodShouldBeAnnotated = ArchRuleDefinition.methods()
//            .that().areDeclaredInClassesThat().implement(IntegrationEventListener.class)
//            .should()
//            .beAnnotatedWith(StreamListener.class);

    @ArchTest
    public static final ArchRule domainEventRules = ArchRuleDefinition.classes()
            .that().implement(DomainEvent.class)
            .should()
            .resideInAPackage("..domain.events..");

    @ArchTest
    public static final ArchRule integrationEventRules = ArchRuleDefinition.classes()
            .that().implement(IntegrationEvent.class)
            .should()
            .resideInAPackage("..api.integration.events.input..")
            .orShould()
            .resideInAPackage("..api.integration.events.output..");

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
    public static final ArchRule domainDoesNotDependOnOtherLayers = ArchRuleDefinition.noClasses()
            .that().resideInAPackage("..domain..")
            .and()
            .resideOutsideOfPackage("..listeners.domain..")
            .should()
            .dependOnClassesThat().resideInAnyPackage("..controller..", "..api..","..application..");

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
