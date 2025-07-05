package luisf.dev.ProjetoBancoDeSangue.Doador;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/doadores")
public class DoadorController {

    private DoadorService doadorService;
    public DoadorController(DoadorService doadorService) {
        this.doadorService = doadorService;
    }

    //post - cria doador
    @PostMapping
    public ResponseEntity<DoadorModel> criaDoador(@RequestBody DoadorModel doador){
        DoadorModel doadorCriado = doadorService.criaDoador(doador);
        URI location = URI.create("/doadores/" + doadorCriado.getId());
        return ResponseEntity.created(location).body(doadorCriado);
    }

    //get - listar todos doadores
    @GetMapping
    public ResponseEntity<List<DoadorModel>> listarTodosDoadores(){
        List<DoadorModel> lista = doadorService.consultaTodosDoadores();
        return ResponseEntity.ok().body(lista);
    }

    //get - listar doador por id
    @GetMapping("/{id}")
    public ResponseEntity<DoadorModel> listarDoadorPorId(@PathVariable Long id){
        DoadorModel doadorEncontrado = doadorService.consultaDoadorPorId(id);
        return ResponseEntity.ok().body(doadorEncontrado);
    }

    //put - alterar doador
    @PutMapping("/{id}")
    public ResponseEntity<DoadorModel> alteraDoador(@PathVariable Long id, @RequestBody DoadorModel doador){
        DoadorModel doadorAtualizado = doadorService.alteraDoador(id, doador);
        return ResponseEntity.ok().body(doadorAtualizado);
    }


    //delete - deletar doador
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDoador(@PathVariable Long id){
        doadorService.deletaDoador(id);
        return ResponseEntity.noContent().build();
    }

}
