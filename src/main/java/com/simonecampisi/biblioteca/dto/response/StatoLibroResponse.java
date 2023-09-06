package com.simonecampisi.biblioteca.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class StatoLibroResponse {
    Map<String, Boolean> responseMap;

    public StatoLibroResponse(Boolean stato) {
        responseMap = new HashMap<>();
        responseMap.put("Stato: ", stato);
    }
}
