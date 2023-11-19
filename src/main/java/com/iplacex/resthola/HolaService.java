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
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/hola")
public class HolaService {

    public class ErrorResponse {

        private String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        // You can add getters/setters if needed
    }

    @GET
    @Path("/{codigo}")
    @Produces("application/json")
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
                    break; // Exit loop once the desired insumo is found
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
    @Produces("application/json")
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
