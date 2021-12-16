package com.wdk.redis.runner;

import com.wdk.redis.consumer.InvoiceMessageConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

/**
 * @author: pengmin
 * @date: 2021/12/16 11:47
 */
//@Component
public class MessageConsumerRunner implements CommandLineRunner {

    @Autowired
    private InvoiceMessageConsumer invoiceMessageConsumer;

    @Override
    public void run(String... args) throws Exception {
        new Thread(() -> invoiceMessageConsumer.consumeInvoiceMessage()).start();
    }
}
