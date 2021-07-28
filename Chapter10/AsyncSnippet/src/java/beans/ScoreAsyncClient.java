/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import javax.ejb.*;
import java.util.concurrent.Future;

public class ScoreAsyncClient {
    @EJB
    SlowBeanAsync slowBeanAsync;
    boolean impatient;
    
    public void doIt() {
        
        Future<String> futureScore = slowBeanAsync.getAverageExamScore("11<age<13&F&county");
        
        // test if the calculation is complete
        futureScore.isDone();
        
        // cancel the result if it is taking too long
        if (impatient) {
            futureScore.cancel(true);
        }
        
        // await the result
        String score = futureScore.get();
        
    }
    
    
}
