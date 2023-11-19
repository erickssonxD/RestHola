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
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/hola")
public class HolaService {

    public class HolaResponse {

        private String message;

        public HolaResponse(String message) {
            this.message = message;
        }

        // You can add getters/setters if needed
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getMsg(@PathParam("id") int id) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        try {
            JsonReader reader = new JsonReader(new FileReader("productos.json"));
            List<Insumo> insumos = gson.fromJson(reader, new TypeToken<List<Insumo>>() {
            }.getType());

            String jsonResponse = gson.toJson(insumos); // Convert to JSON explicitly

            return Response.status(200).entity(jsonResponse).build();
        } catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
            HolaResponse response = new HolaResponse("error reading 'productos.json'");
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
