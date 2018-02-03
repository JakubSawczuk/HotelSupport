package timewithrest;

import org.springframework.web.client.RestTemplate;

/**
 * Created by Kuba on 2018-01-14.
 */

public class DataByRESTful implements Runnable {

    private String time;
    public static String timeClock;
    private RestTemplate restTemplate = new RestTemplate();

    public  String getTime() {
        return time;
    }
    public static DataByRESTful dataByRESTful;

    public void run() {
            try {
                dataByRESTful = restTemplate.getForObject("https://www.amdoren.com/api/timezone.php?api_key=7PELQVEb5hXeq3h4RhiKHFVNjSVyTd&loc=Warsaw", DataByRESTful.class);
                System.out.println(dataByRESTful.getTime());
                reformatData();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    private void reformatData(){
        timeClock = dataByRESTful.getTime().substring(0,11);
    }
}
