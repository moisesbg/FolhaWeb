package com.moisesbg.test;

import com.moisesbg.dao.GenericDao;
import com.moisesbg.model.cargo.Cargo;
import com.moisesbg.model.cargo.CargoService;
import com.moisesbg.util.Producers;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

@RunWith(CdiRunner.class)
@AdditionalClasses(Producers.class)
public class CargoTest {

    //CONFIGURAR O ARQUILIAN PARA SIMULAR O AMBIENTE DO WILDFLY

//    @Mock
//    @Produces
//    @ProducesAlternative

    @Inject
    private GenericDao<Cargo> dao;

    @Inject
    private CargoService service;

    @Test
    public void naoDeveInserirCargoComNomeNulo() {
        Cargo cargo = new Cargo();
        cargo.setNome("testeMoises");

        service.inserir(cargo);
        int i = 0;
    }
}
