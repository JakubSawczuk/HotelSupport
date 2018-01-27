package timewithrest;

import org.springframework.web.client.RestTemplate;

/**
 * Created by Kuba on 2018-01-14.
 */

public class TimeAndData implements Runnable {


    private String time;
    private RestTemplate restTemplate = new RestTemplate();

    public String getTime() {
        return time;
    }


    public void run() {
        while(true){
            TimeAndData timeAndData = restTemplate.getForObject("https://www.amdoren.com/api/timezone.php?api_key=7PELQVEb5hXeq3h4RhiKHFVNjSVyTd&loc=Warsaw", TimeAndData.class);
            System.out.println(timeAndData.getTime());
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
