package PEP3.testService.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import PEP3.testService.Entities.TestEntity;
import PEP3.testService.Repositories.TestRepository;
import PEP3.testService.Services.TestService;
import java.util.ArrayList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/test")

public class TestController {

    @Autowired
    TestService testService;

    @Autowired
    TestRepository testRepository;

    @GetMapping("/preguntas")
    public ResponseEntity<ArrayList<TestEntity>> obtenerPreguntas()
    {
        ArrayList<TestEntity> preguntas = testRepository.getAll();
        if(preguntas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(preguntas);
    }


    @GetMapping("/preguntas/{dif}")
    public ResponseEntity<ArrayList<TestEntity>> obtenerPreguntasDif(@PathVariable("dif") Integer dif)
    {
        ArrayList<TestEntity> preguntas = testRepository.findByDif(dif);
        if(preguntas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(preguntas);
    }

    @GetMapping("/pregunta/{id}")
    public ResponseEntity<TestEntity> obtenerPregunta(@PathVariable("id") Integer id)
    {
        TestEntity pregunta = testRepository.findById(id);
        if(pregunta == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pregunta);
    }

    @PostMapping("/pregunta")
    public ResponseEntity<Boolean> guardarPregunta(@RequestBody TestEntity pregunta)
    {
        testRepository.save(pregunta);
        return ResponseEntity.ok(true);
    }

    @PostMapping("/respuesta")
    public ResponseEntity<Integer> verificarRespuesta(@RequestBody ArrayList<String> respuesta, @RequestBody ArrayList<Integer> id)
    {
        int puntaje = testService.verificarRespuestas(id, respuesta);
        return ResponseEntity.ok(puntaje);
    }

}
