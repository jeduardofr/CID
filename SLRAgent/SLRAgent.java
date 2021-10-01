package handson.SLRAgent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.lang.String;
import jade.core.*;
import jade.core.behaviours.*;
import jade.util.Logger;

interface Summable {
	public Double sum(Double x, Double y);
}

/**
 * @author Eduardo Fuentes
 * @version 1.0
 */
public class SLRAgent extends Agent {
	private Logger logger = Logger.getMyLogger(getClass().getName());

	private class SLRParams {
		public ArrayList<Double> x;
		public ArrayList<Double> y;
		public ArrayList<Double> toPredict;

		public SLRParams(
			ArrayList<Double> x,
			ArrayList<Double> y,
			ArrayList<Double> toPredict
		) {
			this.x = x;
			this.y = y;
			this.toPredict = toPredict;
		}
	}

	private class SimpleLinearRegression extends OneShotBehaviour {
		private SLRParams params;
		private Double b0;
		private Double b1;

		public SimpleLinearRegression(
			Agent parent,
			SLRParams params
		) {
			super(parent);
			this.params = params;
		}

		public Double sum(Summable s) {
			Double res = 0.0;
			for (int i = 0; i < this.params.x.size(); ++i) {
				res += s.sum(this.params.x.get(i), this.params.y.get(i));
			}
			return res;
		}

		public void action() {
			logger.info("SimpleLinearRegression has started");

			Double s1 = this.params.x.size() * this.sum((x, y) -> x * y);
			Double s2 = this.sum((x, y) -> x) * this.sum((x, y) -> y);
			Double s3 = this.params.x.size() * this.sum((x, y) -> Math.pow(x, 2));
			Double s4 = this.sum((x, y) -> x) * this.sum((x, y) -> x);
			Double s5 = s1 - s2;
			Double s6 = s3 - s4;
			this.b1 = s5 / s6;

			Double s7 = this.sum((x, y) -> y);
			Double s8 = this.b1 * this.sum((x, y) -> x);
			this.b0 = (s7 - s8) / this.params.x.size();

			for (Double d : this.params.toPredict) {
				logger.info("When x = " + d + ", y => " + this.predict(d));
			}
		}

		private Double predict(Double x) {
			return this.b0 + (this.b1 * x);
		}
	}

	protected void setup() {
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

		ArrayList<Double> toPredict = new ArrayList<Double>();
		toPredict.add(60.0);
		toPredict.add(70.0);
		toPredict.add(80.0);

		SLRParams params = new SLRParams(x, y, toPredict);
		addBehaviour(new SimpleLinearRegression(this, params));
	}
}


