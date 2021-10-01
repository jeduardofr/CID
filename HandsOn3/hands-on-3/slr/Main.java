package slr;

import java.util.ArrayList;

class Main {
	public static void main(String[] args) {
		ArrayList<Double> x = new ArrayList<Double>();
		x.add(23.0);
		x.add(26.0);
		x.add(30.0);
		x.add(34.0);
		x.add(43.0);
		x.add(48.0);
		x.add(52.0);
		x.add(57.0);
		x.add(58.0);

		ArrayList<Double> y = new ArrayList<Double>();

		y.add(651.0);
		y.add(762.0);
		y.add(856.0);
		y.add(1063.0);
		y.add(1190.0);
		y.add(1298.0);
		y.add(1421.0);
		y.add(1440.0);
		y.add(1518.0);

		SLR slr = new SLR(x, y);
		slr.compute();

		System.out.println("b1 = " + slr.getB1());
		System.out.println("b0 = " + slr.getB0());
		System.out.println("When x = 60, y => " + slr.predict(60.0));
		System.out.println("When x = 70, y => " + slr.predict(70.0));
		System.out.println("When x = 80, y => " + slr.predict(80.0));
	}
}
