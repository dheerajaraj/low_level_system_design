package org.lb;

/**
 * Functional Requirements:
 * 1. Should register
 * 2. Should de-register out of LB
 * 3. Should get random register instance
 * 4. Current count of register
 *
 * Non-functional req:
 * 1. It is more read heavy than write heavy; support large reads
 * 2. Multiple users should be able to get instance in a consistent way
 * 3. Current count of reg should also be thread safe
 * 4. No duplicate entries of server name in the lb.
 */
public class MainApp {

  public static void main(String[] args) {

  }
}