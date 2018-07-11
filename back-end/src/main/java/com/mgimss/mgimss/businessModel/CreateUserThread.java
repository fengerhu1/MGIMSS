package com.mgimss.mgimss.businessModel;
import com.mgimss.mgimss.entity.Battery;
import com.mgimss.mgimss.entity.Job;
import com.mgimss.mgimss.entity.User;
import com.mgimss.mgimss.repository.*;
import com.mgimss.mgimss.AI.getForecastData;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class CreateUserThread extends  Thread {

    @Autowired
    PendingJobRepository pendingJobRepository;

    @Autowired
    RunningJobRepository runningJobRepository;

    @Autowired
    FinishedJobRepository finishedJobRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BattetyRepository battetyRepository;

    @Autowired
    SolarPowerRepository solarPowerRepository;

    private Long clientId;
    public int getTime()
    {
        return 100000;
    }
    public CreateUserThread(Long clientId)
    {
        this.clientId = clientId;
    }
    @Override
    public void run(){
        while (true) {
            try {
//                Job job1 = new Job();
//                job1.myinit(120000, 100000, 110000, 5000, 10, 1);
//                Job job2 = new Job();
//                job2.myinit(120000, 100000, 110000, 5000, 10, 2);
//                Job job3 = new Job();
//                job3.myinit(120000, 100000, 110000, 5000, 10, 3);
//                Job job4 = new Job();
//                job4.myinit(120000, 100000, 110000, 5000, 10, 4);
//                Job job5 = new Job();
//                job5.myinit(120000, 100000, 110000, 5000, 10, 5);
//                ArrayList<Job> sleepJob = new ArrayList();
//                sleepJob.add(job4);
//                sleepJob.add(job5);
//                ArrayList<Job> runJob = new ArrayList();
//                runJob.add(job1);
//                runJob.add(job2);
//                runJob.add(job3);
                ArrayList<Job> beginJob = new ArrayList();
                Schedule newschedule = new Schedule();
                System.out.println("test begin");
                //接受该clientIdDE1runJob，sleepJob;
                ArrayList<Job> runJob = runningJobRepository.findByUid(clientId);
                ArrayList<Job> pendJob = pendingJobRepository.findByUid(clientId);

                User user = userRepository.findByUid(clientId);
                Battery battery = battetyRepository.findByUser(user.getUid());
                ArrayList<Long> predictSourceData = solarPowerRepository.findAllDataByUid(clientId);
                Long[] data = new Long[predictSourceData.size()];
                for (int g = 0 ;g<predictSourceData.size();g++)
                {
                    data[g] = predictSourceData.get(g);
                }

                Long[] predictData =  getForecastData.predicted(data);
                beginJob = newschedule.doschedule(runJob, pendJob,battery,predictData);
                int threadSize = beginJob.size();
                Thread[] threadId;
                threadId = new Thread[threadSize];
                for(int k = 0 ;k<beginJob.size();k++)
                {
                    threadId[k] = new SendAndReceive(beginJob.get(k));
                    threadId[k].start();

                }

                sleep(6 * 100*3);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

}
