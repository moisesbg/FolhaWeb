package com.moisesbg.api;

import com.moisesbg.model.departamento.Departamento;
import com.moisesbg.model.departamento.DepartamentoService;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.List;

@Path("/departamentos")
@RequestScoped
@Consumes(value = MediaType.APPLICATION_JSON)
@Produces(value = MediaType.APPLICATION_JSON)
public class DepartamentoResource {

    @Inject
    private DepartamentoService service;

    @GET
    public List<Departamento> listar(@QueryParam("filterValue") String filterValue) {
        List<Departamento> departamentos;

        if(StringUtils.isEmpty(filterValue)) {
            departamentos = service.listar();
        } else{
            departamentos = service.listarPorNome(filterValue);
        }

        return departamentos;
    }

    @GET
    @Path("/{id}")
    public Departamento departamentoPorId(@PathParam("id") Integer id) {
        return service.listarPorId(id);
    }

    @POST
    public Response inserir(Departamento departamento) {
        service.inserir(departamento);
        return Response
                .created(UriBuilder.fromResource(DepartamentoResource.class).build(departamento))
                .build();
    }


    @PUT
    public Response atualizar(Departamento departamento) {
        return Response
                .status(Response.Status.OK)
                .entity(service.atualizar(departamento))
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response excluir(@PathParam("id") Integer id){
        service.remover(id);
        return Response
                .noContent()
                .status(Response.Status.NO_CONTENT)
                .build();
    }
}
