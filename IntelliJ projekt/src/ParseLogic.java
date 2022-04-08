/**
 * Contains methods which implement application logic for parsing data retrieved from internet.
 */
public class ParseLogic {
    /**
     * Used for getting value of "timestamp" item which is present in downloaded source code of ISS Now.
     * @param pageSource downloaded source code of ISS Now API
     * @return value of "latitude" present in source code of ISS Now
     */
    public String getTimestampFromIssSource(String pageSource){
        String[] splittedSource = pageSource.split("\"timestamp\": "); //split source code
        splittedSource = splittedSource[1].split(",");

        if(splittedSource[0].charAt(splittedSource[0].length() - 1) == '}'){
            splittedSource[0] = splittedSource[0].substring(0, splittedSource[0].length() - 1);
        }
        return splittedSource[0];
    }

    /**
     * Is used for getting value of "latitude" item which is present in downloaded source code of ISS Now.
     * @param pageSource downloaded source code of ISS Now API
     * @return value of "latitude" present in source code of ISS Now
     */
    public String getLatFromIssSource(String pageSource){
        String[] splittedSource = pageSource.split("\"latitude\": \""); //split source code
        splittedSource = splittedSource[1].split("\"");

        return splittedSource[0]; //splittedSource[0] should now contain value of latitude
    }

    /**
     * Used for getting value of "longitude" item which is present in downloaded source code of ISS Now.
     * @param pageSource downloaded source code of ISS Now API
     * @return value of "longitude" present in source code of ISS Now
     */
    public String getLngFromIssSource(String pageSource){
        String[] splittedSource = pageSource.split("\"longitude\": \""); //split source code
        splittedSource = splittedSource[1].split("\"");

        return splittedSource[0]; //splittedSource[0] should now contain value of longitude
    }

    /**
     * Used for removing quotation marks which are contained within Unix timestamp API response.
     * @param pageSource downloaded source code of Unix timestamp API
     * @return human readable datetime
     */
    public String getHumanFromUnixSource(String pageSource){
        String modifiedSource = pageSource.substring(1, pageSource.length() - 1);
        return modifiedSource;
    }

    /**
     * Used for retrieving time from String which contains date + time. Returned by getHumanFromUnixSource method.
     * @param humanDateTime date + time in form of String
     * @return time parsed from given String
     */
    public String getTimeFromHuman(String humanDateTime){
        String[] splitted = humanDateTime.split(" ");
        return splitted[1];
    }

    /**
     * Is used for getting value of "sunrise" item which is present in downloaded source code of Sunrise/Sunset API.
     * @param pageSource downloaded source code of Sunrise/Sunset API
     * @return value of "sunrise" present in source code of Sunrise/Sunset API
     */
    public String getSunriseFromSunSource(String pageSource){
        String[] splittedSource = pageSource.split("\"sunrise\":\"");
        splittedSource = splittedSource[1].split("\",\"sunset\":");

        return splittedSource[0]; //splittedSource[0] should now contain value of sunrise
    }

    /**
     * Is used for getting value of "sunset" item which is present in downloaded source code of Sunrise/Sunset API.
     * @param pageSource downloaded source code of Sunrise/Sunset API
     * @return value of "sunset" present in source code of Sunrise/Sunset API
     */
    public String getSunsetFromSource(String pageSource){
        String[] splittedSource = pageSource.split("\"sunset\":\"");
        splittedSource = splittedSource[1].split("\"");

        return splittedSource[0]; //splittedSource[0] should now contain value of sunset
    }
}
