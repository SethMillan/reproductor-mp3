package com.example.application.views.view;

import com.example.application.views.MainLayout;
import com.example.application.views.objeto.Dto.nodoDto;
import com.example.application.views.objeto.Entity.nodo;
import com.example.application.views.service.AudioPlayer;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@PageTitle("Reproductor")
@Route(value = "hello", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class MainView extends HorizontalLayout {


    private static Button playBtn,nextBtn,lastBtn;
    private static Icon play=new Icon(VaadinIcon.PLAY),next=new Icon(VaadinIcon.STEP_FORWARD),last=new Icon(VaadinIcon.STEP_BACKWARD), stop=new Icon(VaadinIcon.STOP);
    private HorizontalLayout izquierda,iconos;
    private VerticalLayout derecha, vertical;
    //private Image img;
    private static H2 cancion;
    private H1 titulo;
    private static boolean estadoBtn=false;
    private static AudioPlayer audioPlayer;
    private static nodo cancionActual;//=new File("C:\\Users\\milla\\OneDrive - Unidad de Educación Media Superior Tecnológica Industrial y de Servicios\\DevilMan Projects\\Aprendizaje VAADIN\\reproductor\\src\\main\\java\\com\\example\\application\\views\\musica\\Daddy-Yankee-Pose-Sub.Español.wav");
    private static Clip clip;


    public MainView() {
        titulo=new H1("Canción");
        addClassName("fondo");
        setSizeFull();
        // DISEÑO
        izquierda =new HorizontalLayout();
        derecha=new VerticalLayout();
        izquierda.setSizeFull();
        //---PROGRAMACION DE LA IZQUIERDA
        vertical=new VerticalLayout();
        vertical.setMinHeight("50%");
        vertical.setMaxWidth("50%");
        vertical.addClassName("vertical");

        iconos=new HorizontalLayout();
        iconos.addClassName("iconos");
        iconos.setMinHeight("80px");
        iconos.setSizeFull();
        iconos.setJustifyContentMode(JustifyContentMode.CENTER);
        iconos.setAlignItems(Alignment.CENTER);
        Grid<nodoDto> grid=new Grid<>(nodoDto.class);
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.setWidth("80%");
        grid.setMaxHeight("60%");
        insertCanciones(grid);
        cancion=new H2(cancionActual.getNombre());


        //GRID





        iconos.setJustifyContentMode(JustifyContentMode.CENTER);

        izquierda.setJustifyContentMode(JustifyContentMode.CENTER);
        izquierda.setAlignItems(Alignment.CENTER);
        vertical.setJustifyContentMode(JustifyContentMode.CENTER);
        vertical.setAlignItems(Alignment.CENTER);
        vertical.add( titulo,cancion,iconos);
        izquierda.add(vertical);

        //COMPONENTES
        play.getStyle().set("font-size", "35px");
        next.getStyle().set("font-size", "35px");
        last.getStyle().set("font-size", "35px");
        stop.getStyle().set("font-size", "35px");
        playBtn =new Button("",play);
        nextBtn =new Button("", next);
        lastBtn =new Button("", last);
        iconos.add(lastBtn, playBtn, nextBtn);

        //LOGICA DE LAS CANCIONES



        playBtn.addClickListener(e->{
            Notification.show((String.valueOf(estadoBtn)));
            if(!estadoBtn){
                estadoBtn=true;
                playBtn.setIcon(stop);
                reproducirCancion(new File(cancionActual.getDireccion()));
            }else{
                estadoBtn=false;
                playBtn.setIcon(play);
                detenerCancion();
            }

        });
        nextBtn.addClickListener(e->{
            if(clip.isRunning()){
                clip.stop();
                cancionActual=cancionActual.getSiguiente();
                reproducirCancion(new File(cancionActual.getDireccion()));
            }else{
                cancionActual=cancionActual.getSiguiente();
            }
            cancion.setText(cancionActual.getNombre());
        });
        lastBtn.addClickListener(e->{
            if(clip.isRunning()){
                clip.stop();
                cancionActual=cancionActual.getAnterior();
                reproducirCancion(new File(cancionActual.getDireccion()));
            }else{
                cancionActual=cancionActual.getAnterior();
            }
            cancion.setText(cancionActual.getNombre());
        });
        //PROGRAMACION DE LA DERECHA
        derecha.add(grid);
        derecha.setSizeFull();

        derecha.setJustifyContentMode(JustifyContentMode.CENTER);
        derecha.setAlignItems(Alignment.CENTER);

        derecha.setMargin(true);
        derecha.addClassName("derecha");
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);


        add(izquierda,derecha);
    }
    public static void insertCanciones(Grid<nodoDto>grid){
        audioPlayer=new AudioPlayer();
        String filePath="C:\\Users\\milla\\OneDrive - Unidad de Educación Media Superior Tecnológica Industrial y de Servicios\\DevilMan Projects\\Aprendizaje VAADIN\\reproductor\\src\\main\\java\\com\\example\\application\\views\\musica\\Daddy-Yankee-Pose-Sub.Español.wav";
        audioPlayer.insertar("Pose - Daddy Yankee",filePath);
        String filePath2="C:\\Users\\milla\\OneDrive - Unidad de Educación Media Superior Tecnológica Industrial y de Servicios\\DevilMan Projects\\Aprendizaje VAADIN\\reproductor\\src\\main\\java\\com\\example\\application\\views\\musica\\Molotov-Frijolero.wav";
        audioPlayer.insertar("Frijolero - Molotov",filePath2);
        String filePath3="C:\\Users\\milla\\OneDrive - Unidad de Educación Media Superior Tecnológica Industrial y de Servicios\\DevilMan Projects\\Aprendizaje VAADIN\\reproductor\\src\\main\\java\\com\\example\\application\\views\\musica\\No-Me-Toquen-Ese-Vals_-Julio-Jaramillo.wav";
        audioPlayer.insertar("No me toquen ese vals - Julio Jaramillo",filePath3);
        Notification.show(audioPlayer.getCancion(0).getNombre());
        Notification.show(audioPlayer.getCancion(0).getSiguiente().getNombre());
        Notification.show(audioPlayer.getCancion(0).getSiguiente().getSiguiente().getNombre());
        Notification.show(audioPlayer.getCancion(0).getSiguiente().getSiguiente().getSiguiente().getNombre());
        audioPlayer.mostrarCanciones(grid);
        cancionActual=audioPlayer.getCancion(0);
    }
    public static void reproducirCancion(File file){
        //File archivo=new File(audioPlayer.getCancion(0).getDireccion());
        try {
            //File file = new File("C:\\Users\\milla\\OneDrive - Unidad de Educación Media Superior Tecnológica Industrial y de Servicios\\DevilMan Projects\\Aprendizaje VAADIN\\reproductor\\src\\main\\java\\com\\example\\application\\views\\musica\\No-Me-Toquen-Ese-Vals_-Julio-Jaramillo.wav");  // Reemplaza con la ruta correcta
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
          e.printStackTrace();  // Puedes personalizar esto según tus necesidades
        }
    }
    public static void detenerCancion(){
        clip.stop();
        //clip.close();
        Notification.show("Deberia detenerse");
    }

}
