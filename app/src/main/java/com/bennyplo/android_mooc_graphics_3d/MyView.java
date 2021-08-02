package com.bennyplo.android_mooc_graphics_3d;
/*import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class MyView extends View {

    MyShape myShape;
    float ratioRadius, ratioInnerRadius;
    int numberOfPoint = 3; //default

    float rotate;
    Matrix matrix;

    //corresponding to UI element
    TextView textLayerInfo;

    public MyView(Context context) {
        super(context);
        initMyView();
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initMyView();
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initMyView();
    }

    public void initMyView(){
        myShape = new MyShape();
        matrix = new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        long starting = System.nanoTime();

        int w = getWidth();
        int h = getHeight();

        if((w==0) || (h==0)){
            return;
        }

        float x = (float)w/2.0f;
        float y = (float)h/2.0f;
        float radius, innerRadius;
        if(w > h){
            radius = h * ratioRadius;
            innerRadius = h * ratioInnerRadius;
        }else{
            radius = w * ratioRadius;
            innerRadius = w * ratioInnerRadius;
        }

        myShape.setStar(x, y, radius, innerRadius, numberOfPoint);

        //Rotate the path by angle in degree
        Path path = myShape.getPath();
        matrix.reset();
        matrix.postRotate(rotate, x, y);
        path.transform(matrix);

        canvas.drawPath(path, myShape.getPaint());

        long end = System.nanoTime();

        String info = "myView.isHardwareAccelerated() = " + isHardwareAccelerated() + "\n"
                + "canvas.isHardwareAccelerated() = " + canvas.isHardwareAccelerated() + "\n"
                + "processing time (reference only) : " + String.valueOf(end - starting) + " (ns)";
        textLayerInfo.setText(info);

    }

    public void setShapeRadiusRatio(float ratio){
        ratioRadius = ratio;
    }

    public void setShapeInnerRadiusRatio(float ratio){
        ratioInnerRadius = ratio;
    }

    public void setNumberOfPoint(int pt){
        numberOfPoint = pt;
    }

    public void passElements(TextView textLayerInfo){
        this.textLayerInfo = textLayerInfo;
    }

    public void setShapeRotate(int rot){
        rotate = (float)rot;
    }

}
*/
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class MyView extends View {
    private Paint redPaint; //paint object for drawing the lines
    private Coordinate[]headVertices, neckVertices, coreVertices, lhtVertices, lhmVertices, lhbVertices, rhtVertices, rhmVertices, rhbVertices;//the vertices of a 3D cube
    private Paint headPaint, neckPaint, corePaint, lhtPaint, lhmPaint, lhbPaint;
   // private Coordinate[]draw_headVertices;//the vertices for drawing a 3D cube
    public MyView(Context context) {
        super(context, null);
        final MyView thisview=this;
        Matrix matrix = new Matrix();
        //create the paint object
        headPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        headPaint.setStyle(Paint.Style.FILL_AND_STROKE);//Stroke
        headPaint.setColor(Color.BLUE);
        headPaint.setStrokeWidth(2);

        neckPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        neckPaint.setStyle(Paint.Style.FILL_AND_STROKE);//Stroke
        neckPaint.setColor(Color.MAGENTA);
        neckPaint.setStrokeWidth(2);

        corePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        corePaint.setStyle(Paint.Style.FILL_AND_STROKE);//Stroke
        corePaint.setColor(Color.RED);
        corePaint.setStrokeWidth(2);

        lhtPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        lhtPaint.setStyle(Paint.Style.FILL_AND_STROKE);//Stroke
        lhtPaint.setColor(Color.BLUE);
        lhtPaint.setStrokeWidth(2);

        lhmPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        lhmPaint.setStyle(Paint.Style.FILL_AND_STROKE);//Stroke
        lhmPaint.setColor(Color.GREEN);
        lhmPaint.setStrokeWidth(2);

        lhbPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        lhbPaint.setStyle(Paint.Style.FILL_AND_STROKE);//Stroke
        lhbPaint.setColor(Color.CYAN);
        lhbPaint.setStrokeWidth(2);
        //create a 3D cube
        headVertices = new Coordinate[8];
        int hcx = getResources().getDisplayMetrics().widthPixels / 2;
        int hcy = 250;
        int hs2 = 80;
        headVertices[0] = new Coordinate(hcx - hs2, hcy - hs2, -hs2, 1);
        headVertices[2] = new Coordinate(hcx - hs2, hcy + hs2, -hs2, 1);
        headVertices[6] = new Coordinate(hcx + hs2, hcy + hs2, -hs2, 1);
        headVertices[4] = new Coordinate(hcx + hs2, hcy - hs2, -hs2, 1);
        headVertices[5] = new Coordinate(hcx + hs2, hcy - hs2, hs2, 1);
        headVertices[1] = new Coordinate(hcx - hs2, hcy - hs2, hs2, 1);
        headVertices[3] = new Coordinate(hcx - hs2, hcy + hs2, hs2, 1);
        headVertices[7] = new Coordinate(hcx + hs2, hcy + hs2, hs2, 1);

        neckVertices = new Coordinate[8];
        int ncx = getResources().getDisplayMetrics().widthPixels / 2;
        int ncy = 355;
        int ns2 = 25;
        neckVertices[0] = new Coordinate(ncx - ns2, ncy - ns2, -ns2, 1);
        neckVertices[2] = new Coordinate(ncx - ns2, ncy + ns2, -ns2, 1);
        neckVertices[6] = new Coordinate(ncx + ns2, ncy + ns2, -ns2, 1);
        neckVertices[4] = new Coordinate(ncx + ns2, ncy - ns2, -ns2, 1);
        neckVertices[5] = new Coordinate(ncx + ns2, ncy - ns2, ns2, 1);
        neckVertices[1] = new Coordinate(ncx - ns2, ncy - ns2, ns2, 1);
        neckVertices[3] = new Coordinate(ncx - ns2, ncy + ns2, ns2, 1);
        neckVertices[7] = new Coordinate(ncx + ns2, ncy + ns2, ns2, 1);

        coreVertices = new Coordinate[8];
        int ccx = getResources().getDisplayMetrics().widthPixels / 2;
        int ccy = 700;
        int csh2 = 200;
        int csv2 = 320;
        coreVertices[0] = new Coordinate(ccx - csh2, ccy - csv2, -csh2, 1);
        coreVertices[2] = new Coordinate(ccx - csh2, ccy + csv2, -csh2, 1);
        coreVertices[6] = new Coordinate(ccx + csh2, ccy + csv2, -csh2, 1);
        coreVertices[4] = new Coordinate(ccx + csh2, ccy - csv2, -csh2, 1);
        coreVertices[5] = new Coordinate(ccx + csh2, ccy - csv2, csh2, 1);
        coreVertices[1] = new Coordinate(ccx - csh2, ccy - csv2, csh2, 1);
        coreVertices[3] = new Coordinate(ccx - csh2, ccy + csv2, csh2, 1);
        coreVertices[7] = new Coordinate(ccx + csh2, ccy + csv2, csh2, 1);

        lhtVertices = new Coordinate[8];
        int ltcx = getResources().getDisplayMetrics().widthPixels / 2 - csh2 - 50;
        int ltcy = 430;
        int hth2 = 50;
        int htv2 = 50;
        lhtVertices[0] = new Coordinate(ltcx - hth2, ltcy - htv2, -hth2, 1);
        lhtVertices[2] = new Coordinate(ltcx - hth2, ltcy + htv2, -hth2, 1);
        lhtVertices[6] = new Coordinate(ltcx + hth2, ltcy + htv2, -hth2, 1);
        lhtVertices[4] = new Coordinate(ltcx + hth2, ltcy - htv2, -hth2, 1);
        lhtVertices[5] = new Coordinate(ltcx + hth2, ltcy - htv2, hth2, 1);
        lhtVertices[1] = new Coordinate(ltcx - hth2, ltcy - htv2, hth2, 1);
        lhtVertices[3] = new Coordinate(ltcx - hth2, ltcy + htv2, hth2, 1);
        lhtVertices[7] = new Coordinate(ltcx + hth2, ltcy + htv2, hth2, 1);

        lhmVertices = new Coordinate[8];
        int lmcx = getResources().getDisplayMetrics().widthPixels / 2 - csh2 - 50;
        int lmcy = 560;
        int hmh2 = 50;
        int hmv2 = 80;
        lhmVertices[0] = new Coordinate(lmcx - hmh2, lmcy - hmv2, -hmh2, 1);
        lhmVertices[2] = new Coordinate(lmcx - hmh2, lmcy + hmv2, -hmh2, 1);
        lhmVertices[6] = new Coordinate(lmcx + hmh2, lmcy + hmv2, -hmh2, 1);
        lhmVertices[4] = new Coordinate(lmcx + hmh2, lmcy - hmv2, -hmh2, 1);
        lhmVertices[5] = new Coordinate(lmcx + hmh2, lmcy - hmv2, hmh2, 1);
        lhmVertices[1] = new Coordinate(lmcx - hmh2, lmcy - hmv2, hmh2, 1);
        lhmVertices[3] = new Coordinate(lmcx - hmh2, lmcy + hmv2, hmh2, 1);
        lhmVertices[7] = new Coordinate(lmcx + hmh2, lmcy + hmv2, hmh2, 1);

        lhbVertices = new Coordinate[8];
        int lbcx = getResources().getDisplayMetrics().widthPixels / 2 - csh2 - 50;
        int lbcy = 690;
        int hbh2 = 50;
        int hbv2 = 50;
        lhbVertices[0] = new Coordinate(lbcx - hbh2, lbcy - hbv2, -hbh2, 1);
        lhbVertices[2] = new Coordinate(lbcx - hbh2, lbcy + hbv2, -hbh2, 1);
        lhbVertices[6] = new Coordinate(lbcx + hbh2, lbcy + hbv2, -hbh2, 1);
        lhbVertices[4] = new Coordinate(lbcx + hbh2, lbcy - hbv2, -hbh2, 1);
        lhbVertices[5] = new Coordinate(lbcx + hbh2, lbcy - hbv2, hbh2, 1);
        lhbVertices[1] = new Coordinate(lbcx - hbh2, lbcy - hbv2, hbh2, 1);
        lhbVertices[3] = new Coordinate(lbcx - hbh2, lbcy + hbv2, hbh2, 1);
        lhbVertices[7] = new Coordinate(lbcx + hbh2, lbcy + hbv2, hbh2, 1);

        rhtVertices = new Coordinate[8];
        int rtcx = getResources().getDisplayMetrics().widthPixels / 2 + csh2 + 50;
        int rtcy = 430;
        rhtVertices[0] = new Coordinate(rtcx - hth2, rtcy - htv2, -htv2, 1);
        rhtVertices[2] = new Coordinate(rtcx - hth2, rtcy + htv2, -htv2, 1);
        rhtVertices[6] = new Coordinate(rtcx + hth2, rtcy + htv2, -htv2, 1);
        rhtVertices[4] = new Coordinate(rtcx + hth2, rtcy - htv2, -htv2, 1);
        rhtVertices[5] = new Coordinate(rtcx + hth2, rtcy - htv2, htv2, 1);
        rhtVertices[1] = new Coordinate(rtcx - hth2, rtcy - htv2, htv2, 1);
        rhtVertices[3] = new Coordinate(rtcx - hth2, rtcy + htv2, htv2, 1);
        rhtVertices[7] = new Coordinate(rtcx + hth2, rtcy + htv2, htv2, 1);

        rhmVertices = new Coordinate[8];
        int rmcx = getResources().getDisplayMetrics().widthPixels / 2 + csh2 + 50;
        int rmcy = 560;
        rhmVertices[0] = new Coordinate(rmcx - hmh2, rmcy - hmv2, -hmh2, 1);
        rhmVertices[2] = new Coordinate(rmcx - hmh2, rmcy + hmv2, -hmh2, 1);
        rhmVertices[6] = new Coordinate(rmcx + hmh2, rmcy + hmv2, -hmh2, 1);
        rhmVertices[4] = new Coordinate(rmcx + hmh2, rmcy - hmv2, -hmh2, 1);
        rhmVertices[5] = new Coordinate(rmcx + hmh2, rmcy - hmv2, hmh2, 1);
        rhmVertices[1] = new Coordinate(rmcx - hmh2, rmcy - hmv2, hmh2, 1);
        rhmVertices[3] = new Coordinate(rmcx - hmh2, rmcy + hmv2, hmh2, 1);
        rhmVertices[7] = new Coordinate(rmcx + hmh2, rmcy + hmv2, hmh2, 1);

        rhbVertices = new Coordinate[8];
        int rbcx = getResources().getDisplayMetrics().widthPixels / 2 + csh2 + 50;
        int rbcy = 690;
        rhbVertices[0] = new Coordinate(rbcx - hbh2, rbcy - hbv2, -hbh2, 1);
        rhbVertices[2] = new Coordinate(rbcx - hbh2, rbcy + hbv2, -hbh2, 1);
        rhbVertices[6] = new Coordinate(rbcx + hbh2, rbcy + hbv2, -hbh2, 1);
        rhbVertices[4] = new Coordinate(rbcx + hbh2, rbcy - hbv2, -hbh2, 1);
        rhbVertices[5] = new Coordinate(rbcx + hbh2, rbcy - hbv2, hbh2, 1);
        rhbVertices[1] = new Coordinate(rbcx - hbh2, rbcy - hbv2, hbh2, 1);
        rhbVertices[3] = new Coordinate(rbcx - hbh2, rbcy + hbv2, hbh2, 1);
        rhbVertices[7] = new Coordinate(rbcx + hbh2, rbcy + hbv2, hbh2, 1);
        //draw_headVertices = translate(headVertices, 0, -250, 0);

/*draw_headVertices = scale(draw_headVertices, 40, 40, 40);*/


        thisview.invalidate();//update the view

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            boolean reverse = false;
            int angle = 45;
            double ax = getResources().getDisplayMetrics().widthPixels, ay = 250, az = 0;
            @Override
            public void run() {
                double x = Math.sin(Math.toRadians(angle/2))*ax ;
                double y = Math.sin(Math.toRadians(angle/2))*ay;
                double z = Math.sin(Math.toRadians(angle/2))*az;
                //draw_headVertices = rotate(draw_headVertices, angle, 1);
                //draw_headVertices = rotateQuaternion(headVertices, Math.cos(Math.toRadians(angle/2)), x, y, z);
                //draw_headVertices = translate(headVertices, 200, 200, 0);
                thisview.invalidate();
               angle += 10;
                if(angle >= 360) angle = 0;

if(reverse){
                    angle -= 5;
                } else{
                    angle += 5;
                }
                if(angle >= 45) reverse = true;
                if(angle <= -45) reverse = false;

            }
        };
        //timer.scheduleAtFixedRate(timerTask, 100, 100);
    }

    private void DrawLinePairs(Canvas canvas, Coordinate[] vertices, int start, int end, Paint paint)
    {//draw a line connecting 2 points
        //canvas - canvas of the view
        //points - array of points
        //start - index of the starting point
        //end - index of the ending point
        //paint - the paint of the line
        canvas.drawLine((int)vertices[start].x,(int)vertices[start].y,(int)vertices[end].x,(int)vertices[end].y,paint);
    }

    private void DrawCube(Canvas canvas, Coordinate[] draw_headVertices, Paint paint)
    {//draw a cube on the screen
        Log.v("insideDrawCube", draw_headVertices[7].x+"");
        DrawLinePairs(canvas, draw_headVertices, 0, 1, paint);
        DrawLinePairs(canvas, draw_headVertices, 1, 3, paint);
        DrawLinePairs(canvas, draw_headVertices, 3, 2, paint);
        DrawLinePairs(canvas, draw_headVertices, 2, 0, paint);
        DrawLinePairs(canvas, draw_headVertices, 4, 5, paint);
        DrawLinePairs(canvas, draw_headVertices, 5, 7, paint);
        DrawLinePairs(canvas, draw_headVertices, 7, 6, paint);
        DrawLinePairs(canvas, draw_headVertices, 6, 4, paint);
        DrawLinePairs(canvas, draw_headVertices, 0, 4, paint);
        DrawLinePairs(canvas, draw_headVertices, 1, 5, paint);
        DrawLinePairs(canvas, draw_headVertices, 2, 6, paint);
        DrawLinePairs(canvas, draw_headVertices, 3, 7, paint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //draw objects on the screen
        super.onDraw(canvas);
        DrawCube(canvas, neckVertices, neckPaint);
        DrawCube(canvas, headVertices, headPaint);
        DrawCube(canvas, coreVertices, corePaint);
        DrawCube(canvas, lhtVertices, lhtPaint);
        DrawCube(canvas, lhmVertices, lhmPaint);
        DrawCube(canvas, lhbVertices, lhbPaint);
        DrawCube(canvas, rhtVertices, lhtPaint);
        DrawCube(canvas, rhmVertices, lhmPaint);
        DrawCube(canvas, rhbVertices, lhbPaint);//draw a cube onto the screen
    }

    //matrix and transformation functions
    public double []GetIdentityMatrix()
    {//return an 4x4 identity matrix
        double []matrix=new double[16];
        matrix[0]=1;matrix[1]=0;matrix[2]=0;matrix[3]=0;
        matrix[4]=0;matrix[5]=1;matrix[6]=0;matrix[7]=0;
        matrix[8]=0;matrix[9]=0;matrix[10]=1;matrix[11]=0;
        matrix[12]=0;matrix[13]=0;matrix[14]=0;matrix[15]=1;
        return matrix;
    }
    public Coordinate Transformation(Coordinate vertex,double []matrix)
    {//affine transformation with homogeneous coordinates
        //i.e. a vector (vertex) multiply with the transformation matrix
        // vertex - vector in 3D
        // matrix - transformation matrix
        Coordinate result=new Coordinate();
        result.x=matrix[0]*vertex.x+matrix[1]*vertex.y+matrix[2]*vertex.z+matrix[3];
        result.y=matrix[4]*vertex.x+matrix[5]*vertex.y+matrix[6]*vertex.z+matrix[7];
        result.z=matrix[8]*vertex.x+matrix[9]*vertex.y+matrix[10]*vertex.z+matrix[11];
        result.w=matrix[12]*vertex.x+matrix[13]*vertex.y+matrix[14]*vertex.z+matrix[15];
        return result;
    }
    public Coordinate[]Transformation(Coordinate []vertices,double []matrix)
    {   //Affine transform a 3D object with vertices
        // vertices - vertices of the 3D object.
        // matrix - transformation matrix
        Coordinate []result=new Coordinate[vertices.length];
        for (int i=0;i<vertices.length;i++)
        {
           result[i]=Transformation(vertices[i],matrix);
           result[i].Normalise();
        }
        return result;
    }

    //Affine transformation

    private Coordinate[] affineTransformation(Coordinate[] vertices, double[] matrix){
        Coordinate[] result = new Coordinate[vertices.length];

        for(int i = 0; i < vertices.length; i++){
            result[i] = new Coordinate();
            result[i].x = (int) (matrix[0] * vertices[i].x + matrix[1] * vertices[i].y + matrix[2] * vertices[i].z + matrix[3]);
            result[i].y = (int) (matrix[4] * vertices[i].x + matrix[5] * vertices[i].y + matrix[6] * vertices[i].z + matrix[7]);
            result[i].z = (int) (matrix[8] * vertices[i].x + matrix[9] * vertices[i].y + matrix[10] * vertices[i].z + matrix[11]);
            result[i].w = 0;
        }

        return result;
    }

    public Coordinate []translate(Coordinate []vertices,double tx,double ty,double tz)
    {
        double []matrix=GetIdentityMatrix();
        matrix[3]=tx;
        matrix[7]=ty;
        matrix[11]=tz;
        return affineTransformation(vertices,matrix);
    }

    private Coordinate[] scale(Coordinate []vertices,double sx,double sy,double sz)
    {
        double []matrix=GetIdentityMatrix();
        matrix[0]=sx;
        matrix[5]=sy;
        matrix[10]=sz;
        return affineTransformation(vertices,matrix);
    }

    private Coordinate[] shear(Coordinate []vertices, double shx, double shy)
    {
        double []matrix=GetIdentityMatrix();
        matrix[2]=shx;
        matrix[6]=shy;
        return affineTransformation(vertices,matrix);
    }

    private Coordinate[] rotate(Coordinate []vertices, int theta, int about) {
        double []matrix = GetIdentityMatrix();

        if(about == 0){
            matrix[5]= Math.cos(Math.toRadians(theta));
            matrix[6]= -Math.sin(Math.toRadians(theta));
            matrix[9]= Math.sin(Math.toRadians(theta));
            matrix[10]= Math.cos(Math.toRadians(theta));
        } else if(about == 1){
            matrix[0]= Math.cos(Math.toRadians(theta));
            matrix[8]= -Math.sin(Math.toRadians(theta));
            matrix[2]= Math.sin(Math.toRadians(theta));
            matrix[10]= Math.cos(Math.toRadians(theta));
        } else if(about == 2){
            matrix[0]= Math.cos(Math.toRadians(theta));
            matrix[1]= -Math.sin(Math.toRadians(theta));
            matrix[4]= Math.sin(Math.toRadians(theta));
            matrix[5]= Math.cos(Math.toRadians(theta));
        }
        return affineTransformation(vertices,matrix);
    }

    private Coordinate[] rotateQuaternion(Coordinate[] vertices, double w, double x, double y, double z){
        double[] matrix = GetIdentityMatrix();

        matrix[0] = w*w + x*x - y*y - z*z;
        matrix[1] = 2*x*y - 2*w*z;
        matrix[2] = 2*x*z + 2*w*y;
        matrix[4] = 2*x*y + 2*w*z;
        matrix[5] = w*w - x*x + y*y - z*z;
        matrix[6] = 2*y*z - 2*w*x;
        matrix[8] = 2*x*z - 2*w*y;
        matrix[9] = 2*y*z + 2*w*x;
        matrix[10] = w*w - x*x - y*y + z*z;
        matrix[15] = 1;

        return affineTransformation(vertices, matrix);
    }

    private Coordinate[] projectPerspective(Coordinate[] vertices, double far, double near, double left, double right, double top, double bottom, double angle){
        double ar = (right - left) / (top - bottom);
        double tanA = 1 / near;

        double[] matrix = GetIdentityMatrix();

        matrix[0] = 1 / ar * tanA;
        matrix[5] = 1 / tanA;
        matrix[10] = (far + near) / (near - far);
        matrix[11] = 2 * far * near / (near - far);
        matrix[14] = -1;

        return affineTransformation(vertices, matrix);
     }

}
