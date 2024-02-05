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

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@PageTitle("Reproductor")
@Route(value = "hello", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class MainView extends HorizontalLayout {

    private Button playBtn,nextBtn,lastBtn;
    private Icon play=new Icon(VaadinIcon.PLAY),next=new Icon(VaadinIcon.STEP_FORWARD),last=new Icon(VaadinIcon.STEP_BACKWARD);
    private HorizontalLayout izquierda,iconos;
    private VerticalLayout derecha, vertical;
    //private Image img;
    private H2 cancion;
    private H1 titulo;
    private AdvancedPlayer player;
    private String imgPath="https://imgs.search.brave.com/Lse0LmllKvkNW2d8eaPChvn0l1Z6XaL1og5BVojdQ6A/rs:fit:860:0:0/g:ce/aHR0cHM6Ly9zeW1i/bC13b3JsZC5ha2Ft/YWl6ZWQubmV0L2kv/d2VicC80NS9kYWVl/NWZiNTM4NGY3NGNk/Njk2Y2VmOWZmNWQ5/MTMud2VicA";
    private static AudioPlayer audioPlayer;
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
        cancion=new H2("* Nombre de cancion *");


        //GRID
        Grid<nodoDto> grid=new Grid<>(nodoDto.class);
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.setWidth("80%");
        grid.setMaxHeight("60%");




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
        playBtn =new Button("",play);
        nextBtn =new Button("", next);
        lastBtn =new Button("", last);
        iconos.add(lastBtn, playBtn, nextBtn);

        String filePath="C:\\Users\\milla\\OneDrive - Unidad de Educación Media Superior Tecnológica Industrial y de Servicios\\DevilMan Projects\\Aprendizaje VAADIN\\reproductor\\src\\main\\java\\com\\example\\application\\views\\musica\\No Me Toquen Ese Vals, Julio Jaramillo.mp3";
        try {
            AdvancedPlayer audioInputStream=new AdvancedPlayer(new FileInputStream(filePath));
        } catch (FileNotFoundException e) {
            Notification.show(e.toString());

        } catch (JavaLayerException e) {
            throw new RuntimeException(e);
        }

        //LOGICA DE LAS CANCIONES
        insertCanciones(grid);
        //audioPlayer.mostrarCanciones(grid);



        //play.setIcon(new Icon(VaadinIcon.STOP));
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
        String filePath="C:\\Users\\milla\\OneDrive - Unidad de Educación Media Superior Tecnológica Industrial y de Servicios\\DevilMan Projects\\Aprendizaje VAADIN\\reproductor\\src\\main\\java\\com\\example\\application\\views\\musica\\No Me Toquen Ese Vals, Julio Jaramillo.mp3";
        audioPlayer.insertar("No me toquen ese vals - Julio Jaramillo",filePath);
        String filePath2="C:\\Users\\milla\\OneDrive - Unidad de Educación Media Superior Tecnológica Industrial y de Servicios\\DevilMan Projects\\Aprendizaje VAADIN\\reproductor\\src\\main\\java\\com\\example\\application\\views\\musica\\Daddy Yankee- Pose Sub.Español.mp3";
        audioPlayer.insertar("Pose - Daddy Yankee",filePath2);
        String filePath3="C:\\Users\\milla\\OneDrive - Unidad de Educación Media Superior Tecnológica Industrial y de Servicios\\DevilMan Projects\\Aprendizaje VAADIN\\reproductor\\src\\main\\java\\com\\example\\application\\views\\musica\\Molotov - Frijolero.mp3.crdownload";
        audioPlayer.insertar("Frijolero - Molotov",filePath3);
        Notification.show(audioPlayer.getCancion(0).getNombre());
        Notification.show(audioPlayer.getCancion(0).getSiguiente().getNombre());
        Notification.show(audioPlayer.getCancion(0).getSiguiente().getSiguiente().getNombre());
        Notification.show(audioPlayer.getCancion(0).getSiguiente().getSiguiente().getSiguiente().getNombre());
        audioPlayer.mostrarCanciones(grid);
    }

}
