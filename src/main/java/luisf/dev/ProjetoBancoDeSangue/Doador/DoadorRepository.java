package luisf.dev.ProjetoBancoDeSangue.Doador;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DoadorRepository extends JpaRepository<DoadorModel, Long> {
    boolean existsByCpf(String cpf);
}
