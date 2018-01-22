package com.moisesbg.api;

import com.moisesbg.model.funcionario.Funcionario;
import com.moisesbg.model.funcionario.FuncionarioService;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.List;

@Path("/funcionarios")
@RequestScoped
@Produces(value = MediaType.APPLICATION_JSON)
@Consumes(value = MediaType.APPLICATION_JSON)
public class FuncionarioResource {
    @Inject
    FuncionarioService funcionarioService;

    @GET
    public List<Funcionario> listar(@QueryParam("filterValue") String filterValue) {

        List<Funcionario> funcionarios;

        if(StringUtils.isEmpty(filterValue)) {
            funcionarios = funcionarioService.listar();
        } else {
            funcionarios = funcionarioService.listarPorNome(filterValue);
        }

        return funcionarios;
    }

    @GET
    @Path("/{id}")
    public Funcionario funcionarioPorId(@PathParam("id") Integer id) {
        return funcionarioService.listarPorId(id);
    }

    @POST
    public Response inserir(Funcionario funcionario) {

        funcionarioService.inserir(funcionario);
        return Response
                .created(UriBuilder.fromResource(FuncionarioResource.class).build(funcionario))
                .build();
    }

    @PUT
    public Response atualizar(Funcionario funcionario) {
        return Response
                .status(Response.Status.OK)
                .entity(funcionarioService.atualizar(funcionario))
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response excluir(@PathParam("id") Integer id){
        funcionarioService.remover(id);
        return Response
                .noContent()
                .status(Response.Status.NO_CONTENT)
                .build();
    }
}
