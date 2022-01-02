public class
MipsAssignment {
    public static void main(String[]arg){
        String hexNum;
        boolean type;
        String[] testCases= new String[]{"8E510064","AE330064","02538822","014B9025","222800A5","014B482A", "02324020", "12320064","012A9824"};
            for(int i=0; i< testCases.length; i++) {
                System.out.println("Input: "+testCases[i]+"\n");
                //1. hex to binary
                String binNum = hex2bin(testCases[i]);
                //2. Which type?
                type = instructionType(binNum);

                //a. The format of the instruction (e.g., R-Format/I-Format)
                if (type) {
                    System.out.println("Instruction Format: R-Format");
                    rType(binNum);
                }
                else {
                    System.out.println("Instruction Format: I-Format");
                    iType(binNum);
                }
            }
    }
    static String hex2bin(String hex){
        String bin = "";
        String binFragment = "";
        int iHex;
        hex = hex.trim();
        hex = hex.replaceFirst("0x", "");

        for(int i = 0; i < hex.length(); i++){
            iHex = Integer.parseInt(""+hex.charAt(i),16);
            binFragment = Integer.toBinaryString(iHex);

            while(binFragment.length() < 4){
                binFragment = "0" + binFragment;
                }
            bin += binFragment;
             }
        return bin;
        }
         public static boolean instructionType(String binary){
            String check="000000";
            String sixNum=binary.substring(0,6);

            if(check.equals(sixNum))
                return true;
            else
                return false;
        }
        static int bin2Dec(int bin){
            int decimal = 0;
            int p = 0;
            while(true){
                if(bin == 0){
                    break;
                } else {
                    int temp = bin%10;
                    decimal += temp*Math.pow(2, p);
                    bin= bin/10;
                    p++;
                }
            }
            return decimal;
        }
        public static void rType(String binary){
        //b. Last 6 digits are the opperation
            String[] nameArr= new String[]{"add","sub","and","or","slt"};
            String[] codeArr=new String[]{"100000","100010","100100","100101","101010"};
            String opp=binary.substring(26);
            String cur="";
            for(int i=0; i<nameArr.length; i++){
                cur=codeArr[i];
                if(cur.equals(opp))
                    System.out.println("Operation: " + nameArr[i]);
            }
        //c. Source Register
            int source1=Integer.parseInt(binary.substring(6,11));
            System.out.print("Source Registers: "+bin2Dec(source1)+", ");
            int source2=Integer.parseInt(binary.substring(11,16));
            System.out.println(bin2Dec(source2)+"");
        //d. Destination Register
            int desti=Integer.parseInt(binary.substring(16,21));
            System.out.println("Destination Registers: "+ bin2Dec(desti)+"");
        //e. Shift Amount
            int shift=Integer.parseInt(binary.substring(21,26));
            System.out.println("Shift amount: " +bin2Dec(shift)+"");
        //f. Constant/offset is always none
            System.out.println("Constant/Offset: none \n");
        }
        public static void iType(String binary){
        //b. first 6 digits r opperation
            String[] nameArr= new String[]{"addi","lw","sw","beq"};
            String[]codeArr=new String[]{"001000","100011","101011","000100"};
            String opp=binary.substring(0,6);
            String cur="";
            for(int i=0; i<nameArr.length; i++){
                cur=codeArr[i];
                if(cur.equals(opp))
                    System.out.println("Operation: "+ nameArr[i]);
            }
        //c. Source Register
            int source=Integer.parseInt(binary.substring(6,11));
            System.out.println("Source Registers: "+ bin2Dec(source)+"");
        //d. Destination Register
            int desti=Integer.parseInt(binary.substring(11,16));
            System.out.println("Destination Register "+ bin2Dec(desti)+"");
        //e. Shift Amount is always none
            System.out.println("Shift amount: none");
        //f. Constant/offset by checking last 16 and return the decimal value of the last 16 binary numbers
            int constOff=Integer.parseInt(binary.substring(16));
            System.out.println("Constant/Offset: "+ bin2Dec(constOff)+" \n");
        }
    }

