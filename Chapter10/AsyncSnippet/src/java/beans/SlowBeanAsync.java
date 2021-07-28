/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import javax.ejb.*;
import javax.ejb.AsyncResult;
import java.util.concurrent.Future;

/**
 *
 * @author dannycoward
 */
@Stateful
@LocalBean
public class SlowBeanAsync {

    @Asynchronous
    public Future<String> getAverageExamScore(String criteria) {
        String score;
        // unpredictably long operation calculating the score
        
        return new AsyncResult<String>(score);
    }
    
}
