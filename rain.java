import objectdraw.*;
import java.awt.*;
import java.util.*;
public class rain extends ActiveObject
{
    Color green=new Color(0,153,0);
    static FilledRect bucket;
    boolean block=false;
    FilledOval drop;
    Random r=new Random();
    static DrawingCanvas can;
    static boolean moving=true;
    boolean dropCalled=false;
    static FilledRect bucket2;
    static FilledRect collection;
    static Location locationC;
    boolean placeHolder=false;
    static FilledRect cover;
    String colors;
    static double cHeight;
    boolean poison=false;
    static int score=0; //DELETE LATER
    public rain(DrawingCanvas c){
        
        can=c;
        
    }
    
    public rain(DrawingCanvas c, int h)
    {

        int x=r.nextInt(c.getWidth()-10);
        if (x<=3){
            x=4;
        }
        drop=new FilledOval(x,70,6,10,c);
        int p=r.nextInt(10);
        if (p==5 || p==1){
            poison=true;
        drop.setColor(green);
        colors="green";
    }else{
        drop.setColor(Color.blue);
        colors="blue";
    }
    }
    
    public void createBucket(DrawingCanvas c){
        bucket=new FilledRect(c.getWidth()/2,c.getHeight()-70,50,70,c);

        bucket.setColor(Color.white);
        bucket2=new FilledRect(c.getWidth()/2,c.getHeight()-70,50,8,c);
        bucket2.setColor(Color.white);
        collection=new FilledRect(5+c.getWidth()/2,c.getHeight()+75,40,68,c);
        cover=new FilledRect(5+c.getWidth()/2,c.getHeight()+75,40,68,c);
        cHeight=68;
        collection.setColor(Color.blue);
        cover.setColor(Color.white);
    }
    
    public void moveBucket(double x,double y){
        locationC= new Location(x+5,y);
        bucket2.moveTo(x,y);
        bucket.moveTo(x,y);
        collection.moveTo(x+5,y);
        cover.moveTo(x+5,y);
    }
    
    public void generate(){
        rain Rain=new rain(can,90);
        Rain.start();
    }
    
    public void overlap(){
        if (bucket2.overlaps(drop)){
            if (poison){
                collection.setColor(green);
                moving=false;
            }else
           
            score++;
            cHeight-=1;
            cover.setHeight(cHeight);
            drop.hide();
            drop.removeFromCanvas();

            if (cHeight<=2){
                int x= 90;
                moving=false;
            }
            
            }
   }
    
    
    public void run() {
        int speed;
        if (score<35){
        speed=r.nextInt(35-score)+30;
    }else{
        speed=30;
    }
        
        int up=1+r.nextInt(5);
        
        while (moving) {

            move(0,up);
            pause(speed); 
            if (drop.getY()>can.getHeight()){
                if (!drop.isHidden() && (colors=="blue")){
                moving=false;

            }

            }
        }
    }

    public void move(int Over,int Up){
        drop.move(Over,Up);
        if (drop.getY()>=(can.getHeight()/2) && !dropCalled){
            this.generate();
            dropCalled=true;
        }
        this.overlap();
    }
    
   

}
