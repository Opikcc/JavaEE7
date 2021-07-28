package javaeems.chapter3.clockbean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ClockBean {
    
    public long getCurrentTimeSinceEpoch() {
        return System.currentTimeMillis();
    }
    
    public String getReadableDate() {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("EEEEEEEE");
        String today = sdf.format(now);
        return today;
        
    }            
}
