package com.moisesbg.api;

import com.moisesbg.model.calculo.folha.Folha;
import com.moisesbg.model.calculo.folha.FolhaService;
import com.moisesbg.model.calculo.processo.ProcessoCalculoFolhaService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;

@Path("/folhas")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FolhaResource {
    @Inject
    ProcessoCalculoFolhaService calculoService;

    @Inject
    FolhaService folhaService;

    @GET
    @Path("/{competencia}/funcionario/{funcionarioId}")
    public Folha listarFolhas(@PathParam("competencia") String competencia,
                                    @PathParam("funcionarioId") Integer funcionarioId) {

        Folha folha = folhaService.listarFolhaFuncionario(LocalDate.parse(competencia), funcionarioId);
        return folha;
    }

    @GET
    @Path("/calcular/{competencia}")
    public Response calcular(@PathParam("competencia") String competencia,
                             @QueryParam("dataPagamento") String dataPagamento) {

        calculoService.executarProcesso(LocalDate.parse(competencia), LocalDate.parse(dataPagamento));

        return Response.ok().build();
    }

}
