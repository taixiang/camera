package com.qhyccd.mianban;

public class Encrypted
{
    private int SE;

    public String getPWD(String paramString1, String paramString2)
    {
        int i = getSE();
        String str = paramString1.substring(1, paramString1.length());
        int j = Integer.parseInt(str.substring(0, 1));
        int k = Integer.parseInt(str.substring(1, 2));
        int m = Integer.parseInt(str.substring(2, 3));
        int n = Integer.parseInt(str.substring(3, 4));
        int i1 = Integer.parseInt(str.substring(4, 5));
        int i2 = Integer.parseInt(paramString2);
        int i3 = (int)((Math.pow(j, 5.0D) + 1.0D * Math.pow(k, 4.0D) + 1.0D * Math.pow(m, 3.0D) + 1.0D * Math.pow(n, 2.0D) + i1 * 1 + i2) % 1000L);
        int i4 = i3 % 10;
        int i5 = (i3 - i4) / 10 % 10;
        int i6 = (i3 - i5 * 10 - i4) / 100;
        new StringBuilder(String.valueOf(Integer.toHexString(i6))).append(Integer.toHexString(i5)).append(Integer.toHexString(i4)).toString();
        int i7 = i % 10;
        int i8 = (i - i7) / 10 % 10;
        return Integer.toHexString((i - i8 * 10 - i7) / 100) + Integer.toHexString(i6) + Integer.toHexString(i8) + Integer.toHexString(i5) + Integer.toHexString(i7) + Integer.toHexString(i4);
    }

    public int getSE()
    {
        return this.SE;
    }

    public Integer[] init()
    {
        Integer[] arrayOfInteger = new Integer[51];
        arrayOfInteger[0] = Integer.valueOf(1);
        arrayOfInteger[1] = Integer.valueOf(2);
        arrayOfInteger[2] = Integer.valueOf(3);
        arrayOfInteger[3] = Integer.valueOf(4);
        arrayOfInteger[4] = Integer.valueOf(5);
        arrayOfInteger[5] = Integer.valueOf(6);
        arrayOfInteger[6] = Integer.valueOf(7);
        arrayOfInteger[7] = Integer.valueOf(8);
        arrayOfInteger[8] = Integer.valueOf(9);
        arrayOfInteger[9] = Integer.valueOf(10);
        arrayOfInteger[10] = Integer.valueOf(11);
        arrayOfInteger[11] = Integer.valueOf(12);
        arrayOfInteger[12] = Integer.valueOf(13);
        arrayOfInteger[13] = Integer.valueOf(14);
        arrayOfInteger[14] = Integer.valueOf(15);
        arrayOfInteger[15] = Integer.valueOf(16);
        arrayOfInteger[16] = Integer.valueOf(17);
        arrayOfInteger[17] = Integer.valueOf(18);
        arrayOfInteger[18] = Integer.valueOf(19);
        arrayOfInteger[19] = Integer.valueOf(20);
        arrayOfInteger[20] = Integer.valueOf(21);
        arrayOfInteger[21] = Integer.valueOf(22);
        arrayOfInteger[22] = Integer.valueOf(23);
        arrayOfInteger[23] = Integer.valueOf(24);
        arrayOfInteger[24] = Integer.valueOf(25);
        arrayOfInteger[25] = Integer.valueOf(26);
        arrayOfInteger[26] = Integer.valueOf(27);
        arrayOfInteger[27] = Integer.valueOf(28);
        arrayOfInteger[28] = Integer.valueOf(29);
        arrayOfInteger[29] = Integer.valueOf(30);
        arrayOfInteger[30] = Integer.valueOf(35);
        arrayOfInteger[31] = Integer.valueOf(40);
        arrayOfInteger[32] = Integer.valueOf(45);
        arrayOfInteger[33] = Integer.valueOf(50);
        arrayOfInteger[34] = Integer.valueOf(55);
        arrayOfInteger[35] = Integer.valueOf(60);
        arrayOfInteger[36] = Integer.valueOf(75);
        arrayOfInteger[37] = Integer.valueOf(90);
        arrayOfInteger[38] = Integer.valueOf(105);
        arrayOfInteger[39] = Integer.valueOf(120);
        arrayOfInteger[40] = Integer.valueOf(135);
        arrayOfInteger[41] = Integer.valueOf(150);
        arrayOfInteger[42] = Integer.valueOf(165);
        arrayOfInteger[43] = Integer.valueOf(180);
        arrayOfInteger[44] = Integer.valueOf(210);
        arrayOfInteger[45] = Integer.valueOf(240);
        arrayOfInteger[46] = Integer.valueOf(270);
        arrayOfInteger[47] = Integer.valueOf(300);
        arrayOfInteger[48] = Integer.valueOf(330);
        arrayOfInteger[49] = Integer.valueOf(360);
        arrayOfInteger[50] = Integer.valueOf(999);
        return arrayOfInteger;
    }

    public void setComboBox(int paramInt)
    {
        setSE(13 * new int[] { 1, 46, 26, 17, 54, 71, 8, 63, 36, 7, 12, 2, 22, 28, 43, 27, 57, 3, 51, 37, 55, 31, 9, 13, 47, 52, 18, 59, 64, 72, 65, 48, 5, 67, 15, 61, 75, 29, 74, 66, 45, 53, 42, 39, 69, 35, 16, 25, 76, 62, 23 }[paramInt]);
    }

    public void setSE(int paramInt)
    {
        this.SE = paramInt;
    }
}