package ch20.ex06;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.Map.Entry;

public class Operation {

	public enum Operator{
		ADD('+') {
			@Override
			public double calicurate(double val, double val2) {
				return val + val2;
			}
		},
		SUB('-') {
			@Override
			public double calicurate(double val, double val2) {
				return val - val2;
			}
		},
		EQUAL('=') {
			@Override
			public double calicurate(double val, double val2) {
				return val2;
			}
		};

		private char operationCharacter;
		Operator(char op){
			this.operationCharacter = op;
		}
		public static Operator getOPerator(char op) {
			for (Operator operator : Operator.values()) {
				if (operator.getOperatorName() == op) {
					return operator;
				}
			}
			return null;
		}
		public char getOperatorName() {
			return operationCharacter;
		}
		public abstract double calicurate(double val, double val2);
	}

	private HashMap<String, Double> result = new HashMap<>();;

	public Operation() {
	}

	public void calc(Reader reader) throws IOException {
		StreamTokenizer tokenizer = new StreamTokenizer(reader);
		String name = null;
		Operator op = null;
		while(tokenizer.nextToken() != StreamTokenizer.TT_EOF) {
			if (tokenizer.ttype == StreamTokenizer.TT_WORD) {
				String value = tokenizer.sval;
				if (name != null) {
					throw new InvalidPropertiesFormatException("Format Exception. Line : " + tokenizer.lineno());
				}
				name = value;
			} else if (tokenizer.ttype == StreamTokenizer.TT_NUMBER) {
				Double src = result.get(name);
				if (src == null) {
					src = (double) 0;
				}
				double value = tokenizer.nval;
				if (name == null || op == null) {
					throw new InvalidPropertiesFormatException("Format Exception. Line : " + tokenizer.lineno());
				}
				result.put(name, op.calicurate(src, value));
				name = null;
				op = null;
			} else if ((op = Operator.getOPerator((char)tokenizer.ttype)) == null) {
				throw new InvalidPropertiesFormatException("Format Exception. Line : " + tokenizer.lineno());
			}
		}
		disResult();
	}

	public Double getResult(String key) {
		return result.get(key);
	}

	public void disResult() {
		System.out.println("Rresult:");
		for (Entry<String, Double> element : result.entrySet()) {
			System.out.println( element.getKey() + " = " + element.getValue());
		}
	}

}
