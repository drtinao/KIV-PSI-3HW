import java.time.Duration;
import java.time.LocalTime;

/**
 * Class contains entry point of application (main method) and therefore is used for application start.
 */
public class Main {
    /**
     * Method is responsible for application start and calls methods from other classes to achieve rest api client function.
     * @param args arguments not used
     */
    public static void main(String[] args){
        InternetLogic intLogic = new InternetLogic(); //contains app logic for internet communication
        ParseLogic parseLogic = new ParseLogic(); //contains app logic for data parsing
        EvalLogic evalLogic = new EvalLogic(); //contains app logic for retrieved data evaluation

        //ISS Now download + parse
        String issNowLocCont = intLogic.getIssNowLocCont(); //get content of ISS Now API, current location of ISS
        String latitudeIss = parseLogic.getLatFromIssSource(issNowLocCont); //parse latitude from source
        String longitudeIss = parseLogic.getLngFromIssSource(issNowLocCont); //parse longitude from source
        String timestampIss = parseLogic.getTimestampFromIssSource(issNowLocCont); //parse timestamp from source
        //convert unix timestamp to human readable datetime
        String unixTimestampCont = intLogic.getUnixTimestampCont(timestampIss);
        String unixTimestampConv = parseLogic.getHumanFromUnixSource(unixTimestampCont);
        String unixTimeIss = parseLogic.getTimeFromHuman(unixTimestampConv);
        LocalTime unixTimeIssLocal = evalLogic.convIssDate(unixTimeIss);

        //sunrise / sunset download + parse
        String sunriseSunsetCont = intLogic.getSunriseSunsetCont(latitudeIss, longitudeIss);
        String sunrise = parseLogic.getSunriseFromSunSource(sunriseSunsetCont);
        String sunset = parseLogic.getSunsetFromSource(sunriseSunsetCont);
        LocalTime sunriseLocal = evalLogic.convSunriseSunsetDate(sunrise);
        LocalTime sunsetLocal = evalLogic.convSunriseSunsetDate(sunset);

        //print retrieved information to user
        System.out.println("Retrieved information from ISS Now API - START");
        System.out.println("latitude: " + latitudeIss);
        System.out.println("longitude: " + longitudeIss);
        System.out.println("timestamp: " + timestampIss);
        System.out.println("timestamp converted using unix timestamp API: " + unixTimestampConv);
        System.out.println("time from timestamp: " + unixTimeIss);
        System.out.println("time in form of LocalTime: " + unixTimeIssLocal.toString());
        System.out.println("Retrieved information from ISS Now API - END");

        System.out.println("Retrieved information from sunrise / sunset API - START");
        System.out.println("sunrise: " + sunrise);
        System.out.println("sunrise in form of LocalTime: " + sunriseLocal.toString());
        System.out.println("sunset: " + sunset);
        System.out.println("sunset in form of LocalTime: " + sunsetLocal.toString());
        System.out.println("Retrieved information from sunrise / sunset API - END");

        //get required information
        System.out.println("***Evaluation: ISS on part of Earth where sun is shining? - START***");
        boolean isIssVisible = evalLogic.isInSunriseSunsetRange(sunriseLocal, unixTimeIssLocal, sunsetLocal);
        if(isIssVisible){
            System.out.println("ISS is currently on part of Earth where sun is shining.");
        }else{
            System.out.println("ISS is currently NOT on part of Earth where sun is shining.");
        }
        System.out.println("ISS is now located at latitude: " + latitudeIss + ", longitude: " + longitudeIss + ". UTC time is: " + unixTimestampConv + ".");
        System.out.println("Sunrise at this location occurs at: " + sunriseLocal.toString() + " and sunset occurs at: " + sunsetLocal.toString() + ".");
        System.out.println("***Evaluation: ISS on part of Earth where sun is shining? - END***");
        System.out.println("***Evaluation: good conditions for watching ISS? - START***");
        boolean isGoodCondition = evalLogic.isGoodCondition(sunriseLocal, unixTimeIssLocal, sunsetLocal);
        if(isGoodCondition){
            System.out.println("Currently there are good conditions for watching ISS.");
        }else{
            System.out.println("Currently there are NOT good conditions for watching ISS.");
        }
        Duration sunriseDif = Duration.between(unixTimeIssLocal, sunriseLocal); //get time diff between iss and sunrise
        Duration sunsetDif = Duration.between(sunsetLocal, unixTimeIssLocal); //get time diff between iss and sunset
        System.out.println("(one of the below values should be >= 60 && <= 120, else time is not right)");
        System.out.println("Minutes between ISS time / sunrise time: " + sunriseDif.toMinutes() + ".");
        System.out.println("Minutes between sunset time / ISS time: " + sunsetDif.toMinutes() + ".");
        System.out.println("***Evaluation: good conditions for watching ISS? - END***");
    }
}