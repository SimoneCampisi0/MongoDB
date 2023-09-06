package com.simonecampisi.biblioteca.dto.response;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class StatoResponse {
    Map<String, Boolean> responseMap;

    public StatoResponse(Boolean stato) {
        responseMap = new HashMap<>();
        responseMap.put("Stato: ", stato);
    }
}
