package problem;
import javax.media.opengl.GL2;
public class Triangle {
    double ax;
    double ay;
    double bx;
    double by;
    double cx;
    double cy;
    public void render(GL2 gl){
        Vector2 v1 =new Vector2(ax,ay);
        Vector2 v2 =new Vector2(bx,by);
        Vector2 v3 =new Vector2(cx,cy);
        Figures.renderTriangle(gl,v1,v2,v3,true);
    }
    public Triangle(double ax,double ay,double bx,double by, double cx, double cy){
        this.ax=ax;
        this.ay=ay;
        this.bx=bx;
        this.by=by;
        this.cx=cx;
        this.cy=cy;
    }
    public  boolean regular(){
        Vector2 posA =new Vector2(ax,ay);
        Vector2 posB =new Vector2(bx,by);
        Vector2 posC =new Vector2(cx,cy);
        double ABx = posA.x-posB.x;
        double ABy = posA.y-posB.y;
        double BCx = posB.x-posC.x;
        double BCy = posB.y-posC.y;
        double ACx = posA.x-posC.x;
        double ACy = posA.y-posC.y;
        double q=Math.sqrt(ABx*ABx+ABy*ABy);
        double w=Math.sqrt(BCx*BCx+BCy*BCy);
        double e=Math.sqrt(ACx*ACx+ACy*ACy);
        if((Math.abs(q-w)<0.001)&&(Math.abs(q-e)<0.001)&&(Math.abs(e-w)<0.001)) {
            return true;
        }else{
            return false;
        }
    }
}
