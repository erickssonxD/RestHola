/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iplacex.resthola;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Iterator;
import javax.annotation.PostConstruct;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;

@Path("/camping")
public class CampingService {

    private static List<Usuario> usuariosList = new ArrayList<>();
    private static List<Representante> representantesList = new ArrayList<>();
    private static List<TipoAlojamiento> tipoAlojamientosList = new ArrayList<>();
    private static List<TipoVehiculo> tipoVehiculosList = new ArrayList<>();
    private static List<Alojamiento> alojamientosList = new ArrayList<>();

    private static boolean didRun = false;

    @PostConstruct
    public void initialize() {
        if (didRun == false) {
            usuariosList.add(new Usuario("ltrace", "user1@example.com", "John", "Trace"));
            usuariosList.add(new Usuario("lstewart", "user2@example.com", "Linda", "Stewart"));
            usuariosList.add(new Usuario("pcol", "user3@example.com", "Phil", "Col"));
            usuariosList.add(new Usuario("ssto", "user4@example.com", "Stan", "Sto"));

            representantesList.add(new Representante("1-9", "John", "Trace", "+5691"));
            representantesList.add(new Representante("2-7", "Linda", "Stewart", "+5692"));
            representantesList.add(new Representante("3-5", "Phil", "Col", "+5693"));
            representantesList.add(new Representante("4-3", "Stan", "Sto", "+5694"));

            tipoAlojamientosList.add(new TipoAlojamiento(1, "Camping"));
            tipoAlojamientosList.add(new TipoAlojamiento(2, "Cabaña"));
            tipoAlojamientosList.add(new TipoAlojamiento(3, "Departamento"));

            tipoVehiculosList.add(new TipoVehiculo(1, "SUV"));
            tipoVehiculosList.add(new TipoVehiculo(2, "Sedan"));
            tipoVehiculosList.add(new TipoVehiculo(3, "Hatchback"));

            didRun = true;
        }
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
    @Path("/tipo-alojamientos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTiposAlojamientos() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        ResponseRequired requiredResponse = new ResponseRequired(200, tipoAlojamientosList);
        String jsonResponse = gson.toJson(requiredResponse);
        return Response.status(200).entity(jsonResponse).build();
    }

    @POST
    @Path("/tipo-alojamientos")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTipoAlojamiento(String requestBody) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        TipoAlojamiento nuevoTipoAlojamiento = gson.fromJson(requestBody, TipoAlojamiento.class);

        if (nuevoTipoAlojamiento.getIdTipoAlojamiento() <= 0) {
            ResponseRequired requiredResponse = new ResponseRequired(400, "IdTipoAlojamiento must be a positive integer");
            String jsonResponse = gson.toJson(requiredResponse);
            return Response.status(400).entity(jsonResponse).build();
        }

        boolean exists = false;
        for (TipoAlojamiento tipo : tipoAlojamientosList) {
            if (tipo.getIdTipoAlojamiento() == nuevoTipoAlojamiento.getIdTipoAlojamiento()) {
                exists = true;
                break;
            }
        }

        if (!exists) {
            tipoAlojamientosList.add(nuevoTipoAlojamiento);
            ResponseRequired requiredResponse = new ResponseRequired(200, "TipoAlojamiento added successfully");
            String jsonResponse = gson.toJson(requiredResponse);
            return Response.status(200).entity(jsonResponse).build();
        } else {
            ResponseRequired requiredResponse = new ResponseRequired(409, "IdTipoAlojamiento already exists");
            String jsonResponse = gson.toJson(requiredResponse);
            return Response.status(409).entity(jsonResponse).build();
        }
    }

    @GET
    @Path("/tipo-vehiculos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTipoVehiculos() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        ResponseRequired requiredResponse = new ResponseRequired(200, tipoVehiculosList);
        String jsonResponse = gson.toJson(requiredResponse);
        return Response.status(200).entity(jsonResponse).build();
    }

    @POST
    @Path("/tipo-vehiculos")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTipoVehiculo(String requestBody) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        TipoVehiculo nuevoTipoVehiculo = gson.fromJson(requestBody, TipoVehiculo.class);

        if (nuevoTipoVehiculo.getIdTipoVehiculo() <= 0) {
            ResponseRequired requiredResponse = new ResponseRequired(400, "IdTipoVehiculo must be a positive integer");
            String jsonResponse = gson.toJson(requiredResponse);
            return Response.status(400).entity(jsonResponse).build();
        }

        boolean exists = false;
        for (TipoVehiculo tipo : tipoVehiculosList) {
            if (tipo.getIdTipoVehiculo() == nuevoTipoVehiculo.getIdTipoVehiculo()) {
                exists = true;
                break;
            }
        }

        if (!exists) {
            tipoVehiculosList.add(nuevoTipoVehiculo);
            ResponseRequired requiredResponse = new ResponseRequired(200, "TipoVehiculo added successfully");
            String jsonResponse = gson.toJson(requiredResponse);
            return Response.status(200).entity(jsonResponse).build();
        } else {
            ResponseRequired requiredResponse = new ResponseRequired(409, "IdTipoVehiculo already exists");
            String jsonResponse = gson.toJson(requiredResponse);
            return Response.status(409).entity(jsonResponse).build();
        }
    }

    @DELETE
    @Path("/tipo-vehiculos/{id}")
    public Response removeTipoVehiculoById(@PathParam("id") int id) {
        boolean removed = false;
        for (Iterator<TipoVehiculo> iterator = tipoVehiculosList.iterator(); iterator.hasNext();) {
            TipoVehiculo tipo = iterator.next();
            if (tipo.getIdTipoVehiculo() == id) {
                iterator.remove();
                removed = true;
                break;
            }
        }
        if (removed) {
            ResponseRequired requiredResponse = new ResponseRequired(200, "TipoVehiculo removed successfully");
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            String jsonResponse = gson.toJson(requiredResponse);
            return Response.status(200).entity(jsonResponse).build();
        } else {
            ResponseRequired requiredResponse = new ResponseRequired(404, "TipoVehiculo not found");
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            String jsonResponse = gson.toJson(requiredResponse);
            return Response.status(404).entity(jsonResponse).build();
        }
    }

    @DELETE
    @Path("/tipo-alojamientos/{id}")
    public Response removeTipoAlojamientoById(@PathParam("id") int id) {
        boolean removed = false;
        for (Iterator<TipoAlojamiento> iterator = tipoAlojamientosList.iterator(); iterator.hasNext();) {
            TipoAlojamiento tipo = iterator.next();
            if (tipo.getIdTipoAlojamiento() == id) {
                iterator.remove();
                removed = true;
                break;
            }
        }
        if (removed) {
            ResponseRequired requiredResponse = new ResponseRequired(200, "TipoAlojamiento removed successfully");
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            String jsonResponse = gson.toJson(requiredResponse);
            return Response.status(200).entity(jsonResponse).build();
        } else {
            ResponseRequired requiredResponse = new ResponseRequired(404, "TipoAlojamiento not found");
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            String jsonResponse = gson.toJson(requiredResponse);
            return Response.status(404).entity(jsonResponse).build();
        }
    }

    @GET
    @Path("/tipo-vehiculos/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTipoVehiculoById(@PathParam("id") int id) {
        TipoVehiculo foundTipoVehiculo = null;
        for (TipoVehiculo tipo : tipoVehiculosList) {
            if (tipo.getIdTipoVehiculo() == id) {
                foundTipoVehiculo = tipo;
                break;
            }
        }
        if (foundTipoVehiculo != null) {
            ResponseRequired requiredResponse = new ResponseRequired(200, foundTipoVehiculo);
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            String jsonResponse = gson.toJson(requiredResponse);
            return Response.status(200).entity(jsonResponse).build();
        } else {
            ResponseRequired requiredResponse = new ResponseRequired(404, "TipoVehiculo not found");
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            String jsonResponse = gson.toJson(requiredResponse);
            return Response.status(404).entity(jsonResponse).build();
        }
    }

    @GET
    @Path("/tipo-alojamientos/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTipoAlojamientoById(@PathParam("id") int id) {
        TipoAlojamiento foundTipoAlojamiento = null;
        for (TipoAlojamiento tipo : tipoAlojamientosList) {
            if (tipo.getIdTipoAlojamiento() == id) {
                foundTipoAlojamiento = tipo;
                break;
            }
        }
        if (foundTipoAlojamiento != null) {
            ResponseRequired requiredResponse = new ResponseRequired(200, foundTipoAlojamiento);
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            String jsonResponse = gson.toJson(requiredResponse);
            return Response.status(200).entity(jsonResponse).build();
        } else {
            ResponseRequired requiredResponse = new ResponseRequired(404, "TipoAlojamiento not found");
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            String jsonResponse = gson.toJson(requiredResponse);
            return Response.status(404).entity(jsonResponse).build();
        }
    }

    @PUT
    @Path("/tipo-vehiculos/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTipoVehiculoById(@PathParam("id") int id, String requestBody) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        TipoVehiculo updatedTipoVehiculo = gson.fromJson(requestBody, TipoVehiculo.class);

        if (updatedTipoVehiculo.getIdTipoVehiculo() != id) {
            ResponseRequired requiredResponse = new ResponseRequired(400, "ID in request body does not match ID in URL");
            String jsonResponse = gson.toJson(requiredResponse);
            return Response.status(400).entity(jsonResponse).build();
        }

        boolean updated = false;
        for (int i = 0; i < tipoVehiculosList.size(); i++) {
            TipoVehiculo tipo = tipoVehiculosList.get(i);
            if (tipo.getIdTipoVehiculo() == id) {
                tipoVehiculosList.set(i, updatedTipoVehiculo);
                updated = true;
                break;
            }
        }

        if (updated) {
            ResponseRequired requiredResponse = new ResponseRequired(200, "TipoVehiculo updated successfully");
            String jsonResponse = gson.toJson(requiredResponse);
            return Response.status(200).entity(jsonResponse).build();
        } else {
            ResponseRequired requiredResponse = new ResponseRequired(404, "TipoVehiculo not found");
            String jsonResponse = gson.toJson(requiredResponse);
            return Response.status(404).entity(jsonResponse).build();
        }
    }

    @PUT
    @Path("/tipo-alojamientos/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTipoAlojamientoById(@PathParam("id") int id, String requestBody) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        TipoAlojamiento updatedTipoAlojamiento = gson.fromJson(requestBody, TipoAlojamiento.class);

        if (updatedTipoAlojamiento.getIdTipoAlojamiento() != id) {
            ResponseRequired requiredResponse = new ResponseRequired(400, "ID in request body does not match ID in URL");
            String jsonResponse = gson.toJson(requiredResponse);
            return Response.status(400).entity(jsonResponse).build();
        }

        boolean updated = false;
        for (int i = 0; i < tipoAlojamientosList.size(); i++) {
            TipoAlojamiento tipo = tipoAlojamientosList.get(i);
            if (tipo.getIdTipoAlojamiento() == id) {
                tipoAlojamientosList.set(i, updatedTipoAlojamiento);
                updated = true;
                break;
            }
        }

        if (updated) {
            ResponseRequired requiredResponse = new ResponseRequired(200, "TipoAlojamiento updated successfully");
            String jsonResponse = gson.toJson(requiredResponse);
            return Response.status(200).entity(jsonResponse).build();
        } else {
            ResponseRequired requiredResponse = new ResponseRequired(404, "TipoAlojamiento not found");
            String jsonResponse = gson.toJson(requiredResponse);
            return Response.status(404).entity(jsonResponse).build();
        }
    }

    @GET
    @Path("/representantes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRepresentantes() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        ResponseRequired requiredResponse = new ResponseRequired(200, representantesList);
        String jsonResponse = gson.toJson(requiredResponse);
        return Response.status(200).entity(jsonResponse).build();
    }

    @POST
    @Path("/representantes")

    @Consumes(MediaType.APPLICATION_JSON)
    public Response addRepresentante(String requestBody) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Representante nuevoRepresentante = gson.fromJson(requestBody, Representante.class);

        boolean exists = false;
        for (Representante representante : representantesList) {
            if (representante.getRut().equals(nuevoRepresentante.getRut())) {
                exists = true;
                break;
            }
        }

        if (!exists) {
            representantesList.add(nuevoRepresentante);
            ResponseRequired requiredResponse = new ResponseRequired(200, "Representante added successfully");
            String jsonResponse = gson.toJson(requiredResponse);
            return Response.status(200).entity(jsonResponse).build();
        } else {
            ResponseRequired requiredResponse = new ResponseRequired(409, "Rut already exists");
            String jsonResponse = gson.toJson(requiredResponse);
            return Response.status(409).entity(jsonResponse).build();
        }
    }

    @GET
    @Path("/representantes/{rut}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRepresentanteByRut(@PathParam("rut") String rut) {
        Representante foundRepresentante = null;
        for (Representante representante : representantesList) {
            if (representante.getRut().equals(rut)) {
                foundRepresentante = representante;
                break;
            }
        }
        if (foundRepresentante != null) {
            ResponseRequired requiredResponse = new ResponseRequired(200, foundRepresentante);
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            String jsonResponse = gson.toJson(requiredResponse);
            return Response.status(200).entity(jsonResponse).build();
        } else {
            ResponseRequired requiredResponse = new ResponseRequired(404, "Representante not found");
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            String jsonResponse = gson.toJson(requiredResponse);
            return Response.status(404).entity(jsonResponse).build();
        }
    }

    @PUT
    @Path("/representantes/{rut}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateRepresentanteByRut(@PathParam("rut") String rut, String requestBody) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Representante updatedRepresentante = gson.fromJson(requestBody, Representante.class);

        if (!updatedRepresentante.getRut().equals(rut)) {
            ResponseRequired requiredResponse = new ResponseRequired(400, "RUT in request body does not match RUT in URL");
            String jsonResponse = gson.toJson(requiredResponse);
            return Response.status(400).entity(jsonResponse).build();
        }

        boolean updated = false;
        for (int i = 0; i < representantesList.size(); i++) {
            Representante representante = representantesList.get(i);
            if (representante.getRut().equals(rut)) {
                representantesList.set(i, updatedRepresentante);
                updated = true;
                break;
            }
        }

        if (updated) {
            ResponseRequired requiredResponse = new ResponseRequired(200, "Representante updated successfully");
            String jsonResponse = gson.toJson(requiredResponse);
            return Response.status(200).entity(jsonResponse).build();
        } else {
            ResponseRequired requiredResponse = new ResponseRequired(404, "Representante not found");
            String jsonResponse = gson.toJson(requiredResponse);
            return Response.status(404).entity(jsonResponse).build();
        }
    }

    @DELETE
    @Path("/representantes/{rut}")
    public Response removeRepresentanteByRut(@PathParam("rut") String rut) {
        boolean removed = false;
        for (Iterator<Representante> iterator = representantesList.iterator(); iterator.hasNext();) {
            Representante representante = iterator.next();
            if (representante.getRut().equals(rut)) {
                iterator.remove();
                removed = true;
                break;
            }
        }
        if (removed) {
            ResponseRequired requiredResponse = new ResponseRequired(200, "Representante removed successfully");
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            String jsonResponse = gson.toJson(requiredResponse);
            return Response.status(200).entity(jsonResponse).build();
        } else {
            ResponseRequired requiredResponse = new ResponseRequired(404, "Representante not found");
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            String jsonResponse = gson.toJson(requiredResponse);
            return Response.status(404).entity(jsonResponse).build();
        }
    }

    @GET
    @Path("/alojamientos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAlojamientos() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        ResponseRequired requiredResponse = new ResponseRequired(200, alojamientosList);
        String jsonResponse = gson.toJson(requiredResponse);
        return Response.status(200).entity(jsonResponse).build();
    }

    @GET
    @Path("/alojamientos/nombreagrupacion/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlojamientoByNombreAgrupacion(@PathParam("nombre") String nombreAgrupacion) {
        List<Alojamiento> alojamientosByNombreAgrupacion = new ArrayList<>();

        for (Alojamiento alojamiento : alojamientosList) {
            if (alojamiento.getNombreAgrupacion().equalsIgnoreCase(nombreAgrupacion)) {
                alojamientosByNombreAgrupacion.add(alojamiento);
            }
        }

        if (!alojamientosByNombreAgrupacion.isEmpty()) {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            ResponseRequired requiredResponse = new ResponseRequired(200, alojamientosByNombreAgrupacion);
            String jsonResponse = gson.toJson(requiredResponse);
            return Response.status(200).entity(jsonResponse).build();
        } else {
            ResponseRequired requiredResponse = new ResponseRequired(404, "No Alojamiento found for the given NombreAgrupacion");
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            String jsonResponse = gson.toJson(requiredResponse);
            return Response.status(404).entity(jsonResponse).build();
        }
    }

    @PUT
    @Path("/alojamientos/{nombreAgrupacion}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAlojamientoByNombreAgrupacion(@PathParam("nombreAgrupacion") String nombreAgrupacion, String requestBody) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Alojamiento updatedAlojamiento = gson.fromJson(requestBody, Alojamiento.class);

        boolean found = false;
        for (Alojamiento alojamiento : alojamientosList) {
            if (alojamiento.getNombreAgrupacion().equalsIgnoreCase(nombreAgrupacion)) {
                // Update fields for the found Alojamiento
                alojamiento.setIdTipoVehiculo(updatedAlojamiento.getIdTipoVehiculo());
                alojamiento.setIdTipoAlojamiento(updatedAlojamiento.getIdTipoAlojamiento());
                alojamiento.setDias(updatedAlojamiento.getDias());

                found = true;
                break;
            }
        }

        if (found) {
            ResponseRequired requiredResponse = new ResponseRequired(200, "Alojamiento updated successfully");
            String jsonResponse = gson.toJson(requiredResponse);
            return Response.status(200).entity(jsonResponse).build();
        } else {
            ResponseRequired requiredResponse = new ResponseRequired(404, "Alojamiento not found for the given NombreAgrupacion");
            String jsonResponse = gson.toJson(requiredResponse);
            return Response.status(404).entity(jsonResponse).build();
        }
    }

    @DELETE
    @Path("/alojamientos/{nombreAgrupacion}")
    public Response removeAlojamientoByNombreAgrupacion(@PathParam("nombreAgrupacion") String nombreAgrupacion) {
        boolean removed = false;

        for (Iterator<Alojamiento> iterator = alojamientosList.iterator(); iterator.hasNext();) {
            Alojamiento alojamiento = iterator.next();
            if (alojamiento.getNombreAgrupacion().equalsIgnoreCase(nombreAgrupacion)) {
                iterator.remove();
                removed = true;
                break;
            }
        }

        if (removed) {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            ResponseRequired requiredResponse = new ResponseRequired(200, "Alojamiento removed successfully");
            String jsonResponse = gson.toJson(requiredResponse);
            return Response.status(200).entity(jsonResponse).build();
        } else {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            ResponseRequired requiredResponse = new ResponseRequired(404, "Alojamiento not found for the given NombreAgrupacion");
            String jsonResponse = gson.toJson(requiredResponse);
            return Response.status(404).entity(jsonResponse).build();
        }
    }

    @POST
    @Path("/alojamientos")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAlojamientoWithConditions(String requestBody) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Alojamiento nuevoAlojamiento = gson.fromJson(requestBody, Alojamiento.class);

        boolean existingAlojamiento = false;
        for (Alojamiento alojamiento : alojamientosList) {
            if (alojamiento.getNombreAgrupacion().equalsIgnoreCase(nuevoAlojamiento.getNombreAgrupacion())) {
                existingAlojamiento = true;
                break;
            }
        }

        if (existingAlojamiento) {
            ResponseRequired requiredResponse = new ResponseRequired(409, "Alojamiento with provided NombreAgrupacion already exists");
            String jsonResponse = gson.toJson(requiredResponse);
            return Response.status(409).entity(jsonResponse).build();
        }

        boolean existingRepresentative = false;
        for (Representante representante : representantesList) {
            if (representante.getRut().equals(nuevoAlojamiento.getRut())) {
                existingRepresentative = true;
                break;
            }
        }

        if (!existingRepresentative) {
            ResponseRequired requiredResponse = new ResponseRequired(409, "Previous representative with provided RUT not found");
            String jsonResponse = gson.toJson(requiredResponse);
            return Response.status(409).entity(jsonResponse).build();
        }

        boolean tipoAlojamientoExists = false;
        for (TipoAlojamiento tipo : tipoAlojamientosList) {
            if (tipo.getIdTipoAlojamiento() == nuevoAlojamiento.getIdTipoAlojamiento()) {
                tipoAlojamientoExists = true;
                break;
            }
        }

        if (!tipoAlojamientoExists) {
            ResponseRequired requiredResponse = new ResponseRequired(409, "Provided TipoAlojamiento not found");
            String jsonResponse = gson.toJson(requiredResponse);
            return Response.status(409).entity(jsonResponse).build();
        }

        boolean tipoVehiculoExists = false;
        for (TipoVehiculo tipo : tipoVehiculosList) {
            if (tipo.getIdTipoVehiculo() == nuevoAlojamiento.getIdTipoVehiculo()) {
                tipoVehiculoExists = true;
                break;
            }
        }

        if (!tipoVehiculoExists) {
            ResponseRequired requiredResponse = new ResponseRequired(409, "Provided TipoVehiculo not found");
            String jsonResponse = gson.toJson(requiredResponse);
            return Response.status(409).entity(jsonResponse).build();
        }

        if (nuevoAlojamiento.getDias() < 1) {
            ResponseRequired requiredResponse = new ResponseRequired(409, "Dias must be greater than 0");
            String jsonResponse = gson.toJson(requiredResponse);
            return Response.status(409).entity(jsonResponse).build();
        }

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        nuevoAlojamiento.setFechaIngreso(formattedDateTime);

        alojamientosList.add(nuevoAlojamiento);
        ResponseRequired requiredResponse = new ResponseRequired(200, "Alojamiento added successfully");
        String jsonResponse = gson.toJson(requiredResponse);
        return Response.status(200).entity(jsonResponse).build();
    }
}
