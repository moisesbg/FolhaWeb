package com.moisesbg.api;

import com.moisesbg.model.cargo.Cargo;
import com.moisesbg.model.cargo.CargoService;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.List;

@Path("/cargos")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CargoResource {

    @Inject
    private CargoService cargoService;

    @GET
    public List<Cargo> listar(@QueryParam("filterValue") String filterValue) {

        List<Cargo> cargos;

        if(StringUtils.isEmpty(filterValue)) {
            cargos = cargoService.listar();
        } else {
            cargos = cargoService.listarPorNome(filterValue);
        }

        return cargos;
    }

    @GET
    @Path("/{id}")
    public Cargo cargoPorId(@PathParam("id") Integer id) {
        return cargoService.listarPorId(id);
    }

    @POST
    public Response inserir(Cargo cargo) {
        cargoService.inserir(cargo);
        return Response
                .created(UriBuilder.fromResource(CargoResource.class).build(cargo))
                .build();
    }

    @PUT
    public Response atualizar(Cargo cargo) {
        return Response
                .status(Response.Status.OK)
                .entity(cargoService.atualizar(cargo))
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response excluir(@PathParam("id") Integer id){
        cargoService.remover(id);
        return Response
                .noContent()
                .status(Response.Status.NO_CONTENT)
                .build();
    }
}










