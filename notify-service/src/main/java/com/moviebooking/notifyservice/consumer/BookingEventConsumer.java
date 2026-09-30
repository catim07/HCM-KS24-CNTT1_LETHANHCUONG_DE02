package com.moviebooking.notifyservice.consumer;

import com.moviebooking.notifyservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class BookingEventConsumer {

    private final NotificationService notificationService;

    @KafkaListener(topics = "booking-created", groupId = "notify-group")
    public void consumeBookingCreatedEvent(String customerEmail) {
        log.info("Nhận được thông điệp Kafka từ topic [booking-created]: [{}]", customerEmail);

        if (customerEmail == null || customerEmail.isBlank()) {
            log.warn("Thông điệp không hợp lệ: customerEmail bị null hoặc trống. Bỏ qua thông điệp.");
            return;
        }

        notificationService.sendBookingConfirmation(customerEmail);
    }
}
