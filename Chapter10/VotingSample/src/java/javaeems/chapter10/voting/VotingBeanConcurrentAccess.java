package javaeems.chapter10.voting;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;


@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class VotingBeanConcurrentAccess {
    private String contestant;
    private long voterId;
    
    public String getLastVote() {
        return "Voter: " + voterId + " vote for " + contestant;
    }
    
    
    public void doVote(long voterId, String contestant) {
        
        // this code must be hand synchronized
       
    }
}

