
package javaeems.chapter10.voting;

import javax.ejb.Singleton;

@Singleton
public class VotingBeanSingleThreaded {
    private String contestant;
    private long voterId;

    public String getLastVote() {
        return "Voter: " + voterId + " vote for " + contestant;
    }
    
    public void doVote(long voterId, String contestant) {
        // process vote in voting system
        this.voterId = voterId;
        this.contestant = contestant;
    }
}
