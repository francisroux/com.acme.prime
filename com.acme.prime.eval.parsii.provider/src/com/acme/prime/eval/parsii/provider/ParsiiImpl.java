package com.acme.prime.eval.parsii.provider;

import org.osgi.service.component.annotations.Component;

import com.acme.prime.eval.api.Eval;

import parsii.eval.Parser;

/**
 * Eval implementation using parsii parser
 */
@Component(name = "com.acme.prime.eval.parsii")
public class ParsiiImpl implements Eval {

	@Override
	public double eval(String expression) throws Exception {
		return Parser.parse(expression).evaluate();
	}
}
