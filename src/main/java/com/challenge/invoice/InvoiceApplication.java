package com.challenge.invoice;

import com.challenge.invoice.config.BoletoBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class InvoiceApplication implements CommandLineRunner {

    @Autowired
    private BoletoBuilder boletoBuilder;

    public static void main(String[] args) {
        SpringApplication.run(InvoiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        boletoBuilder.runOnStartUp();
    }
}
