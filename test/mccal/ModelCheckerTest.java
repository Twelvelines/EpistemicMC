package mccal;

import mccal.model.Model;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ModelCheckerTest {
    private final String MFP = "test/models/";

    private Set<String> solutionForSample(String sampleFilename, String formula) {
        Model model = ModelChecker.readModel(sampleFilename);
        return ModelChecker.evalFormula(model, formula);
    }

    @Test
    void test1EvalFormulaSample1S1() {
        assertTrue(solutionForSample(MFP + "sample1.gwm", "<<2>>K(1, atom1)").contains("S1"));
    }

    @Test
    void test2EvalFormulaSample1S1() {
        assertTrue(solutionForSample(MFP + "sample1.gwm", "<<2>>!K(1, atom1)").contains("S1"));
    }

    @Test
    void test3EvalFormulaSample1S1() {
        assertTrue(solutionForSample(MFP + "sample1.gwm", "!<<1>>K(1, atom1)").contains("S1"));
    }

    @Test
    void test4EvalFormulaSample1S1() {
        assertTrue(solutionForSample(MFP + "sample1.gwm", "<<1>>K(2, atom1)").contains("S1"));
    }

    @Test
    void test5EvalFormulaSample1S1() {
        assertTrue(solutionForSample(MFP + "sample1.gwm", "<<1, 2>>K(1, atom1)").contains("S1"));
    }

    @Test
    void test6EvalFormulaSample1S1() {
        assertTrue(solutionForSample(MFP + "sample1.gwm", "<<2>><<1>>K(1, atom1)").contains("S1"));
    }

    @Test
    void test1EvalFormulaSample1S2() {
        assertTrue(solutionForSample(MFP + "sample1.gwm", "!<<2>>K(1, atom1)").contains("S2"));
    }

    @Test
    void test2EvalFormulaSample1S2() {
        assertTrue(solutionForSample(MFP + "sample1.gwm", "<<2>>K(1, !atom1)").contains("S2"));
    }

    @Test
    void test3EvalFormulaSample1S2() {
        assertTrue(solutionForSample(MFP + "sample1.gwm", "!<<1>>K(1, atom1)").contains("S2"));
    }

    @Test
    void test1EvalFormulaSample2S1() {
        assertTrue(solutionForSample(MFP + "sample2.gwm", "<<3>>K(1, atom1)").contains("S1"));
    }

    @Test
    void test2EvalFormulaSample2S1() {
        assertFalse(solutionForSample(MFP + "sample2.gwm", "<<3>>!K(1, atom1)").contains("S1"));
    }

    @Test
    void test3EvalFormulaSample2S1() {
        assertTrue(solutionForSample(MFP + "sample2.gwm", "<<1, 2>>K(1, atom1)").contains("S1"));
    }

    @Test
    void test4EvalFormulaSample2S1() {
        assertTrue(solutionForSample(MFP + "sample2.gwm", "[atom1][atom2][atom3] K(1, atom1)").contains("S1"));
    }

    @Test
    void test5EvalFormulaSample2S1() {
        assertTrue(solutionForSample(MFP + "sample2.gwm", "[atom1][atom2][atom3] K(1, atom3)").contains("S1"));
        // as nonsense announcement always results to return true
    }
}
