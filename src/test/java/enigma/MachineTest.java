package enigma;

import static org.junit.Assert.*;

import org.junit.Test;

import app.Main;

public class MachineTest {
	
	String config ="* B III IV I AXLE";
	String msg1 = "FROM his shoulder Hiawatha";
	String code1 = "HYIHBSBOEBTWJZSXMWQKYGWSFR";
	String msg2 = "HYIHLBKOMLIUYDCMPPSFSZW";
	String code2 = "FROMHISSHOULDERHIAWATHA";
	
	@Test
	/**
	 * This is more a validation tests, than an unit test
	 */
	public void convert_Msg1_code1() {
		Machine m = new Machine();
		Main.configure(m, config);
		String expected = code1;
		String actual = m.convert(msg1);
		assertEquals(expected, actual);
	}

	@Test
	/**
	 * This is more a validation tests, than an unit test
	 */
	public void unconvert_Msg2_code2() {
		Machine m = new Machine();
		Main.configure(m, config);
		String expected = code2;
		String actual = m.convert(msg2);
		assertEquals(expected, actual);
	}
        
        @Test
        public void advanceRotors_False() {
            Rotor left = Rotor.rotorFactory(msg1, code1);
            Rotor middle = Rotor.rotorFactory(msg1, code1);
            Rotor right = Rotor.rotorFactory(msg1, code1);
            Reflector reflector = Reflector.reflectorFactory(msg1);
            
            Machine m = new Machine();
            m.configure(reflector, left, middle, right, config);
            
            Machine m2 = m;
            
            m.advanceRotors();
            
            /*on doit tester si chaque rotor a été modifié ou non en fonction
            de la position et des notch
            */
            
        }

}
