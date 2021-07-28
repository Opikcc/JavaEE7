

package javaeems.chapter10.voting;

import javax.ejb.Singleton;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class VotingBeanConcurrentReadAccess {
    private String contestant;
    private long voterId;
    
    @Lock(LockType.READ)
    public String getLastVote() {
        return "Voter: " + voterId + " vote for " + contestant;
    }
    
    @Lock(LockType.WRITE)
    public void doVote(long voterId, String contestant) {
        // process vote in voting system
        this.voterId = voterId;
        this.contestant = contestant;
    }
}

