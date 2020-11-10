import java.io.*;

public class opg {
	static char[] equ1 = new char[1100];
	static char[] equ2 = new char[1100];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File(args[0]);
		try{
			FileReader reader = new FileReader(file);
			int newchar;
			
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
                            ept1-=2;
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
                if (rop == '#' && equ2[ept2] == '#')
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
		int  i=5,j=5;
		switch(fir) {
			case '+':i = 0;break;
			case '*':i = 1;break;
			case 'i':i = 2;break;
			case '(':i = 3;break;
			case ')':i = 4;break;
			case '#':i = 5;break;
		}
		switch(sec) {
			case '+':j = 0;break;
			case '*':j = 1;break;
			case 'i':j = 2;break;
			case '(':j = 3;break;
			case ')':j = 4;break;
			case '#':j = 5;break;
		}
		return fuhao(i,j);
	} 
	
	public static int fuhao(int i,int j) {
		int[][] arr = new int[6][6];
		arr[0][0] = arr[1][0] = arr[2][0] = arr[4][0] = arr[1][1] = arr[2][1] = arr[4][1] = arr[0][4] = arr[1][4] = arr[2][4] = arr[4][4] = arr[0][5] = arr[1][5] = arr[2][5] = arr[4][5] = 1;
		arr[0][1] = arr[0][2] = arr[0][3] = arr[1][2] = arr[1][3] = arr[3][0] = arr[3][1] = arr[3][2] = arr[3][3] = arr[5][0] = arr[5][1] = arr[5][2] = arr[5][3] = -1;
		arr[2][2] = arr[2][3] = arr[3][5] = arr[4][2] = arr[4][3] = arr[5][4] = arr[5][5] = 2;
		arr[3][4] = 0;
		return arr[i][j];
	}
}

