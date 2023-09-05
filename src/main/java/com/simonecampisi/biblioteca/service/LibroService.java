package com.simonecampisi.biblioteca.service;

import com.simonecampisi.biblioteca.dao.LibroRepo;
import com.simonecampisi.biblioteca.dto.request.CreateLibroRequest;
import com.simonecampisi.biblioteca.model.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroService {
    @Autowired
    private LibroRepo libroRepo;
    public void createLibro(CreateLibroRequest request) {
        libroRepo.insert(Libro.builder().titolo(request.getTitolo()).preso(request.isPreso()).annoProduzione(request.getAnnoProduzione()).autore(request.getAutore()).build());
    }
}
