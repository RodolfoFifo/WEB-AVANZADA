package com.example.PracticaCrud.controllers;

import com.example.PracticaCrud.entities.Student;
import com.example.PracticaCrud.services.StudentService;
import jakarta.websocket.server.PathParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = {"/crud","/"})
public class StudentController {
    private StudentService sest = StudentService.getInstancia();

    @GetMapping(path = {"/"})
    public String incio(){
        return "redirect:/crud/listar";
    }

    @GetMapping(path = {"/listar"})
    public String listar(Model model){
        model.addAttribute("estudiantes",sest.getEstudiantes());
        return "listar";
    }
    @GetMapping(path = {"/agregar"})
    public String agregarView(){
        return "formulario";
    }
    @PostMapping(path = {"/agregar"})
    public String agregarEstudiante(@RequestParam(name = "apellido") String apellido, @RequestParam(name = "nombre") String nombre, @RequestParam(name = "matricula") int matricula, @RequestParam(name = "telefono") String telefono){
        Student e = new Student(nombre,apellido,matricula,telefono);
        sest.anadirEstudiante(e);
        return "redirect:/crud/listar";
    }
    @GetMapping(path = {"/editar"})
    public String editarView(Model model, @PathParam("id") int id){
        Student e = sest.getEstudiante(id);
        model.addAttribute("estudiante",e);
        return "formulario";
    }
    @PostMapping(path = {"/editar"})
    public String editarEstudiante(@PathParam("id") int id,@RequestParam(name = "apellido") String apellido,@RequestParam(name = "nombre") String nombre,@RequestParam(name = "matricula") int matricula,@RequestParam(name = "telefono") String telefono){
        sest.editarEstudiante(id,nombre,apellido,telefono,matricula);
        return "redirect:/crud/visualizar?id=" + id;
    }
    @GetMapping(path = {"visualizar"})
    public String visualizar(Model model,@PathParam("id") int id){
        Student e = sest.getEstudiante(id);
        model.addAttribute("estudiante",e);
        return "visualizar";
    }
    @GetMapping(path = {"/eliminar"})
    public String eliminar(@PathParam("id") int id){
        sest.eliminarEstudiante(id);
        return "redirect:/crud/listar";
    }
}
