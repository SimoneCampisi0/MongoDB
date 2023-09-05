package com.simonecampisi.biblioteca.dao;

import com.simonecampisi.biblioteca.model.Libro;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepo extends MongoRepository<Libro, String> {
}
