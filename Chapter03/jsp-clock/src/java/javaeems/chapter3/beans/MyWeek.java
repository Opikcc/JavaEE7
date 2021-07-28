package javaeems.chapter3.beans;

import java.util.Arrays;
import java.util.List;


public class MyWeek {
    private String name;
    
    public enum Day {
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY,
        THURSDAY, FRIDAY, SATURDAY     
    }
    
    public MyWeek() {}
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return "A description of how the week usually goes for " + name;
    }
    
    public int getNumberDays() {
        return 7;
    }

    public List<Day> getWorkingDays() {
        return Arrays.asList(Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY, Day.THURSDAY, Day.FRIDAY); 
    }
    
    public int getNumberWorkingDays() {
        return this.getWorkingDays().size();
    }
    
    public boolean isWeekendOff() {
        return true;
    }
    
    
    
    
}
