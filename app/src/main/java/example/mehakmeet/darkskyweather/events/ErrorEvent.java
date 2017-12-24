package example.mehakmeet.darkskyweather.events;

/**
 * Created by MEHAKMEET on 24-12-2017.
 */

public class ErrorEvent {

    private final String errorMessage;

    public ErrorEvent(String errorMessage) {

        this.errorMessage=errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
