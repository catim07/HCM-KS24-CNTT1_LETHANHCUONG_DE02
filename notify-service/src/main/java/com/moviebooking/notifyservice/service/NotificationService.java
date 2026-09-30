package com.moviebooking.notifyservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationService {

    public void sendBookingConfirmation(String customerEmail) {
        if (customerEmail == null || customerEmail.isBlank()) {
            return;
        }

        String subject = "Xác nhận đặt vé xem phim";
        String content = "Đặt vé thành công. Yêu cầu đặt vé xem phim của bạn đã được hệ thống tiếp nhận";

        log.info("Sending notification to [{}]: Subject: [{}], Content: [{}]", customerEmail, subject, content);
    }
}
