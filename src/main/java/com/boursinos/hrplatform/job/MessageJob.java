package com.boursinos.hrplatform.job;

import com.boursinos.hrplatform.model.scheduler.Message;
import com.boursinos.hrplatform.repositories.scheduler.MessageRepository;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Optional;

public class MessageJob implements Job {
    private static final Logger log = LoggerFactory.getLogger(MessageJob.class);

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "new_test";
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        /* Get message id recorded by scheduler during scheduling */
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();

        String messageId = dataMap.getString("messageId");

        log.info("Executing job for message id {}", messageId);

        /* Get message from database by id */
        int id = Integer.parseInt(messageId);
        Optional<Message> messageOpt = messageRepository.findById(id);

        kafkaTemplate.send(TOPIC, messageOpt.get().getContent());

        /* update message visible in database */
        Message message = messageOpt.get();
        message.setVisible(true);
        message.setContent("Test_async");
        messageRepository.save(message);

        kafkaTemplate.send(TOPIC, message.getContent());


        /* unschedule or delete after job gets executed */

        try {
            context.getScheduler().deleteJob(new JobKey(messageId));

            TriggerKey triggerKey = new TriggerKey(messageId);

            context.getScheduler().unscheduleJob(triggerKey);

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}