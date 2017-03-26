import java.util.*;
import java.awt.*;
import objectdraw.*;
public class app extends FrameWindowController 
{
    int t=0;
    rain R;
    boolean ready=false;
    public void begin(){
        Color sky=new Color(225,225,225);
        ((JDrawingCanvas)canvas).setBackground(sky);
        FilledOval cloud=new FilledOval(-10,0,160,70,canvas);
        cloud.setColor(Color.gray);
        cloud=new FilledOval(120,0,160,70,canvas);
        cloud.setColor(Color.gray);
        cloud=new FilledOval(250,0,160,70,canvas);
        cloud.setColor(Color.gray);
        R=new rain(canvas);
        R.createBucket(canvas);
        ready=true;
    }
    
    public void onMouseMove(Location p){
        
        if (ready){
            R.moveBucket(p.getX(),canvas.getHeight()-70);
        }
    }
    
    public void onMouseClick(Location p){
        if (t==0){
            rain Drops=new rain(canvas,90);
            Drops.start();
        }
        t++;
    }

}