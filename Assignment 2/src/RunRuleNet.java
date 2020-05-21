
/**
 * RunRuleNet.java
 * create a rule network & make deductions with it
 * @author Phil Green
 * This Version 23/1/2019
 */

import sheffield.*;
import java.util.*;
import pmatch.*;

public class RunRuleNet {
	public static void main(String[] arg) {
		// create object for output
		EasyWriter scr = new EasyWriter();

		// make some rules

		// cousin
		ArrayList<String> cantes = new ArrayList<String>();
		cantes.add("?p is the parent of ?c");
		cantes.add("?ps is the sibling of ?p");
		cantes.add("?ps is the parent of ?co");
		RuleNode crule = new RuleNode("cousin rule", cantes, "?co is the cousin of ?c");
		ArrayList<RuleNode> csuccs = new ArrayList<RuleNode>();
		crule.setSuccessors(csuccs);

		// uncle
		ArrayList<String> uantes = new ArrayList<String>();
		uantes.add("?p is the parent of ?c");
		uantes.add("?p is the sibling of ?ps");
		uantes.add("?ps is male");
		RuleNode urule = new RuleNode("uncle rule", uantes, "?ps is the uncle of ?c");
		ArrayList<RuleNode> usuccs = new ArrayList<RuleNode>();
		urule.setSuccessors(usuccs);

		// aunt
		ArrayList<String> aantes = new ArrayList<String>();
		aantes.add("?p is the parent of ?c");
		aantes.add("?p is the sibling of ?ps");
		aantes.add("?ps is female");
		RuleNode arule = new RuleNode("aunt rule", aantes, "?ps is the aunt of ?c");
		ArrayList<RuleNode> asuccs = new ArrayList<RuleNode>();
		arule.setSuccessors(asuccs);

		// brother
		ArrayList<String> bantes = new ArrayList<String>();
		bantes.add("?s is the sibling of ?b");
		bantes.add("?b is male");
		RuleNode brule = new RuleNode("brother rule", bantes, "?b is the brother of ?s");
		ArrayList<RuleNode> bsuccs = new ArrayList<RuleNode>();
		brule.setSuccessors(bsuccs);

		// sister
		ArrayList<String> sisantes = new ArrayList<String>();
		sisantes.add("?s is the sibling of ?sis");
		sisantes.add("?sis is female");
		RuleNode sisrule = new RuleNode("sister rule", sisantes, "?sis is the sister of ?s");
		ArrayList<RuleNode> sissuccs = new ArrayList<RuleNode>();
		sisrule.setSuccessors(sissuccs);

		// niece
		ArrayList<String> nantes = new ArrayList<String>();
		nantes.add("?s is the sibling of ?pe");
		nantes.add("?pe is the parent of ?n");
		nantes.add("?n is female");
		RuleNode nrule = new RuleNode("niece rule", nantes, "?n is the niece of ?s");
		ArrayList<RuleNode> nsuccs = new ArrayList<RuleNode>();
		nrule.setSuccessors(nsuccs);

		// nephew
		ArrayList<String> npantes = new ArrayList<String>();
		npantes.add("?s is the sibling of ?pe");
		npantes.add("?pe is the parent of ?n");
		npantes.add("?n is male");
		RuleNode nprule = new RuleNode("nephew rule", npantes, "?n is the nephew of ?s");
		ArrayList<RuleNode> npsuccs = new ArrayList<RuleNode>();
		nprule.setSuccessors(npsuccs);

		// sibling
		ArrayList<String> santes = new ArrayList<String>();
		santes.add("?p is the parent of ?s1");
		santes.add("?p is the parent of ?s2");
		RuleNode srule = new RuleNode("sibling rule", santes, "?s1 is the sibling of ?s2");
		ArrayList<RuleNode> ssuccs = new ArrayList<RuleNode>();
		ssuccs.add(crule);
		ssuccs.add(urule);
		ssuccs.add(arule);
		ssuccs.add(brule);
		ssuccs.add(sisrule);
		ssuccs.add(nrule);
		ssuccs.add(nprule);
		srule.setSuccessors(ssuccs);

		// mother is female
		ArrayList<String> mfantes = new ArrayList<String>();
		mfantes.add("?m is the mother of ?c");
		RuleNode mfrule = new RuleNode("mother-is-female rule", mfantes, "?m is female");
		ArrayList<RuleNode> mfsuccs = new ArrayList<RuleNode>();
		mfsuccs.add(arule);
		mfsuccs.add(sisrule);
		mfsuccs.add(nrule);
		mfrule.setSuccessors(mfsuccs);

		// father is male
		ArrayList<String> fmantes = new ArrayList<String>();
		fmantes.add("?f is the father of ?c");
		RuleNode fmrule = new RuleNode("father-is-male rule", fmantes, "?f is male");
		ArrayList<RuleNode> fmsuccs = new ArrayList<RuleNode>();
		fmsuccs.add(urule);
		fmsuccs.add(brule);
		fmsuccs.add(nprule);
		fmrule.setSuccessors(fmsuccs);

		// grandmother
		ArrayList<String> gmantes = new ArrayList<String>();
		gmantes.add("?gm is the mother of ?p");
		gmantes.add("?p is the parent of ?c");
		RuleNode gmrule = new RuleNode("grandmother rule", gmantes, "?gm is the grandmother of ?c");
		ArrayList<RuleNode> gmsuccs = new ArrayList<RuleNode>();
		gmrule.setSuccessors(gmsuccs);

		// grandfather
		ArrayList<String> gfantes = new ArrayList<String>();
		gfantes.add("?gf is the father of ?p");
		gfantes.add("?p is the parent of ?c");
		RuleNode gfrule = new RuleNode("grandfather rule", gfantes, "?gf is the grandfather of ?c");
		ArrayList<RuleNode> gfsuccs = new ArrayList<RuleNode>();
		gfrule.setSuccessors(gfsuccs);

		// father-is-parent
		ArrayList<String> fpantes = new ArrayList<String>();
		fpantes.add("?f is the father of ?c");
		RuleNode fprule = new RuleNode("father-is-parent rule", fpantes, "?f is the parent of ?c");
		ArrayList<RuleNode> fpsuccs = new ArrayList<RuleNode>();
		fpsuccs.add(gfrule);
		fpsuccs.add(gmrule);
		fpsuccs.add(srule);
		fpsuccs.add(fmrule);
		fpsuccs.add(crule);
		fpsuccs.add(urule);
		fpsuccs.add(arule);
		fpsuccs.add(nrule);
		fpsuccs.add(nprule);
		fprule.setSuccessors(fpsuccs);

		// mother-is-parent
		ArrayList<String> mpantes = new ArrayList<String>();
		mpantes.add("?m is the mother of ?c");
		RuleNode mprule = new RuleNode("mother-is-parent rule", mpantes, "?m is the parent of ?c");
		ArrayList<RuleNode> mpsuccs = new ArrayList<RuleNode>();
		mpsuccs.add(gfrule);
		mpsuccs.add(gmrule);
		mpsuccs.add(srule);
		mpsuccs.add(mfrule);
		mpsuccs.add(crule);
		mpsuccs.add(urule);
		mpsuccs.add(arule);
		mpsuccs.add(nrule);
		mpsuccs.add(nprule);
		mprule.setSuccessors(mpsuccs);

		// the set of rulenodes

		ArrayList<RuleNode> rset = new ArrayList<RuleNode>();
		rset.add(gmrule);
		rset.add(gfrule);
		rset.add(fprule);
		rset.add(mprule);
		rset.add(srule);
		rset.add(mfrule);
		rset.add(fmrule);
		rset.add(crule);
		rset.add(urule);
		rset.add(arule);
		rset.add(brule);
		rset.add(sisrule);
		rset.add(nrule);
		rset.add(nprule);

		// make the rule net
		RuleNet rs = new RuleNet(rset);
		// initialise it - set up initial tokens
		rs.initialise();

		// add facts
		ArrayList<String> facts = new ArrayList<String>();
		long startTime = System.currentTimeMillis();
		rs.addFact("Jill is the mother of David");
		rs.addFact("Jill is the mother of Shula");
		rs.addFact("David is the parent of Pip");
		rs.addFact("David is male");
		rs.addFact("Shula is the mother of Daniel");
		long stopTime = System.currentTimeMillis();
		scr.println("compute time (ms) " + (stopTime-startTime));
	}
}