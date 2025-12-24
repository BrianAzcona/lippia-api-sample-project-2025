package ar.steps;

import com.crowdar.core.PageSteps;
import io.cucumber.java.en.And;
import services.TimeEntryService;

public class TimeEntrySteps extends PageSteps {


    @And("^I add hours (.*), minutes (.*) and seconds (.*) to the project$")
    public void iAddHoursMinutesAndSecondsToTheProject(String p_hours, String p_minutes, String p_seconds) {
        TimeEntryService.addTimeEntry(p_hours,p_minutes,p_seconds);
    }
}
