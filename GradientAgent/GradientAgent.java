package challenges.GradientAgent;

import java.util.ArrayList;
import java.util.List;
import java.lang.String;
import jade.core.*;
import jade.core.behaviours.*;
import jade.util.Logger;

/**
 * @author Eduardo Fuentes
 * @version 1.0
 */
public class GradientAgent extends Agent {
	private Logger logger = Logger.getMyLogger(getClass().getName());

	private class GradientDescentParams {
		public Double m;
		public Double b;
		public Double learningRate;
		public ArrayList<Double> x;
		public ArrayList<Double> y;

		public GradientDescentParams() {}

		public GradientDescentParams(
			Double m,
			Double b,
			Double learningRate,
			ArrayList<Double> x,
			ArrayList<Double> y
		) {
			this.m = m;
			this.b = b;
			this.learningRate = learningRate;
			this.x = x;
			this.y = y;
		}
	}

	private class ComputeGradientDescent extends OneShotBehaviour {
		private GradientDescentParams params;

		public ComputeGradientDescent(
			Agent parent,
			GradientDescentParams params
		) {
			super(parent);
			this.params = params;
		}

		public void action() {
			logger.info("ComputeGradientDescent has started");

			Double mDeriv = 0.0;
			Double bDeriv = 0.0;

			Double m = params.m;
			Double b = params.b;
			Integer n = params.x.size();
			for (Integer i = 0; i < n; ++i) {
				mDeriv += -2 * params.x.get(i) * (params.y.get(i) - (m * params.x.get(i) + b));
				bDeriv += -2 * (params.y.get(i) - (m * params.x.get(i) + b));
			}

			Double _n = Double.parseDouble(n.toString());
			m -= (mDeriv / _n) * params.learningRate;
			b -= (bDeriv / _n) * params.learningRate;

			logger.info("m=" + m + "; b=" + b);
		}
	}

	protected ArrayList<Double> parseElements(String str, Integer expectedLength) {
		String[] strings = str.split(":");
		ArrayList<Double> elements = new ArrayList<Double>(expectedLength);

		for (String s : strings) {
			elements.add(Double.parseDouble(s));
		}

		return elements;
	}

	protected void setup() {
		Object[] args = getArguments();
		if (args.length == 6) {
			List<Double> Y = new ArrayList<Double>();

			Double m = Double.parseDouble((String)args[0]);
			Double b = Double.parseDouble((String)args[1]);
			Double learningRate = Double.parseDouble((String)args[2]);
			Integer numberOfElements = Integer.parseInt((String)args[3]);

			ArrayList<Double> x = parseElements((String)args[4], numberOfElements);
			ArrayList<Double> y = parseElements((String)args[5], numberOfElements);

			GradientDescentParams params = new GradientDescentParams(m, b, learningRate, x, y);
			addBehaviour(new ComputeGradientDescent(this, params));
		} else {
			logger.log(Logger.SEVERE, "Invalid amount of arguments");
			logger.log(Logger.SEVERE, "Arguments shoud be in the format: iterations,learningRate,numberOfElements,xElements,yElements");
		}
	}
}

