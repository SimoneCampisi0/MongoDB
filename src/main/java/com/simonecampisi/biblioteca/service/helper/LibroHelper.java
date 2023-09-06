package com.simonecampisi.biblioteca.service.helper;

import com.simonecampisi.biblioteca.dto.response.CreateLibroResponse;
import com.simonecampisi.biblioteca.dto.response.ViewLibroResponse;
import com.simonecampisi.biblioteca.model.Libro;
import org.springframework.stereotype.Component;

@Component
public class LibroHelper {
    public ViewLibroResponse buildLibroResponse(Libro l) {
        ViewLibroResponse viewLibroResponse = new ViewLibroResponse();
        viewLibroResponse.setId(l.getId());
        viewLibroResponse.setTitolo(l.getTitolo());
        viewLibroResponse.setAutore(l.getAutore());
        viewLibroResponse.setAnnoProduzione(l.getAnnoProduzione());
        viewLibroResponse.setTaken(l.isTaken());
        return viewLibroResponse;
    }

    public CreateLibroResponse buildCreateLibroResponse(Libro l) {
        CreateLibroResponse response = new CreateLibroResponse();
        response.setId(l.getId());
        response.setTitolo(l.getTitolo());
        response.setAutore(l.getAutore());
        response.setAnnoProduzione(l.getAnnoProduzione());
        response.setTaken(l.isTaken());
        return response;
    }
}
