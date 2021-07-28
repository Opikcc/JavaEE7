/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import javax.ejb.*;

public class ScoreClient {
    @EJB
    SlowBean slowBean;
    
    public void doIt() {
        
        String score = slowBean.getAverageExamScore("11<age<13&F&county");
        
        
    }
}
