package br.edu.ufersa.pw.todo.todoAPI.todo;

import br.edu.ufersa.pw.todo.todoAPI.auth.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUsuId(Long userId);
    List<Todo> findByUsu(Usuario usu);
    Optional<Todo> findByIdAndUsuId(Long id, Long userId);
    boolean existsByItemAndUsuId(String item,Long userId);
}
