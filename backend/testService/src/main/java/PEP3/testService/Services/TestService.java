package PEP3.testService.Services;

import PEP3.testService.Entities.TestEntity;
import PEP3.testService.Repositories.TestRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TestService {

    TestRepository testRepository;

    public void guardarDataDB(TestEntity data)
    {
        testRepository.save(data);
    }

    public void guardarData(String pregunta, String respuesta, Integer dif)
    {
        TestEntity newData = new TestEntity();
        newData.setPregunta(pregunta);
        newData.setRespuesta(respuesta);
        newData.setDificultad(dif);
        guardarDataDB(newData);
    }

    public Integer verificarRespuestas(ArrayList<Integer> id_test, ArrayList<String> respuestas)
    {
        ArrayList<Boolean> revision = new ArrayList<>();
        for (int i = 0; i < 4; i++){
            TestEntity pregunta = testRepository.findById(id_test.get(i));
            String respuesta = respuestas.get(i);
            if (pregunta != null)
            {
                revision.add(pregunta.getRespuesta().equals(respuesta));
            }
            else{
                System.out.println("Pregunta no encontrada");
            }

        }

        int puntaje = 0;
        for (Boolean aBoolean : revision) {
            if (aBoolean) {
                puntaje += 1;
            }
        }

        return puntaje;
    }


}
