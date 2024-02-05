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

import javax.sound.sampled.*;
import java.io.File;
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
    private static nodo nodoActual;//=new File("C:\\Users\\milla\\OneDrive - Unidad de Educación Media Superior Tecnológica Industrial y de Servicios\\DevilMan Projects\\Aprendizaje VAADIN\\reproductor\\src\\main\\java\\com\\example\\application\\views\\musica\\Daddy-Yankee-Pose-Sub.Español.wav");
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
        cancion=new H2(nodoActual.getNombre());


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
            if(!estadoBtn){
                reproducirCancion(new File(nodoActual.getDireccion()));
            }else{
                detenerCancion();
            }

        });
        nextBtn.addClickListener(e->{
            if(clip.isRunning()){
                clip.stop();
                nodoActual = nodoActual.getSiguiente();
                reproducirCancion(new File(nodoActual.getDireccion()));
            }else{
                nodoActual = nodoActual.getSiguiente();
            }
            cancion.setText(nodoActual.getNombre());
        });
        lastBtn.addClickListener(e->{
            if(clip.isRunning()){
                clip.stop();
                nodoActual = nodoActual.getAnterior();
                reproducirCancion(new File(nodoActual.getDireccion()));
            }else{
                nodoActual = nodoActual.getAnterior();
            }
            cancion.setText(nodoActual.getNombre());
        });

        grid.addItemClickListener(e->{
            String nombreCancion=e.getItem().getCancion();
            if(nodoActual.getNombre().equals(nombreCancion)){
                Notification.show("La cancion ya se esta reproduciendo");
            }else{
                if(clip.isRunning()){
                    detenerCancion();
                }
                Notification.show("Cambiando de cancion...");
                for (int i = 0; i < audioPlayer.getTamanio(); i++) {
                    nodoActual=nodoActual.getSiguiente();
                    if(nodoActual.getNombre().equals(nombreCancion)){
                        break;
                    }
                }
                cancion.setText(nodoActual.getNombre());
                reproducirCancion(new File(nodoActual.getDireccion()));
            }


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
        audioPlayer.mostrarCanciones(grid);
        nodoActual =audioPlayer.getCancion(0);
    }
    public static void reproducirCancion(File file){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            estadoBtn=true;
            playBtn.setIcon(stop);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
          e.printStackTrace();  // Puedes personalizar esto según tus necesidades
        }
    }
    public static void detenerCancion(){
        clip.stop();
        estadoBtn=false;
        playBtn.setIcon(play);
    }

}
