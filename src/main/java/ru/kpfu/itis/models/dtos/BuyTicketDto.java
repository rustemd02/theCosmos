package ru.kpfu.itis.models.dtos;

import lombok.Data;

@Data
public class BuyTicketDto {
    private String seanceId;
    private Boolean useCosmostar;
}
