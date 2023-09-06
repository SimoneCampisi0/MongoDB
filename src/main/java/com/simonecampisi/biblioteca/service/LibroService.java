package com.simonecampisi.biblioteca.service;

import com.simonecampisi.biblioteca.dao.LibroRepo;
import com.simonecampisi.biblioteca.dto.request.CreateLibroRequest;
import com.simonecampisi.biblioteca.dto.response.CreateLibroResponse;
import com.simonecampisi.biblioteca.dto.response.StatoResponse;
import com.simonecampisi.biblioteca.dto.response.ViewLibroResponse;
import com.simonecampisi.biblioteca.model.Libro;
import com.simonecampisi.biblioteca.service.helper.LibroHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class LibroService {
    @Autowired
    private LibroRepo libroRepo;

    @Autowired
    private LibroHelper libroHelper;
    public CreateLibroResponse aggiungiLibro(CreateLibroRequest request) {
        if(!Objects.isNull(libroRepo.findByTitolo(request.getTitolo()))) {
            return null;
        } else {
            return libroHelper.buildCreateLibroResponse(libroRepo.save(Libro.builder()
                .titolo(request.getTitolo())
                .isTaken(false)
                .annoProduzione(request.getAnnoProduzione())
                .autore(request.getAutore())
                .build()));
        }
    }

    public List<ViewLibroResponse> listaLibri() {
        return libroRepo.findAll().stream()
                .filter(l -> !l.isTaken())
                .map(libro -> libroHelper.buildLibroResponse(libro))
                .toList();
    }

    public StatoResponse rimuoviLibro(String nomeLibro) {
        StatoResponse statoResponse;
        if (!Objects.isNull(libroRepo.findByTitolo(nomeLibro))) {
            libroRepo.deleteLibroByTitolo(nomeLibro);
            statoResponse = new StatoResponse(true);
        } else {
            statoResponse = new StatoResponse(false);
        }
        return statoResponse;
    }

    public StatoResponse richiediLibro(String nomeLibro) {
        Libro l = libroRepo.findByTitolo(nomeLibro);
        StatoResponse statoResponse;
        if(!Objects.isNull(l)) {
            if(l.isTaken())  {
                statoResponse = new StatoResponse(false);
            } else {
                l.setTaken(true);
                libroRepo.save(l);
                statoResponse = new StatoResponse(true);
            }
        } else {
            statoResponse = new StatoResponse(false);
        }
        return statoResponse;
    }

    public StatoResponse restituisciLibro(String nomeLibro) {
        Libro l = libroRepo.findByTitolo(nomeLibro);
        StatoResponse statoResponse;
        if(!Objects.isNull(l)) {
            if(l.isTaken()) {
                l.setTaken(false);
                libroRepo.save(l);
                statoResponse = new StatoResponse(true);
            } else {
                statoResponse = new StatoResponse(false);
            }
        } else {
            statoResponse = new StatoResponse(false);
        }
        return statoResponse;
    }
}
