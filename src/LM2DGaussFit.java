
import jaolho.data.lma.LMA;
import jaolho.data.lma.LMAMultiDimFunction;
//import jaolho.data.lma.implementations.JAMAMatrix;

/** 
 * An example fit which fits 2D gauss plane to some data points
 * and prints out the resulting fit parameters.
 */
public class LM2DGaussFit {
	public int Rows=0;
	public int Cols=0;
	public double[] yData;
        public double[] initialParam;
        public static double[][] dataPoint;
	/** An example function with a form of f = p0*(exp(-0.5*(sqrt((x-p1)/p2)+sqrt((y-p3)/p4))))+p5 */
	public static class LM2DGaussFunction extends LMAMultiDimFunction {

		public double getY(double x[], double[] p) {
			return p[0]*(Math.exp(-0.5*(Math.pow((x[0]-p[1])/p[2],2)+Math.pow((x[1]-p[3])/p[4],2))))+p[5];
		}

		public double getPartialDerivate(double x[], double[] p, int parameterIndex) {
			switch (parameterIndex) {
				case 0: return Math.exp(-0.5*(Math.pow((x[0]-p[1])/p[2],2.0)+Math.pow((x[1]-p[3])/p[4],2.0)));
				case 1: return p[0]*(Math.exp(-0.5*(Math.pow((x[0]-p[1])/p[2],2.0)+Math.pow((x[1]-p[3])/p[4],2.0))))*
				((x[0]-p[1])/(p[2]*p[2]));
				case 2: return p[0]*(Math.exp(-0.5*(Math.pow((x[0]-p[1])/p[2],2)+Math.pow((x[1]-p[3])/p[4],2))))*
				Math.pow(x[0]-p[1], 2.0)*Math.pow(p[2], -3.0);
				case 3: return p[0]*(Math.exp(-0.5*(Math.pow((x[0]-p[1])/p[2],2)+Math.pow((x[1]-p[3])/p[4],2))))*
				((x[1]-p[3])/(p[4]*p[4]));
				case 4: return p[0]*(Math.exp(-0.5*(Math.pow((x[0]-p[1])/p[2],2)+Math.pow((x[1]-p[3])/p[4],2))))*
				Math.pow(x[1]-p[3], 2.0)*Math.pow(p[4], -3.0);
				case 5: return 1;
			}
			throw new RuntimeException("No such parameter index: " + parameterIndex);
		}
	}
	
        static double[][] GenerateDataPoint(int Rows, int Cols, double[][] yData){
                        //Matrix dataPoint;
                double tempDataPoint[][] = new double[Rows*Cols][3];
                int temp = 0;
                if ((Rows==0)||(Cols==0))
                        throw new RuntimeException(" Havn't Initialize Fit Area Size!!");

                        for (int i=0;i<Rows;i++){
                            for(int j=0;j<Cols;j++){
                                    tempDataPoint[temp][0] = yData[i][j];
                                    tempDataPoint[temp][1] = (double)i;
                                    tempDataPoint[temp][2] = (double)j;
                                    temp+=1;
                            }
                    }
                return tempDataPoint;
                }
	/** Does the actual fitting by using the above MultiDimExampleFunction (a plane) */
	public static void main(String[] args) {
                double[] InitParam = {10000,4.5,2,4.5,2,100};
                double InitYData[][]  = 
                	{
                    {138,	148,	184,	199,	223,	249,	213,	183,	152,	157},
                    {141,	177,	210,	293,	419,	455,	366,	287,	190,	182},
                    {161,	192,	301,	550,	976,	1415,	1003,	480,	292,	229},
                    {159,	220,	400,	937,	3009,	5683,	3792,	1047,	443,	249},
                    {155,	207,	399,	1200,	5111,	10227,	6556,	1592,	487,	260},
                    {150,	211,	335,	910,	3370,	6695,	4309,	1189,	438,	224},
                    {141,	190,	264,	522,	1261,	1896,	1231,	525,	291,	183},
                    {141,	159,	195,	330,	539,	602,	418,	269,	206,	168},
                    {134,	137,	172,	211,	260,	264,	237,	168,	150,	137},
                    {125,	124,	131,	157,	171,	173,	165,	142,	123,	136},
                    {119,	118,	118,	131,	146,	142,	120,	142,	125,	123}
                };
                
                double [][] InitConbineData;
                InitConbineData = GenerateDataPoint(10,10,InitYData);
		LMA lma = new LMA(
			new LM2DGaussFunction(),
                        InitParam,
                        InitConbineData
		);
                lma.maxIterations = 100;
		lma.fit();
		System.out.println("iterations: " + lma.iterationCount);
		System.out.println(
			"chi2: " + lma.chi2 + ",\n" +
			"param0: " + lma.parameters[0] + ",\n" +
			"param1: " + lma.parameters[1] + ",\n" +
			"param2: " + lma.parameters[2]+ ",\n" +
                        "param3: " + lma.parameters[3]+ ",\n" +
                        "param4: " + lma.parameters[4]+ ",\n" +
                        "param5: " + lma.parameters[5]
		);


	}
  /*      public LM2DGaussFit(int Rows,int Cols, double[] yData, LM2DGaussFunction function){
              this.Rows = Rows;
              this.Cols = Cols;
              this.yData = yData;
              this.dataPoint = GenerateDataPoint();

        }*/
}
