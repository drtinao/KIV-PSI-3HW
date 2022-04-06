import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Contains various methods which implement application logic for downloading data from internet.
 */
public class InternetLogic {
    /* constants - START */
    private static final String ISS_NOW_LOC_API = "http://api.open-notify.org/iss-now.json"; //API for current ISS location
    private static final String SUNRISE_SUNSET_API = "https://api.sunrise-sunset.org/json?lat=LAT_PLACEHOLDER&lng=LNG_PLACEHOLDER"; //API used for sunrise / sunset detection
    private static final String UNIX_TIMESTAMP_API = "https://showcase.api.linx.twenty57.net/UnixTime/fromunix?timestamp=TIMESTAMP_PLACEHOLDER"; //API for converting timestamp to human datetime
    /* constants - END */

    /**
     * Connects to API specified by ISS_NOW_LOC_API constant and returns content of given page. This API should provide actual location of ISS.
     * @return content of page specified by ISS_NOW_LOC_API constant
     */
    public String getIssNowLocCont(){
        String issNowPageCont = downPageCont(ISS_NOW_LOC_API); //download page content
        return issNowPageCont;
    }

    /**
     * Connects to API specified by SUNRISE_SUNSET_API constant and returns retrieved content. API is used for retrieving information regarding to sunrise / sunset.
     * @param lat latitude of ISS - retrieved from ISS_NOW
     * @param lng longitude of ISS - retrieved from ISS_NOW
     * @return content of page specified by SUNRISE_SUNSET_API constant
     */
    public String getSunriseSunsetCont(String lat, String lng){
        String sunriseSunsetUpd = SUNRISE_SUNSET_API.replace("LAT_PLACEHOLDER", lat).replace("LNG_PLACEHOLDER", lng); //replace placeholders
        String sunriseSunsetPageCont = downPageCont(sunriseSunsetUpd); //download page content
        return sunriseSunsetPageCont;
    }

    /**
     * Connects to API specified by UNIX_TIMESTAMP_API constant, updates placeholder and returns retrieved content. The API is used for conversion between unix timestamp -> human readable date.
     * @param timestamp time in form of unix timestamp
     * @return content specified by modified UNIX_TIMESTAMP_API constant
     */
    public String getUnixTimestampCont(String timestamp){
        String unixTimestampUpd = UNIX_TIMESTAMP_API.replace("TIMESTAMP_PLACEHOLDER", timestamp);
        String unixTimestampCont = downPageCont(unixTimestampUpd); //download page content
        return unixTimestampCont;
    }

    /**
     * Download content of web page specified by urlToDown.
     * @param pageToDown address of web page which content should be retrieved
     * @return downloaded content of web page
     */
    public String downPageCont(String pageToDown){
        System.out.println("***Downloading content of page: " + pageToDown + " - START***");
        String pageCont = ""; //downloaded content

        try {
            URL urlToDown = new URL(pageToDown);
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlToDown.openStream()));

            String oneLine;
            while((oneLine = reader.readLine()) != null){
                pageCont += oneLine;
            }

            reader.close();
            System.out.println("Downloaded content: " + pageCont);
        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException error while downloading page content...");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException error while downloading page content...");
            e.printStackTrace();
        }
        System.out.println("***Downloading content of page: " + pageToDown + " - END***");
        return pageCont;
    }
}
