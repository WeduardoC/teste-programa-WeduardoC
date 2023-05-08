package br.ufma.sppg.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.ufma.sppg.repo.DocenteRepository;
import br.ufma.sppg.repo.ProgramaRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
public class ProgramaTest {
    
    @Autowired
    ProgramaRepository repository;

    @Autowired
    DocenteRepository repo;

    @Test
    public void deveSalvarPrograma(){
        Programa prog = Programa.builder().nome("programa 1").build();

        Programa salvo = repository.save(prog);

        Assertions.assertNotNull(salvo);
        Assertions.assertEquals(prog.getId(),salvo.getId());
        Assertions.assertEquals(prog.getNome(),salvo.getNome());
    }

    @Test
    public void deveSalvarProgramaComDocente() throws ParseException {
        //cenário
        Docente novDocente = Docente.builder().nome("Geraldo Braz Junior")
                                        .lattes("123")
                                        .dataAtualizacao(new SimpleDateFormat("dd/MM/yyyy").parse("23/04/2023"))
                                        .build();
        Programa novoPPg = Programa.builder().nome("PPGCC").build();
        
        Programa progSalvo = repository.save(novoPPg);
        Docente docSalvo = repo.save(novDocente);

        //ação
        ArrayList<Docente> docentes = new ArrayList<>();
        docentes.add(docSalvo);
        progSalvo.setDocentes(docentes);
        
        Programa progSalvo2 = repository.save(progSalvo);

        //teste
        Assertions.assertNotNull(progSalvo2);
        Assertions.assertEquals(progSalvo2.getDocentes().size(), 1);

    }
}
