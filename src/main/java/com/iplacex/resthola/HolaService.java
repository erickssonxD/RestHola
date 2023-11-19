/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iplacex.resthola;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.google.gson.stream.JsonReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import javax.ws.rs.DELETE;

@Path("/hola")
public class HolaService {

    public class ErrorResponse {

        private String message;

        public ErrorResponse(String message) {
            this.message = message;
        }
    }

    /*
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createInsumo(Insumo insumo) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

        try {
            String jsonResponse = gson.toJson(insumo);
            return Response.status(201).entity(jsonResponse).build();
        } catch (Exception e) {
            ErrorResponse response = new ErrorResponse("Error processing the request");
            String jsonResponse = gson.toJson(response);
            return Response.status(500).entity(jsonResponse).build();
        }
    }
     */
    @DELETE
    @Path("/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarInsumo(@PathParam("codigo") int codigo) throws IOException {

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        try {
            JsonReader reader = new JsonReader(new FileReader("productos.json"));
            List<Insumo> insumos = gson.fromJson(reader, new TypeToken<List<Insumo>>() {
            }.getType());

            boolean removed = false;
            Iterator<Insumo> iterator = insumos.iterator();
            while (iterator.hasNext()) {
                Insumo insumo = iterator.next();
                if (insumo.getCodigo() == codigo) {
                    iterator.remove();
                    removed = true;
                }
            }

            if (removed) {
                try (Writer writer = new FileWriter("productos.json")) {
                    gson.toJson(insumos, writer);
                }

                ErrorResponse response = new ErrorResponse("remove successfully");
                String jsonResponse = gson.toJson(response);
                return Response.status(200).entity(jsonResponse).build();
            } else {
                ErrorResponse response = new ErrorResponse("product code does not exist");
                String jsonResponse = gson.toJson(response);
                return Response.status(409).entity(jsonResponse).build();
            }
        } catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
            ErrorResponse response = new ErrorResponse("error reading 'productos.json'");
            String jsonResponse = gson.toJson(response);
            return Response.status(500).entity(jsonResponse).build();
        }
    }

    @GET
    @Path("/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarInsumo(@PathParam("codigo") int codigo) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        try {
            JsonReader reader = new JsonReader(new FileReader("productos.json"));
            List<Insumo> insumos = gson.fromJson(reader, new TypeToken<List<Insumo>>() {
            }.getType());

            Insumo foundInsumo = null;

            for (Insumo insumo : insumos) {
                if (insumo.getCodigo() == codigo) {
                    foundInsumo = insumo;
                    break;
                }
            }

            if (foundInsumo != null) {
                String jsonResponse = gson.toJson(foundInsumo); // Convert to JSON explicitly
                return Response.status(200).entity(jsonResponse).build();
            } else {
                ErrorResponse response = new ErrorResponse("Not found");
                String jsonResponse = gson.toJson(response); // Convert to JSON explicitly
                return Response.status(400).entity(jsonResponse).build();
            }
        } catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
            ErrorResponse response = new ErrorResponse("error reading 'productos.json'");
            String jsonResponse = gson.toJson(response);
            return Response.status(500).entity(jsonResponse).build();
        }
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarInsumos() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        try {
            JsonReader reader = new JsonReader(new FileReader("productos.json"));
            List<Insumo> insumos = gson.fromJson(reader, new TypeToken<List<Insumo>>() {
            }.getType());

            String jsonResponse = gson.toJson(insumos); // Convert to JSON explicitly
            return Response.status(200).entity(jsonResponse).build();
        } catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
            ErrorResponse response = new ErrorResponse("error reading 'productos.json'");
            String jsonResponse = gson.toJson(response);
            return Response.status(500).entity(jsonResponse).build();
        }
    }

    /*
    @GET
    public String consultarInsumos() {
        String salida;
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Response r = new Response();

        try {
            JsonReader reader = new JsonReader(new FileReader("productos.json"));
            List<Insumo> insumos = gson.fromJson(reader, new TypeToken<List<Insumo>>() {
            }.getType());
            r.setStatus(200);
            r.setData(insumos);
        } catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
            r.setStatus(500);
            r.setData("Error reading 'productos.json'");
        }

        salida = gson.toJson(r);
        return salida;
    }

     */
}
