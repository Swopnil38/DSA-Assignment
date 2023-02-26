package JobSheduling;

import java.util.Comparator;
import java.util.stream.Collector;

public class Compare implements Comparator<JobModel> {

    @Override
    public int compare(JobModel o1, JobModel o2) {
        if(o1.getProfit()<o2.getProfit()){//if ending value of first object is higher than second value then swap those two value
            return 1;
        }
        else if(o1.getProfit()>o2.getProfit()){
            return -1;
        }
        return 0;
    }
}
