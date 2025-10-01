package br.com.bibliotecapessoal.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import br.com.bibliotecapessoal.model.Livro;


public interface LivroRepository extends JpaRepository<Livro, Long> {
}