package com.example.aurastream.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchCreatedResponse {

    private List<StockResponse> created;

}
