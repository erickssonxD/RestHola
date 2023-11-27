/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iplacex.resthola;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import javax.annotation.PostConstruct;

@Path("/camping")
public class CampingService {

    private static List<Usuario> usuariosList = new ArrayList<>();
    private static List<Representante> representantesList = new ArrayList<>();

    @PostConstruct
    public void initialize() {
        usuariosList.add(new Usuario("ltrace", "user1@example.com", "John", "Trace"));
        usuariosList.add(new Usuario("lstewart", "user2@example.com", "Linda", "Stewart"));
        usuariosList.add(new Usuario("pcol", "user3@example.com", "Phil", "Col"));
        usuariosList.add(new Usuario("ssto", "user4@example.com", "Stan", "Sto"));

        representantesList.add(new Representante("1-9", "John", "Trace", "+5691"));
        representantesList.add(new Representante("2-7", "Linda", "Stewart", "+5692"));
        representantesList.add(new Representante("3-5", "Phil", "Col", "+5693"));
        representantesList.add(new Representante("4-3", "Stan", "Sto", "+5694"));

    }

    public class ResponseRequired {

        private int statusCode;
        private Object body;

        public ResponseRequired(int statusCode, Object body) {
            this.statusCode = statusCode;
            this.body = body;
        }
    }

    @GET
    @Path("/usuarios")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsuarios() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        ResponseRequired requiredResponse = new ResponseRequired(200, usuariosList);
        String jsonResponse = gson.toJson(requiredResponse);
        return Response.status(200).entity(jsonResponse).build();
    }

    @POST
    @Path("/usuarios")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUsuario(String requestBody) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Usuario nuevoUsuario = gson.fromJson(requestBody, Usuario.class);
        usuariosList.add(nuevoUsuario);
        ResponseRequired requiredResponse = new ResponseRequired(200, "Usuario added successfully");
        String jsonResponse = gson.toJson(requiredResponse);
        return Response.status(200).entity(jsonResponse).build();
    }

    @GET
    @Path("/representantes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRepresentantes() {
        return Response.status(200).entity(representantesList).build();
    }

    @POST
    @Path("/representantes")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearRepresentante(String nuevoRepresentante) {
        // Add representative creation logic here
        return Response.status(201).entity("Representante created successfully").build();
    }

}
