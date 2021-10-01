package slr;

import java.util.ArrayList;

interface Summable {
	public Double sum(Double x, Double y);
}

public class SLR {
	private ArrayList<Double> x;
	private ArrayList<Double> y;
	private Double b0;
	private Double b1;

	public SLR(
		ArrayList<Double> x,
		ArrayList<Double> y
	) {
		this.x = x;
		this.y = y;
	}

	public Double sum(Summable s) {
		Double res = 0.0;

		for (int i = 0; i < this.x.size(); ++i) {
			res += s.sum(this.x.get(i), this.y.get(i));
		}

		return res;
	}

	public void compute() {
		Double s1 = this.x.size() * this.sum((x, y) -> x * y);
		Double s2 = this.sum((x, y) -> x) * this.sum((x, y) -> y);
		Double s3 = this.x.size() * this.sum((x, y) -> Math.pow(x, 2));
		Double s4 = this.sum((x, y) -> x) * this.sum((x, y) -> x);
		Double s5 = s1 - s2;
		Double s6 = s3 - s4;
		this.b1 = s5 / s6;

		Double s7 = this.sum((x, y) -> y);
		Double s8 = this.b1 * this.sum((x, y) -> x);
		this.b0 = (s7 - s8) / this.x.size();
	}

	public Double predict(Double x) {
		return this.b0 + (this.b1 * x);
	}

	public Double getB0() {
		return this.b0;
	}

	public Double getB1() {
		return this.b1;
	}
}

