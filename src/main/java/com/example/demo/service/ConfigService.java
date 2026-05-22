package com.example.demo.service;

import com.example.demo.dto.AppConfigDTO;
import com.example.demo.dto.NotificationConfigDTO;
import com.example.demo.dto.UserConfigDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfigService {

    public AppConfigDTO getAppConfig() {

        return new AppConfigDTO(
                "Comida Consciente",
                "1.0.0",
                "suporte@comidaconsciente.com"
        );
    }

    public UserConfigDTO updateUserConfig(UserConfigDTO dto) {

        return dto;
    }

    public NotificationConfigDTO getNotificationConfig() {

        NotificationConfigDTO dto = new NotificationConfigDTO();

        dto.setDonationNotifications(true);
        dto.setAppointmentNotifications(true);
        dto.setMarketingNotifications(false);

        return dto;
    }

    public NotificationConfigDTO updateNotificationConfig(
            NotificationConfigDTO dto
    ) {

        return dto;
    }
}