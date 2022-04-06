import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Contains methods which implement application logic for evaluation of given data.
 */
public class EvalLogic {
    /**
     * Checks whether there are good conditions for watching ISS. Good conditions = 1-2 hours before sunrise / 1-2 hours after sunset.
     * @param sunrise time when sunrise occurs on iss location - retrieved from Sunrise/Sunset API
     * @param issTime current iss time - retrieved from iss now
     * @param sunset time when sunset occurs on iss location - retrieved from Sunrise/Sunset API
     * @return true if good conditions are present - else false
     */
    public boolean isGoodCondition(LocalTime sunrise, LocalTime issTime, LocalTime sunset){
        Duration sunriseDif = Duration.between(issTime, sunrise); //get time diff between iss and sunrise
        Duration sunsetDif = Duration.between(sunset, issTime); //get time diff between iss and sunset

        if((sunriseDif.toMinutes() > 0 && sunriseDif.toMinutes() >= 60 && sunriseDif.toMinutes() <= 120)
        || (sunsetDif.toMinutes() > 0 && sunsetDif.toMinutes() >= 60 && sunsetDif.toMinutes() <= 120)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Checks whether time specified by issTime is between sunrise and sunset range.
     * @param sunrise time when sunrise occurs on iss location - retrieved from Sunrise/Sunset API
     * @param issTime current iss time - retrieved from iss now
     * @param sunset time when sunset occurs on iss location - retrieved from Sunrise/Sunset API
     * @return true if issTime is within given range, else false
     */
    public boolean isInSunriseSunsetRange(LocalTime sunrise, LocalTime issTime, LocalTime sunset){
        if(issTime.equals(sunrise) || issTime.equals(sunset) || (issTime.isAfter(sunrise) && issTime.isBefore(sunset))){ //sun is visible
            return true;
        }else{ //sun is not visible
            return false;
        }
    }

    /**
     * Converts sunrise / sunset time retrieved from Sunrise/Sunset API to form of LocalTime object which can be used for finding time difference.
     * @param sunriseSunset time represented in form of hh:mm:ss AM/PM
     * @return LocalTime object which represents time from Sunrise/Sunset API
     */
    public LocalTime convSunriseSunsetDate(String sunriseSunset){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm:ss a", Locale.ENGLISH);
        LocalTime time = LocalTime.from(formatter.parse(sunriseSunset));
        return time;
    }

    /**
     * Converts time retrieved from unix timestamp API to form of LocalTime object which can be used for finding time difference.
     * @param iss converted time of iss now
     * @return LocalTime object which represents time from Sunrise/Sunset API
     */
    public LocalTime convIssDate(String iss){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("k:mm:ss", Locale.ENGLISH);
        LocalTime time = LocalTime.from(formatter.parse(iss));
        return time;
    }
}
