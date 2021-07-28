/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import javax.ejb.Stateful;
import javax.ejb.LocalBean;

/**
 *
 * @author dannycoward
 */
@Stateful
@LocalBean
public class SlowBean {

    public String getAverageExamScore(String criteria) {
        String score;
        // unpredictably long operation calculating the score
        return score;
    }
    
}
