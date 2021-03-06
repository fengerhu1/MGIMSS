package com.mgimss.mgimss.repository;

import com.mgimss.mgimss.entity.Appliance;
import com.mgimss.mgimss.entity.Job;
import com.mgimss.mgimss.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


public interface PendingJobRepository extends JpaRepository<Job, Long> {

    @Query(nativeQuery = true, value = "select * from job where uid =:uid and status = 0  order by job_id")
    ArrayList<Job> findByUid(@Param("uid") Long id);
    @Query(nativeQuery = true, value = "select * from job where app_id =:appId  and status = 0")
    Job findByAppliance(@Param("appId") Long appId);
    @Query(nativeQuery = true, value = "select * from job where job_id =:jobId  and status = 0")
    Job findByJobId(@Param("jobId") Long jobId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value="delete from job where job_id =:jid and status = 0" )
    int deleteByJid(@Param("jid") Long jid);
}