package com.example.PracticaCrud.services;

import com.example.PracticaCrud.entities.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private static List<Student> est;
    private long cont_estudiante;
    private static StudentService instancia;

    private StudentService(){
        est = new ArrayList<>();
        cont_estudiante = 0;
    }

    public static StudentService getInstancia(){
        if(instancia==null){
            instancia = new StudentService();
        }
        return instancia;
    }

    public List<Student> getEstudiantes() {
        return est;
    }

    public void setEstudiantes(List<Student> est) {
        StudentService.est = est;
    }

    public void anadirEstudiante(Student e) {
        cont_estudiante++;
        e.setId(cont_estudiante);
        est.add(e);
    }

    public Student getEstudiante(long id){

        for(Student estudiante : est) {
            if (id == estudiante.getId()) {
                return estudiante;
            }
        }

        return null;
    }

    public int getIndexEstudiante(long id){

        for(int i = 0;i< est.size();i++) {
            if (id == est.get(i).getId()) {
                return i;
            }
        }

        return -1;
    }

    public void editarEstudiante(long id,String nombre,String apellido,String telefono,int matricula){
        int i = this.getIndexEstudiante(id);
        est.get(i).setNombre(nombre);
        est.get(i).setApellido(apellido);
        est.get(i).setMatricula(matricula);
        est.get(i).setTelefono(telefono);
    }

    public void eliminarEstudiante(long id){
        int i = this.getIndexEstudiante(id);
        est.remove(i);
    }

}
