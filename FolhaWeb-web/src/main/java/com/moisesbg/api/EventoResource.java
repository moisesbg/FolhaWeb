package com.moisesbg.api;

import com.moisesbg.model.evento.Evento;
import com.moisesbg.model.evento.EventoService;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.List;

@Path("/eventos")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EventoResource {
    @Inject
    private EventoService eventoService;

    @GET
    public List<Evento> listar(@QueryParam("filterValue") String filterValue) {

        List<Evento> eventos;

        if(StringUtils.isEmpty(filterValue)) {
            eventos = eventoService.listar();
        } else {
            eventos = eventoService.listarPorNome(filterValue);
        }

        return eventos;
    }

    @GET
    @Path("/{id}")
    public Evento eventoPorId(@PathParam("id") Integer id) {
        return eventoService.listarPorId(id);
    }

    @POST
    public Response inserir(Evento evento) {
        eventoService.inserir(evento);
        return Response
                .created(UriBuilder.fromResource(EventoResource.class).build(evento))
                .build();
    }

    @PUT
    public Response atualizar(Evento evento) {
        return Response
                .status(Response.Status.OK)
                .entity(eventoService.atualizar(evento))
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response excluir(@PathParam("id") Integer id){
        eventoService.remover(id);
        return Response
                .noContent()
                .status(Response.Status.NO_CONTENT)
                .build();
    }
}
