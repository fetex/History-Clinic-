package com.qoajad.backend.rpc.eps.colsanitas;

import com.qoajad.backend.model.external.eps.appointment.*;
import com.qoajad.backend.model.external.eps.response.Response;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "ColsanitasAppointmentService", url = "http://thawing-stream-48846.herokuapp.com")
@Qualifier("defaultColsanitasAppointmentRPC")
public interface ColsanitasAppointmentRPC {

    @RequestMapping(value = "/horarios/{healthProviderInstituteName}/{specialtyName}", method = RequestMethod.GET)
    List<ConsultingRoom> findAvailableAppointments(@PathVariable("healthProviderInstituteName") String healthProviderInstituteName, @PathVariable("specialtyName") String specialtyName);

    @RequestMapping(value = "/horarios", method = RequestMethod.POST)
    CreateAppointmentResponse attemptToCreateAppointment(@RequestBody final CreateAppointment createAppointment);

    @RequestMapping(value = "/horarios/{appointmentId}", method = RequestMethod.DELETE)
    Response attemptToDeleteAppointment(@PathVariable("appointmentId") final int appointmentId);

    @RequestMapping(value = "/horarios/{userDocument}", method = RequestMethod.GET)
    List<Appointment> findUserAppointments(@PathVariable("userDocument") final int userDocument);

    @RequestMapping(value = "/solicitud", method = RequestMethod.POST)
    Response attemptToUpdateAppointment(UpdateAppointment updateAppointment);

    @RequestMapping(value = "/{userDocument}/{appointmentId}")
    Appointment findUserAppointment(@PathVariable("userDocument") final int userDocument,
                                    @PathVariable("appointmentId") final int appointmentId);
}
