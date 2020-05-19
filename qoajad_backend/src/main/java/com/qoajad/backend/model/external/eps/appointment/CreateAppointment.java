package com.qoajad.backend.model.external.eps.appointment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qoajad.backend.utils.ValidationUtils;

import java.util.Date;
import java.util.Objects;

public class CreateAppointment {

    private final int patientDocument;
    // Format this date in the following way when sent to eps.
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy HH:mm:ss")
    private final Date date;
    private final int doctorDocument;
    private final String healthProviderInstituteName;

    public CreateAppointment(final int patientDocument, final Date date, final int doctorDocument, final String healthProviderInstituteName) {
        this.patientDocument = patientDocument;
        this.date = date;
        this.doctorDocument = doctorDocument;
        this.healthProviderInstituteName = healthProviderInstituteName;
        ValidationUtils.requireLeftGreaterThanRight(this.patientDocument, 0, "The patient document must be positive.");
        Objects.requireNonNull(this.date, "The date field cannot be null.");
        ValidationUtils.requireLeftGreaterThanRight(this.doctorDocument, 0, "The doctor document must be positive.");
        Objects.requireNonNull(this.healthProviderInstituteName, "The health provider institute name cannot be null.");
    }

    public int getPatientDocument() {
        return patientDocument;
    }

    public Date getDate() {
        return date;
    }

    public int getDoctorDocument() {
        return doctorDocument;
    }

    public String getHealthProviderInstituteName() {
        return healthProviderInstituteName;
    }
}