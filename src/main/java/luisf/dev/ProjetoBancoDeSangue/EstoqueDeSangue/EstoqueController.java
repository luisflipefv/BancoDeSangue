package luisf.dev.ProjetoBancoDeSangue.EstoqueDeSangue;

import luisf.dev.ProjetoBancoDeSangue.Doador.TipoSanguineo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoques")
public class EstoqueController {

    private EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    //get - listar todos os estoques
    @GetMapping
    public ResponseEntity<List<EstoqueModel>> listarTodosEstoques(){
        List<EstoqueModel> lista = estoqueService.listarTodos();
        return ResponseEntity.ok().body(lista);
    }


    //get - listar estoque por id
    @GetMapping("/{tipo}")
    public ResponseEntity<EstoqueModel> listarEstoquePorTipo(@PathVariable TipoSanguineo tipo){
        EstoqueModel TipoEncontrado = estoqueService.listarPorTipo(tipo);
        return ResponseEntity.ok().body(TipoEncontrado);
    }

    @PostMapping("/saida")
    public ResponseEntity<Void> registrarSaida(@RequestBody SaidaEstoqueDTO saidaDTO){
        estoqueService.decrementarEstoque(saidaDTO.getTipoSanguineo(), saidaDTO.getQuantidade());
        return ResponseEntity.ok().build();
    }

}
