package enigma;

public class Rotor {

    private int position;
    protected int tailleTableau = 26;
    private int[] cipher = new int[26];
    private int[] bcipher = new int[26];
    private int notch1 = -1;
    private int notch2 = -1;

    public int getPosition() {
        return position;
    }

    public void setPosition(int posn) {
        position = posn;
    }
    
	public static Rotor rotorFactory(String str, String notches){
		char[] s = str.trim().replace(" ", "").toCharArray();
		int[] cipher = new int[26];
		for (int i = 0; i< 26; i++){
			cipher[i] = toIndex(s[i]);
		}
		s = notches.trim().replace(" and ", "").toCharArray();
		if (s.length == 2){
			return new Rotor(cipher, toIndex(s[0]), toIndex(s[1]));
		} else {
			return new Rotor(cipher, toIndex(s[0]));
		}
		
	}
	
	Rotor(int[] c, int notch1, int notch2) {
		this.notch1 = notch1;
		this.notch2 = notch2;
		cipher = c;
		createBCipher();
	}
	
	Rotor(int[] c, int notch1) {
		this.notch1 = notch1;
		cipher = c;
		createBCipher();
	}

    public int convertForward(int p) {
        return ((cipher[((p+position)%tailleTableau+tailleTableau)%tailleTableau]-position)%tailleTableau+tailleTableau)%tailleTableau;
        //return (cipher[(p+position)%tailleTableau]-position)%tailleTableau;
    }

    public int convertBackward(int e) {
        return ((bcipher[((e+position)%tailleTableau+tailleTableau)%tailleTableau]-position)%tailleTableau+tailleTableau)%tailleTableau;
        //return (bcipher[(e+position)%tailleTableau]-position)%tailleTableau;
    }
    
    public void advance() {
        position = (position+1) % tailleTableau;
    }
    
    protected boolean atNotch() {
        return (position == notch1 || position == notch2);
    }

    protected static char toLetter(int p) {
        return (char)(p + 'A');
    }

    protected static int toIndex(char c) {
        return (int)(c) - (int)('A');
    }
    
	private void createBCipher() {
		for(int i =0; i<tailleTableau; i++)
			bcipher[cipher[i]] = i;
	}



}
