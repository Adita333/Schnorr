package org.suai.protocol;

public class Main {

	public static void main(String[] args) {
		Alice alice = new Alice();
		Bob bob = new Bob();
		bob.client = alice;
		alice.server = bob;
		Schnorr schnor = new Schnorr();
		alice.SCHNORR = schnor;
		bob.SCHNORR = schnor;
		alice.setVisible(true);
		bob.setVisible(true);
	}

}

