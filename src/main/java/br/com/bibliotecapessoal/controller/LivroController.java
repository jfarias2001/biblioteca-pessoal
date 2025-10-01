package br.com.bibliotecapessoal.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import br.com.bibliotecapessoal.model.Livro;
import br.com.bibliotecapessoal.repository.LivroRepository;


@RestController
@RequestMapping("/api/livros")
public class LivroController {


    @Autowired
    private LivroRepository repo;


    @GetMapping
    public List<Livro> listar() {
        return repo.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscar(@PathVariable Long id) {
        Optional<Livro> opt = repo.findById(id);
        return opt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Livro> criar(@RequestBody Livro livro) {
        Livro salvo = repo.save(livro);
        return ResponseEntity.status(201).body(salvo);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizar(@PathVariable Long id, @RequestBody Livro livro) {
        return repo.findById(id).map(existing -> {
            existing.setTitulo(livro.getTitulo());
            existing.setAutor(livro.getAutor());
            existing.setAnoPublicacao(livro.getAnoPublicacao());
            existing.setLido(livro.isLido());
            Livro atualizado = repo.save(existing);
            return ResponseEntity.ok(atualizado);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return repo.findById(id).map(existing -> {
            repo.deleteById(id);
            return ResponseEntity.noContent().<Void>build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}