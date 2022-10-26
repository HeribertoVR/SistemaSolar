package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.bounding.BoundingSphere;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Cylinder;
import com.jme3.scene.shape.Sphere;
import com.jme3.system.AppSettings;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 * @author normenhansen
 */
public class Main extends SimpleApplication {
    
    public Spatial ob;
    public Spatial ob1;
    public Spatial ob2;
    public Spatial ob3;
    public Spatial ob4;        
    
        Node nodoMercurio;
        Node nodoVenus;
        Node nodoTierra;
        Node nodoMarte;
        Node nodoJupiter;
        Node sistemaSolar;
        
    Geometry mercurio;
    Geometry venus;
    Geometry tierra;
    Geometry marte;
    Geometry jupiter;
    
    public static final Quaternion PITCH090 = new Quaternion().fromAngleAxis(FastMath.PI/2, new Vector3f(1,0,0));

    public static void main(String[] args) {
        AppSettings settings = new AppSettings(true); //Creamos el objeto para controlar las especificaciones
        settings.setTitle("Tower of God"); //Cambiamos el nombre de la ventana
        //Integramos una imagen personal a la pantalla de inicio
        settings.setSettingsDialogImage("Interface/Fondo4.png");
        //modificar la resolucion
        settings.setResolution(400, 400);
        Main app = new Main();
        app.setSettings(settings);//Aplicamos las esfecificaciones a la app
        
        app.start();     
    }

    @Override
    public void simpleInitApp() {
        
        flyCam.setMoveSpeed(10f);
        
        //Cambiaremos la ubicacion y rotacion de la camara para dar la perspectiva que req
        cam.setLocation(new Vector3f(0, 40, 15));
        cam.setRotation(PITCH090);
        
        
        //Se crea una esfera para el Sol
        Sphere s = new Sphere(90,90,8f,true,true);
        Geometry sol = new Geometry("Sphere", s);
                
        
        //Se crea una esfera para Mercurio
        Sphere m= new Sphere(90,90,0.5f,true,true);
        mercurio = new Geometry("Sphere", m);
        Material matMercurio =new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matMercurio.setTexture("ColorMap", assetManager.loadTexture("Textures/mercurio.jpg"));
        mercurio.setMaterial(matMercurio);
               
        //Se crea una esfera para Venus
        Sphere v= new Sphere(90,90,0.8f,true,true);
        venus = new Geometry("Sphere", v);
        Material matVenus=new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matVenus.setTexture("ColorMap", assetManager.loadTexture("Textures/venus.jpg"));
        venus.setMaterial(matVenus);
        
        //Se crea una esfera para Marte
        Sphere mar= new Sphere(90,90,0.7f,true,true);
        marte = new Geometry("Sphere", mar);
        Material matMarte=new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matMarte.setTexture("ColorMap", assetManager.loadTexture("Textures/marte.jpg"));
        marte.setMaterial(matMarte);
        
        //Se crea una esfera para Jupiter
        Sphere j= new Sphere(90,90,2.5f,true,true);
        jupiter = new Geometry("Sphere", j);
        Material matJupiter=new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matJupiter.setTexture("ColorMap", assetManager.loadTexture("Textures/jupiter.jpg"));
        jupiter.setMaterial(matJupiter);     

        Material matSol = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        //matSol.setColor("Color", ColorRGBA.Yellow);
        matSol.setTexture("ColorMap", assetManager.loadTexture("Textures/sol.jpg"));
        sol.setMaterial(matSol);
        
        //Se crea una esfera para la Tierra
        Sphere t = new Sphere(90,90,1f,true,true);
        tierra = new Geometry("Sphere", t);        
        Material matTierra = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        //matTierra.setColor("Color", ColorRGBA.Blue);
        matTierra.setTexture("ColorMap", assetManager.loadTexture("Textures/planeta_tierra.jpg"));
        tierra.setMaterial(matTierra);
        
        
        Quaternion roll90 = new Quaternion();
        roll90.fromAngleAxis(-FastMath.HALF_PI, Vector3f.UNIT_X);
        
        //tierra.rotate(roll90);
        mercurio.rotate(roll90);
        venus.rotate(roll90);
        marte.rotate(roll90);
        jupiter.rotate(roll90);
        sol.rotate(roll90);

        //Se crea un nodo para meter a los planetas
        sistemaSolar = new Node("sistemaSolar");
        
        nodoMercurio = new Node("nodoMercurio");
        nodoVenus = new Node("nodoVenus");
        nodoTierra = new Node("nodoTierra");
        nodoMarte = new Node("nodoMarte");
        nodoJupiter = new Node("nodoJupiter");
        
        nodoMercurio.attachChild(mercurio);
        nodoVenus.attachChild(venus);
        nodoTierra.attachChild(tierra);
        nodoMarte.attachChild(marte);
        nodoJupiter.attachChild(jupiter);
        sistemaSolar.attachChild(sol);
        
        sistemaSolar.attachChild(nodoMercurio);
        sistemaSolar.attachChild(nodoVenus);
        sistemaSolar.attachChild(nodoTierra);
        sistemaSolar.attachChild(nodoMarte);
        sistemaSolar.attachChild(nodoJupiter);
        
        //sistemaSolar.attachChild(mercurio);
        //sistemaSolar.attachChild(venus);
        //sistemaSolar.attachChild(tierra);
        //sistemaSolar.attachChild(marte);
        //sistemaSolar.attachChild(jupiter);
        
        //Se mueve de posición el Sol
        // sol.move(1,0,0);
       
        //Se mueve de posicion Mercurio
        mercurio.move(0,0,13);
        
        //Se mueve de posicion Venus
        venus.move(0,0,17);
        
        //Se mueve de posición la Tierra
        tierra.move(0,0,21);
            
        //Se mueve de posicion Marte
        marte.move(0,0,25);
        
        //Se mueve de posicion Jupiter
        jupiter.move(0,0,31);
        
        //Se mueve el nodo
        sistemaSolar.move(0,0,0);
        
        rootNode.attachChild(sistemaSolar);
    }

    @Override
    public void simpleUpdate(float tpf) {
        //Para hacer que la tierra gire alrededor del Sol
        if(ob==null){
            System.out.println("No está asignado el objeto");
            ob = rootNode.getChild("nodoMercurio");            
        }
        else{
            ob.rotate(0,tpf*5,0);            
            mercurio.rotate(0, tpf, 0);
            
        }
        
        //Para hacer que se traslade Mercurio con velocidad diferente
        if(ob1==null){
            System.out.println("No está asignado el objeto");
            ob1 = rootNode.getChild("nodoVenus");
            
        }
        else{
            ob1.rotate(0,tpf*4,0);
            venus.rotate(0, tpf, 0);
        }
        
        //Para hacer que se traslade la Tierra con velocidad diferente
        if(ob2==null){
            System.out.println("No está asignado el objeto");
            ob2 = rootNode.getChild("nodoTierra");
        }
        else{
            ob2.rotate(0,tpf*3,0);
            tierra.rotate(0, tpf, 0);
        }
        
        //Para hacer que se traslade Marte con velocidad diferente
        if(ob3==null){
            System.out.println("No está asignado el objeto");
            ob3 = rootNode.getChild("nodoMarte");
        }
        else{
            ob3.rotate(0,tpf*2,0);
            marte.rotate(0, tpf, 0);
        }
        
        //Para hacer que se traslade Jupiter con velocidad diferente
        if(ob4==null){
            System.out.println("No está asignado el objeto");
            ob4 = rootNode.getChild("nodoJupiter");
        }
        else{
            ob4.rotate(0,tpf,0);
            jupiter.rotate(0, tpf, 0);
        }
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
