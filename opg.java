import java.io.*;

public class opg {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File(args[0]);
		try{
			FileReader reader = new FileReader(file);
			int newchar;
			char[] equ1 = new char[1100];
			char[] equ2 = new char[1100];
			int ept1 = -1;
			int ept2 = -1;
			char rop;
			equ2[++ept2] = '#';
			while((newchar = reader.read()) != -1) {
				rop = (char) newchar;
				if(rop == '\r') {
					rop = '#';
				}
				if(cmp(equ2[ept2],rop) == 2 || (rop!='+' && rop!='*' &&rop!='i' &&rop!='(' &&rop!=')' &&rop!='#')) {
					System.out.println("E");
					System.exit(0);
				}				
				while(cmp(equ2[ept2],rop) == 1)
                {
                    if(equ2[ept2] == 'i')
                    {
                        equ1[ept1] = 'E';
                        ept2 --;
                        System.out.println("R");
                    }
                    else if(equ2[ept2] == '+' || equ2[ept2] == '*')
                    {
                        if(equ1[ept1] != 'E')
                        {
                            System.out.println("RE");
                            System.exit(0);
                        }
                        else{
                            ept1 --;
                            ept2 --;
                            if(equ1[ept1] != 'E')
                            {
                                System.out.println("RE");
                                System.exit(0);
                            }
                            else
                            {
                                ept2 --;
                                System.out.println("R");
                            }
                        }
                    }
                    else {
                        ept1 --;
                        ept2 --;
                        if(equ1[ept1] != 'E')
                        {
                            System.out.println("RE");
                            System.exit(0);
                        }
                        else{
                            ept1 --;
                            if(equ1[ept1] != '(')
                            {
                                System.out.println("RE");
                                System.exit(0);
                            }
                            else{
                            	equ1[ept1] = 'E';
                                ept2 --;
                                System.out.println("R");
                            }
                        }
                    }
                }
                if(cmp(equ2[ept2],rop) == -1 || cmp(equ2[ept2],rop) == 0) {
                    equ1[++ept1] = rop;
                    equ2[++ept2] = rop;
                    System.out.println("I"+rop);
                }
                if (rop == '\r' && equ2[ept2] == '\r')
                {
                    ept1 --;
                    break;
                }
			}
			reader.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}

	public static int cmp(char fir,char sec) {
		if(fir == '+') {
			switch(sec) {
				case '+':return 1;
				case '*':return -1;
				case 'i':return -1;
				case '(':return -1;
				case ')':return 1;
				case '#':return 1;
			}
		} else if(fir == '*'){
			switch(sec) {
				case '+':return 1;
				case '*':return 1;
				case 'i':return -1;
				case '(':return -1;
				case ')':return 1;
				case '#':return 1;
			}
		} else if(fir == 'i') {
			switch(sec) {
				case '+':return 1;
				case '*':return 1;
				case 'i':return 2;
				case '(':return 2;
				case ')':return 1;
				case '#':return 1;
			}
		} else if(fir == '(') {
			switch(sec) {
				case '+':return -1;
				case '*':return -1;
				case 'i':return -1;
				case '(':return -1;
				case ')':return 0;
				case '#':return 2;
			}
		} else if(fir == ')') {
			switch(sec) {
				case '+':return 1;
				case '*':return 1;
				case 'i':return 2;
				case '(':return 2;
				case ')':return 1;
				case '#':return 1;
			}
		} else if(fir == '#') {
			switch(sec) {
			case '+':return -1;
			case '*':return -1;
			case 'i':return -1;
			case '(':return -1;
			case ')':return 2;
			case '#':return 0;
			} 
		}
		return 5;
	} 
}

