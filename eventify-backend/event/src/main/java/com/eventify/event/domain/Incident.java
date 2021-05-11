package com.eventify.event.domain;

import com.eventify.shared.ddd.UUIDAggregate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

//If incident is confirmed by the police, it cannot be modified anymore

//One transaction is for one aggregate. Transactions should not cross aggregate boundaries.

@Entity
public class Incident extends UUIDAggregate {

    @OneToMany(cascade = CascadeType.ALL)
    private List<Form> forms;
    //form is entity...obviously, form cannot exist without incident.
    //also if we want to update form, we need to do that through aggregate

    //it is important that other aggregates are referenced only by id so we cannot update them accidentally here
    //if for example person or car is deleted then Domain event is published and we need to consume that event and alter values here
    private UUID personId;
    private UUID carId;
    private boolean confirmedByPolice;
    // those date times must be in the past or now
    private LocalDateTime dateTimeReported;
    private LocalDateTime incidentDateTime;

    public void update(IncidentUpdateParam incidentUpdateParam) {
        if (confirmedByPolice) {
            throw new IllegalStateException("You cannot update incident which is confirmed by the police.");
        }
        if (incidentUpdateParam.getIncidentDateTime().isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Incident date time must be in the past.");
        }
        if (incidentUpdateParam.getDateTimeReported().isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Reported date time must be in the past.");
        }
        this.dateTimeReported = incidentUpdateParam.getDateTimeReported();
        this.incidentDateTime = incidentUpdateParam.getIncidentDateTime();
        this.personId = incidentUpdateParam.getPersonId();
        this.carId = incidentUpdateParam.getCarId();
    }

    public void policeConfirm() {
        this.confirmedByPolice = true;
    }

    public int calculateInsurance() {
        //let's say if you have an accident in foreign country then you populated one kind of form
        // and based on that form we will calculate your insurance money
        return Integer.MAX_VALUE;
    }

}
